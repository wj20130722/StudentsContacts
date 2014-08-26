package com.linkage.contacts.server.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.linkage.contacts.server.bo.ValidationMailInfoBO;
import com.linkage.contacts.server.entity.ValidationMailInfo;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;

/**
 * 验证相关接口：
 * 
 * @author wangjie
 *
 */
@Controller
@RequestMapping(value="/validate")
public class ValidationController
{
	@Autowired
	private ValidationMailInfoBO validationMailInfoBO;
	
	//@Autowired
	//private UserInfoBO userInfoBO;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView validateUser(@RequestParam("key") String key,@RequestParam("from") String fromtoken)
	{
		ModelAndView mv = new ModelAndView("validate");
		ValidationMailInfo validationMailInfo = validationMailInfoBO.selectByKey(key);
		String message = "";
		if(null==validationMailInfo||validationMailInfo.getValidate_id()==0)
		{
			message = "验证key值不合法！";
		}
		else
		{
			if(validationMailInfo.getIs_validate()==1)
			{
				message = "该用户已经验证过,请勿重复验证！";
			}
			else
			{
				String sql = "update user_info set is_authentication=1 where access_token='"+fromtoken+"' and is_authentication=0";
				int count = commonMapper.updateDataBySql(sql);
				if(count==1)
				{
					message = "该用户已成功验证！";
					//更新验证标识和验证时间
					sql = "update validation_mail_info set validate_time=now(),is_validate=1 where validate_key='"+key+"'";
					commonMapper.updateDataBySql(sql);
				}
				else
				{
					message = "该用户验证失败！";
				}
			}
		}
		mv.addObject("message", message);
		return mv;
	}
}
