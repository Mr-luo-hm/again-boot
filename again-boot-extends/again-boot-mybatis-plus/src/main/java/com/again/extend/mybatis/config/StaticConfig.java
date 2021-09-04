package com.again.extend.mybatis.config;

import java.util.HashSet;
import java.util.Set;

/**
 * @author create by 罗英杰 on 2021/9/4
 * @description: 用于配置 需要 动态加载，但是 静态来使用的变量
 */
public class StaticConfig {

	/**
	 * 更新时忽略的字段
	 */
	public static final Set<String> UPDATE_IGNORE_FIELDS = new HashSet<>();

}
