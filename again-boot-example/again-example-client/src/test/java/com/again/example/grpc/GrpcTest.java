package com.again.example.grpc;

import com.again.example.grpc.client.GrpcClientService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author create by 罗英杰 on 2021/9/1
 * @description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GrpcClientApplication.class)
public class GrpcTest {

	@Autowired
	private GrpcClientService grpcClientService;

	/**
	 * 简单模式
	 */
	@Test
	public void briefness() {
		String again = grpcClientService.sendMessage("again");
		System.out.println(again);
	}

	/**
	 * 服务端流
	 */
	@Test
	public void serverStream() {
		grpcClientService.sendMessage2("again");

	}

	/**
	 * 客户端流
	 * @throws InterruptedException
	 */
	@Test
	public void clientStream() throws InterruptedException {
		grpcClientService.sendMessage3("again");

	}

	/**
	 * 双向流
	 * @throws InterruptedException
	 */
	@Test
	public void bothWayStream() throws InterruptedException {
		grpcClientService.sendMessage4("again");

	}

}
