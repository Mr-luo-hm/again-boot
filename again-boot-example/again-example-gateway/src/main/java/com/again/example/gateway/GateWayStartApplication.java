package com.again.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author create by 罗英杰 on 2021/9/10
 * @description:
 */
@SpringBootApplication
@EnableEurekaClient
public class GateWayStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayStartApplication.class);
	}

	/**
	 * bean 配置得route
	 * @param builder RouteLocatorBuilder
	 * @return RouteLocator
	 */
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes().route("path_route", r -> r.path("/api").uri("https://www.baodu.com")).build();
	}

	/**
	 * 根据userid进行限流
	 * @return KeyResolver
	 */
	@Bean
	KeyResolver userKeyResolver() {
		return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getQueryParams().getFirst("user")));
	}

	/**
	 * 根据IP限流，
	 * @return KeyResolver
	 */
	@Bean
	@Primary
	public KeyResolver ipKeyResolver() {
		return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostName());
	}

	/**
	 * 根据接口的URI进行限流
	 * @return KeyResolver
	 */
	@Bean
	KeyResolver apiKeyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getPath().value());
	}

}
