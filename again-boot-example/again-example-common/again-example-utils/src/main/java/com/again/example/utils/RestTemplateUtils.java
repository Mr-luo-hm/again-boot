package com.again.example.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author create by 罗英杰 on 2021/9/13
 * @description: http 重试策略
 */
@Slf4j
public class RestTemplateUtils {

	public static String get(String url, String parameter) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<>(parameter, headers);
			return request(() -> restTemplate.getForObject(url, String.class, request));
		}
		catch (Exception e) {
			log.error("RestTemplateUtils get error:{}", e.getMessage(), e);
		}
		return null;
	}

	public static String get(String url) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			// 抛出指定异常以及异常子类重试
			return request(() -> restTemplate.getForObject(url, String.class));
		}
		catch (Exception e) {
			if (log == null) {

			}
			log.error("RestTemplateUtils get error:{},url:{}", e.getMessage(), url, e);
			return e.getMessage();
		}
	}

	/**
	 * get请求
	 * @param url url
	 * @param headers 请求头 没有传空集合
	 * @param bool 必传 区分与上边得get请求
	 * @return 返回值
	 */
	public static String get(String url, Map<String, String> headers, Boolean bool) {
		try {
			HttpRequest request = HttpUtil.createRequest(Method.GET, url);
			// 添加 请求头
			if (headers != null && !headers.isEmpty()) {
				headers.forEach(request::header);
			}
			return request(() -> request.execute().body());
		}
		catch (Exception e) {
			log.error("post error :{},url:{}", e.getMessage(), url, e);
			return e.getMessage();
		}
	}

	public static String post(String url, String parameter) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<>(parameter, headers);
			return request(() -> restTemplate.postForObject(url, request, String.class));
		}
		catch (Exception e) {
			log.error("RestTemplateUtils post error:{},url:{}", e.getMessage(), url, e);
		}
		return null;
	}

	/**
	 * post 请求
	 * @param url 请求url
	 * @param headers 请求头参数
	 * @param body 请求体json
	 * @return return
	 */
	public static String post(String url, Map<String, String> headers, String body) {
		try {
			// 创建http 请求
			HttpRequest post = HttpUtil.createPost(url);
			// 添加 请求头
			if (headers != null && !headers.isEmpty()) {
				headers.forEach(post::header);
			}
			log.info("request url:{},body:{} ", url, body);
			return request(() -> post.body(body).execute().body());
		}
		catch (Exception e) {
			log.error("post error :{},url:{},body:{}", e.getMessage(), url, body, e);
			return e.getMessage();
		}
	}

	/**
	 * 服务器发起put 请求
	 * @param url 请求url
	 * @param headers 请求头
	 * @param body 请求体json
	 * @return 返回json格式得内容
	 */
	public static String put(String url, Map<String, String> headers, String body) {
		try {
			// 创建请求
			HttpRequest request = HttpUtil.createRequest(Method.PUT, url);
			// 添加请求头
			if (headers != null && !headers.isEmpty()) {
				headers.forEach(request::header);
			}
			log.info("request url:{} ", url);
			String resp = request(() -> request.body(body).execute().body());
			log.info("response resp:{} ", resp);
			return resp;
		}
		catch (Exception e) {
			log.error("request put error :{},url:{},body:{}", e.getMessage(), url, body, e);
			return e.getMessage();
		}
	}

	/**
	 * delete 请求
	 * @param url url
	 * @param headers 请求头
	 * @return 返回值
	 */
	public static String delete(String url, Map<String, String> headers) {
		try {
			HttpRequest request = HttpUtil.createRequest(Method.DELETE, url);
			// 添加 请求头
			if (headers != null && !headers.isEmpty()) {
				headers.forEach(request::header);
			}
			log.info("request url:{} ", url);
			String resp = request(() -> request.execute().body());
			log.info("response url:{} ", resp);
			return resp;
		}
		catch (Exception e) {
			log.error("url delete error:{},url:{}", e.getMessage(), url, e);
			return e.getMessage();
		}
	}

	/**
	 * 重试策略 发起请求
	 * @param callable request请求
	 * @return 返回值
	 * @throws ExecutionException ExecutionException
	 * @throws RetryException RetryException
	 */
	private static String request(Callable<String> callable) throws ExecutionException, RetryException {
		// 抛出异常以及异常之类
		return RetryerBuilder.<String>newBuilder().retryIfExceptionOfType(Exception.class)
				// 不满足条件重试
				.retryIfResult(ObjectUtils::isEmpty)
				// 休眠时间 即中途停止多长时间进行重试
				.withWaitStrategy(WaitStrategies.fixedWait(0, TimeUnit.SECONDS))
				// 停止策
				// 略
				.withStopStrategy(StopStrategies.stopAfterAttempt(3)).build().call(callable);
	}

}
