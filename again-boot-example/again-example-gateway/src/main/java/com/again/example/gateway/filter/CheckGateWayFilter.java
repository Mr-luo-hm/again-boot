package com.again.example.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author create by 罗英杰 on 2021/9/10
 * @description: 局部过滤器
 */
@Slf4j
@Component
public class CheckGateWayFilter implements GatewayFilter, Ordered {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String url = exchange.getRequest().getPath().pathWithinApplication().value();
		log.info("请求URL:" + url);
		log.info("method:" + exchange.getRequest().getMethod());
		/*
		 * String secret = exchange.getRequest().getHeaders().getFirst("secret"); if
		 * (StringUtils.isBlank(secret)) { return chain.filter(exchange); }
		 */
		// 获取param 请求参数
		String uname = exchange.getRequest().getQueryParams().getFirst("uname");
		// 获取header
		String userId = exchange.getRequest().getHeaders().getFirst("user-id");
		log.info("userId：" + userId);

		if (StringUtils.isBlank(userId)) {
			log.info("*****头部验证不通过，请在头部输入  user-id");
			// 终止请求，直接回应
			exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}

	// 值越小，优先级越高
	// int HIGHEST_PRECEDENCE = -2147483648;
	// int LOWEST_PRECEDENCE = 2147483647;
	@Override
	public int getOrder() {
		return HIGHEST_PRECEDENCE;
	}

}
