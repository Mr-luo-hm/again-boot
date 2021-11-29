package com.again.boot.starter.pv.config;

import com.again.boot.starter.pv.filter.PvFilter;
import com.again.boot.starter.pv.handler.PvLogHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author create by 罗英杰 on 2021/11/24
 * @description:
 */
@Slf4j
@ConditionalOnWebApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(PvProperties.class)
public class PvConfiguration {

	private final PvProperties pvProperties;

	private final PvLogHandler<?> pvLogHandler;

	@Bean
	@ConditionalOnClass(PvLogHandler.class)
	public FilterRegistrationBean<PvFilter> pvFilterFilterRegistrationBean() {
		log.info("pv log 记录拦截器开启====");
		FilterRegistrationBean<PvFilter> pvFilterFilterRegistrationBean = new FilterRegistrationBean<>(
				new PvFilter(pvLogHandler, pvProperties.getIgnoreUrlPatterns()));
		pvFilterFilterRegistrationBean.setOrder(-10);
		return pvFilterFilterRegistrationBean;
	}

}
