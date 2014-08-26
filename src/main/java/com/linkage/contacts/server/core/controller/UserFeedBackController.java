package com.linkage.contacts.server.core.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkage.contacts.server.bo.UserFeedBackBO;
import com.linkage.contacts.server.bo.UserInfoBO;
import com.linkage.contacts.server.bo.UserVersionBO;
import com.linkage.contacts.server.entity.UserFeedBack;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;
import com.linkage.contacts.server.util.AuthorizationConfig;
import com.linkage.contacts.server.vo.FormUserAdvice;
import com.linkage.util.MybatisUtil;

/**
 * 个人中心补充接口：
 * 用户意见反馈接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/person/advice
 * (修改)版本检查更新接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/person/checkversion?platform=1&version_id=xxxx&version_info=xxxx
 * @author wangjie
 *
 */
@Controller
@RequestMapping(value="/person")
public class UserFeedBackController
{
	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private UserFeedBackBO userFeedBackBO;
	
	@Autowired
	private UserInfoBO userInfoBO;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Value("${android_down_url}")
	private String android_down_url;
	
	@Value("${ios_down_url}")
	private String ios_down_url;
	
	@Value("${switch_of_validaiton}")
	private String switch_of_validaiton;
	
	@Autowired
	private UserVersionBO userVersionBO;
	
	@ModelAttribute
	public void initModelAttribute(@RequestHeader(value=AuthorizationConfig.HEADER_AUTHORIZATION,required=false) String authorization, Model model)
	{
		UserInfo userInfo = userInfoBO.selectByTokenAnthorization(authorization);
		model.addAttribute("userInfo", userInfo);
	}
	
	@RequestMapping(value = "/advice", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> addAdvice(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,UserFeedBack userFeedBack,@RequestBody FormUserAdvice formUserAdvice)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "advice", null==userInfo?null:userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		userFeedBack.setAdvice(formUserAdvice.getAdvice());
		if(StringUtils.isBlank(formUserAdvice.getContacts_way()) && null!=userInfo && 0!=userInfo.getUser_id())
			userFeedBack.setContacts_way(userInfo.getMail());
		else
			userFeedBack.setContacts_way(formUserAdvice.getContacts_way());
		try
		{
			userFeedBackBO.saveAdviceinfo(userFeedBack);
			map.put("result", 1);
			map.put("message", "反馈意见成功");
		}
		catch (Exception e)
		{
			log.info(e.getMessage(), e);
			map.put("result", 0);
			map.put("message", "反馈意见失败");
		}
		return map;
	}
	
	@RequestMapping(value = "/checkversion", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> checkVersion(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("platform")  int platform,@RequestParam(required=false,value="version_id") String version_id,@RequestParam(required=false,value="version_info") String version_info)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "checkversion", null==userInfo?null:userInfo.getAccess_token());
		//TODO WJ 记录用户当前最近版本信息
		if(null!=userInfo && userInfo.getUser_id()!=0)
		{
			userVersionBO.saveUserVersion(userInfo,platform,version_id,version_info);
		}
		HashMap<String, Object> map = null;
		String sql = "";
		if(1==platform)
			sql = "select version_id,version_desc,force_update from version_config where plat_form="+platform+" order by version_time desc";
		else
			sql = "select version_info,version_desc,force_update from version_config where plat_form="+platform+" order by version_time desc";
		List<HashMap<String, Object>> maps = commonMapper.getTableRowBySql(sql);
		if(null!=maps && maps.size()>0)
		{
			map = maps.get(0);
			if(1==platform)
				map.put("url", android_down_url);
			else
				map.put("url", ios_down_url);
		}
		if(1==platform)
			map = MybatisUtil.converColumnNull(map, new String[]{"version_id","version_desc","url","force_update"});
		else
			map = MybatisUtil.converColumnNull(map, new String[]{"version_info","version_desc","url","force_update"});
		return map;
	}
	
}
