package com.again.boot.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_permission")
@ApiModel(value = "SysPermission对象", description = "SysPermission表")
public class SysPermission {

	private Integer id;

	private String permissionCode;

	private String permissionName;

}
