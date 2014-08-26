/******************************************************************************
 *  Copyright (c) 2011 GitHub Inc.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *    Kevin Sawicki (GitHub Inc.) - initial API and implementation
 *****************************************************************************/
package com.linkage.contacts.server.util;

import java.io.UnsupportedEncodingException;
/**
 * Encoding utilities
 */
public abstract class EncodingUtils {

	/**
	 * Decode base64 encoded string
	 * 
	 * @param content
	 * @return byte array
	 */
	public static final byte[] fromBase64(final String content) {
		return Base64.decode(content);
	}

	/**
	 * Base64 encode given byte array
	 * 
	 * @param content
	 * @return byte array
	 */
	public static final String toBase64(final byte[] content) {
		return Base64.encodeBytes(content);
	}

	/**
	 * Base64 encode given byte array
	 * 
	 * @param content
	 * @return byte array
	 */
	public static final String toBase64(final String content) {
		byte[] bytes;
		try {
			bytes = content.getBytes(Base64.CHARSET_UTF8);
		} catch (UnsupportedEncodingException e) {
			bytes = content.getBytes();
		}
		return toBase64(bytes);
	}

	public static final void main(String args[]) throws Exception {
/*		String basic = "Basic ";
		String credentials = "Basic " //$NON-NLS-1$
				+ EncodingUtils.toBase64("dht1234132" + ':' + "psw");
		System.out.println(credentials);
		
		int indexBasic = credentials.indexOf(basic);
		
		if(0 == indexBasic)
		{
			String basicAuth = credentials.substring(basic.length());
			System.out.println(basicAuth);
			String up = new String(EncodingUtils.fromBase64(basicAuth),Base64.CHARSET_UTF8);
			System.out.println(up);
		}
		
//		 credentials = EncodingUtils.toBase64("dht" + ':' + "psw");
//		String p = new String(EncodingUtils.fromBase64(credentials),Base64.CHARSET_UTF8);
//		System.out.println(p);
//		String p2 = new String(EncodingUtils.fromBase64(credentials));
//		System.out.println(p2);
//		System.out.println(Crypt.crypt("abc", "$5$1"));
//		
//		System.out.println(DigestUtils.sha256Hex(StringUtils.getBytesUtf8("abc中")));
//		
//		System.out.println(DigestUtils.sha256Hex("$cpm#"+"abc"));
		
		("$cpm#"+"abc").getBytes(Base64.CHARSET_UTF8);
		
		System.out.println(AubDigestUtils.encodeSHA256Hex(("$cpm#"+"abc").getBytes(Base64.CHARSET_UTF8)));
		
//		System.out.println(Sha2Crypt.sha256Crypt("ww".getBytes(Charsets.UTF_8), "ddd"));

*/
		

	/*	Employeeinfo user = new Employeeinfo();
		    user.setEmployeeid("1");
		    user.setEmployeename("中");

		    Object o = user;
		    try {
	            Class ownerClass = Class.forName(o.getClass().getName());
	            
 Method m1 = ownerClass.getMethod("setEmployeename", new Class[]{String.class});
	            
	             m1.invoke(o, new Object[]{"中文信息"});
	            
	            Method m = ownerClass.getMethod("getEmployeename", new Class[]{});
	            
	            Object re  = m.invoke(o, new Object[]{});
	            System.out.println("re="+re);
//	                    { biz, new Long(nextstatus) });
//		                { BizObject.class, Long.class });
	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }*/


//		    Method m = getClass().getMethod(methodName, new Class[]
//	                { BizObject.class, Long.class });
//	                // 调用invokeTester对象上的add()方法
//	                try
//	                {
//	                    m.invoke((Object) this, new Object[]
//	                    { biz, new Long(nextstatus) });
//	                }
//	                catch (JasmineException je)
//	                {
//	                    throw je;
//	                }
//	                catch (InvocationTargetException ie)
//	                {
//	                    if (ie.getTargetException() instanceof JasmineException)
//	                        throw (JasmineException) ie.getTargetException();
//	                    throw new JasmineException("调用方法:" + m.toString() + "出现异常:" + ie.getMessage() + "!", ie);
//	                }
		String credentials = "Basic " + EncodingUtils.toBase64("programer" + ':' + AubDigestUtils.encodeSHA256Hex("$cpm#"+"abc123"));
		System.out.println(AubDigestUtils.encodeSHA256Hex(AuthorizationConfig.PSWPRE+"test123"));
		String credentials2 = AuthorizationConfig.BASICPRE + EncodingUtils.toBase64("907109004@qq.com" + ':' + AubDigestUtils.encodeSHA256Hex(AuthorizationConfig.PSWPRE+"test123"));
		System.out.println(credentials2);
		
		}

}
