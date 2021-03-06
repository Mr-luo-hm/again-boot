package com.again.example.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author create by 罗英杰 on 2021/9/10
 * @description: 定义全局过滤器
 */
@Configuration
@Slf4j
public class FilterConfig {

	@Bean
	@Order(-1)
	public GlobalFilter a() {
		return new AFilter();
	}

	@Bean
	@Order(0)
	public GlobalFilter b() {
		return new BFilter();
	}

	@Bean
	@Order(1)
	public GlobalFilter c() {
		return new CFilter();
	}

	public class AFilter implements GlobalFilter, Ordered {

		@Override
		public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
			log.info("AFilter前置逻辑");
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				log.info("AFilter后置逻辑");
			}));
		}

		// 值越小，优先级越高
		int HIGHEST_PRECEDENCE = -2147483648;

		int LOWEST_PRECEDENCE = 2147483647;

		@Override
		public int getOrder() {
			return HIGHEST_PRECEDENCE + 100;
		}

	}

	public class BFilter implements GlobalFilter, Ordered {

		@Override
		public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
			log.info("BFilter前置逻辑");
			return chain.filter(exchange).then(Mono.fromRunnable(() -> log.info("BFilter后置逻辑")));
		}

		// 值越小，优先级越高
		int HIGHEST_PRECEDENCE = -2147483648;

		int LOWEST_PRECEDENCE = 2147483647;

		@Override
		public int getOrder() {
			return HIGHEST_PRECEDENCE + 200;
		}

	}

	public class CFilter implements GlobalFilter, Ordered {

		@Override
		public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
			log.info("CFilter前置逻辑");
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				log.info("CFilter后置逻辑");
			}));
		}

		// 值越小，优先级越高
		int HIGHEST_PRECEDENCE = -2147483648;

		int LOWEST_PRECEDENCE = 2147483647;

		@Override
		public int getOrder() {
			return HIGHEST_PRECEDENCE + 300;
		}

	}

}
