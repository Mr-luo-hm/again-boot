package com.again.boot.security.service;

import com.again.boot.security.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
public interface SysPermissionService extends IService<SysPermission> {

	List<SysPermission> selectListByUser(Integer userId);

}
