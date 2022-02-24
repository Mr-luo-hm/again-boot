package com.again.boot.web.controller;

import com.again.boot.security.model.vo.PermissionVO;
import com.again.boot.security.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author create by 罗英杰 on 2022/2/22
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
@Api(value = "/", tags = "菜单")
public class MenuController {

	private final SysPermissionService sysPermissionService;

	@GetMapping(value = "/")
	@ApiOperation("菜单权限")
	public R<List<PermissionVO>> permissionMenu(String[] roles) {
		return R.ok(sysPermissionService.findPermissionVOsByRoleCode("ROLE_ADMIN"));
	}

}
