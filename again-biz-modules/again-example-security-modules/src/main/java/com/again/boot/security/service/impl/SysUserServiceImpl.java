package com.again.boot.security.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.again.boot.security.model.dto.UserInfoDTO;
import com.again.boot.security.model.entity.SysRole;
import com.again.boot.security.model.entity.SysUser;
import com.again.boot.security.mapper.SysUserMapper;
import com.again.boot.security.model.vo.PermissionVO;
import com.again.boot.security.service.SysPermissionService;
import com.again.boot.security.service.SysUserRoleService;
import com.again.boot.security.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	private final SysUserMapper sysUserMapper;

	private final SysPermissionService sysPermissionService;

	private final SysUserRoleService sysUserRoleService;

	@Override
	public SysUser selectByName(String userName) {
		return sysUserMapper.selectByName(userName);
	}

	/**
	 * 通过查用户的全部信息
	 * @param sysUser 用户
	 * @return 用户信息
	 */
	@Override
	public UserInfoDTO findUserInfo(SysUser sysUser) {
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO.setSysUser(sysUser);
		// 设置角色列表 （ID）
		List<SysRole> roleList = sysUserRoleService.getRoles(sysUser.getId());
		List<Integer> roleIds = new ArrayList<>();
		List<String> roles = new ArrayList<>();
		for (SysRole role : roleList) {
			roleIds.add(role.getId());
			roles.add(role.getCode());
		}
		userInfoDTO.setRoles(roles);
		userInfoDTO.setRoleIds(roleIds);
		// 设置权限列表（permission）
		Set<String> permissions = new HashSet<>();
		roles.forEach(code -> {
			List<String> permissionList = sysPermissionService.findPermissionVOsByRoleCode(code).stream()
					.filter(sysPermission -> StrUtil.isNotEmpty(sysPermission.getCode())).map(PermissionVO::getCode)
					.collect(Collectors.toList());
			permissions.addAll(permissionList);
		});
		userInfoDTO.setPermissions(new ArrayList<>(permissions));
		return userInfoDTO;
	}

}
