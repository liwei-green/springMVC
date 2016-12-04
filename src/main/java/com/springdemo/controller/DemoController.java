package com.springdemo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springdemo.entity.User;
import com.springdemo.service.UserService;

@Controller
public class DemoController {
	@Autowired
	private UserService userService;
	/*加上
	@Autowired
	private DataSource dataSource
	编译报错，没有这句编译正常*/
	@Autowired
	private DataSource dataSource;

	@RequestMapping("login.do")
	public ModelAndView  index(HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println(userService);
		System.out.println(dataSource);
		String userName=request.getParameter("userName");

		String userAge=request.getParameter("userAge");

		User user= userService.findById(3);
		if(userName.equals(user.getName())){
			return new ModelAndView("sys/success","user",user);
		}else{
			return new ModelAndView("sys/faild","user",user);
		}





	}

}
