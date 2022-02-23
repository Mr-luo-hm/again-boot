package com.again.boot.web.config;

import com.again.boot.security.service.SysUserService;
import com.again.boot.security.utils.ResultTool;
import com.again.boot.security.utils.SysUserDetails;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description: 登录成功处理逻辑
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	SysUserService sysUserService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException {
		// 更新用户表上次登录时间、更新人、更新时间等字段
		SysUserDetails userDetails = (SysUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		// 现在的时间,代码执行时候的时间
		Instant instant = Instant.now();
		// 设置签名的方式和盐
		String jwtString = Jwts.builder().signWith(SignatureAlgorithm.HS256, userDetails.getUsername())
				// 存放的内容
				.claim("username", userDetails.getUsername()).claim("role", userDetails.getAuthorities())
				// 设置当前是谁
				.setSubject(authentication.getName())
				// 指定令牌生效的时间,也就是说可以不是生成的时候立刻生效
				.setIssuedAt(Date.from(instant))
				// 什么时候过期,以instant的时间为基准,向后延迟3600秒,注意这个东西会有时区问题,发令牌的服务器和校验的服务器必须是用相同的时区或者要进行转换
				// 返回给客户端,我们约定是通过响应头返回
				.setExpiration(Date.from(instant.plusSeconds(12000))).compact();
		httpServletResponse.addHeader("Authorization", jwtString);
		httpServletResponse.getWriter().write(JSON.toJSONString(ResultTool.success()));
	}

}
