package com.linkage.contacts.server.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.util.StringUtils;

public class AuthorizationConfig
{
	/**
	 * Authorization header
	 */
	public static final String HEADER_AUTHORIZATION = "Authorization"; //$NON-NLS-1$
	public static final String BASICPRE = "Basic ";//认证类型
	public static final String TOKENPRE = "token ";//认证类型
	public static final String PSWPRE ="$contact#";//密码加密头
	public static final String CLIENT_ID="client_id";//客户端id
	public static final String HEADER_VALIDATION = "Validation";//验证消息头
	public static final String VALIDATE_CODE="1344481253";//客户端与服务端验证码
	
	/**
	 * 针对 VALIDATE_CODE,functionName,token做MD5加密
	 * @param functionName
	 * @param token
	 * @return
	 */
	public static String encodeMD5(String functionName,String token)
	{
		String str = "";
		if(StringUtils.isBlank(token))
			token = "null";
		str = VALIDATE_CODE+functionName+token;
		return AubDigestUtils.encodeMD5Hex(str);
	}
	
	public static String encodeMD5(long timestamps, String functionName, String token)
	{
		String str = "";
		if(StringUtils.isBlank(token))
			token = "null";
		str = ""+timestamps+VALIDATE_CODE+functionName+token;
		System.out.println(str);
		return AubDigestUtils.encodeMD5Hex(str);
	}
	
	public static void validation(String switch_of_validaiton,HttpServletRequest request,String functionName,String token)
	{
		if(1==Integer.parseInt(switch_of_validaiton))
		{
			String signature = request.getHeader(AuthorizationConfig.HEADER_VALIDATION);
			String decodeStr = "";
		//解密加密的字符串
      try
      {
	      decodeStr = new String(EncodingUtils.fromBase64(signature),Base64.CHARSET_UTF8);
      }
      catch (UnsupportedEncodingException e)
      {
	      e.printStackTrace();
      }
      //获取MD5加密字符串
      String decode = decodeStr.substring(0, decodeStr.indexOf(':'));
      //获取客户端的时间戳
      long timestamps = Long.parseLong(decodeStr.substring(decodeStr.indexOf(':')+1));
      long diffTimestamps = Math.abs(new Date().getTime()-timestamps);
      //判断访问是否超时
      if(diffTimestamps <= 5*60*1000)
      {
      	String encodeStr = AuthorizationConfig.encodeMD5(timestamps, functionName, token);
  			if(StringUtils.isBlank(signature) || !encodeStr.equals(decode))
  				throw new ContactException("服务端访问认证不通过！");
      }
      else
      {
      	throw new ContactException("服务端访问超时！");
      }
      
		}
	}
	
	
	public static void main(String[] args)
  {
	 // System.out.println(encodeMD5("getuserinfo", "53a11ecf-634d-4d09-8671-e215c020a151"));
	 //System.out.println(encodeMD5(1407898199000L,"getsystemtime", null));
		String md5 = "834b27f3165ad686a1d3c50ed85f21c3";
		System.out.println(EncodingUtils.toBase64(md5+":"+1407898199000L));
		//System.out.println(EncodingUtils.toBase64("sss"));
  }
}
