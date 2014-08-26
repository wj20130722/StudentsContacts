package com.linkage.emojicon.emoji;

import java.io.UnsupportedEncodingException;

public class TestUnicode
{
	public static void main(String[] args)
	{
		//System.out.println(chinaToUnicode("你好aAbc"));
		//System.out.println(string2Unicode("Abc"));
		//System.out.println(string2Unicode(new String(Character.toChars(0x1f51f))));
		//System.out.println(string2Unicode(new String(Character.toChars(0x1f604))));
		//System.out.println(string2Unicode(new String(Character.toChars(0x263a))));
		//System.out.println(string2Unicode(new String(Character.toChars(0x0031))));
		//System.out.println(string2Unicode("\u5f20\u6d77\u971e"));
		System.out.println(40 & 40);
		
	}

	/**
	 * 把中文转成Unicode码
	 * 
	 * @param str
	 * @return
	 */
	public static String chinaToUnicode(String str)
	{
		String result = "";
		for (int i = 0; i < str.length(); i++)
		{
			int chr1 = (char) str.charAt(i);
			if (chr1 >= 19968 && chr1 <= 171941)
			{// 汉字范围 \u4e00-\u9fa5 (中文)
				result += "\\u" + Integer.toHexString(chr1);
			}
			else
			{
				// result+=str.charAt(i);
				result += "\\u" + Integer.toHexString(chr1);
			}
		}
		return result;
	}

	public static String string2Unicode(String s)
	{
		try
		{
			System.out.println(s);
			StringBuffer out = new StringBuffer("");
			byte[] bytes = s.getBytes("unicode");
			for (int i = 2; i < bytes.length - 1; i += 2)
			{
				out.append("\\u");
				String str = Integer.toHexString(bytes[i + 1] & 0xff);
				for (int j = str.length(); j < 2; j++)
				{
					//out.append("0");
					str = "0"+str;
				}
				String str1 = Integer.toHexString(bytes[i] & 0xff);
				for (int j = str1.length(); j < 2; j++)
				{
					//out.append("0");
					str1 = "0"+str1;
				}
				out.append(str1);
				out.append(str);
				out.append(" ");
			}
			return out.toString();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static String unicode2String(String unicodeStr)
	{
		StringBuffer sb = new StringBuffer();
		String str[] = unicodeStr.toUpperCase().split("U");
		for (int i = 0; i < str.length; i++)
		{
			if (str[i].equals(""))
				continue;
			char c = (char) Integer.parseInt(str[i].trim(), 16);
			sb.append(c);
		}
		return sb.toString();
	}
	
}
