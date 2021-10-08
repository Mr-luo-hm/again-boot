package com.again.boot.security.constants;

/**
 * @author create by 罗英杰 on 2021/9/26
 * @description:
 */
public final class UserResourceConstant {

	private UserResourceConstant() {
	}

	/**
	 * 用户资源key：角色 </br>
	 * 角色资源项为角色标识(roleCode)
	 */
	public static final String RESOURCE_ROLE = "role";

	/**
	 * 用户资源key：权限 </br>
	 * 权限资源项为权限ID(permissionId)
	 */
	public static final String RESOURCE_PERMISSION = "permission";

	/**
	 * 用户数据权限
	 */
	public static final String RESOURCE_DATA_SCOPE = "dataScope";

	/**
	 * 全部
	 */
	public static final Integer RESOURCE_DATA_SCOPE_ONE_LEVEL = 1;

	/**
	 * 本部门以及以下部门
	 */
	public static final Integer RESOURCE_DATA_SCOPE_TWO_LEVEL = 2;

	/**
	 * 本部门
	 */
	public static final Integer RESOURCE_DATA_SCOPE_THREE_LEVEL = 3;

	/**
	 * 自己
	 */
	public static final Integer RESOURCE_DATA_SCOPE_FOUR_LEVEL = 4;

	/**
	 * 部门list
	 */
	public static final String RESOURCE_ORGANIZATION = "organizationList";

	/**
	 * 角色code 超级管理员
	 */
	public static final String ADMIN_CODE = "ROLE_ADMIN";

	/**
	 * 角色code 普通管理员
	 */
	public static final String ROLE_NORMAL_MANAGER = "ROLE_NORMAL_MANAGER";

	/**
	 * 角色code 普通坐席
	 */
	public static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";

}
