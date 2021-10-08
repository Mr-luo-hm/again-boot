package com.again.boot.security.mapper;

import com.again.boot.security.model.entity.SysPermission;
import com.again.boot.security.model.vo.PermissionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

	/**
	 * 通过角色ID查询权限
	 * @param roleCode 角色ID
	 * @return
	 */
	List<PermissionVO> listPermissionVOsByRoleCode(String roleCode);

}
