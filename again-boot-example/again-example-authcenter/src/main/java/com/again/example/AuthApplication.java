package com.again.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author create by 罗英杰 on 2021/10/20
 * @description:
 */
@EnableEurekaClient
@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class);
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// 配置 可以访问的地址
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		// 你需要跨域的地址 注意这里的 127.0.0.1 != localhost

		// * 表示对所有的地址都可以访问
		corsConfiguration.addAllowedOrigin("*");
		// 表示只允许http://localhost:8080地址的访问（重点哦！！！！）
		// corsConfiguration.addAllowedOrigin("http://localhost:8080");

		// 跨域的请求头
		corsConfiguration.addAllowedHeader("*"); // 2
		// 跨域的请求方法
		corsConfiguration.addAllowedMethod("*"); // 3
		// 加上了这一句，大致意思是可以携带 cookie
		// 最终的结果是可以 在跨域请求的时候获取同一个 session
		corsConfiguration.setAllowCredentials(true);
		source.registerCorsConfiguration("/**", corsConfiguration); // 4
		return new CorsFilter(source);
	}

}
