package com.again.boot.security.service;

import com.again.boot.security.model.entity.SysRole;
import com.again.boot.security.model.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author create by 罗英杰 on 2021/9/26
 * @description:
 */
public interface SysUserRoleService extends IService<SysUserRole> {

	/**
	 * 通过用户ID，查询角色列表
	 * @param userId
	 * @return List<SysRole>
	 */
	List<SysRole> getRoles(Integer userId);

}
