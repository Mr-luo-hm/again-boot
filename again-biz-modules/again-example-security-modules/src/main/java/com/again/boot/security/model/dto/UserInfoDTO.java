package com.again.boot.security.model.dto;

import com.again.boot.security.model.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author create by 罗英杰 on 2021/9/26
 * @description:
 */
@Data
@ApiModel(value = "用户信息")
public class UserInfoDTO implements Serializable {

	/**
	 * 用户基本信息
	 */
	@ApiModelProperty(value = "用户基本信息")
	private SysUser sysUser;

	/**
	 * 权限标识集合
	 */
	@ApiModelProperty(value = "权限标识集合")
	private List<String> permissions;

	/**
	 * 角色集合
	 */
	@ApiModelProperty(value = "角色标识集合")
	private List<String> roles;

	/**
	 * 角色ID集合
	 */
	@ApiModelProperty(value = "角色Id集合")
	private List<Integer> roleIds;

}
