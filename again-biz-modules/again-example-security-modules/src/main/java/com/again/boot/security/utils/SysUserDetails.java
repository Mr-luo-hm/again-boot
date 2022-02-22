package com.again.boot.security.utils;

import com.again.boot.security.model.entity.SysUser;
import jdk.nashorn.internal.runtime.GlobalConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Map;

/**
 * @author create by 罗英杰 on 2022/2/22
 * @description:
 */
public class SysUserDetails implements UserDetails {

	private final SysUser sysUser;

	private final Collection<? extends GrantedAuthority> authorities;

	/**
	 * 用户所有的资源Map <br/>
	 * key: resource标识 value: resourceItem <br/>
	 * 以角色为例 => role: roleCodeList
	 */
	private final Map<String, Collection<?>> userResources;

	/**
	 * 用户属性 <br/>
	 * 对于不同类型的用户，可能在业务上需要获取到不同的用户属性
	 */
	private final Map<String, Object> userAttributes;

	public SysUserDetails(SysUser sysUser, Collection<? extends GrantedAuthority> authorities,
			Map<String, Collection<?>> userResources, Map<String, Object> userAttributes) {
		this.sysUser = sysUser;
		this.authorities = authorities;
		this.userResources = userResources;
		this.userAttributes = userAttributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return sysUser.getPassword();
	}

	@Override
	public String getUsername() {
		return sysUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public Map<String, Collection<?>> getUserResources() {
		return userResources;
	}

	public Map<String, Object> getUserAttributes() {
		return userAttributes;
	}

}
