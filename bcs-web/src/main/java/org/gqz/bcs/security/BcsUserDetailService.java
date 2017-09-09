package org.gqz.bcs.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.gqz.bcs.model.Role;
import org.gqz.bcs.service.IUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class BcsUserDetailService implements UserDetailsService {

	@Resource(name="userService")
	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * 根据用户名获取用户 - 用户的角色、权限等信息
	 * 
	 * @param username
	 *            用户名
	 */
	public UserDetails loadUserByUsername(String username){
		UserDetails userDetails = null;

		try {
			org.gqz.bcs.model.User user = userService.loadByUsername(username);
			Collection<GrantedAuthority> authList = getAuthorities(user);
			System.out.println("username:" + username + "  password:" + user.getPassword());
			userDetails = new User(username, user.getPassword(), true, true, true, true, authList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userDetails;
	}

	/**
	 * 获取用户的角色权限,为了降低实验的难度，这里去掉了根据用户名获取角色的步骤
	 * 
	 * @return 权限的集合
	 */
	private Collection<GrantedAuthority> getAuthorities(org.gqz.bcs.model.User user) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

		List<Role> roles = userService.listUserRoles(user.getId());
		for (Role role : roles) {
			authList.add(new SimpleGrantedAuthority(role.getRoleType().name()));
			System.out.println(" Role:" + role.getRoleType().name() );
		}

		return authList;
	}
}
