package com.again.boot.web.config;

import com.again.boot.security.utils.JsonResult;
import com.again.boot.security.utils.ResultCode;
import com.again.boot.security.utils.ResultTool;
import com.alibaba.fastjson.JSON;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description: 会话信息过期策略
 */

@Component
public class CustomizeSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent)
			throws IOException {
		JsonResult result = ResultTool.fail(ResultCode.USER_ACCOUNT_USE_BY_OTHERS);
		HttpServletResponse httpServletResponse = sessionInformationExpiredEvent.getResponse();
		httpServletResponse.setContentType("text/json;charset=utf-8");
		httpServletResponse.getWriter().write(JSON.toJSONString(result));
	}

}
