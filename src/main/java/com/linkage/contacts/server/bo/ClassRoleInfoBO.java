package com.linkage.contacts.server.bo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.linkage.contacts.server.dao.ClassInfoDAO;
import com.linkage.contacts.server.dao.ClassRoleInfoDAO;
import com.linkage.contacts.server.entity.ClassInfo;
import com.linkage.contacts.server.entity.ClassRoleInfo;
import com.linkage.contacts.server.entity.SystemPushMessage;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;
import com.linkage.push.xinge.XingeAppPushUtil;
import com.linkage.util.MybatisUtil;
import com.linkage.util.Utility;
import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;

@Service
public class ClassRoleInfoBO
{
	private static final Log log = LogFactory.getLog(ClassRoleInfoBO.class);
	
	@Autowired
	@Qualifier("mybatisClassRoleInfoDao")
	private ClassRoleInfoDAO classRoleInfoDAO;
	
	@Autowired
	@Qualifier("mybatisClassInfoDao")
	private ClassInfoDAO classInfoDAO;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private SystemPushMessageBO systemPushMessageBO;
	
	@Autowired
	private UserMessageMarkBO userMessageMarkBO;
	
	public void saveClassRoleInfo(UserInfo userInfo, int class_id)
  {
		//TODO WJ 用户重新加入班级的判断处理
		int count = classRoleInfoDAO.countByUserClass(userInfo.getUser_id(), class_id);
		if(count>0)
		{
			String sql = "update class_role_info set class_role_state=0 where class_id="+class_id+" and user_id="+userInfo.getUser_id();
			commonMapper.updateDataBySql(sql);
		}
		else
		{
			ClassRoleInfo classRoleInfo = new ClassRoleInfo();
			classRoleInfo.setUser_id(userInfo.getUser_id());
			classRoleInfo.setClass_id(class_id);
			classRoleInfo.setClass_admin(0);
			classRoleInfo.setClass_role_state(0);
			classRoleInfoDAO.insert(classRoleInfo);
		}
		log.info("用户加入班级成功。");
		//推送系统消息---->班级管理员
		int nid = 10000+class_id;
		ClassInfo classInfo = classInfoDAO.selectByPrimaryKey(class_id);
		String college_name = commonMapper.getColumnValueBySql("select college_name from college_config where college_id="+classInfo.getCollege_id());
		String content = "您有一条待审核信息：用户"+userInfo.getUser_name()+"申请加入"+classInfo.getYear()+"级"+college_name+classInfo.getClass_name()+"请您尽快审核！";
		String sql = "select u.user_id,u.user_name,u.online_status,u.access_token from class_role_info cr join user_info u on u.user_id=cr.user_id where cr.class_id="+class_id+" and cr.class_role_state=1 and cr.class_admin=1";
		//获取班级管理员信息
		List<HashMap<String, Object>> list = commonMapper.getTableRowBySql(sql);
		list = MybatisUtil.converColumnNullList(list, new String[]{"user_name","access_token"});
		HashMap<String, Object> map;
		Date now = new Date();
		SystemPushMessage systemMessage = null;
		for(int i=0;i<list.size();i++)
		{
			map = list.get(i);
			Map<String, Object> custom = new HashMap<String, Object>();
			custom.put("type", "2");
			custom.put("content", content);
			custom.put("time", now.getTime());
			//Andriod推送
			Message message = XingeAppPushUtil.constructMessageAndriod(Message.TYPE_NOTIFICATION, nid, ClickAction.TYPE_ACTIVITY, "班级加入申请", content, null, custom);
			JSONObject jsonObject =  XingeAppPushUtil.pushSingleAccountAndriod(message, Utility.chgNull(map.get("access_token")));
			int push_state = jsonObject.getInt("ret_code");
			//IOS推送
			int push_state2 = 0;
			if("1".equals(Utility.chgNull(map.get("online_status"))))
			{
				Map<String, Object> custom2 = new HashMap<String, Object>();
				custom2.put("type", "2");
				custom2.put("time", now.getTime());
				MessageIOS message2 = XingeAppPushUtil.constructMessageIOS(1, "【班级加入申请】"+content, custom2);
				JSONObject jsonObject2 = XingeAppPushUtil.pushSingleAccountIOS(message2, Utility.chgNull(map.get("access_token")));
				push_state2 = jsonObject2.getInt("ret_code");
			}
			else
			{
				push_state2 = 77;//用户不在线
			}
			
			if(0 == (push_state & push_state2))
			{
				log.info("班级申请加入审核消息发送给班级管理员"+Utility.chgNull(map.get("user_name"))+"成功！");
				userMessageMarkBO.addUserSysMessageMark(Integer.parseInt(Utility.chgNull(map.get("user_id"))));
			}
			else
				log.info("班级申请加入审核消息发送给班级管理员"+Utility.chgNull(map.get("user_name"))+"失败！");
			//保存系统消息
			systemMessage = new SystemPushMessage();
			systemMessage.setMessage_type_id(2);//加入班级的系统消息
			systemMessage.setFrom_user_id(userInfo.getUser_id());
			systemMessage.setTo_user_id(Integer.parseInt(Utility.chgZero(map.get("user_id"))));
			systemMessage.setMessage(content);
			systemMessage.setPush_time(now);
			systemMessage.setPush_state(push_state & push_state2);
			this.systemPushMessageBO.save(systemMessage);
		}
		
  }

	/**
	 * 用户权限设置设置班级管理员或者剔除某个用户
	 * @param class_id
	 * @param user_id
	 * @param type
	 * @return
	 */
	public boolean roleSetClass(int class_id, int user_id, int type)
  {
		boolean result = false;
		int count = 0;
		String sql = "";
		if(1 == type) //设置班级管理员
		{
			sql = "update class_role_info set class_admin=1 where user_id="+user_id+" and class_id="+class_id;
			count = commonMapper.updateDataBySql(sql);
		}
		else if(0 == type) //剔除班级成员
		{
			sql = "delete from class_role_info where user_id="+user_id+" and class_id="+class_id;
			count = commonMapper.deleteDataBySql(sql);
			if(count == 1)
			{
				//将班级成员数量减去1
				commonMapper.updateDataBySql("update class_info set student_num=ifnull(student_num,0)-1 where class_id="+class_id);
			}
		}
		if(1==count)
			result = true;
		else
			result = false;
	  return result;
  }
	
	public boolean deleteUserClass(int user_id,int class_id)
	{
		boolean result = false;
		int count = this.classRoleInfoDAO.deleteByUserClass(user_id, class_id);
		if(count==1)
			result = true;
		else
			result = false;
		return result;
	}
	
	
	
}
