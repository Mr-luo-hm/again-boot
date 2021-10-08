package com.again.boot.security.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_permission")
@ApiModel(value = "SysPermission对象", description = "SysPermission表")
public class SysPermission {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	@TableId(type = IdType.INPUT)
	@ApiModelProperty(value = "菜单ID")
	private Integer id;

	/**
	 * 菜单标题
	 */
	@ApiModelProperty(value = "菜单标题")
	private String title;

	/**
	 * 菜单权限标识
	 */
	@ApiModelProperty(value = "菜单权限标识")
	private String code;

	/**
	 * 路由URL
	 */
	@ApiModelProperty(value = "路由URL")
	private String path;

	/**
	 * 路由名称
	 */
	@ApiModelProperty(value = "路由名称")
	private String routerName;

	/**
	 * component地址
	 */
	@ApiModelProperty(value = "component地址")
	private String component;

	/**
	 * 重定向地址
	 */
	@ApiModelProperty(value = "重定向地址")
	private String redirect;

	/**
	 * 链接跳转目标
	 */
	@ApiModelProperty(value = "链接跳转目标")
	private String target;

	/**
	 * 父菜单ID
	 */
	@ApiModelProperty(value = "父菜单ID")
	private Integer parentId;

	/**
	 * 图标
	 */
	@ApiModelProperty(value = "图标")
	private String icon;

	/**
	 * 排序值
	 */
	@ApiModelProperty(value = "排序值")
	private Integer sort;

	/**
	 * 0-开启，1- 关闭
	 */
	@ApiModelProperty(value = "0-开启，1- 关闭")
	private Integer keepAlive;

	/**
	 * 是否隐藏路由: 0否,1是
	 */
	@ApiModelProperty(value = "是否隐藏路由: 0否,1是")
	private Integer hidden;

	/**
	 * 菜单类型 （0菜单 1按钮）
	 */
	@ApiModelProperty(value = "菜单类型 （0菜单 1按钮）")
	private Integer type;

	/**
	 * 逻辑删除标识，已删除:0，未删除：删除时间戳
	 */
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(value = "逻辑删除标识，已删除:0，未删除：删除时间戳")
	private Long deleted;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

}
