package com.again.example.gateway.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author create by 罗英杰 on 2021/10/22
 * @description:
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class VerifyGlobalFilter implements GlobalFilter, Ordered {

	private final static String SECRET = "secret";

	public final static String ATTRIBUTE_IGNORE_TEST_GLOBAL_FILTER = "IgnoreTestGlobalFilter";

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		if (exchange.getAttribute(ATTRIBUTE_IGNORE_TEST_GLOBAL_FILTER) != null) {
			return chain.filter(exchange);
		}
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
		return 11;
	}

}
