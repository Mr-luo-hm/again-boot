package com.again.boot.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.again.boot.security.constants.UserResourceConstant;
import com.again.boot.security.model.dto.UserInfoDTO;
import com.again.boot.security.model.entity.SysUser;
import com.again.boot.security.service.SysUserService;
import com.again.boot.security.utils.SysUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private SysUserService sysUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username == null || "".equals(username)) {
			throw new InternalAuthenticationServiceException("用户不能为空");
		}
		// 根据用户名查询用户
		SysUser sysUser = sysUserService.selectByName(username);
		if (sysUser == null) {
			throw new InternalAuthenticationServiceException("用户不存在");
		}
		UserInfoDTO userInfoDTO = sysUserService.findUserInfo(sysUser);
		return getUserDetailsByUserInfo(userInfoDTO);

	}

	/**
	 * 根据UserInfo 获取 UserDetails
	 * @param userInfoDTO 用户信息DTO
	 * @return UserDetails
	 */
	private UserDetails getUserDetailsByUserInfo(UserInfoDTO userInfoDTO) {

		SysUser sysUser = userInfoDTO.getSysUser();
		List<String> roles = userInfoDTO.getRoles();
		List<String> permissions = userInfoDTO.getPermissions();
		Set<String> dbAuthsSet = new HashSet<>();
		if (CollUtil.isNotEmpty(roles)) {
			// 获取角色
			dbAuthsSet.addAll(roles);
			// 获取资源
			dbAuthsSet.addAll(permissions);

		}
		Collection<? extends GrantedAuthority> authorities = AuthorityUtils
				.createAuthorityList(dbAuthsSet.toArray(new String[0]));

		// 用户资源，角色和权限
		Map<String, Collection<?>> userResources = new HashMap<>();
		userResources.put(UserResourceConstant.RESOURCE_ROLE, roles);
		userResources.put(UserResourceConstant.RESOURCE_PERMISSION, permissions);
		Map<String, Object> userAttributes = new HashMap<>(12);
		return new SysUserDetails(sysUser, authorities, userResources, userAttributes);
	}

}