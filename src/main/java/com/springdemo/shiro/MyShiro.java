package com.springdemo.shiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.springdemo.entity.Role;
import com.springdemo.entity.User;
import com.springdemo.service.UserService;



public class MyShiro extends AuthorizingRealm {
	@Autowired
	private UserService userService;
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		User user = userService.findByUserName(userName);
		List<String> roles = new ArrayList<String>();
		Set<Role> role=user.getRoleList();
		for(Role r:role){
			roles.add(r.getRolename());
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(roles);
		return info;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
				UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
				String userName=token.getUsername();
				User user = userService.findByUserName(userName);
				if (user != null) {
					return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
				} else {
					return null;
				}
		
	}

}
