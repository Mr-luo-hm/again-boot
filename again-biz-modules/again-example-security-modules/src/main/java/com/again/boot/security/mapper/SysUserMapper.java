package com.again.boot.security.mapper;

import com.again.boot.security.model.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

	SysUser selectByName(@Param("userName") String userName);

}
