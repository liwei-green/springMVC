package com.springdemo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



import com.springdemo.entity.User;
import com.springdemo.service.UserService;

@Controller

public class DemoController {
	@Autowired
	private UserService userService;
	@Autowired
	private DataSource dataSource;
	
	

	@RequestMapping("/submit")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response ,ModelMap model) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		if(userName==null){
			userName="admin";
		}
		String password = request.getParameter("password");
		User user = userService.findByUserName(userName);

		if(user!=null){
			try {
				// 如果登陆成功
				if (user.getName().equals(userName) && user.getPassword().equals(password)) {
					UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user
							.getPassword().toString());
					Subject subject = SecurityUtils.getSubject();
					subject.login(token);
				}
			} catch (Exception e) {
					e.printStackTrace();
			}
		}		

		return new ModelAndView("redirect:/sys/success");
	}
}
