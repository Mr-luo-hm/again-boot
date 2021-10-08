package com.again.boot.security.service;

import com.again.boot.security.model.dto.UserInfoDTO;
import com.again.boot.security.model.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
public interface SysUserService extends IService<SysUser> {

	SysUser selectByName(String userName);

	/**
	 * 获取用户详情信息
	 * @param user SysUser
	 * @return UserInfoDTO
	 */
	UserInfoDTO findUserInfo(SysUser user);

}
