package com.linkage.contacts.server.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.linkage.contacts.server.dao.AluassociationConfigDAO;
import com.linkage.contacts.server.dao.ClassInfoDAO;
import com.linkage.contacts.server.dao.ClassRoleInfoDAO;
import com.linkage.contacts.server.entity.AluassociationConfig;
import com.linkage.contacts.server.entity.ClassInfo;
import com.linkage.contacts.server.entity.ClassRoleInfo;
import com.linkage.contacts.server.entity.SystemPushMessage;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;
import com.linkage.contacts.server.vo.FormClassCreateInfo;
import com.linkage.contacts.server.vo.FormClassInfo;
import com.linkage.contacts.server.vo.FormCreateClassInfo;
import com.linkage.mybatis.util.CriteriaManager;
import com.linkage.mybatis.util.Restrictions;
import com.linkage.push.xinge.XingeAppPushUtil;
import com.linkage.util.MybatisUtil;
import com.linkage.util.Utility;
import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;

@Service
public class ClassInfoBO
{
	private static final Log log = LogFactory.getLog(ClassInfoBO.class);
	
	@Autowired
	@Qualifier("mybatisClassInfoDao")
	private ClassInfoDAO classInfoDAO;
	
	@Autowired
	@Qualifier("mybatisAluassociationConfigDao")
	private AluassociationConfigDAO aluassociationConfigDAO;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private CodeBO code;
	
	@Autowired
	private UserInfoBO userInfoBO;
	
	@Autowired
	@Qualifier("mybatisClassRoleInfoDao")
	private ClassRoleInfoDAO classRoleInfoDAO;
	
	@Autowired
	private SystemPushMessageBO systemPushMessageBO;
	
	@Autowired
	private UserMessageMarkBO userMessageMarkBO;
	
	
	public void save(ClassInfo classInfo)
	{
		if(0==classInfo.getClass_id())
		{
			classInfo.setClass_id((int)code.newCode("class_info.class_id", "班级信息", 1));
			this.classInfoDAO.insert(classInfo);
			log.info("班级信息保存成功。");
		}
		else
		{
			this.classInfoDAO.update(classInfo);
			log.info("班级信息更新成功。");
		}
		
	}
	
	public List<FormClassInfo> getClassInfosByUser(UserInfo userInfo)
	{
		List<FormClassInfo> list = null;
		//TODO WJ 校级管理员查看的班级信息修改
		//if(0 == userInfo.getSuper_admin())
	//	{
			list = classInfoDAO.getClassInfosByUser(userInfo.getUser_id(), userInfo.getUniversity_id());
	//	}
	//	else
	//	{
	//		list = classInfoDAO.getClassInfosByAdmin(userInfo.getUniversity_id());
	//	}
		return list;
	}
	
	public List<FormClassCreateInfo> getCreateClassInfo(UserInfo userInfo)
	{
		List<FormClassCreateInfo> list = null;
		if(0 == userInfo.getSuper_admin())
		{
			list = classInfoDAO.getCreateClassInfo(userInfo.getUser_id(), userInfo.getUniversity_id());
		}
		else
		{
			list = classInfoDAO.getCreateClassInfoByAdmin(userInfo.getUniversity_id());
		}
		return list;
	}
	
	//获取班级创建者信息
	public HashMap<String,Object> getCreteUserInfo(UserInfo userInfo, int class_id)
  {
		HashMap<String,Object> result = new HashMap<String,Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("select       \n");
		sb.append("c.user_id,      \n");
		sb.append("u.user_name,       \n");
		sb.append("u.avatar_large,       \n");
		//增加城市、行业、公司信息
		sb.append("u.city_name,(convert(CONCAT(u.city_secret,''),UNSIGNED)) city_secret,  \n");
		sb.append("u.industry,(convert(CONCAT(u.industry_secret,''),UNSIGNED)) industry_secret,   \n");
		sb.append("u.companyname,(convert(CONCAT(u.company_secret,''),UNSIGNED)) company_secret,   \n");
		sb.append("(convert(CONCAT(u.is_authentication,''),UNSIGNED)) is_authentication       \n");
		sb.append("from class_info c      \n");
		sb.append("inner join user_info u on u.user_id=c.user_id     \n");
		sb.append("where c.class_id="+class_id+" and c.university_id="+userInfo.getUniversity_id()+"  ");
		List<HashMap<String,Object>> lists = commonMapper.getTableRowBySql(sb.toString());
		if(null!=lists && lists.size()>0)
			result = lists.get(0);
		result = MybatisUtil.converColumnNull(result, new String[]{"user_name","avatar_large","city_name","industry","companyname"});
	  return result;
  }
	
	//获取班级创建者信息
	public HashMap<String,Object> getClassInfo(UserInfo userInfo, int class_id)
  {
		HashMap<String,Object> result = new HashMap<String,Object>();
		StringBuffer sb = new StringBuffer("");
		sb.append("select       \n");
		sb.append("c.class_id,      \n");
		sb.append("c.year,      \n");
		sb.append("(case c.class_degree when 1 then '本科' when 2 then '硕士' when 3 then '博士' else '大专' end) degree_name,    \n");
		sb.append("(select college_name from college_config where college_id=c.college_id) college_name,      \n");
		sb.append("c.class_name,      \n");
		sb.append("c.teacher      \n");
		sb.append("from class_info c     \n");
		sb.append("where c.class_id="+class_id+" and c.university_id="+userInfo.getUniversity_id()+"  ");
		List<HashMap<String,Object>> lists = commonMapper.getTableRowBySql(sb.toString());
		if(null!=lists && lists.size()>0)
			result = lists.get(0);
		result = MybatisUtil.converColumnNull(result, new String[]{"college_name","class_name","teacher"});
		//增加匹配的班主任信息
		if(StringUtils.isNotBlank(Utility.chgNull(result.get("teacher"))))
		{
			String sql = "select degree_name,college_name,teacher from ustc_teacher";
			CriteriaManager cri = new CriteriaManager();
			cri.or().add(Restrictions.equalTo("begin_year", Integer.parseInt(Utility.chgZero(result.get("year")))))
			.add(Restrictions.equalTo("teacher", Utility.chgNull(result.get("teacher"))));
			List<HashMap<String, Object>> maps = commonMapper.getTableRowByWhereCondition(sql, cri);
			maps = MybatisUtil.converColumnNullList(maps, new String[]{"degree_name","college_name","teacher"});
			result.put("teacherinfos", maps);
		}
		else
		{
			result.put("teacherinfos", new ArrayList<HashMap<String, Object>>());
		}
	  return result;
  }
	
	
	public HashMap<String,Object> getClassInfoByUser(UserInfo userInfo,int class_id)
	{
		HashMap<String,Object> result = new HashMap<>();
		//获取校友会信息
		StringBuffer sb = new StringBuffer("");
		sb.append("select   \n");
		sb.append("cc.class_id,  \n");
		sb.append("c.class_name,  \n");
		sb.append("(select user_name from user_info where user_id=c.user_id) admin_name,  \n");
		sb.append("c.student_num,  \n");
		sb.append("(select count(1) from class_role_info where class_id=c.class_id and class_role_state=0) student_apply,  \n");
		sb.append("(convert(CONCAT(cc.class_role_state,''),UNSIGNED)) class_role_state,  \n");
		sb.append("(convert(CONCAT(cc.class_admin,''),UNSIGNED)) class_admin,  \n");
		sb.append("c.class_pic  \n");
		sb.append("from class_role_info cc \n");
		sb.append("join class_info c on c.class_id = cc.class_id  \n");
		sb.append("join user_info u on u.user_id = cc.user_id  \n");
		sb.append(" where cc.user_id="+userInfo.getUser_id()+" and c.university_id="+userInfo.getUniversity_id()+" and cc.class_id="+class_id+" and c.class_state=1  \n");
		List<HashMap<String, Object>> classList = commonMapper.getTableRowBySql(sb.toString());
		if(null!=classList && classList.size()>0)
		{
			result = MybatisUtil.converColumnNull(classList.get(0), new String[]{"class_name","admin_name","class_pic"});
			List<HashMap<String,Object>> personList = new ArrayList<HashMap<String,Object>>();
			if(1 == Integer.parseInt(Utility.chgZero(result.get("class_role_state"))))  //评审通过的成员可以查看对应的班级信息
			{
				sb = new StringBuffer("");
				sb.append("select       \n");
				sb.append("cc.user_id,      \n");
				sb.append("u.user_name,       \n");
				sb.append("u.avatar_large,       \n");
				//增加城市、行业、公司信息
				sb.append("u.city_name,(convert(CONCAT(u.city_secret,''),UNSIGNED)) city_secret,  \n");
				sb.append("u.industry,(convert(CONCAT(u.industry_secret,''),UNSIGNED)) industry_secret,   \n");
				sb.append("u.companyname,(convert(CONCAT(u.company_secret,''),UNSIGNED)) company_secret,   \n");
				sb.append("(convert(CONCAT(u.is_authentication,''),UNSIGNED)) is_authentication,       \n");
				sb.append("(convert(CONCAT(cc.class_admin,''),UNSIGNED)) class_admin,   \n");
				sb.append("(convert(CONCAT(cc.class_role_state,''),UNSIGNED)) class_role_state   \n");
				sb.append("from class_role_info cc      \n");
				sb.append("left join class_info c on c.class_id=cc.class_id      \n");
				sb.append("left join user_info u on u.user_id=cc.user_id     \n");
				sb.append("where cc.class_id="+class_id+" and c.university_id="+userInfo.getUniversity_id()+" and c.class_state=1 ");
				if(1==Integer.parseInt(Utility.chgZero(result.get("class_admin")))) //管理员(班级管理员以及校班管理员)可以查看待评审和评审通过的成员信息
				{
					sb.append("and cc.class_role_state<>2  \n");
				}
				else //普通成员只可以查看评审通过的成员信息
				{
					sb.append("and cc.class_role_state=1  \n");
				}
				sb.append("order by convert(u.user_name USING gbk) COLLATE gbk_chinese_ci asc");
				personList = commonMapper.getTableRowBySql(sb.toString());
				if(null == personList)
					personList = new ArrayList<HashMap<String,Object>>();
				//TODO WJ 保密状态处理
				for (HashMap<String, Object> hashMap : personList)
	      {
		      if(userInfo.getUser_id()!=Integer.parseInt(Utility.chgZero(hashMap.get("user_id"))))
		      {
		      	sb = new StringBuffer("");
						sb.append("select count(class_id) num1,count(distinct class_id) num2   \n");
						sb.append("from class_role_info   \n");
						sb.append("where user_id in ("+userInfo.getUser_id()+","+Utility.chgZero(hashMap.get("user_id"))+")");
						HashMap<String, Object> numMap = commonMapper.getTableRowBySql(sb.toString()).get(0);
						if(Integer.parseInt(Utility.chgZero(numMap.get("num1")))==Integer.parseInt(Utility.chgZero(numMap.get("num2"))))
						{
							//当前用户和查看的用户不属于同一班级,隐藏用户保密信息
							if(Integer.parseInt(Utility.chgZero(hashMap.get("company_secret")))==0)
								hashMap.put("companyname", "公司保密");
							if(Integer.parseInt(Utility.chgZero(hashMap.get("city_secret")))==0)
								hashMap.put("city_name", "城市保密");
							if(Integer.parseInt(Utility.chgZero(hashMap.get("industry_secret")))==0)
								hashMap.put("industry", "行业保密");
						}
		      }
	      }
			}
			personList = MybatisUtil.converColumnNullList(personList, new String[]{"user_name","avatar_large","city_name","industry","companyname"});
			result.put("personinfos", personList);
		}
		return result;
	}

	public HashMap<String, Object> getAluassociationInfoByUser(UserInfo userInfo, int aluassociation_id)
  {
		HashMap<String,Object> result = new HashMap<>();
		//获取校友会信息
		StringBuffer sb = new StringBuffer("");
		sb.append("select   \n");
		sb.append("a.aluassociation_id,  \n");
		sb.append("aa.aluassociation_name,  \n");
		sb.append("(select group_concat(user_name) from user_info where user_id in (select user_id from aluassociation_role_info where aluassociation_admin=1 and aluassociation_id=a.aluassociation_id)) admin_name,  \n");
		sb.append("aa.aluassociation_num,  \n");
		sb.append("(select count(1) from aluassociation_role_info where aluassociation_id=a.aluassociation_id and aluassociation_role_state=0) aluassociation_apply,  \n");
		sb.append("(convert(CONCAT(a.aluassociation_role_state,''),UNSIGNED)) aluassociation_role_state,  \n");
		sb.append("(convert(CONCAT(a.aluassociation_admin,''),UNSIGNED)) aluassociation_admin,  \n");
		sb.append("aa.aluassociation_pic,  \n");
		sb.append("aa.aluassociation_desc  \n");
		sb.append("from aluassociation_role_info a  \n");
		sb.append("join aluassociation_config aa on aa.aluassociation_id=a.aluassociation_id  \n");
		sb.append("join user_info u on u.user_id=a.user_id  \n");
		sb.append("where a.user_id="+userInfo.getUser_id()+" and aa.university_id="+userInfo.getUniversity_id()+" and a.aluassociation_id="+aluassociation_id+" and aa.aluassociation_state=1 \n");
		List<HashMap<String, Object>> aluList = commonMapper.getTableRowBySql(sb.toString());
		if(null!=aluList && aluList.size()>0)
		{
			result = MybatisUtil.converColumnNull(aluList.get(0), new String[]{"aluassociation_name","admin_name","aluassociation_pic","aluassociation_desc"});
			List<HashMap<String,Object>> personList = new ArrayList<HashMap<String,Object>>();
			if(1 == Integer.parseInt(Utility.chgZero(result.get("aluassociation_role_state"))))  //评审通过的成员可以查看对应的校友会信息
			{
				sb = new StringBuffer();
				sb.append("select       \n");
				sb.append("a.user_id,      \n");
				sb.append("u.user_name,      \n");
				sb.append("u.avatar_large,    \n");
				//增加城市、行业、公司信息
				sb.append("u.city_name,(convert(CONCAT(u.city_secret,''),UNSIGNED)) city_secret,  \n");
				sb.append("u.industry,(convert(CONCAT(u.industry_secret,''),UNSIGNED)) industry_secret,   \n");
				sb.append("u.companyname,(convert(CONCAT(u.company_secret,''),UNSIGNED)) company_secret,   \n");
				sb.append("(convert(CONCAT(u.is_authentication,''),UNSIGNED)) is_authentication,       \n");
				sb.append("(convert(CONCAT(a.aluassociation_admin,''),UNSIGNED)) aluassociation_admin,   \n");
				sb.append("(convert(CONCAT(a.aluassociation_role_state,''),UNSIGNED)) aluassociation_role_state   \n");
				sb.append("from aluassociation_role_info a      \n");
				sb.append("left join aluassociation_config aa on aa.aluassociation_id=a.aluassociation_id   \n");
				sb.append("left join user_info u on u.user_id=a.user_id     \n");
				sb.append("where a.aluassociation_id="+aluassociation_id+" and aa.university_id="+userInfo.getUniversity_id()+" and aa.aluassociation_state=1 ");
				if(1==Integer.parseInt(Utility.chgZero(result.get("aluassociation_admin")))) //管理员(校友会管理员以及校班管理员)可以查看待评审和评审通过的成员信息
				{
					sb.append("and a.aluassociation_role_state<>2  \n");
				}
				else //普通成员只可以查看评审通过的成员信息
				{
					sb.append("and a.aluassociation_role_state=1  \n");
				}
				sb.append("order by convert(u.user_name USING gbk) COLLATE gbk_chinese_ci asc");
				personList = commonMapper.getTableRowBySql(sb.toString());
				if(null == personList)
					personList = new ArrayList<HashMap<String,Object>>();
				//TODO WJ 增加班级信息
				String sql = "";
				for (final HashMap<String, Object> hashMap : personList)
	      {
				 if(userInfo.getUser_id()!=Integer.parseInt(Utility.chgZero(hashMap.get("user_id"))))
		      {
		      	sb = new StringBuffer("");
						sb.append("select count(class_id) num1,count(distinct class_id) num2   \n");
						sb.append("from class_role_info   \n");
						sb.append("where user_id in ("+userInfo.getUser_id()+","+Utility.chgZero(hashMap.get("user_id"))+")");
						HashMap<String, Object> numMap = commonMapper.getTableRowBySql(sb.toString()).get(0);
						if(Integer.parseInt(Utility.chgZero(numMap.get("num1")))==Integer.parseInt(Utility.chgZero(numMap.get("num2"))))
						{
							//当前用户和查看的用户不属于同一班级,隐藏用户保密信息
							if(Integer.parseInt(Utility.chgZero(hashMap.get("company_secret")))==0)
								hashMap.put("companyname", "公司保密");
							if(Integer.parseInt(Utility.chgZero(hashMap.get("city_secret")))==0)
								hashMap.put("city_name", "城市保密");
							if(Integer.parseInt(Utility.chgZero(hashMap.get("industry_secret")))==0)
								hashMap.put("industry", "行业保密");
						}
		      }
		      sql = "select c.year,c.class_name "
		      		+" from class_role_info cc join class_info c on c.class_id = cc.class_id and c.class_state=1 join user_info u on u.user_id = cc.user_id " +
		      		" where cc.user_id="+Utility.chgZero(hashMap.get("user_id"))+" and c.university_id=1 and cc.class_role_state=1 ";
		      List<HashMap<String, Object>> maps = commonMapper.getTableRowBySql(sql);
		      if(null!=maps && maps.size()>0)
		      {
		      	hashMap.put("year", maps.get(0).get("year"));
		      	hashMap.put("class_name", maps.get(0).get("class_name"));
		      }
		      else
		      {
		      	hashMap.put("year", null);
		      	hashMap.put("class_name", null);
		      }
	      }
			}
			personList = MybatisUtil.converColumnNullList(personList, new String[]{"user_name","avatar_large","city_name","industry","companyname"});
			result.put("personinfos", personList);
		}
		return result;
  }

	public boolean deleteClassinfo(UserInfo userInfo, int class_id, int university_id)
  {
		boolean result = false;
		String sql = "update class_info set class_state=3 where class_id="+class_id+" and user_id="+userInfo.getUser_id()+" and university_id="+university_id;;
		int count = commonMapper.updateDataBySql(sql);
		if (count==1)
			result = true;
		else
			result = false;
	  return result;
  }
	
	//校班级管理员同意或者否决创建班级
	public boolean createClassinfo(UserInfo userInfo,FormCreateClassInfo formCreateClassInfo)
  {
		boolean result = false;
		String sql = "";
		if(1 == formCreateClassInfo.getType()) //同意创建班级
		{
			sql = "update class_info set class_state=1 where class_id="+formCreateClassInfo.getClass_id()+" and university_id="+userInfo.getUniversity_id();
		}
		else if(0 == formCreateClassInfo.getType()) //拒绝创建班级
		{
			sql = "update class_info set class_state=2,refusereason='"+formCreateClassInfo.getRefusereason()+"' where class_id="+formCreateClassInfo.getClass_id()+" and university_id="+userInfo.getUniversity_id();
		}
		else
		{
			return false;
		}
		int count = commonMapper.updateDataBySql(sql);
		if (count==1)
			result = true;
		else
			result = false;
		//TODO WJ 同意创建班级之后 班级创建者默认为该班级的管理员
		//推送系统消息---->班级创建者
		UserInfo toUserInfo = userInfoBO.selectByPrimaryKey(formCreateClassInfo.getCreate_user_id());
		int nid = 10000+formCreateClassInfo.getClass_id();
		ClassInfo classInfo = classInfoDAO.selectByPrimaryKey(formCreateClassInfo.getClass_id());
		String college_name = commonMapper.getColumnValueBySql("select college_name from college_config where college_id="+classInfo.getCollege_id());
		String content = "";
		Date now = new Date();
		SystemPushMessage systemMessage = null;
		if(result)
		{
			if(1==formCreateClassInfo.getType())
			{
				ClassRoleInfo classRoleInfo = new ClassRoleInfo();
				classRoleInfo.setUser_id(formCreateClassInfo.getCreate_user_id());
				classRoleInfo.setClass_id(formCreateClassInfo.getClass_id());
				classRoleInfo.setClass_admin(1);
				classRoleInfo.setClass_role_state(1);
				classRoleInfoDAO.insert(classRoleInfo);
				log.info("班级创建成功之后班级管理员创建成功。");
				//将管理员加入班级数量中
				commonMapper.updateDataBySql("update class_info set student_num=ifnull(student_num,0)+1 where class_id="+formCreateClassInfo.getClass_id());
				//推送系统消息
				content = "您有一条审核通过的信息：系统管理员"+userInfo.getUser_name()+"同意您创建"+classInfo.getYear()+"级"+college_name+classInfo.getClass_name()+"！";
			}
			else if(0==formCreateClassInfo.getType())
			{
				//推送系统消息
				content = "您有一条审核不通过的信息：系统管理员"+userInfo.getUser_name()+"不同意您创建"+classInfo.getYear()+"级"+college_name+classInfo.getClass_name()+"！"+
				"原因："+formCreateClassInfo.getRefusereason();
			}
			Map<String, Object> custom = new HashMap<String, Object>();
			custom.put("type", "2");
			custom.put("content", content);
			custom.put("time", now.getTime());
			//Andriod推送
			Message message = XingeAppPushUtil.constructMessageAndriod(Message.TYPE_NOTIFICATION, nid, ClickAction.TYPE_ACTIVITY, "班级创建审核反馈", content,null, custom);
			JSONObject jsonObject =  XingeAppPushUtil.pushSingleAccountAndriod(message, toUserInfo.getAccess_token());
			int push_state = jsonObject.getInt("ret_code");
			//IOS推送
			int push_state2 = 0;
			if(1 == toUserInfo.getOnline_status())
			{
				Map<String, Object> custom2 = new HashMap<String, Object>();
				custom2.put("type", "2");
				custom2.put("time", now.getTime());
				MessageIOS message2 = XingeAppPushUtil.constructMessageIOS(1, "【班级创建审核反馈】"+content, custom2);
				JSONObject jsonObject2 = XingeAppPushUtil.pushSingleAccountIOS(message2, toUserInfo.getAccess_token());
				push_state2 = jsonObject2.getInt("ret_code");
			}
			else
			{
				push_state2 = 77;//用户不在线
			}
			if(0 == (push_state & push_state2))
			{
				log.info("班级创建审核反馈消息发送给"+toUserInfo.getUser_name()+"成功！");
				userMessageMarkBO.addUserSysMessageMark(toUserInfo.getUser_id());
			}
			else
				log.info("班级创建审核反馈消息发送给"+toUserInfo.getUser_name()+"失败！");
			//保存系统消息
			systemMessage = new SystemPushMessage();
			systemMessage.setMessage_type_id(1);//加入班级的系统消息
			systemMessage.setFrom_user_id(userInfo.getUser_id());
			systemMessage.setTo_user_id(formCreateClassInfo.getCreate_user_id());
			systemMessage.setMessage(content);
			systemMessage.setPush_time(now);
			systemMessage.setPush_state(push_state & push_state2);
			this.systemPushMessageBO.save(systemMessage);
		}
		
	  return result;
  }

	public boolean applyClassinfo(UserInfo userInfo,int class_id, int type, int user_id)
  {
		boolean result = false;
		String sql = "";
		if(1 == type) //同意用户申请加入班级
		{
			sql = "update class_role_info set class_role_state=1 where class_id="+class_id+" and user_id="+user_id;
		}
		else if(0 == type) //拒绝用户申请加入班级
		{
			sql = "update class_role_info set class_role_state=2 where class_id="+class_id+" and user_id="+user_id;
		}
		else
		{
			return false;
		}
		int count = commonMapper.updateDataBySql(sql);
		if (count==1)
		{
			result = true;
			//推送消息给普通用户
			UserInfo toUserInfo = userInfoBO.selectByPrimaryKey(user_id);//普通用户
			int nid = 10000+class_id;
			ClassInfo classInfo = classInfoDAO.selectByPrimaryKey(class_id);
			String college_name = commonMapper.getColumnValueBySql("select college_name from college_config where college_id="+classInfo.getCollege_id());
			String content = "";
			Date now = new Date();
			SystemPushMessage systemMessage = null;
			if(1==type)
			{
				//同意用户加入之后将班级的成员数量加1
				commonMapper.updateDataBySql("update class_info set student_num=ifnull(student_num,0)+1 where class_id="+class_id);
				//同意用户申请加入班级之后推送消息通知用户
				content = "您有一条审核通过的信息：班级管理员"+userInfo.getUser_name()+"同意你加入"+classInfo.getYear()+"级"+college_name+classInfo.getClass_name()+"!";
			}
			else
			{
				//拒绝用户申请加入班级之后推送消息通知用户
				content = "您有一条审核不通过的信息：班级管理员"+userInfo.getUser_name()+"拒绝你加入"+classInfo.getYear()+"级"+college_name+classInfo.getClass_name()+"!";
			}
			Map<String, Object> custom = new HashMap<String, Object>();
			custom.put("type", "2");
			custom.put("content", content);
			custom.put("time", now.getTime());
			//Andriod推送
			Message message = XingeAppPushUtil.constructMessageAndriod(Message.TYPE_NOTIFICATION, nid, ClickAction.TYPE_ACTIVITY, "班级加入审核反馈信息", content,null, custom);
			JSONObject jsonObject =  XingeAppPushUtil.pushSingleAccountAndriod(message, Utility.chgNull(toUserInfo.getAccess_token()));
			int push_state = jsonObject.getInt("ret_code");
			//IOS推送
			int push_state2 = 0;
			if(1 == toUserInfo.getOnline_status())
			{
				Map<String, Object> custom2 = new HashMap<String, Object>();
				custom2.put("type", "2");
				custom2.put("time", now.getTime());
				MessageIOS message2 = XingeAppPushUtil.constructMessageIOS(1, "【班级加入审核反馈信息】"+content, custom2);
				JSONObject jsonObject2 = XingeAppPushUtil.pushSingleAccountIOS(message2, Utility.chgNull(toUserInfo.getAccess_token()));
				push_state2 = jsonObject2.getInt("ret_code");
			}
			else
			{
				push_state2 = 77;//用户不在线
			}
			if(0 == (push_state & push_state2))
			{
				log.info("班级加入审核反馈信息发送给"+toUserInfo.getUser_name()+"成功！");
				userMessageMarkBO.addUserSysMessageMark(toUserInfo.getUser_id());
			}
			else
				log.info("班级加入审核反馈信息发送给"+toUserInfo.getUser_name()+"失败！");
			//保存系统消息
			systemMessage = new SystemPushMessage();
			systemMessage.setMessage_type_id(2);//加入班级的审核反馈消息
			systemMessage.setFrom_user_id(userInfo.getUser_id());
			systemMessage.setTo_user_id(user_id);
			systemMessage.setMessage(content);
			systemMessage.setPush_time(now);
			systemMessage.setPush_state(push_state & push_state2);
			this.systemPushMessageBO.save(systemMessage);
		}
		else
			result = false;
	  return result;
  }

	public boolean applyApplyaluassociationInfo(UserInfo userInfo,int aluassociation_id, int type, int user_id)
  {
		boolean result = false;
		String sql = "";
		if(1 == type) //同意用户申请加入班级
		{
			sql = "update aluassociation_role_info set aluassociation_role_state=1 where aluassociation_id="+aluassociation_id+" and user_id="+user_id;
		}
		else if(0 == type) //拒绝用户申请加入班级
		{
			sql = "update aluassociation_role_info set aluassociation_role_state=2 where aluassociation_id="+aluassociation_id+" and user_id="+user_id;
		}
		else
		{
			return false;
		}
		int count = commonMapper.updateDataBySql(sql);
		if (count==1)
		{
			//推送消息给普通用户
			UserInfo toUserInfo = userInfoBO.selectByPrimaryKey(user_id);//普通用户
			int nid = 20000+aluassociation_id;
			AluassociationConfig aluassociationConfig = aluassociationConfigDAO.selectByPrimaryKey(aluassociation_id);
			String content = "";
			Date now = new Date();
			SystemPushMessage systemMessage = null;
			result = true;
			//同意用户加入之后将校友会的成员数量加1
			if(1==type)
			{
				commonMapper.updateDataBySql("update aluassociation_config set aluassociation_num=ifnull(aluassociation_num,0)+1 where aluassociation_id="+aluassociation_id);
			//同意用户申请加入校友会之后推送消息通知用户
				content = "您有一条审核通过的信息：校友会管理员"+userInfo.getUser_name()+"同意您加入"+aluassociationConfig.getAluassociation_name()+"!";
			}
			else
			{
				//拒绝用户申请加入校友会之后推送消息通知用户
				content = "您有一条审核不通过的信息：校友会管理员"+userInfo.getUser_name()+"拒绝您加入"+aluassociationConfig.getAluassociation_name()+"!";
			}
			Map<String, Object> custom = new HashMap<String, Object>();
			custom.put("type", "2");
			custom.put("content", content);
			custom.put("time", now.getTime());
			//Andriod推送
			Message message = XingeAppPushUtil.constructMessageAndriod(Message.TYPE_NOTIFICATION, nid, ClickAction.TYPE_ACTIVITY, "校友会加入审核反馈信息", content,null, custom);
			JSONObject jsonObject =  XingeAppPushUtil.pushSingleAccountAndriod(message, Utility.chgNull(toUserInfo.getAccess_token()));
			int push_state = jsonObject.getInt("ret_code");
			//IOS推送
			int push_state2 = 0;
			if(1 == toUserInfo.getOnline_status())
			{
				Map<String, Object> custom2 = new HashMap<String, Object>();
				custom2.put("type", "2");
				custom2.put("time", now.getTime());
				MessageIOS message2 = XingeAppPushUtil.constructMessageIOS(1, "【校友会加入审核反馈信息】"+content, custom2);
				JSONObject jsonObject2 = XingeAppPushUtil.pushSingleAccountIOS(message2, Utility.chgNull(toUserInfo.getAccess_token()));
				push_state2 = jsonObject2.getInt("ret_code");
			}
			else
			{
				push_state2 = 77;//用户不在线
			}
			if(0 == (push_state & push_state2))
			{
				log.info("校友会加入审核反馈信息发送给"+toUserInfo.getUser_name()+"成功！");
				userMessageMarkBO.addUserSysMessageMark(toUserInfo.getUser_id());
			}
			else
				log.info("校友会加入审核反馈信息发送给"+toUserInfo.getUser_name()+"失败！");
			//保存系统消息
			systemMessage = new SystemPushMessage();
			systemMessage.setMessage_type_id(3);//加入校友会的审核反馈消息
			systemMessage.setFrom_user_id(userInfo.getUser_id());
			systemMessage.setTo_user_id(user_id);
			systemMessage.setMessage(content);
			systemMessage.setPush_time(now);
			systemMessage.setPush_state(push_state & push_state2);
			this.systemPushMessageBO.save(systemMessage);
		}
		else
			result = false;
	  return result;
  }

	public List<HashMap<String, Object>> searchClassInfo(int user_id, int university_id, int year, int class_degree, int college_id)
  {
		List<HashMap<String,Object>> result = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select    \n");
		sb.append("c.class_id,   \n");
		//sb.append("CONCAT(c.year,'级',(select college_name from college_config where college_id=c.college_id),c.class_name) class_name,   \n");
		sb.append("c.class_name,   \n");
		sb.append("(select group_concat(user_name) from user_info where user_id in (select user_id from class_role_info where class_admin=1 and class_id=c.class_id)) admin_name,   \n");
		sb.append("c.student_num,  \n");
		sb.append("(select convert(CONCAT(class_role_state,''),UNSIGNED) from class_role_info where user_id="+user_id+" and class_id=c.class_id) class_role_state,  \n");
		sb.append("c.class_pic  \n");
		sb.append("from class_info c    \n");
		sb.append("where c.class_state=1 and c.university_id="+university_id+" and c.year="+year+" and c.class_degree="+class_degree+" and c.college_id="+college_id+"   \n");
		sb.append("order by c.class_id");
		result = commonMapper.getTableRowBySql(sb.toString());
		result = MybatisUtil.converColumnNullList(result, new String[]{"class_name","admin_name","class_role_state","class_pic"});
	  return result;
  }

	public void saveClassInfo(UserInfo userInfo, ClassInfo classInfo)
  {
		if(0==classInfo.getClass_id())
		{
			classInfo.setClass_id((int)code.newCode("class_info.class_id", "班级信息", 1));
			classInfoDAO.insert(classInfo);
			//推送系统消息---->提醒校班管理员审核用户班级创建
			int nid = 10000+classInfo.getClass_id();
			String college_name = commonMapper.getColumnValueBySql("select college_name from college_config where college_id="+classInfo.getCollege_id()+" and university_id="+classInfo.getUniversity_id());
			String content = "您有一条待审核信息：用户"+userInfo.getUser_name()+"申请创建"+classInfo.getYear()+"级"+college_name+classInfo.getClass_name()+"请您尽快审核！";
			List<UserInfo> admins = userInfoBO.getSysAdmin(userInfo.getUniversity_id());
			SystemPushMessage systemMessage = null;
			//发送用户信息给对方用户
			Date now = new Date();
			for (final UserInfo admin : admins)
      {
				Map<String, Object> custom = new HashMap<String, Object>();
				custom.put("type", "2");
				custom.put("content", content);
				custom.put("time", now.getTime());
				//Andriod推送
				Message message = XingeAppPushUtil.constructMessageAndriod(Message.TYPE_NOTIFICATION, nid, ClickAction.TYPE_ACTIVITY, "班级创建申请", content,null, custom);
				JSONObject jsonObject = XingeAppPushUtil.pushSingleAccountAndriod(message, admin.getAccess_token());
				int push_state = jsonObject.getInt("ret_code");
				//IOS推送
				int push_state2 = 0;
				if(1 == admin.getOnline_status())
				{
					Map<String, Object> custom2 = new HashMap<String, Object>();
					custom2.put("type", "2");
					custom2.put("time", now.getTime());
					MessageIOS message2 = XingeAppPushUtil.constructMessageIOS(1, "【班级创建申请】"+content, custom2);
					JSONObject jsonObject2 = XingeAppPushUtil.pushSingleAccountIOS(message2, admin.getAccess_token());
					push_state2 = jsonObject2.getInt("ret_code");
				}
				else
				{
					push_state2 = 77;//用户不在线
				}
				if(0 == (push_state & push_state2))
				{
					log.info("班级创建审核消息发送给管理员"+admin.getUser_name()+"成功！");
					userMessageMarkBO.addUserSysMessageMark(admin.getUser_id());
				}
				else
					log.info("班级创建审核消息发送给管理员"+admin.getUser_name()+"失败！");
				//保存系统发送的消息
				systemMessage = new SystemPushMessage();
				systemMessage.setMessage_type_id(1);//创建班级的系统消息
				systemMessage.setFrom_user_id(userInfo.getUser_id());
				systemMessage.setTo_user_id(admin.getUser_id());
				systemMessage.setMessage(content);
				systemMessage.setPush_time(now);
				systemMessage.setPush_state(push_state & push_state2);
				this.systemPushMessageBO.save(systemMessage);
      }
		}
		else
		{
			classInfoDAO.update(classInfo);
		}
  }




	
	
	
}
