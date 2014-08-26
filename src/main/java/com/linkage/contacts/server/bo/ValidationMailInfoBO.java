package com.linkage.contacts.server.bo;

import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.linkage.contacts.mail.MailSenderFactory;
import com.linkage.contacts.mail.MailSenderType;
import com.linkage.contacts.mail.SimpleMailSender;
import com.linkage.contacts.server.dao.ValidationMailInfoDAO;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.entity.ValidationMailInfo;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;
import com.linkage.util.UUID;

@Service
public class ValidationMailInfoBO
{
	private static final Log log = LogFactory.getLog(ValidationMailInfoBO.class);
	
	@Autowired
	@Qualifier("mybatisValidationMailInfoDao")
	private ValidationMailInfoDAO validationMailInfoDAO;
	
	@Autowired
	private CodeBO code;
	
	@Autowired
	private UserInfoBO userInfoBO;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Value("${service_url}")
	private String serviceUrl;
	
	public void save(ValidationMailInfo validationMailInfo)
	{
		if(0==validationMailInfo.getValidate_id())
		{
			validationMailInfo.setValidate_id((int)code.newCode("validation_mail_info.validate_id", "验证信息", 1));
			this.validationMailInfoDAO.insert(validationMailInfo);
			log.info("验证信息消息保存成功。");
		}
		else
		{
			this.validationMailInfoDAO.update(validationMailInfo);
			log.info("验证信息更新成功。");
		}
	}

	//发送验证信息给验证人
	public HashMap<String, Object> sendMail(UserInfo userInfo, String xuehao)
  {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try
		{
			UserInfo validateMan = userInfoBO.selectByXuehao(xuehao);
			//获取用户验证信息,防止用户重复点击纪录
			ValidationMailInfo validationMailInfo = this.selectByUser(validateMan.getUser_id(), userInfo.getUser_id());
			String tokenid = UUID.randomUUID().toString();
			if(validationMailInfo.getValidate_id()==0)
			{
				validationMailInfo.setValidate_man_id(validateMan.getUser_id());
				validationMailInfo.setUser_id(userInfo.getUser_id());
				validationMailInfo.setValidate_key(tokenid);
				validationMailInfo.setIs_validate(0);
				//保存用户验证信息
				this.save(validationMailInfo);
			}
			//发送邮件给验证人
	    SimpleMailSender sms = MailSenderFactory
	        .getSender(MailSenderType.SERVICE);
	    StringBuffer sb = new StringBuffer();
	    sb.append("尊敬的用户您好！<br>");
	    sb.append("注册用户"+userInfo.getYear()+"级"+userInfo.getUser_name()+"请求您的验证,请点击下面链接来完成该用户的验证：<br>");
	    sb.append("<a href='"+serviceUrl+"/validate/user?key="+validationMailInfo.getValidate_key()+"&from="+userInfo.getAccess_token()+"'>点击这里完成验证</a>");
	    sms.send(validateMan.getMail(), "用户信息验证", sb.toString());
	    map.put("result", 1);
	    map.put("message", "邮件发送成功！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			 map.put("result", 0);
		   map.put("message", "邮件发送失败！");
		}
	  return map;
  }

	public ValidationMailInfo selectByKey(String key)
  {
	  return validationMailInfoDAO.selectByKey(key);
  }
	
	public ValidationMailInfo selectByUser(int validate_man_id,int user_id)
  {
	  return validationMailInfoDAO.selectByUser(validate_man_id,user_id);
  }
	
	
	
}
