package com.again.extend.mybatis.mysql;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Collection;

/**
 * @author create by 罗英杰 on 2021/9/4
 * @description:
 */
public class ExtendServiceImpl<M extends ExtendBaseMapper<T>, T> extends ServiceImpl<M, T> implements ExtendService<T> {

	@Override
	public int insertByBatch(Collection<T> list) {
		return baseMapper.insertByBatch(list);
	}

	@Override
	public int insertIgnoreByBatch(Collection<T> list) {
		return baseMapper.insertIgnoreByBatch(list);
	}

	@Override
	public int insertOrUpdateByBatch(Collection<T> list, boolean ignore) {
		return baseMapper.insertOrUpdateByBatch(list);
	}

	@Override
	public int insertOrUpdateFieldByBatch(Collection<T> list, Columns<T> columns) {
		return baseMapper.insertOrUpdateFieldByBatch(list, columns);
	}

}