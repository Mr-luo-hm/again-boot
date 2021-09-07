package com.again.boot.security.mapper;

import com.again.boot.security.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

	List<SysPermission> selectListByUser(Integer userId);

}
