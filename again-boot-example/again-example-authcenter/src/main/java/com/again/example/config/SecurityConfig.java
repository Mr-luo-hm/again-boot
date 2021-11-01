package com.again.example.config;

import com.again.example.config.util.JwtTokenFilter;
import com.again.example.config.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * @author create by 罗英杰 on 2021/10/20
 * @description:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

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
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/login")
				.permitAll().anyRequest().authenticated();
		http.logout().logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
			httpServletResponse.setCharacterEncoding(String.valueOf(Charset.defaultCharset()));
			httpServletResponse.getWriter().write("退出成功");
		});

		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		http.headers().cacheControl();
	}

	/**
	 * Spring Security 不允许数据库中使用明文密码,必须使用校验值,所以必须添加密码的编解码器
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtTokenFilter();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
