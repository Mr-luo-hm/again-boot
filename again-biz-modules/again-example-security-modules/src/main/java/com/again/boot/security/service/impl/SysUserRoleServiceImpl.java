package com.again.boot.security.service.impl;

import com.again.boot.security.mapper.SysUserRoleMapper;
import com.again.boot.security.model.entity.SysRole;
import com.again.boot.security.model.entity.SysUserRole;
import com.again.boot.security.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author create by 罗英杰 on 2021/9/26
 * @description:
 */
@Service
@RequiredArgsConstructor
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

	/**
	 * 通过用户ID 获取用户所有角色ID
	 * @param userId
	 * @return
	 */
	@Override
	public List<SysRole> getRoles(Integer userId) {
		return baseMapper.getRoles(userId);
	}

}
