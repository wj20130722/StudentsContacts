package com.linkage.contacts.server.bo;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.linkage.contacts.server.dao.ReportInfoDAO;
import com.linkage.contacts.server.entity.ReportInfo;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;
import com.linkage.contacts.server.vo.FormReportInfo;
import com.linkage.util.StringUtils;
import com.linkage.util.Utility;

@Service
public class ReportInfoBO
{
	private static final Log log = LogFactory.getLog(ReportInfoBO.class);
	
	@Autowired
	@Qualifier("mybatisReportInfoDao")
	private ReportInfoDAO reportInfoDAO;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private CodeBO code;
	
	public void save(ReportInfo reportInfo)
	{
		if(0==reportInfo.getReport_id())
		{
			this.reportInfoDAO.insert(reportInfo);
			log.info("举报信息保存成功。");
		}
		else
		{
			this.reportInfoDAO.update(reportInfo);
			log.info("举报信息更新成功。");
		}
		
	}
	
	public static void main(String[] args)
  {
	  System.out.println("1,".substring(0,1));
  }

	public void saveReportInfo(UserInfo userInfo, FormReportInfo formReportInfo)
  {
		String report_type_str = "";
		for(int i=0;i<formReportInfo.getReport_types().length;i++)
		{
			report_type_str += formReportInfo.getReport_types()[i]+",";
		}
		if(Utility.chgNull(report_type_str).length()>0)
			report_type_str = report_type_str.substring(0,report_type_str.length()-1);
	  ReportInfo reportInfo = new ReportInfo();
	  reportInfo.setReport_type_str(report_type_str);
	  reportInfo.setReport_user_id(userInfo.getUser_id());
	  reportInfo.setUser_id(formReportInfo.getUser_id());
	  reportInfo.setReport_content(formReportInfo.getReport_content());
	  reportInfo.setReport_time(new Date());
	  this.save(reportInfo);
  }
	
	
}
