package com.linkage.contacts.mail;


public class TestMailSend
{
	public static void main(String[] args) throws Exception
  {
	// 发送邮件
  /*  SimpleMailSender sms = MailSenderFactory
        .getSender(MailSenderType.SERVICE);
    sms.send("wangjie_191989@126.com", "邮件发送测试3", "证明请<br><a href='http://www.baidu.com'>点击这里 </a> ");*/
    
		 SimpleMailSender sms = MailSenderFactory
	        .getSender(MailSenderType.SERVICE);
    StringBuffer sb = new StringBuffer();
    sb.append("尊敬的用户您好！<br>");
    sb.append("注册用户请求您的验证,请点击下面链接来完成该用户的验证：<br>");
    sb.append("<a href='/validate/user?key=sss&from=sss'>点击这里完成验证</a>");
    sms.send("907109004@qq.com", "用户信息验证", sb.toString());
    
  }
}
