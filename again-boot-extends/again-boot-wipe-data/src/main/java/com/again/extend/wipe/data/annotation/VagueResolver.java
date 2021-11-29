package com.again.extend.wipe.data.annotation;

import com.again.extend.wipe.data.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author create by 罗英杰 on 2021/11/12
 * @description:
 */

@Aspect
@Component
@Slf4j
public class VagueResolver {

	@Pointcut("@annotation(com.again.extend.wipe.data.annotation.WipeData)")
	public void serviceStatistics() {

	}

	@Before("serviceStatistics()")
	public void doBefore(JoinPoint joinPoint) {
		long beginTime = System.currentTimeMillis();
	}

	@AfterReturning(value = "@annotation(com.again.extend.wipe.data.annotation.WipeData)", returning = "value")
	public void doAfter(JoinPoint joinPoint, Object value) {
		log.info("----数据模糊处理开始----");
		value = this.dataHandle(value);
		log.info("----数据模糊处理结束----");
	}

	public Object dataHandle(Object value) {
		try {
			if (value instanceof ArrayList) {
				return this.handListType((List<Object>) value);
			}
			else if (value instanceof List) {
				return this.handListType((List<Object>) value);
			}
			else if (value.getClass().getTypeName().contains("com.again")) {
				return this.handPojoType(value);
			}
			else {
				return value;
			}
		}
		catch (Exception e) {
			return value;
		}
	}

	public Object handPojoType(Object object) {
		if (object != null) {
			Class clz = object.getClass();
			// 获取到对象所有属性，并且遍历
			Field[] fields = clz.getDeclaredFields();
			for (Field field : fields) {
				String classType = field.getType().getTypeName();
				boolean fieldHasAnno = field.isAnnotationPresent(WipeData.class);
				// 判断属性上是否有注解，如果有进入逻辑，如果没有，返回对象
				if (fieldHasAnno) {
					// 如果属性是String 模糊化他（模糊化处理只能处理String了，不要问为什么）
					if ("java.lang.String".equals(classType)) {
						WipeData wipeData = field.getAnnotation(WipeData.class);
						String type = wipeData.value();
						object = this.handleValue(field, object, type);
					}
					// 这儿就相当于递归处理了，处理对象嵌套对象的方式的模糊化
					// 如果不是，获取到他的值，继续走dataHandle（为什么又要走dataHandle？因为万一他又是List<POJO>这些呢？）
					else {
						Class fieldClass = object.getClass();
						String name = this.firstUpperCase(field.getName());
						field.setAccessible(true);// 设置操作权限为true
						Method getMethod = null;
						try {
							getMethod = fieldClass.getMethod("get" + name);
							Object value = getMethod.invoke(object);
							if (value != null) {
								value = this.dataHandle(value);
								Method setMethod = fieldClass.getMethod("set" + name, field.getType());
								setMethod.invoke(object, value);
							}
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return object;
	}

	public Object handListType(List<Object> page) {
		if (page != null && !page.isEmpty()) {
			for (int i = 0; i < page.size(); i++) {
				page.set(i, this.dataHandle(page.get(i)));
			}
		}
		return page;
	}

	public Object handleValue(Field field, Object object, String type) {
		try {
			Class clz = object.getClass();
			String name = this.firstUpperCase(field.getName());
			field.setAccessible(true);// 设置操作权限为true
			Method getMethod = clz.getMethod("get" + name);
			Object value = getMethod.invoke(object);
			Method setMethod = clz.getMethod("set" + name, field.getType());
			value = this.handleValue(value, type);
			setMethod.invoke(object, value);
			return object;
		}
		catch (Exception e) {
			e.printStackTrace();
			return object;
		}
	}

	public Object handleValue(Object object, String type) {
		switch (type) {
		case Constants.NAME:
			return handleNAME(object);
		case Constants.ID_CODE:
			return handleIDCODE(object);
		case Constants.BANK_CODE:
			return handleBANK_CODE(object);
		case Constants.ADDRESS:
			return handleNAME(object);
		case Constants.PHONE_NO:
			return handlePHONE_NO(object);
		}
		return object;
	}

	public Object handlePHONE_NO(Object object) {
		if (object != null) {
			String phoneNo = object.toString();
			if (phoneNo.length() == 11) {
				return phoneNo.substring(0, 3) + "****" + phoneNo.substring(7);
			}
		}
		return object;
	}

	/**
	 * 银行卡替换，保留后四位
	 * <p>
	 * 如果银行卡号为空 或者 null ,返回null ；否则，返回替换后的字符串；
	 * @param object 银行卡号
	 * @return
	 */
	public Object handleBANK_CODE(Object object) {
		if (object != null) {
			String bankCard = object.toString();
			if (bankCard.isEmpty()) {
				return null;
			}
			else {
				return bankCard.substring(0, 4) + "**********" + bankCard.substring(bankCard.length() - 4);
			}
		}
		return object;
	}

	/**
	 * 身份证号替换，保留前四位和后四位
	 * <p>
	 * 如果身份证号为空 或者 null ,返回null ；否则，返回替换后的字符串；
	 * @param object 身份证号
	 * @return
	 */
	public Object handleIDCODE(Object object) {
		if (object != null) {
			String idCode = object.toString();
			if (idCode.isEmpty()) {
				return object;
			}
			else {
				return idCode.substring(0, 4) + "**********" + idCode.substring(idCode.length() - 4);
			}
		}
		return object;
	}

	public Object handleNAME(Object object) {
		if (object != null) {
			String userName = object.toString();
			int length = object.toString().length();
			if (length <= 1) {
				return "*";
			}
			else if (length == 2) {
				return userName.substring(0, 1) + "*";
			}
			else if (length == 3) {
				return userName.substring(0, 1) + "**";
			}
			else {
				StringBuilder sb = new StringBuilder();
				sb.append(userName, 0, 2);
				for (int i = 0; i < length - 2; i++) {
					sb.append("*");
				}
				return sb.toString();
			}
		}
		return object;
	}

	public String firstUpperCase(String str) {
		return StringUtils.replaceChars(str, str.substring(0, 1), str.substring(0, 1).toUpperCase());
	}

}
