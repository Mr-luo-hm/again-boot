package com.again.boot.starter.pv.service;

import com.again.boot.starter.pv.model.PvDTO;

/**
 * @author create by 罗英杰 on 2021/11/24
 * @description:
 */
public interface OperationPvHandler {

	/**
	 * 保存操作日志
	 * @param pvDTO 操作日志传输对象
	 */
	void saveLog(PvDTO pvDTO);

}
