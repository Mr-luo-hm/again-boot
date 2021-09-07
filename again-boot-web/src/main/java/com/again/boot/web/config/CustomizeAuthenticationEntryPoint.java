package com.again.boot.web.config;

import com.again.boot.security.utils.JsonResult;
import com.again.boot.security.utils.ResultCode;
import com.again.boot.security.utils.ResultTool;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description: 匿名用户访问无权限资源时的异常
 */

@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {
		JsonResult result = ResultTool.fail(ResultCode.USER_NOT_LOGIN);
		httpServletResponse.setContentType("text/json;charset=utf-8");
		httpServletResponse.getWriter().write(JSON.toJSONString(result));
	}

}
