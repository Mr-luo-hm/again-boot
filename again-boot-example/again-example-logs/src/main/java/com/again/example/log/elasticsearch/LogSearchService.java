package com.again.example.log.elasticsearch;

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

}
