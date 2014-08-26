/**
 * <p>Title: 中国电信计划建设管理系统<标准版></p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: xwtec.com</p>
 * @author PlanManageOrg (EntityCreator)
 * @version 1.0
 * create datetime : 2007-08-29 16:13:14
 * modify datetime :
 */
package com.linkage.contacts.server.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.linkage.contacts.server.entity.Codepool;
import com.linkage.core.ApplicationObject;
import com.linkage.entity.RowSet;
import com.linkage.util.Utility;


/**
 * 会话类：DAO(Data Access Object)
 */
public class CodeDAO {

	private long id = 0; // 批量操作取出的ID值的基值
	private long cur_pos = 0; // 未用ID的位置
	private long id_unit_quantity = 1; // 一次批量操作取出的ID个数,默认1个

	public String id_name = null; // 标识ID唯一名称
	private String id_title = null; // ID注释
	private boolean is_new = true; // 是否已取出ID
	// private boolean is_new_connection = false;
	// private boolean is_free_connection = true;

	private String CACHECODE_CODE = "code";
//codeMap用于存放code池的对象Codepool，key=codeid，value=codepool对象。
	private static HashMap<String, Codepool> codeMap = new HashMap<String, Codepool>();
	
	private Connection conn;
	public CodeDAO(Connection conn) {
		this.conn=conn;
	}

	/**
	 * 取得一个可用的ID,返回值小于0表示出错
	 */
	public synchronized long newCode(String s_id_name, String title, long quantity) {
		id_name = s_id_name;
		id_title = title;
		id_unit_quantity = quantity;
		if (is_new == false) {
			cur_pos++;
			if (cur_pos == id_unit_quantity) {
				is_new = true;
			}
		}

		// 只有is_new==true时才操作池。
		if (is_new == true) {
			id = this.codeFromPool();
			cur_pos = 0;
			is_new = false;
		}

		if (id < 0 || id + cur_pos < 0) {
			// Track.getInstance().set("DB","Code","newBaseCode",
			// "生成的ID错误",Log.PROGRAM_ERROR);
			// Track.getInstance().log();
			return -1;
		}

		return id + cur_pos;
	}

	/**
	 * 重code池中获取code tracker 3889 Open 引入池的概念，优化取code的效率
	 * 如果获取的codeid在codeMap不存在，则新建此Codepool，并添加到codeMap中。
	 * 1.如果code的数量在池里面的数量（Codepool.maxid-Codepool.curid）足够，就直接在Codepool中获取
	 * nextnbr = Codepool.curid+1，同时更新Codepool.curid += 数量
	 * 2.如果code的数量在池里面的数量（Codepool
	 * .maxid-Codepool.curid）不够（不够的话，那么将获取新的code，当前池里面的code会丢失）， 再比较
	 * if（数量<=Codepool.poolnum）
	 * 2.1.则到数据库获取poolnum个code，同时更新Codepool.maxid和Codepool.curid。 else
	 * 2.2.则到数据库获取code需要数量个code，并更新Codepool（为了保证后面取的id一定比前面取的id大）。
	 * 
	 * @return -1表示失败
	 */
	private long codeFromPool() {
		long id = -1;
		// 如果获取的codeid在codeMap不存在，则新建此Codepool，并添加到codeMap中。
		Codepool cp = (Codepool) this.codeMap.get(this.id_name);
		if (null == cp) {
			int poolnum = 0;
			// poolnum =
			poolnum = this.selectPoolnumByCodeid(this.id_name);
			if (poolnum <= 0)
				poolnum = Codepool.POOLNUM_DEFAULT;
			// 池的数量大于需要的数量,则取池的大小,否则取需要的数量
			if (poolnum >= this.id_unit_quantity)
				id = this.codeFromDatabase(poolnum);
			else
				id = this.codeFromDatabase((int) this.id_unit_quantity);
			if (-1 != id) {
				cp = new Codepool(this.id_name);
				cp.setPoolnum(poolnum);
				cp.setCurid(id + this.id_unit_quantity - 1); // 当前数量为已用的最后一个
				if (poolnum >= this.id_unit_quantity)
					cp.setMaxid(id + cp.getPoolnum() - 1);
				else
					cp.setMaxid(id + this.id_unit_quantity - 1);

				// 并添加到codeMap中。
				this.codeMap.put(this.id_name, cp);
			}
		} else {
			// 1.如果code的数量在池里面的数量（Codepool.maxid-Codepool.curid）足够
			// ，就直接在Codepool中获取 nextnbr = Codepool.curid+1，同时更新Codepool.curid +=
			// 数量
			if ((cp.getMaxid() - cp.getCurid() >= this.id_unit_quantity)) {
				id = cp.getCurid() + 1;
				cp.setCurid(cp.getCurid() + this.id_unit_quantity);
			}
			// 2.如果code的数量在池里面的数量（Codepool.maxid-Codepool.curid）不够
			else {
				// 2.1.如果需要数量不超过池的大小，则到数据库获取poolnum个code
				// ，同时更新Codepool.maxid和Codepool.curid。
				if (this.id_unit_quantity <= cp.getPoolnum()) {
					id = this.codeFromDatabase(cp.getPoolnum());
					if (-1 != id) {
						cp.setCurid(id + this.id_unit_quantity - 1);
						cp.setMaxid(id + cp.getPoolnum() - 1);
					}
				}
				// 2.2.否则到数据库获取code需要数量个code，并更新Codepool（为了保证后面取的id一定比前面取的id大）。
				else {
					id = this.codeFromDatabase((int) this.id_unit_quantity);
					if (-1 != id) {
						cp.setCurid(id + this.id_unit_quantity - 1);
						cp.setMaxid(id + this.id_unit_quantity - 1);
					}
				}
			}
		}
		return id;
	}

	/**
	 * 重数据库获取code
	 * 
	 * @return -1表示失败
	 */
	long codeFromDatabase(int codecount) {
		long id = -1;

		// 将code单独作为一个事务提交,
		int curTransactionIsolation = -1;
		try {
			id = -1;

			/**
			 * tracker 2404 modify by dht 20060116
			 */
			// if(this.conn.getAutoCommit())
			/**
			 * tracker 3596 2.取code的时候重复了,提高数据库连接的事务隔离级别到最高。
			 */
			// 记录事务隔离级别
			curTransactionIsolation = this.getConnection()
					.getTransactionIsolation();
			// log.info("A"+this.conn.getTransactionIsolation());
			this.getConnection().setTransactionIsolation(
					Connection.TRANSACTION_SERIALIZABLE);
			// log.info("B"+this.conn.getTransactionIsolation());
			this.getConnection().setAutoCommit(false);
			/**
			 * 设置了高级别的事务隔离级别 ，为了避免取code时候发生冲突，特意允许重复取3次
			 */
			for (int i = 0; i < 3; i++) {
				id = newBaseCode(id_name, id_title, codecount); // 此数量是传入的参数要求的数量
				if (id > 0 && id + cur_pos > 0)
					break;
			}
			if (id < 0 || id + cur_pos < 0)
				throw new Exception("更新code表时发生错误,请检查数据库连接是否有误或者session是否已失效");
			this.getConnection().commit();
			this.getConnection().setAutoCommit(true);
			// 事务隔离级别还原
			if (-1 != curTransactionIsolation)
				this.getConnection().setTransactionIsolation(
						curTransactionIsolation);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				id = -1;
				this.getConnection().rollback();
				this.getConnection().setAutoCommit(true);
				// 事务隔离级别还原
				if (-1 != curTransactionIsolation)
					this.getConnection().setTransactionIsolation(
							curTransactionIsolation);
			} catch (Exception f) {
				// log.info("事务会滚时发生错误");
			}
		} finally {
			// 针对有的人执行了,有的人没有执行,考虑此情况,现强制释放连接

		}
		return id;
	}

	private Connection getConnection()
  {
	  return this.conn;
  }

	/**
	 * 批量取ID操作,返回值小于0表示出错
	 * 
	 * @ s_id_name ID名称 @ title ID注释 @ quantity 一次操作取出的ID个数
	 */
	private long newBaseCode(String s_id_name, String title, long quantity) {
		long current = -1; // dxt20040114将默认值设为-1，原始默认值为0
		String sql = "select * from code  where codeid="
				+ Utility.convertSQLString(s_id_name);
		try {
			Statement stmt = this.getConnection().createStatement();
			RowSet rs = ApplicationObject.getCacheObject().generateRowSet(
					this.getConnection(), CACHECODE_CODE, sql);
			int count;
			// 根据查询记录若存在则更新，否则，插入
			if (rs.next()) {
				current = Long.parseLong(rs.getString("nextnbr"));
				String upsql = "update code set nextnbr="
						+ Utility.chgNull((current + quantity) + "")
						+ " , codedate="
						+ Utility.convertSQLString(Utility.getEightCurDate())
						+ " , codetime="
						+ Utility.convertSQLString(Utility.getEightCurTime())
						+ " where codeid="
						+ Utility.convertSQLString(s_id_name);
				count = stmt.executeUpdate(upsql);
				if (count != 1) {
					throw new RuntimeException("影响了" + count
							+ "条记录（正确的应该是1条），可是是SQL 语句错误，请检查SQL = \n" + upsql);
				}
			} else {
				current = 1;
				String insql = "insert into code values("
						+ Utility.convertSQLString(s_id_name) + ","
						+ Utility.convertSQLString(title) + ",1,'"
						+ Utility.getEightCurDate() + "','"
						+ Utility.getEightCurTime() + "'," + quantity + ")";
				count = stmt.executeUpdate(insql);
				if (count != 1) {
					throw new RuntimeException("影响了" + count
							+ "条记录（正确的应该是1条），可是是SQL 语句错误，请检查SQL = \n" + insql);
				}
			}
		} catch (SQLException e) {
			current = -1;
		}
		return current;
	}

	public int selectPoolnumByCodeid(String codeid) {
		int poolnum = 0;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT poolnum  FROM code  WHERE codeid="
				+ Utility.convertSQLString(codeid);
		try {
			stmt = this.getConnection().createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				poolnum = rs.getInt("poolnum");
			}
		} catch (SQLException e) {
			/*
			 * ExceptionInfo ei = new
			 * ExceptionInfo("SQL 语句错误",e,"请检查SQL = \n"+sql);
			 * ei.outputConsole();
			 */
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				// log.error("", e);
			}
		}
		return poolnum;
	}
}