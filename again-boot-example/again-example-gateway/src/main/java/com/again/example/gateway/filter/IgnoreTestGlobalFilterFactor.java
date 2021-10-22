package com.again.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author create by 罗英杰 on 2021/10/22
 * @description:
 */
@Component
public class IgnoreTestGlobalFilterFactor extends AbstractGatewayFilterFactory<IgnoreTestGlobalFilterFactor.Config> {

	public IgnoreTestGlobalFilterFactor() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return this::filter;
	}

	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		exchange.getAttributes().put(VerifyGlobalFilter.ATTRIBUTE_IGNORE_TEST_GLOBAL_FILTER, true);
		return chain.filter(exchange);
	}

	public static class Config {

	}

	@Override
	public String name() {
		return "IgnoreTestGlobalFilter";
	}

}
