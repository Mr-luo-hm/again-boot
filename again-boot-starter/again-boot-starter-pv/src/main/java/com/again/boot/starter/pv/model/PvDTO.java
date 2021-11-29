package com.again.boot.starter.pv.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author create by 罗英杰 on 2021/11/24
 * @description:
 */
@Data
@Accessors(chain = true)
public class PvDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 请求URI
	 */
	private String uri;

	/**
	 * 渠道号
	 */
	private String channel;

	/**
	 * 机型
	 */
	private String phoneModel;

	/**
	 * 国家
	 */
	private String country;

	/**
	 * 省
	 */
	private String province;

	/**
	 * ip
	 */
	private String ip;

	/**
	 * 网络
	 */
	private String network;

	/**
	 * 客户端版本号
	 */
	private String versionName;

	/**
	 * 构件号
	 */
	private String versionCode;

	/**
	 * 语言
	 */
	private String locale;

	/**
	 * app语言选项
	 */
	private String appLocale;

	/**
	 * 经度
	 */
	private String longitude;

	/**
	 * 维度
	 */
	private String latitude;

	/**
	 * Google advertising ID advertising ID=== device_id
	 * com.google.android.gms.ads.identifier
	 */
	private String deviceId;

	/**
	 * android版本
	 */
	private String androidVersion;

	/**
	 * android id
	 */
	private String androidId;

	/**
	 * 分辨率
	 */
	private String resolution;

	/**
	 * 制造商
	 */
	private String vendor;

	/**
	 * android序列号
	 */
	private String serial;

	/**
	 * mac地址
	 */
	private String mac;

	/**
	 * 软件版本号
	 */
	private String buildNum;

	/**
	 * user_agent
	 */
	private String userAgent;

	/**
	 * cookie信息
	 */
	private String cookie;

	/**
	 * 日期
	 */
	private LocalDateTime createTime;

}
