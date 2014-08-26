package com.linkage.push.xinge;

import com.tencent.xinge.XingeApp;

public class LinkageXingeApp
{
	//ANDRIOD 配置
	public static final long ANDRIOD_ACCESS_ID = 2100026551L;
	
	public static final String ANDRIOD_ACCESS_KEY = "A4S32GA3X5EG";
	
	public static final String ANDRIOD_SECRET_KEY = "65de3387cb5c150826a29d0e2ca62fde";
	
	//IOS 配置
	public static final long IOS_ACCESS_ID = 2200044833L;
	
	public static final String IOS_ACCESS_KEY = "IB1KYX9S552Z";
	
	public static final String IOS_SECRET_KEY = "903d6162d4b4940b6232f33d3e8d3a7d";
	
	private LinkageXingeApp()
	{
		
	}
	
	private static class XingeAppInstance 
	{
		private static XingeApp xingeAndriod = new XingeApp(ANDRIOD_ACCESS_ID, ANDRIOD_SECRET_KEY);
		
		private static XingeApp xingeIOS = new XingeApp(IOS_ACCESS_ID, IOS_SECRET_KEY);
		
		
  }
	
	public static XingeApp getInstance(int platform)
	{
		if(1 == platform)
			return XingeAppInstance.xingeAndriod;
		else if(2 == platform)
			return XingeAppInstance.xingeIOS;
		else
			return XingeAppInstance.xingeAndriod;
	}
	
	
}
