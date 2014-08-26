package com.linkage.contacts.server.spring.mvc.custom.intercepor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.linkage.contacts.server.dao.UserInfoDAO;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.util.AuthorizationConfig;
/**
 * <p>springMVC拦截器对request进行拦截</p>
 * <p>对request请求在调用控制器之前进行token验证,验证通过则继续调用控制器,验证不通过则返回错误信息</p>
 * @author wangjie
 * @version 1.0
 * @create time 2013-07-30 11:30
 *
 */
public class AuthorizationValidationIntercepor extends HandlerInterceptorAdapter
{
	private static final Log log = LogFactory.getLog(AuthorizationValidationIntercepor.class);
	
	@Autowired
	@Qualifier("mybatisUserInfoDao")
	private UserInfoDAO userInfoDAO;

	/**
	 * preHandle
		/CpmisServer/cpmisapi/hngchome
		/CpmisServer
		/cpmisapi
	 */
	@Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
  {
		log.info("preHandle begin....");
		String authorization = request.getHeader(AuthorizationConfig.HEADER_AUTHORIZATION);
		//Token验证
	 if(null!=authorization && authorization.indexOf(AuthorizationConfig.TOKENPRE)==0)
	 {
		//获取加密字符串
     String tokenAuthor = authorization.substring(AuthorizationConfig.TOKENPRE.length());
     UserInfo userInfo = userInfoDAO.selectByTokenid(tokenAuthor);
     if(null!=userInfo && userInfo.getUser_id()>0)
     {
     	log.info("Token认证通过");
     	return true;
     }
     else
     {
    	 log.info("Token认证失败");
    	 //认证失败则转发到错误页面
    	 request.setAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, 401);
    	 request.setAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE, "Token认证失败");
    	 request.getRequestDispatcher(request.getContextPath()+request.getServletPath()+"/error/notauthorization").forward(request, response);
    	 return false;
     }
	 }
	 else
	 {
		 log.info("目前暂不支持其他认证");
		 request.setAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, 401);
  	 request.setAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE, "Token认证失败");
  	 request.getRequestDispatcher(request.getContextPath()+request.getServletPath()+"/error/notauthorization").forward(request, response);
		 return false;
	 }
  }

	@Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
  {
	  super.postHandle(request, response, handler, modelAndView);
  }

	@Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
  {
	  super.afterCompletion(request, response, handler, ex);
  }

}
