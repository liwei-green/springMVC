package com.springdemo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
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

	@RequestMapping("register.do")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println(userService);
		System.out.println(dataSource);
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		User user = new User();
		user.setName(userName);
		user.setPassword(password);
		userService.saveUser(user);
		return new ModelAndView("sys/success", "user", user);
	}
}
