package com.again.boot.web.config;

import com.again.boot.web.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// 登录成功处理逻辑
	@Autowired
	CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;

	// 登录失败处理逻辑
	@Autowired
	CustomizeAuthenticationFailureHandler authenticationFailureHandler;

	// 匿名用户访问无权限资源时的异常
	@Autowired
	CustomizeAuthenticationEntryPoint authenticationEntryPoint;

	// 会话失效(账号被挤下线)处理逻辑
	@Autowired
	CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;

	// 登出成功处理逻辑
	@Autowired
	CustomizeLogoutSuccessHandler logoutSuccessHandler;

	// 访问决策管理器
	@Autowired
	CustomizeAccessDecisionManager accessDecisionManager;

	// 实现权限拦截
	@Autowired
	CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource;

	@Autowired
	private CustomizeAbstractSecurityInterceptor securityInterceptor;

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
		/*
		 * http.cors().and().csrf().disable();
		 * http.authorizeRequests().withObjectPostProcessor(new
		 * ObjectPostProcessor<FilterSecurityInterceptor>() {
		 *
		 * @Override public <O extends FilterSecurityInterceptor> O postProcess(O o) {
		 * o.setAccessDecisionManager(accessDecisionManager);// 决策管理器
		 * o.setSecurityMetadataSource(securityMetadataSource);// 安全元数据源 return o; } }).
		 * // 登出 and().logout().permitAll().// 允许所有用户
		 * logoutSuccessHandler(logoutSuccessHandler).// 登出成功处理逻辑
		 * deleteCookies("JSESSIONID").// 登出之后删除cookie // 登入
		 * and().formLogin().permitAll().// 允许所有用户
		 * successHandler(authenticationSuccessHandler).// 登录成功处理逻辑
		 * failureHandler(authenticationFailureHandler).// 登录失败处理逻辑 // 异常处理(权限拒绝、登录失效等)
		 * and().exceptionHandling(). //
		 * accessDeniedHandler(accessDeniedHandler).//权限拒绝处理逻辑
		 * authenticationEntryPoint(authenticationEntryPoint).// 匿名用户访问无权限资源时的异常处理 // 会话管理
		 * and().sessionManagement().maximumSessions(1).// 同一账号同时登录最大用户数
		 * expiredSessionStrategy(sessionInformationExpiredStrategy);// 会话失效(账号被挤下线)处理逻辑
		 * http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);
		 */
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
