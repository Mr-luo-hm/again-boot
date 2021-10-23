package com.again.example.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author create by 罗英杰 on 2021/10/20
 * @description:
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 查询数据库的方法,要求我们返回一个用户对象
	 * @return
	 */

	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return username -> {
			ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			return new User("admin", passwordEncoder.encode("a123456"), authorities);
		};

	}

	/**
	 * 安全的配置
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()// 验证所有的请求
				.anyRequest().authenticated()// 没有单独指定的地址只需要登录就可以
				.and()//
				.formLogin()// 自定义登录框
				.loginProcessingUrl("/login")// Spring Security会拦截这个地址,这个地址会交给Security 来处理
				.usernameParameter("username")// 登录表单中用户名的参数名叫什么,Security会获取这个参数然后帮我们查询用户
				.passwordParameter("password")// 登录中密码的参数名
				.successHandler((httpServletRequest, httpServletResponse, authentication) -> {
					Instant instant = Instant.now();// 现在的时间,代码执行时候的时间
					String jwtString = Jwts.builder().signWith(SignatureAlgorithm.HS256, "secret".getBytes())// 设置签名的方式和盐
							.setIssuedAt(Date.from(instant))// 指定令牌生效的时间,也就是说可以不是生成的时候立刻生效
							.setExpiration(Date.from(instant.plusSeconds(12000))).compact();// 什么时候过期,以instant的时间为基准,向后延迟3600秒,注意这个东西会有时区问题,发令牌的服务器和校验的服务器必须是用相同的时区或者要进行转换
					// 返回给客户端,我们约定是通过响应头返回

					httpServletResponse.addHeader("secret", jwtString);
				})//
				.permitAll()// 放行登录相关的信息,就是上面的配置
				.and()//
				.logout()//
				.logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> httpServletResponse
						.getWriter().write("退出成功"))
				.permitAll()//
				.and().httpBasic().and().csrf().disable();

	}

	/**
	 * Spring Security 不允许数据库中使用明文密码,必须使用校验值,所以必须添加密码的编解码器
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
