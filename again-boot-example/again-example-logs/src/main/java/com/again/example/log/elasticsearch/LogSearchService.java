package com.again.example.log.elasticsearch;

import java.io.IOException;

/**
 * @author create by 罗英杰 on 2021/9/15
 * @description:
 */
public interface LogSearchService {

	/**
	 * 添加文档
	 * @param json json
	 */
	void addDoc(String json);

	/**
	 * Reindex 更换索引 whatsFriend
	 * @param oldIndex 旧索引
	 * @param newIndex 新索引
	 * @param size 条数
	 * @throws IOException IOException
	 */
	void updateIndexReindexWhatsFriend(String oldIndex, String newIndex, Integer size) throws IOException;

	/**
	 * 创建别名whatsFriendIndex
	 * @param index
	 * @throws IOException
	 */
	void addIndexWhatsFriendAlias(String index) throws IOException;

	/**
	 * 滚动api 创建 rollover api
	 * @throws IOException io
	 */
	void rolloverWhatsFriend() throws IOException;

}
