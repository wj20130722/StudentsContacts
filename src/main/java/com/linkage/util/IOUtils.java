package com.linkage.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils
{
	/**
	 * 拷贝文件
	 * @param src 源文件
	 * @param des 目标文件
	 */
	public static void copy(String src,String des) throws IOException{
		FileInputStream fis = new FileInputStream(src);
		FileOutputStream fos = new FileOutputStream(des);
		int b = 0;
		long start = System.currentTimeMillis();
		while((b = fis.read()) != -1) {
			fos.write(b);
		}
		long end = System.currentTimeMillis();
		fos.close();
		fis.close();
		System.out.println("文件复制完毕！用时"+(end-start));
	}
	
	/**
	 * 
	 * @param src
	 * @param des
	 * @throws IOException
	 */
	public static void copy1(String src,String des) throws IOException{
		FileInputStream fis = new FileInputStream(src);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		FileOutputStream fos = new FileOutputStream(des);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
	
		int b = 0;
		long start = System.currentTimeMillis();
		while((b = bis.read()) != -1) {
			bos.write(b);
		}
		long end = System.currentTimeMillis();
		bos.flush();
		bos.close();
		fos.close();
		bis.close();
		fis.close();
		System.out.println("文件复制完毕！用时"+(end-start));
	}
	
	/**
	 * 使用字节数组进行读写操作，效率的高低取决于字节数组的大小，一般取适当的大小，效率比缓冲区要高,但稳定性没有缓冲区好
	 * @param src 原文件的路径
	 * @param des 目标文件的路径
	 * @throws IOException IO异常
	 */
	public static void copy2(String src,String des) throws IOException{
		FileInputStream fis = new FileInputStream(src);
		FileOutputStream fos = new FileOutputStream(des);
		
		byte[] buf = new byte[1024*512];
		int len = 0;
		long start = System.currentTimeMillis();
		while((len = fis.read(buf))!= -1) {
			fos.write(buf, 0, len); 
		}
		
		long end = System.currentTimeMillis();
		fis.close();
		fos.close();
		System.out.println("文件复制完毕！用时"+(end-start));	
	}
	
	public static void copy3(InputStream input,OutputStream output) throws IOException
	{
		byte[] buf = new byte[1024*1024];  //使用1M大小的字节数组对文件进行读写操作
		int len = 0;
		long start = System.currentTimeMillis();
		while((len = input.read(buf))!= -1) {
			output.write(buf, 0, len); 
			output.flush();
		}
		
		long end = System.currentTimeMillis();
		input.close();
		output.close();
		System.out.println("文件复制完毕！用时"+(end-start));	
	}
}
