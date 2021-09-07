package com.again.boot.security.service;

import com.again.boot.security.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
public interface SysUserService extends IService<SysUser> {

	SysUser selectByName(String userName);

}
