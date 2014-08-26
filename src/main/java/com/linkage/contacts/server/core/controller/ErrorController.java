package com.linkage.contacts.server.core.controller;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.linkage.contacts.server.spring.mvc.custom.resolver.RequestAttribute;

@Controller
@RequestMapping(value="/error",method = RequestMethod.GET)
public class ErrorController {
	
	
    @RequestMapping(value = "/notauthorization")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> notAuthorization(@RequestAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE) int statusCode,@RequestAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE) String message) {
   
    	//设置消息头
    	HttpHeaders responseHeaders = new HttpHeaders();
    	responseHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("result", -1);
    	map.put("message", message);
       return new ResponseEntity<Map<String,Object>>(map, responseHeaders, jugeStatusCode(statusCode));
    }
    
    @RequestMapping(value = "/innerexception")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> innerServerException(@RequestAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE) int statusCode,@RequestAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE) String message)
    {
    //设置消息头
    	HttpHeaders responseHeaders = new HttpHeaders();
    	responseHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("result", -1);
    	map.put("message", message);
       return new ResponseEntity<Map<String,Object>>(map, responseHeaders, jugeStatusCode(statusCode));
    }
    
    /**
     * 返回状态码
     * @param statusCode 状态码
     * @return
     */
    public HttpStatus jugeStatusCode(int statusCode)
    {
    	switch(statusCode)
    	{
    	case 200: return HttpStatus.OK;
    	case 401: return HttpStatus.UNAUTHORIZED;
    	case 404: return HttpStatus.NOT_FOUND;
    	case 500: return HttpStatus.INTERNAL_SERVER_ERROR;
    	
    	default :
    		 return HttpStatus.OK;
    	}
    }
}
