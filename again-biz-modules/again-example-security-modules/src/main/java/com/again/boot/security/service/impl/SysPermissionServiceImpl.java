package com.again.boot.security.service.impl;

import com.again.boot.security.entity.SysPermission;
import com.again.boot.security.mapper.SysPermissionMapper;
import com.again.boot.security.mapper.SysUserMapper;
import com.again.boot.security.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission>
		implements SysPermissionService {

	private final SysPermissionMapper sysPermissionMapper;

	@Override
	public List<SysPermission> selectListByUser(Integer userId) {
		return sysPermissionMapper.selectListByUser(userId);
	}

}
