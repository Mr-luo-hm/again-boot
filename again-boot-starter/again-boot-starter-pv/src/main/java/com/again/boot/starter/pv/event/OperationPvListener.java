package com.again.boot.starter.pv.event;

import com.again.boot.starter.pv.service.OperationPvHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @author create by 罗英杰 on 2021/11/24
 * @description:
 */
@Slf4j
@RequiredArgsConstructor
public class OperationPvListener {

	private final OperationPvHandler operationPvHandler;

	@Async
	@Order
	@EventListener(OperationPvLogEvent.class)
	public void saveSysLog(OperationPvLogEvent event) {
		operationPvHandler.saveLog(event.getPvDTO());
	}

}
