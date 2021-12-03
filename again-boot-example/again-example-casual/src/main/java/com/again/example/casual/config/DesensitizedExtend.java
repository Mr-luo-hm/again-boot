package com.again.example.casual.config;

import com.again.boot.starter.desensitized.handler.DesensitizedHandler;
import com.again.boot.starter.desensitized.utils.R;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author create by 罗英杰 on 2021/11/29
 * @description:
 */
@Component
public class DesensitizedExtend extends DesensitizedHandler {

	@Override
	public Object vestIn(Object o) {
		if (o instanceof R) {
			R result = (R) o;
			return result.getData();
		}
		return null;
	}

}
