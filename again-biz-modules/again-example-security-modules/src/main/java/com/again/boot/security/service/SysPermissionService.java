package com.again.boot.security.service;

import com.again.boot.security.model.entity.SysPermission;
import com.again.boot.security.model.vo.PermissionVO;
import com.baomidou.mybatisplus.extension.service.IService;
import sun.nio.ch.ThreadPool;

import java.util.List;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
public interface SysPermissionService extends IService<SysPermission> {

	/**
	 * 通过角色编号查询URL 权限
	 * @param roleCode 角色Code
	 * @return 菜单列表
	 */
	List<PermissionVO> findPermissionVOsByRoleCode(String roleCode);

}
