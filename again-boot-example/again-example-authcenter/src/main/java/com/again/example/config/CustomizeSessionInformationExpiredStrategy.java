package com.again.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author create by 罗英杰 on 2021/10/30
 * @description: 会话信息过期策略
 */
@Component
@Slf4j
public class CustomizeSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent)
			throws IOException {
		log.info("session check start");
		HttpServletResponse httpServletResponse = sessionInformationExpiredEvent.getResponse();
		httpServletResponse.setContentType("text/json;charset=utf-8");
		httpServletResponse.getWriter().write("账号被挤下线");
	}

}
