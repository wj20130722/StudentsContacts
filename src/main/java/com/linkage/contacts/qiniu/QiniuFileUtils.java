package com.linkage.contacts.qiniu;

import java.io.File;

import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

public class QiniuFileUtils
{
	static 
	{
		Config.ACCESS_KEY = "6ifKr52_2r1jXzD-daEjE_1miSFnJLa1HokwaNYH";
    Config.SECRET_KEY = "PLsOaVeCbfbrdxm2cfRVHtGmFSprmjSgwrrh--9H";
	}
	
	//获取上传凭证
	public static String getUpToken(String bucketName) throws Exception
	{
		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		PutPolicy putPolicy = new PutPolicy(bucketName);
		//指定上传策略 。。。。。
    String uptoken = putPolicy.token(mac);
		return uptoken;
	}
	
	//上传文件
	public static String uploadFile(String uptoken,String key,File file,PutExtra extra)
	{
		String fileName = "";
		PutRet ret = IoApi.putFile(uptoken, key, file, extra);
		if(ret.ok()) //文件上传成功
			fileName = ret.getKey();
		else
			fileName = "";
		return fileName;
	}
	
	public static void main(String[] args) throws Exception
  {
		String uptoken = getUpToken("zlzwimg");
		System.out.println(uptoken);
	  //String uptoken = "6ifKr52_2r1jXzD-daEjE_1miSFnJLa1HokwaNYH:nE71Wh_2ohMLy98-c2Cn19Ky5qU=:eyJzY29wZSI6InpsendpbWciLCJkZWFkbGluZSI6MTQwNjc3OTYyNX0=";
	  File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\8.jpg");
	  PutExtra extra = new PutExtra();
	  System.out.println(uploadFile(uptoken, "8.jpg", file, extra));
  }
	
}
