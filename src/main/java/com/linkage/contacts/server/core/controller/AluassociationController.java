package com.linkage.contacts.server.core.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkage.contacts.server.bo.AluassociationConfigBO;
import com.linkage.contacts.server.bo.AluassociationRoleInfoBO;
import com.linkage.contacts.server.bo.ClassCallShareBO;
import com.linkage.contacts.server.bo.ClassInfoBO;
import com.linkage.contacts.server.bo.ClassRoleInfoBO;
import com.linkage.contacts.server.bo.ReportInfoBO;
import com.linkage.contacts.server.bo.UserInfoBO;
import com.linkage.contacts.server.entity.ClassInfo;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;
import com.linkage.contacts.server.util.AuthorizationConfig;
import com.linkage.contacts.server.vo.FormAluDesc;
import com.linkage.contacts.server.vo.FormClassCallShare;
import com.linkage.contacts.server.vo.FormCreateClassInfo;
import com.linkage.contacts.server.vo.FormReportInfo;
import com.linkage.mybatis.util.CriteriaManager;
import com.linkage.mybatis.util.Restrictions;
import com.linkage.util.MybatisUtil;
import com.linkage.util.StringUtils;

/**
 * 校友组织接口说明：
 * 用户所属班级和校友会接口查询：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/get
 * (修改* 认证)班级详细信息接口查询：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/classinfo/12323?class_admin=0&class_role_state=1
 * (修改* 认证)校友会详情接口查询：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/aluassociationinfo/12323?aluassociation_admin=0&aluassociation_role_state=1
 * (修改* 认证)校友会查看用户名片信息：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/userinfo/122
 * (修改* 消息推送)校班管理员同意以及拒绝创建班级接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/createclass/122?type=xxx&create_user_id=xxx
 * (修改* 消息推送)校班管理员或者班级管理员同意拒绝用户加入接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/applyclassinfo/122?type=xxx&common_user_id=xxx
 * (修改* 消息推送)校班管理员或者校友会管理员同意拒绝用户加入接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/applyaluassociation/122?type=xxx&common_user_id=xxx
 * 查找班级接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/searchclass?year=2005&class_degree=1&college_id=2
 * 创建班级接口(POST)：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/newclass?year=2005&class_degree=1&college_id=2&class_name=xxxx
 * 普通用户申请加入班级接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/joinclass/1222
 * 校班管理员或者班级管理员设置或者剔除班级成员接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/rolesetclass?class_id=xxx&user_id&type=xxx
 * 校友会类型接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/typeinfo
 * 根据校友会类型获取校友会信息：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/getinfobytype/{type_id}
 * 普通用户申请加入校友会接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/joinaluassociation/1222
 * 校班管理员或者校友会管理员设置或者剔除校友会成员接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/rolesetaluassociation?aluassociation_id=xxx&user_id&type=xxx
 * (新增) 班级创建被拒绝后, 里面就一个按钮： 我知道了， 点了后，删除该信息:http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/deleteclass/122
 * (新增) 班级审批信息接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/approvalclass/1222
 * (新增) 获取班主任列表接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/teacherlist/2005
 * (新增) 用户放弃班级申请接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/giveupclass/122
 * (新增) 用户放弃校友会申请接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/giveupalu/122
 * (新增) 校友会管理员编辑校友会简介接口(POST)：http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/updatedesc
 * (新增) 用户举报接口(POST)： http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/report
 * (新增) 记录用户召唤分享(GET):http://127.0.0.1:7080/StudentsContacts/contactsapi/aluassociation/classcallshare?class_id=xxx&share_type=xx
 * 
 * @author wangjie
 *
 */
@Controller
@RequestMapping(value="/aluassociation")
public class AluassociationController
{
	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private UserInfoBO userInfoBO;
	
	@Autowired
	private ClassInfoBO classInfoBO;
	
	@Autowired
	private ClassRoleInfoBO classRoleInfoBO;
	
	@Autowired
	private AluassociationRoleInfoBO aluassociationRoleInfoBO;
	
	@Autowired
	private AluassociationConfigBO aluassociationConfigBO;
	
	@Autowired
	private ReportInfoBO reportInfoBO;
	
	@Autowired
	private ClassCallShareBO classCallShareBO;
	
	@Value("${switch_of_validaiton}")
	private String switch_of_validaiton;
	
	@ModelAttribute
	public void initModelAttribute(@RequestHeader(AuthorizationConfig.HEADER_AUTHORIZATION) String authorization, Model model)
	{
		UserInfo userInfo = userInfoBO.selectByTokenAnthorization(authorization);
		model.addAttribute("userInfo", userInfo);
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> getAluassocationInfoByUser(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "get", userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("super_admin", userInfo.getSuper_admin());
		//获取班级创建信息
		map.put("createclassinfo", classInfoBO.getCreateClassInfo(userInfo));
		//获取用户的班级信息
		map.put("classinfo", classInfoBO.getClassInfosByUser(userInfo));
		//获取用户校友会信息
		map.put("aluassociationinfo", aluassociationConfigBO.getAluInfosByUser(userInfo));
		return map;
	}
	
	@RequestMapping(value = "/classinfo/{class_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> getClassInfoByUser(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,
	      @PathVariable("class_id") int class_id/*,@RequestParam("class_admin") int class_admin,@RequestParam("class_role_state") int class_role_state*/)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "classinfo", userInfo.getAccess_token());
		return classInfoBO.getClassInfoByUser(userInfo, class_id);
	}
	
	@RequestMapping(value = "/aluassociationinfo/{aluassociation_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> getAluassociationInfoByUser(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,
	      @PathVariable("aluassociation_id") int aluassociation_id/*,@RequestParam("aluassociation_admin") int aluassociation_admin,@RequestParam("aluassociation_role_state") int aluassociation_role_state*/)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "aluassociationinfo", userInfo.getAccess_token());
		return classInfoBO.getAluassociationInfoByUser(userInfo, aluassociation_id);
	}
	
	@RequestMapping(value = "/userinfo/{store_user_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> getUserInfoById(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@PathVariable("store_user_id") int store_user_id)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "userinfo", userInfo.getAccess_token());
		return userInfoBO.getUserInfoById(userInfo.getUser_id(),store_user_id);
	}
	
	
	//同意或者拒绝创建班级修改
	@RequestMapping(value = "/createclass", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> createclass(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestBody FormCreateClassInfo formCreateClassInfo)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "createclass", userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		boolean result = classInfoBO.createClassinfo(userInfo,formCreateClassInfo);
		if(result)
		{
			map.put("result", 1);
			if(1==formCreateClassInfo.getType())
				map.put("message", "同意创建班级成功。");
			else 
				map.put("message", "拒绝创建班级成功。");
		}
		else
		{
			map.put("result", 0);
			map.put("message", "操作失败！");
		}
		return map;
	}
	
	@RequestMapping(value = "/deleteclass/{class_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> deleteclass(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@PathVariable("class_id") int class_id)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "deleteclass", userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		boolean result = classInfoBO.deleteClassinfo(userInfo,class_id,userInfo.getUniversity_id());
		if(result)
		{
			map.put("result", 1);
			map.put("message", "删除班级成功。");
		}
		else
		{
			map.put("result", 0);
			map.put("message", "删除班级失败！");
		}
		return map;
	}
	
	
	@RequestMapping(value = "/applyclassinfo/{class_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> applyclass(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@PathVariable("class_id") int class_id,@RequestParam("type") int type,@RequestParam("common_user_id") int user_id)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "applyclassinfo", userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		boolean result = classInfoBO.applyClassinfo(userInfo,class_id,type,user_id);
		if(result)
		{
			map.put("result", 1);
			if(1==type)
				map.put("message", "同意该用户申请加入班级成功。");
			else 
				map.put("message", "拒绝该用户申请加入班级成功。");
		}
		else
		{
			map.put("result", 0);
			map.put("message", "操作失败！");
		}
		return map;
	}
	
	@RequestMapping(value = "/applyaluassociation/{aluassociation_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> applyaluassociation(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@PathVariable("aluassociation_id") int aluassociation_id,@RequestParam("type") int type,@RequestParam("common_user_id") int user_id)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "applyaluassociation", userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		boolean result = classInfoBO.applyApplyaluassociationInfo(userInfo,aluassociation_id,type,user_id);
		if(result)
		{
			map.put("result", 1);
			if(1==type)
				map.put("message", "同意该用户申请加入校友会成功。");
			else 
				map.put("message", "拒绝该用户申请加入校友会成功。");
		}
		else
		{
			map.put("result", 0);
			map.put("message", "操作失败！");
		}
		return map;
	}
	
	@RequestMapping(value = "/searchclass", method = RequestMethod.GET)
	@ResponseBody
	public List<HashMap<String, Object>> searchClassInfo(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("year") int year,@RequestParam("class_degree") int class_degree,@RequestParam("college_id") int college_id)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "searchclass", userInfo.getAccess_token());
		return classInfoBO.searchClassInfo(userInfo.getUser_id(),userInfo.getUniversity_id(),year,class_degree,college_id);
	}
	
	
	@RequestMapping(value = "/newclass", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> newClassInfo(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestBody ClassInfo classInfo)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "newclass", userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		classInfo.setUser_id(userInfo.getUser_id());
		classInfo.setUniversity_id(userInfo.getUniversity_id());
		classInfo.setClass_state(0);
		classInfo.setStudent_num(0);
		try
		{
			classInfoBO.saveClassInfo(userInfo,classInfo);
			map.put("result", "1");
			map.put("message", "班级创建成功,正在等待管理员的审核...");
		}
		catch(Exception e)
		{
			log.info(e.getMessage(),e);
			map.put("result", "0");
			map.put("message", "班级创建失败！");
		}
		return map;
	}
	
	//获取待审核班级信息
	@RequestMapping(value = "/approvalclass/{class_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> getApprovalclassInfo(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@PathVariable("class_id") int class_id)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "approvalclass", userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		//获取班级创建者信息
		map.put("userinfo", classInfoBO.getCreteUserInfo(userInfo,class_id));
		//获取班级信息
		map.put("classinfo", classInfoBO.getClassInfo(userInfo,class_id));
		return map;
	}
	
	//对应学级下的班主任信息
	@RequestMapping(value = "/teacherlist/{year}",method = RequestMethod.GET)
	@ResponseBody
	public List<HashMap<String, Object>> getTeacherList(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@PathVariable("year") int year)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "teacherlist", userInfo.getAccess_token());
		String sql = "select degree_name,college_name,teacher from ustc_teacher";
		CriteriaManager cri = new CriteriaManager();
	  cri.or().add(Restrictions.equalTo("begin_year", year));
		List<HashMap<String, Object>> teacherinfos = commonMapper.getTableRowByWhereCondition(sql, cri);
		teacherinfos = MybatisUtil.converColumnNullList(teacherinfos, new String[]{"degree_name","college_name","teacher"});
		return teacherinfos;
	}
	
	
	@RequestMapping(value = "/joinclass/{class_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> joinClassInfo(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@PathVariable("class_id") int class_id)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "joinclass", userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		try
		{
			classRoleInfoBO.saveClassRoleInfo(userInfo,class_id);
			map.put("result", "1");
			map.put("message", "用户申请加入班级成功。");
		}
		catch(Exception e)
		{
			log.info(e.getMessage(),e);
			map.put("result", "0");
			map.put("message", "用户申请加入班级失败！");
		}
		return map;
	}
	
	@RequestMapping(value = "/rolesetclass", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> roleSetClass(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("class_id") int class_id,@RequestParam("user_id") int user_id,@RequestParam("type") int type)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "rolesetclass", userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		boolean result = classRoleInfoBO.roleSetClass(class_id,user_id,type);
		if(result)
		{
			map.put("result", 1);
			if(1==type)
				map.put("message", "设置班级管理员成功。");
			else 
				map.put("message", "剔除该用户成功。");
		}
		else
		{
			map.put("result", 0);
			map.put("message", "操作失败！");
		}
		return map;
	}
	
	@RequestMapping(value = "/typeinfo", method = RequestMethod.GET)
	@ResponseBody
	public List<HashMap<String, Object>> getTypeInfo(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "typeinfo", userInfo.getAccess_token());
		return aluassociationConfigBO.getTypeInfo();
	}
	
	@RequestMapping(value = "/getinfobytype/{type_id}", method = RequestMethod.GET)
	@ResponseBody
	public List<HashMap<String, Object>> getInfoBytype(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@PathVariable("type_id") int type_id)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "getinfobytype", userInfo.getAccess_token());
		return aluassociationConfigBO.getInfoByType(userInfo.getUser_id(),userInfo.getUniversity_id(),type_id);
	}
	
	@RequestMapping(value = "/joinaluassociation/{aluassociation_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> joinAluassociation(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@PathVariable("aluassociation_id") int aluassociation_id)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "joinaluassociation", userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		try
		{
			//TODO WJ 申请加入校友会的时候， 要求行业，公司，职位 信息不能为空，否则报错
			if(StringUtils.isBlank(userInfo.getIndustry()))
			{
				map.put("result", "0");
				map.put("message", "用户行业不能为空。");
			}
			else if(StringUtils.isBlank(userInfo.getCompanyname()))
			{
				map.put("result", "0");
				map.put("message", "用户公司不能为空。");
			}
			else if(StringUtils.isBlank(userInfo.getCompany_position()))
			{
				map.put("result", "0");
				map.put("message", "用户职位不能为空。");
			}
			else
			{
				aluassociationRoleInfoBO.saveAluassociationInfo(userInfo,aluassociation_id);
				map.put("result", "1");
				map.put("message", "用户申请加入校友会成功。");
			}
		}
		catch(Exception e)
		{
			log.info(e.getMessage(),e);
			map.put("result", "0");
			map.put("message", "用户申请加入校友会失败！");
		}
		return map;
	}
	
	@RequestMapping(value = "/rolesetaluassociation", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> roleSetAluassociation(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("aluassociation_id") int aluassociation_id,@RequestParam("user_id") int user_id,@RequestParam("type") int type)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "rolesetaluassociation", userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		boolean result = aluassociationRoleInfoBO.roleSetAluassociation(aluassociation_id,user_id,type);
		if(result)
		{
			map.put("result", 1);
			if(1==type)
				map.put("message", "设置校友会管理员成功。");
			else 
				map.put("message", "剔除该用户成功。");
		}
		else
		{
			map.put("result", 0);
			map.put("message", "操作失败！");
		}
		return map;
	}
	
	@RequestMapping(value = "/giveupclass/{class_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> giveupclass(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@PathVariable("class_id") int class_id)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "giveupclass", userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		boolean result = classRoleInfoBO.deleteUserClass(userInfo.getUser_id(), class_id);
		if(result)
		{
			map.put("result", 1);
			map.put("message", "用户放弃申请加入班级成功。");
		}
		else
		{
			map.put("result", 0);
			map.put("message", "用户放弃申请加入班级失败！");
		}
		return map;
	}
	
	@RequestMapping(value = "/giveupalu/{aluassociation_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> giveupalu(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@PathVariable("aluassociation_id") int aluassociation_id)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "giveupalu", userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		boolean result = aluassociationRoleInfoBO.deleteByUserAlu(userInfo.getUser_id(), aluassociation_id);
		if(result)
		{
			map.put("result", 1);
			map.put("message", "用户放弃申请加入校友会成功。");
		}
		else
		{
			map.put("result", 0);
			map.put("message", "用户放弃申请加入校友会失败！");
		}
		return map;
	}
	
	
	//校友会管理员修改校友会简介
	@RequestMapping(value = "/updatedesc", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> updateDesc(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestBody FormAluDesc formAluDesc)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "updatedesc", userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		String sql = "update aluassociation_config set aluassociation_desc='"+formAluDesc.getAluassociation_desc()+"' where aluassociation_id="+formAluDesc.getAluassociation_id();
		int count = commonMapper.updateDataBySql(sql);
		if(count>0)
		{
			map.put("result", 1);
			map.put("message", "校友会简介更新成功。");
		}
		else
		{
			map.put("result", 0);
			map.put("message", "校友会简介更新失败！");
		}
		return map;
	}
	
	//用户举报接口
	@RequestMapping(value = "/report", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> reportUserInfo(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestBody FormReportInfo formReportInfo)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "report", userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		try
		{
			reportInfoBO.saveReportInfo(userInfo,formReportInfo);
			map.put("result", 1);
			map.put("message", "用户举报成功。");
		}
		catch(Exception e)
		{
			log.info(e.getMessage(),e);
			map.put("result", 0);
			map.put("message", "用户举报失败。");
			
		}
		return map;
	}
	
//用户举报接口
	@RequestMapping(value = "/classcallshare", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> classCallShare(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,FormClassCallShare formClassCallShare)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "classcallshare", userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		try
		{
			classCallShareBO.saveShareInfo(userInfo,formClassCallShare);
			map.put("result", 1);
			map.put("message", "用户召唤分享成功。");
		}
		catch(Exception e)
		{
			log.info(e.getMessage(),e);
			map.put("result", 0);
			map.put("message", "用户召唤分享失败。");
		}
		return map;
	}
	
}
