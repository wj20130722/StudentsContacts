package com.linkage.contacts.server.mybatis.custom.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import com.linkage.util.DateBean;
import com.linkage.util.Utility;

/**
 * <p>对于char类型的日期字符串进行格式转化</p>
 * @author wangjie
 * @version 1.0
 * @createtime 2013-08-07 14:30
 *
 */
@MappedJdbcTypes(JdbcType.CHAR)
public class DateTimeTypeHandler extends BaseTypeHandler<String>
{
	public static final String DATEREGEX = "^\\d{4}-\\d{2}-\\d{2}$";
	
	public static final String DATEREGEX2 = "^\\d{8}$";
	
	public static final String DATEREGEX3 = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
	
	public static final String DATEREGEX4 = "^\\d{14}$";

	@Override
  public String getNullableResult(ResultSet rs, String columnname) throws SQLException
  {
		String formaterdate = Utility.chgNull(rs.getString(columnname));
		if(null!=formaterdate && formaterdate.matches(DATEREGEX2))
			formaterdate= new DateBean().getYear_Month_DayOther_null(rs.getString(columnname));
		else if(null!=formaterdate && formaterdate.matches(DATEREGEX4))
			formaterdate= new DateBean().getAllDatetime(rs.getString(columnname));
	  return formaterdate;
  }

	@Override
  public String getNullableResult(ResultSet rs, int index) throws SQLException
  {
		String formaterdate = Utility.chgNull(rs.getString(index));
		if(null!=formaterdate && formaterdate.matches(DATEREGEX2))
			formaterdate= new DateBean().getYear_Month_DayOther_null(rs.getString(index));
		else if(null!=formaterdate && formaterdate.matches(DATEREGEX4))
			formaterdate= new DateBean().getAllDatetime(rs.getString(index));
	  return formaterdate;
  }

	@Override
  public String getNullableResult(CallableStatement cs, int index) throws SQLException
  {
		String formaterdate = Utility.chgNull(cs.getString(index));
		if(null!=formaterdate && formaterdate.matches(DATEREGEX2))
			formaterdate= new DateBean().getYear_Month_DayOther_null(cs.getString(index));
		else if(null!=formaterdate && formaterdate.matches(DATEREGEX4))
			formaterdate= new DateBean().getAllDatetime(cs.getString(index));
	  return formaterdate;
  }

	@Override
  public void setNonNullParameter(PreparedStatement ps, int index, String parameter, JdbcType jdbcType) throws SQLException
  {
		String formaterdate = Utility.chgNull(parameter);
		if(null!=formaterdate && parameter.matches(DATEREGEX))
			formaterdate = new DateBean().getYearMonthDay(parameter);
		else if(null!=formaterdate && parameter.matches(DATEREGEX3))
			formaterdate = new DateBean().getAllDatetimeNull(parameter);
	  ps.setString(index, formaterdate);
  }

}
