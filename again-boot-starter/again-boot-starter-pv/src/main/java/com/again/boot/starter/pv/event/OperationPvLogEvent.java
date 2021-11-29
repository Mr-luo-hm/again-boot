package com.again.boot.starter.pv.event;

import com.again.boot.starter.pv.model.PvDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author create by 罗英杰 on 2021/11/24
 * @description:
 */
@Getter
@AllArgsConstructor
public class OperationPvLogEvent {

	private final PvDTO pvDTO;

}
