package com.again.boot.security.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author create by 罗英杰 on 2021/9/26
 * @description:
 */
@Data
@TableName("sys_role_permission")
@ApiModel(value = "角色菜单")
@EqualsAndHashCode(callSuper = true)
public class SysRolePermission extends Model<SysRolePermission> {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 角色 Code
	 */
	@ApiModelProperty(value = "角色 Code")
	private String roleCode;

	/**
	 * 权限ID
	 */
	@ApiModelProperty(value = "菜单id")
	private Integer permissionId;

}
