package com.linkage.contacts.server.bo;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.linkage.contacts.server.dao.AluassociationConfigDAO;
import com.linkage.contacts.server.entity.AluassociationConfig;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;
import com.linkage.contacts.server.vo.FormAluassociation;
import com.linkage.util.MybatisUtil;

@Service
public class AluassociationConfigBO
{
	private static final Log log = LogFactory.getLog(AluassociationConfigBO.class);
	
	@Autowired
	@Qualifier("mybatisAluassociationConfigDao")
	private AluassociationConfigDAO aluassociationConfigDAO;
	
	@Autowired
	private CodeBO code;
	
	@Autowired
	private CommonMapper commonMapper;
	
	public void save(AluassociationConfig aluassociationConfig)
	{
		if(0==aluassociationConfig.getAluassociation_id())
		{
			aluassociationConfig.setAluassociation_id((int)code.newCode("aluassociation_conf.aluassociation_id", "校友会信息", 1));
			this.aluassociationConfigDAO.insert(aluassociationConfig);
			log.info("校友会信息保存成功。");
		}
		else
		{
			this.aluassociationConfigDAO.update(aluassociationConfig);
			log.info("校友会信息更新成功。");
		}
		
	}
	
	public List<FormAluassociation> getAluInfosByUser(UserInfo userInfo)
	{
		List<FormAluassociation> list = null;
		//TODO wj 校级管理员查看校友会信息
		//if(0 == userInfo.getSuper_admin())
		//{
			list = aluassociationConfigDAO.getAluInfosByUser(userInfo.getUser_id(),userInfo.getUniversity_id());
		//}
		//else
		//{
		//	list = aluassociationConfigDAO.getAluInfosByAdmin(userInfo.getUniversity_id());
		//}
		return list;
	}

	public List<HashMap<String, Object>> getTypeInfo()
  {
		String sql = "select type_id,type_name from aluassociation_type_info";
		List<HashMap<String, Object>> result = commonMapper.getTableRowBySql(sql);
		result = MybatisUtil.converColumnNullList(result, new String[]{"type_name"});
	  return result;
  }

	public List<HashMap<String, Object>> getInfoByType(int user_id, int university_id, int type_id)
  {
		List<HashMap<String,Object>> result = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select    \n");
		sb.append("aa.aluassociation_id,   \n");
		sb.append("aa.aluassociation_name,   \n");
		sb.append("(select group_concat(user_name) from user_info where user_id in (select user_id from aluassociation_role_info where aluassociation_admin=1 and aluassociation_id=aa.aluassociation_id)) admin_name,   \n");
		sb.append("aa.aluassociation_num,");
		sb.append("(select convert(CONCAT(aluassociation_role_state,''),UNSIGNED) from aluassociation_role_info where user_id="+user_id+" and aluassociation_id=aa.aluassociation_id) aluassociation_role_state,   \n");
		sb.append("aa.aluassociation_pic  \n");
		sb.append("from aluassociation_config aa  \n");
		sb.append("where aa.aluassociation_state=1 and aa.type_id="+type_id+"  \n");
		sb.append("order by aa.aluassociation_id");
		result = commonMapper.getTableRowBySql(sb.toString());
		result = MybatisUtil.converColumnNullList(result, new String[]{"aluassociation_name","admin_name","aluassociation_role_state","aluassociation_pic"});
	  return result;
  }
	
	
	
	
}
