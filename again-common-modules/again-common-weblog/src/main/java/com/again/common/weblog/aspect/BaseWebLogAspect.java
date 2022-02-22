package com.again.common.weblog.aspect;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import com.again.common.weblog.bean.WebLog;
import com.again.common.weblog.utils.HttpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author create by 罗英杰 on 2021/12/31
 * @description:
 */
@Aspect
@Slf4j
@Order(1)
public abstract class BaseWebLogAspect {

	/**
	 * 切入点
	 */
	@Pointcut("@within(org.springframework.web.bind.annotation.RestController)"
			+ "||@within( org.springframework.stereotype.Controller)")
	public void log() {

	}

	/**
	 * 环绕操作
	 * @param point 切入点
	 * @return 原方法返回值
	 * @throws Throwable 异常信息
	 */
	@Around("log()")
	public Object aroundLog(ProceedingJoinPoint point) throws Throwable {

		// 开始打印请求日志
		Object result = null;
		WebLog l = new WebLog();
		try {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

			// 打印请求相关参数
			long startTime = System.currentTimeMillis();
			result = point.proceed();
			String header = request.getHeader("User-Agent");
			UserAgent userAgent = UserAgent.parseUserAgentString(header);

			l = WebLog.builder().threadId(Long.toString(Thread.currentThread().getId()))
					.threadName(Thread.currentThread().getName()).ip(HttpUtils.getIp(request))
					.url(request.getRequestURL().toString())
					.classMethod(String.format("%s.%s", point.getSignature().getDeclaringTypeName(),
							point.getSignature().getName()))
					.httpMethod(request.getMethod()).requestParams(getNameAndValue(point)).result(result)
					.timeCost(System.currentTimeMillis() - startTime).userAgent(header)
					.browser(userAgent.getBrowser().toString()).os(userAgent.getOperatingSystem().toString()).build();
			process(l);
		}
		catch (Throwable throwable) {
			log.error("Request Log error : {},throwable:{}", JSONUtil.toJsonStr(l), throwable.getMessage(), throwable);
		}

		return result;
	}

	/**
	 * 获取方法参数名和参数值
	 * @param joinPoint
	 * @return
	 */
	private Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {

		final Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		final String[] names = methodSignature.getParameterNames();
		final Object[] args = joinPoint.getArgs();

		if (ArrayUtil.isEmpty(names) || ArrayUtil.isEmpty(args)) {
			return Collections.emptyMap();
		}
		if (names.length != args.length) {
			log.warn("{}方法参数名和参数值数量不一致", methodSignature.getName());
			return Collections.emptyMap();
		}
		Map<String, Object> map = new HashMap<>();
		// 需要过滤掉指定的参数，否则会出现栈溢出异常
		for (int i = 0; i < args.length; i++) {
			Object arg = args[i];
			String name = names[i];
			if (arg instanceof BindingResult || arg instanceof ServletRequest || arg instanceof ServletResponse
					|| arg instanceof MultipartFile || arg instanceof List) {
				continue;
			}

			map.put(name, args[i]);
		}

		return map;
	}

	private Map<String, Object> getFieldsName(JoinPoint joinPoint) throws Exception {
		String classType = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		// 参数值
		Object[] args = joinPoint.getArgs();
		Class<?>[] classes = new Class[args.length];
		for (int k = 0; k < args.length; k++) {
			Object arg = args[k];
			// 对于接受参数中含有MultipartFile，ServletRequest，ServletResponse类型的特殊处理，我这里是直接返回了null。
			if (arg instanceof MultipartFile || arg instanceof ServletRequest || arg instanceof ServletResponse) {
				return Collections.emptyMap();
			}
			if (!args[k].getClass().isPrimitive()) {
				// 当方法参数是封装类型
				Class s = args[k].getClass();

				classes[k] = s == null ? args[k].getClass() : s;
			}
		}
		ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
		// 获取指定的方法，第二个参数可以不传，但是为了防止有重载的现象，还是需要传入参数的类型
		Method method = Class.forName(classType).getMethod(methodName, classes);
		// 参数名
		String[] parameterNames = pnd.getParameterNames(method);
		// 通过map封装参数和参数值
		HashMap<String, Object> paramMap = new HashMap();
		for (int i = 0; i < Objects.requireNonNull(parameterNames).length; i++) {
			paramMap.put(parameterNames[i], args[i]);
		}
		return paramMap;
	}

	public abstract void process(WebLog webLog);

}
