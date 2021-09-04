package com.again.extend.mybatis;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author create by 罗英杰 on 2021/9/4
 * @description:
 */
@RequiredArgsConstructor
public class SqlInjector extends DefaultSqlInjector {

	private final List<AbstractMethod> list;

	@Override
	public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
		List<AbstractMethod> list = super.getMethodList(mapperClass);
		list.addAll(this.list);
		return list;
	}

}
