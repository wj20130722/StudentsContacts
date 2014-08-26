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

import com.linkage.contacts.server.dao.AluassociationConfigDAO;
import com.linkage.contacts.server.dao.AluassociationRoleInfoDAO;
import com.linkage.contacts.server.entity.AluassociationConfig;
import com.linkage.contacts.server.entity.AluassociationRoleInfo;
import com.linkage.contacts.server.entity.ClassInfo;
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
public class AluassociationRoleInfoBO
{
	private static final Log log = LogFactory.getLog(AluassociationRoleInfoBO.class);
	
	@Autowired
	@Qualifier("mybatisAluassociationRoleInfoDao")
	private AluassociationRoleInfoDAO aluassociationRoleInfoDAO;
	
	@Autowired
	@Qualifier("mybatisAluassociationConfigDao")
	private AluassociationConfigDAO aluassociationConfigDAO;
	
	@Autowired
	private SystemPushMessageBO systemPushMessageBO;
	
	@Autowired
	private UserMessageMarkBO userMessageMarkBO;
	
	@Autowired
	private CommonMapper commonMapper;

	public void saveAluassociationInfo(UserInfo userInfo, int aluassociation_id)
  {
		int count = aluassociationRoleInfoDAO.countByUserAlu(userInfo.getUser_id(), aluassociation_id);
		if(count>0)
		{
			String sql = "update aluassociation_role_info set aluassociation_role_state=0 where aluassociation_id="+aluassociation_id+" and user_id="+userInfo.getUser_id();
			commonMapper.updateDataBySql(sql);
		}
		else
		{
			 AluassociationRoleInfo aluassociationRoleInfo = new AluassociationRoleInfo();
			 aluassociationRoleInfo.setAluassociation_id(aluassociation_id);
			 aluassociationRoleInfo.setUser_id(userInfo.getUser_id());
			 aluassociationRoleInfo.setAluassociation_admin(0);
			 aluassociationRoleInfo.setAluassociation_role_state(0);
			 aluassociationRoleInfoDAO.insert(aluassociationRoleInfo);
		}
		log.info("用户申请加入校友会成功。");
		//推送系统消息---->校友会管理员
		int nid = 20000+aluassociation_id;
		AluassociationConfig aluassociationConfig = aluassociationConfigDAO.selectByPrimaryKey(aluassociation_id);
		String content = "您有一条待审核信息：用户"+userInfo.getUser_name()+"申请加入"+aluassociationConfig.getAluassociation_name()+"请您尽快审核！";
		String sql = "select u.user_id,u.user_name,u.online_status,u.access_token from aluassociation_role_info al join user_info u on u.user_id=al.user_id where al.aluassociation_id="+aluassociation_id+" and al.aluassociation_role_state=1 and al.aluassociation_admin=1";
		//获取校友会管理员信息
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
			Message message = XingeAppPushUtil.constructMessageAndriod(Message.TYPE_NOTIFICATION, nid, ClickAction.TYPE_ACTIVITY, "校友会加入申请", content, null,custom);
			JSONObject jsonObject =  XingeAppPushUtil.pushSingleAccountAndriod(message, Utility.chgNull(map.get("access_token")));
			int push_state = jsonObject.getInt("ret_code");
			//IOS推送
			int push_state2 = 0;
			if("1".equals(Utility.chgNull(map.get("online_status"))))
			{
				Map<String, Object> custom2 = new HashMap<String, Object>();
				custom2.put("type", "2");
				custom2.put("time", now.getTime());
				MessageIOS message2 = XingeAppPushUtil.constructMessageIOS(1, "【校友会加入申请】"+content, custom2);
				JSONObject jsonObject2 = XingeAppPushUtil.pushSingleAccountIOS(message2, Utility.chgNull(map.get("access_token")));
				push_state2 = jsonObject2.getInt("ret_code");
			}
			else
			{
				push_state2 = 77;//用户不在线
			}
			
			if(0 == (push_state & push_state2))
			{
				log.info("校友会申请加入审核消息发送给校友会管理员"+Utility.chgNull(map.get("user_name"))+"成功！");
				userMessageMarkBO.addUserSysMessageMark(Integer.parseInt(Utility.chgNull(map.get("user_id"))));
			}
			else
				log.info("校友会申请加入审核消息发送给校友会管理员"+Utility.chgNull(map.get("user_name"))+"失败！");
			//保存系统消息
			systemMessage = new SystemPushMessage();
			systemMessage.setMessage_type_id(3);//加入校友会的系统消息
			systemMessage.setFrom_user_id(userInfo.getUser_id());
			systemMessage.setTo_user_id(Integer.parseInt(Utility.chgZero(map.get("user_id"))));
			systemMessage.setMessage(content);
			systemMessage.setPush_time(now);
			systemMessage.setPush_state(push_state & push_state2);
			this.systemPushMessageBO.save(systemMessage);
		}
  }

	public boolean roleSetAluassociation(int aluassociation_id, int user_id, int type)
  {
		boolean result = false;
		int count = 0;
		String sql = "";
		if(1 == type) //设置校友会管理员
		{
			sql = "update aluassociation_role_info set aluassociation_admin=1 where user_id="+user_id+" and aluassociation_id="+aluassociation_id;
			count = commonMapper.updateDataBySql(sql);
		}
		else if(0 == type) //剔除校友会成员
		{
			sql = "delete from aluassociation_role_info where user_id="+user_id+" and aluassociation_id="+aluassociation_id;
			count = commonMapper.deleteDataBySql(sql);
			if(count == 1)
			{
				//将校友会成员数量减去1
				commonMapper.updateDataBySql("update aluassociation_config set aluassociation_num=ifnull(aluassociation_num,0)-1 where aluassociation_id="+aluassociation_id);
			}
		}
		if(1==count)
			result = true;
		else
			result = false;
	  return result;
  }
	
	public boolean deleteByUserAlu(int user_id, int aluassociation_id)
	{
		boolean result = false;
		int count = this.aluassociationRoleInfoDAO.deleteByUserAlu(user_id, aluassociation_id);
		if(count==1)
			result = true;
		else
			result = false;
		return result;
	}
}
