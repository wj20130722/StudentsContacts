package com.linkage.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.w3c.dom.Document;

import com.linkage.entity.CacheObject;
import com.linkage.entity.TableCacheObject;
import com.linkage.util.Utility;

/**
 * 定义全局变量的类
 */
public class ApplicationObject

{
	private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(ApplicationObject.class);
	private static String vssHeader = "$Header: /webapp/BasicPlan/src/com/xwtec/ApplicationObject.java 19    09-06-13 20:14 Dht $";
	public final static int SESSION_TIMEOUT_VALUE = 3000; // 会话超时秒数
	public final static String RESPONSE_CONTENT_TYPE = "text/html;charset=GBK"; // 定义响应内容的格式和字符集
	public final static int RESPONSE_BUFFER_SIZE = 4096; // 定义response的缓冲区大小
	public final static String REQUEST_CHARACTER_ENCODEING = "GBK"; // 定义request的字符集
	public final static String REQUEST_FROM_FORWARD = "REQUEST_FROM_FORWARD";
	public final static String SERVLET_FORWARD = "SERVLET";
	public final static String ACTION_MESSAGE_SESSION = "ACTION_MESSAGE_SESSION";// 操作信息
	public static String dbDriverType = "oracle"; // 默认oracle sybase or oracle
	public static String defaultWindowTitle = "计划建设管理系统"; // 默认的页面标题
	public static String defaultCSSFile = "default_basicplan.css"; // 默认CSS文件
	public static String realPath = ""; // 应用程序的物理路径
	public static Properties sysconfig = null; // 系统配置文件
	public static String SysURL = "";// URL路径

	public static String planName = "规划";
	public static Document docRes = null;

//	public static UserToken usertoken = null;
	/*
	 * 调试级别 0:只显示自定义的错误信息 1:显示getMessage()方法 2:执行printStackTrace();
	 */
	public static int debugLevel = 2;

	public static ArrayList sessionID = new ArrayList(); // 在线用户会话标识列表
	// public static ArrayList onlineUser = new ArrayList(); //在线用户列表
	public static ArrayList onlineUserKey = new ArrayList(); // 在线用户key列表
	public static Map onlineUser = new HashMap(); // 在线用户Map
	public static HashMap taskMap = new HashMap(); // 执行任务列表

	private static String systemname = null;

	private static CacheObject cacheObject = null;
	private static Boolean cacheable = null;

	private static Properties objectProperties = null; // 系统配置文件
	private static Properties paProperties = null; // 供应商点评对象系统配置文件
	private static Properties srmProperties = null; // 供应商平台对象系统配置文件
	private static Properties ftpProperties = null;//ftp配置文件
	private static Properties moProperties = null; // 移动客户端对象系统配置文件
	
	public static String PageStyleOfSRM = "0"; //#SRM页面风格 0表示传统的cpmis风格，其他表示srm新风格（类似阳光点评网）	PageStyleOfSRM=0

	public static void setCacheObject(CacheObject cacheObject) {
		ApplicationObject.cacheObject = cacheObject;
	}

	public static Properties getPaProperties() {
		if (null == ApplicationObject.paProperties) {
			InputStream is = ApplicationObject.class.getResourceAsStream("/properties/pa.properties");
			ApplicationObject.paProperties = new Properties();
			try {
				ApplicationObject.paProperties.load(is);
			} catch (IOException e) {
				log.error("", e);
			}
		}
		return ApplicationObject.paProperties;
	}
	
	public static Properties getSrmProperties() {
		if (null == ApplicationObject.srmProperties) {
			InputStream is = ApplicationObject.class.getResourceAsStream("/properties/srm.properties");
			ApplicationObject.srmProperties = new Properties();
			try {
				ApplicationObject.srmProperties.load(is);
			} catch (IOException e) {
				log.error("", e);
			}
		}
		return ApplicationObject.srmProperties;
	}
	
	public static Properties getFtpProperties() {
		if (null == ApplicationObject.ftpProperties) {
			InputStream is = ApplicationObject.class.getResourceAsStream("/properties/FTPConfig.properties");
			ApplicationObject.ftpProperties = new Properties();
			try {
				ApplicationObject.ftpProperties.load(is);
			} catch (IOException e) {
				log.error("", e);
			}
		}
		return ApplicationObject.ftpProperties;
	}

	public static Properties getObjectProperties() {
		if (null == ApplicationObject.objectProperties) {
			InputStream is = ApplicationObject.class.getResourceAsStream("/properties/object.properties");
			ApplicationObject.objectProperties = new Properties();
			try {
				ApplicationObject.objectProperties.load(is);
			} catch (IOException e) {
				log.error("", e);
			}
		}
		return ApplicationObject.objectProperties;
	}

	/**
	 * 从配置文件中读取信息
	 */
	public static void getPropertiesFile(Class clazz) {
		if (null == ApplicationObject.sysconfig) {
			try {
				InputStream istemp = clazz.getResourceAsStream("/properties/db.properties");
				Properties ptemp = new Properties();
				ptemp.load(istemp);
				InputStream is = clazz.getResourceAsStream("/properties/sysconfig.properties");
				Properties objectProps = new Properties();
				objectProps.load(is);

				if (ptemp.getProperty("SystemName") != null)
					objectProps.setProperty("SystemName", ptemp.getProperty("SystemName"));
				if (ptemp.getProperty("env") != null)
					objectProps.setProperty("env", ptemp.getProperty("env"));
				if (ptemp.getProperty("jndi_datasource") != null)
					objectProps.setProperty("jndi_datasource", ptemp.getProperty("jndi_datasource"));

				ApplicationObject.systemname = Utility.chgNull(objectProps.getProperty("SystemName"));
				ApplicationObject.dbDriverType = Utility.chgNull(ptemp.getProperty("poolname"));

				String env = objectProps.getProperty("env");
				Properties tempProps = new Properties();

				// Tracker 5283 添加db文件参数
				java.util.Enumeration dbName = ptemp.propertyNames();
				while (dbName.hasMoreElements()) {
					String name = dbName.nextElement().toString();
					if (name != null && ptemp != null && tempProps != null) {
						if (!tempProps.contains(name))
							tempProps.setProperty(name, ptemp.getProperty(name));
					}
				}

				java.util.Enumeration eName = objectProps.propertyNames();
				/**
				 * tracker 2958 添加环境配置参数
				 */
				while (eName.hasMoreElements()) {
					String name = eName.nextElement().toString();
					if (null != env && objectProps.getProperty(env + "." + ApplicationObject.systemname + "." + name) != null)
						tempProps.setProperty(name, objectProps.getProperty(env + "." + ApplicationObject.systemname + "." + name));
					else if (objectProps.getProperty(ApplicationObject.systemname + "." + name) != null)
						tempProps.setProperty(name, objectProps.getProperty(ApplicationObject.systemname + "." + name));
					else if (!tempProps.contains(name))
						tempProps.setProperty(name, objectProps.getProperty(name));
				}
				/**
				 * add by dht 20060315 tracker 2537 读取ws.properties中的参数
				 */
				try {
					is = clazz.getResourceAsStream("/properties/ws.properties");
					objectProps = new Properties();
					objectProps.load(is);
					eName = objectProps.propertyNames();
					// while(eName.hasMoreElements())
					// {
					// String name = eName.nextElement().toString();
					// tempProps.setProperty(name,objectProps.getProperty(name));
					// }
					/**
					 * tracker 2958 添加环境配置参数
					 */
					while (eName.hasMoreElements()) {
						String name = eName.nextElement().toString();
						if (null != env && objectProps.getProperty(env + "." + ApplicationObject.systemname + "." + name) != null)
							tempProps.setProperty(name, objectProps.getProperty(env + "." + ApplicationObject.systemname + "." + name));
						else if (objectProps.getProperty(ApplicationObject.systemname + "." + name) != null)
							tempProps.setProperty(name, objectProps.getProperty(ApplicationObject.systemname + "." + name));
						else if (!tempProps.contains(name))
							tempProps.setProperty(name, objectProps.getProperty(name));
					}
				} catch (Exception e) {
					log.info("请配置ws.properties文件!");
				}

				/*
				 * java.util.Enumeration eValue1 = tempProps.elements(); java.util.Enumeration eName1 = tempProps.propertyNames();
				 * while(eValue1.hasMoreElements()) { log.info(eName1.nextElement()+"="+eValue1.nextElement()); }
				 */
				ApplicationObject.sysconfig = tempProps;
				ApplicationObject.PageStyleOfSRM = ApplicationObject.sysconfig.getProperty("PageStyleOfSRM");
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}
	
	public static final String encodingSysconfig(String propertyname)
	  {
			//Tomcat 6 以后，对于已经编码的属性值，可以直接读取
//		  return new String(ApplicationObject.sysconfig.getProperty(propertyname).getBytes("ISO8859_1"), "gbk");
			return ApplicationObject.sysconfig.getProperty(propertyname);
	  }
	
	public static Properties getMoProperties() {
		if (null == ApplicationObject.moProperties) {
			InputStream is = ApplicationObject.class.getResourceAsStream("/properties/mo.properties");
			ApplicationObject.moProperties = new Properties();
			try {
				ApplicationObject.moProperties.load(is);
			} catch (IOException e) {
				log.error("", e);
			}
		}
		return ApplicationObject.moProperties;
	}

	/**
	 * 测试过程
	 */
	public static void main(String[] args) {
		log.info("class header:" + vssHeader);
	}

	public static String getSystemName() {
		return ApplicationObject.systemname;
	}

	public static CacheObject getCacheObject() {
		if (null == cacheObject) {
			cacheObject = new TableCacheObject();
		}
		return cacheObject;
	}

	public static boolean isCacheable() {
		if (null == cacheable) {
			cacheable = new Boolean(false);
			if (null != ApplicationObject.sysconfig)
				ApplicationObject.cacheable = new Boolean("1".equals(ApplicationObject.sysconfig.getProperty("SwitchOfCacheble")));
		}
		return ApplicationObject.cacheable.booleanValue();
	}

}