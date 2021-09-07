package com.again.boot.web.config;

import com.again.boot.security.utils.JsonResult;
import com.again.boot.security.utils.ResultTool;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description: 登出成功处理逻辑
 */

@Component
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {
		JsonResult result = ResultTool.success();
		httpServletResponse.setContentType("text/json;charset=utf-8");
		httpServletResponse.getWriter().write(JSON.toJSONString(result));
	}

}
