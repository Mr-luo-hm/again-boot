package com.again.boot.security.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "SysUser表")
public class SysUser extends Model<SysUser> {

	private Integer id;

	private String account;

	private String userName;

	private String password;

	private LocalDateTime lastLoginTime;

	private Boolean enabled;

	private Boolean notExpired;

	private Boolean accountNotLocked;

	private Boolean credentialsNotExpired;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;

	private Boolean accountNotExpired;

	private Integer createUser;

	private Integer updateUser;

}
