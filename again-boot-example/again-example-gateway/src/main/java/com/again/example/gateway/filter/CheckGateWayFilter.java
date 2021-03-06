package com.again.example.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author create by 罗英杰 on 2021/9/10
 * @description: 局部过滤器 token 过滤器 保护一些需要验证的服务
 */
@Slf4j
@Component
public class CheckGateWayFilter implements GatewayFilter, Ordered {

	private final static String SECRET = "secret";

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String url = exchange.getRequest().getPath().pathWithinApplication().value();
		log.info("请求URL:" + url);
		log.info("method:" + exchange.getRequest().getMethod());
		String secret = exchange.getRequest().getHeaders().getFirst(SECRET);
		if (secret == null) {
			log.info("secret id null,验证失败");
			exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
			return exchange.getResponse().setComplete();
		}
		else {
			return chain.filter(exchange);
		}
	}

	@Override
	public int getOrder() {
		return HIGHEST_PRECEDENCE;
	}

}
