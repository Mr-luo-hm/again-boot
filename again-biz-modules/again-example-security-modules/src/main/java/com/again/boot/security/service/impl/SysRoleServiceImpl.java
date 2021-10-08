package com.again.boot.security.service.impl;

import com.again.boot.security.mapper.SysRoleMapper;
import com.again.boot.security.model.entity.SysRole;
import com.again.boot.security.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author create by 罗英杰 on 2021/9/26
 * @description:
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

}
