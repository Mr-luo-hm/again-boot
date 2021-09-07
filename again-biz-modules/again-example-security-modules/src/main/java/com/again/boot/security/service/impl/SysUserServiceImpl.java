package com.again.boot.security.service.impl;

import com.again.boot.security.entity.SysUser;
import com.again.boot.security.mapper.SysUserMapper;
import com.again.boot.security.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	private final SysUserMapper sysUserMapper;

	@Override
	public SysUser selectByName(String userName) {
		return sysUserMapper.selectByName(userName);
	}

}
