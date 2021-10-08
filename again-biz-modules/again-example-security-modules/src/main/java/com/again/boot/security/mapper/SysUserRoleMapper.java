package com.again.boot.security.mapper;

import com.again.boot.security.model.entity.SysRole;
import com.again.boot.security.model.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author create by 罗英杰 on 2021/9/26
 * @description:
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

	/**
	 * 通过用户ID，查询角色s
	 * @param userId
	 * @return
	 */
	List<SysRole> getRoles(Integer userId);

}
