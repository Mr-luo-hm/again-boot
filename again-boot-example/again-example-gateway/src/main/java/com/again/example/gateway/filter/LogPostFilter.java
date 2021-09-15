package com.again.example.gateway.filter;

import com.again.example.beans.LogBean;
import com.again.example.gateway.mq.SendLogStream;
import com.again.example.utils.JacksonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

/**
 * @author create by 罗英杰 on 2021/9/15
 * @description: 当前过滤器的作用是为了记录日志并发送
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LogPostFilter implements GlobalFilter, Ordered {

	private final SendLogStream sendLogStream;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		try {
			LogBean bean = new LogBean();
			// 请求url
			String url = exchange.getRequest().getPath().pathWithinApplication().value();
			HttpMethod method = exchange.getRequest().getMethod();
			// 请求路径 /api/...
			URI uri = exchange.getRequest().getURI();
			String path = uri.getPath();
			// ip :port
			String authority = uri.getAuthority();
			// 查询参数
			String query = uri.getQuery();

			bean.setServerIp(InetAddress.getLocalHost().getHostAddress());
			bean.setUrl(url);
			bean.setMethod(method.name());
			bean.setPath(path);
			bean.setRequestContent(query);
			sendLogStream.messageChannel().send(new GenericMessage<String>(JacksonUtils.toJson(bean)));
		}
		catch (UnknownHostException e) {
			log.error("LogPostFilter error:{}", e.getMessage(), e);
		}
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 0;
	}

}
