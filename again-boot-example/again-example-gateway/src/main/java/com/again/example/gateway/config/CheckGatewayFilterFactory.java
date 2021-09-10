package com.again.example.gateway.config;

import com.again.example.gateway.filter.CheckGateWayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @author create by 罗英杰 on 2021/9/10
 * @description:
 */
@Component
public class CheckGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

	@Override
	public GatewayFilter apply(Object config) {
		return new CheckGateWayFilter();
	}

}
