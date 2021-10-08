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
@TableName("sys_user_role")
@ApiModel(value = "用户角色")
@EqualsAndHashCode(callSuper = true)
public class SysUserRole extends Model<SysUserRole> {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户id")
	private Integer userId;

	/**
	 * 角色Code
	 */
	@ApiModelProperty(value = "角色Code")
	private String roleCode;

}
