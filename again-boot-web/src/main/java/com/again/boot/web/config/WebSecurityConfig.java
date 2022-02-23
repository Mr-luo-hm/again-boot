package com.again.boot.web.config;

import com.again.boot.web.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 登录成功处理逻辑
	 */
	@Autowired
	CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;

	/**
	 * 登录失败处理逻辑
	 */

	@Autowired
	CustomizeAuthenticationFailureHandler authenticationFailureHandler;

	/**
	 * 登出成功处理逻辑
	 */
	@Autowired
	CustomizeLogoutSuccessHandler logoutSuccessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginProcessingUrl("/auth/login")
				// 自定义的登录验证成功或失败后的去向
				.successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
				// 禁用csrf防御机制(跨域请求伪造)，这么做在测试和开发会比较方便。
				.and().csrf().disable();
	}

	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		// 获取用户账号密码及权限信息
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		// 设置默认的加密方式（强hash方式加密）
		return new BCryptPasswordEncoder();
	}

}
