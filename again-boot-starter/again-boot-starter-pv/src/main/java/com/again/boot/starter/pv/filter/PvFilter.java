package com.again.boot.starter.pv.filter;

import com.again.boot.starter.pv.event.OperationPvLogEvent;
import com.again.boot.starter.pv.handler.PvLogHandler;
import com.again.boot.starter.pv.model.PvDTO;
import com.again.boot.starter.pv.service.OperationPvHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author create by 罗英杰 on 2021/11/24
 * @description:
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class PvFilter extends OncePerRequestFilter {

	private final PvLogHandler<?> pvLogHandler;

	private final List<String> ignoreUrlPatterns;

	@Autowired
	ApplicationEventPublisher publisher;

	/**
	 * 针对需忽略的Url的规则匹配器
	 */
	private final static AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException {
		try {
			// 跳过部分忽略 url
			String requestUri = request.getRequestURI();
			for (String ignoreUrlPattern : ignoreUrlPatterns) {
				if (ANT_PATH_MATCHER.match(ignoreUrlPattern, requestUri)) {
					filterChain.doFilter(request, response);
					return;
				}
			}
			filterChain.doFilter(request, response);
		}
		catch (Exception e) {
			log.error("PV collect error, err msg:{}", e.getMessage());
		}
		finally {
			// 生产一个日志并记录
			try {
				pvLogHandler.logRecord(request, response);
				PvDTO operationPvHandler = new PvDTO();
				publisher.publishEvent(new OperationPvLogEvent(operationPvHandler));
			}
			catch (Exception e) {
				logger.error("logging access_log error!", e);
			}
		}
	}

}
