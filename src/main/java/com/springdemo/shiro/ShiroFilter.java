/**
 * 
 */
package com.springdemo.shiro;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.springdemo.entity.Role;
import com.springdemo.entity.User;




/**
 * @author chenlf
 * 
 *         2014-3-24
 */
public class ShiroFilter implements Filter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Principal principal = httpRequest.getUserPrincipal();

		if (principal != null) {
			Subject subjects = SecurityUtils.getSubject();
			// Ϊ�˼򵥣������ʼ��һ���û���ʵ����Ŀ��Ŀ��Ӧ��ȥ���ݿ���ͨ������ȡ�û���
			// ���磺User user = userService.getByAccount(principal.getName());
			User user = new User();
			user.setName("shiro");
			user.setPassword("123456");
			Set<Role> roles=(Set<Role>) new ArrayList();
			Role role=new Role();
			role.setRolename("member");
			roles.add(role);
			user.setRoleList(roles);
			if (user.getName().equals(principal.getName())) {
				UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user
						.getPassword());
				subjects = SecurityUtils.getSubject();
				subjects.login(token);
				subjects.getSession();
			} else {
				// ����û�Ϊ�գ���subjects��Ϣ�ǳ�
				if (subjects != null) {
					subjects.logout();
				}
			}
		}
		chain.doFilter(httpRequest, httpResponse);


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
