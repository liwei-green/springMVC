package com.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;







import redis.dao.AddressDao;


import redis.entity.Address;

import com.springdemo.dao.UserDao;



@Controller
public class IndexMember {
	@Autowired
    private UserDao userDao; 
	@Autowired
    private AddressDao addressDAO;
	
	
	// ����/index.htm ����ΪGET������
	@RequestMapping(value = "/sys/success", method = RequestMethod.GET)
	public String  index(ModelMap map) {
		String message = "�����Ҫ���ݵ�����";
		
		Address address = new Address();
		address.setId(1);
		address.setName("liuxg");
		addressDAO.saveAddress(address);
		
		Address liuxg = addressDAO.getAddress(1);
		
		map.put("message", message);
		map.addAttribute("liuxg", liuxg);
		//view.setViewName("/sys/success");
		return "/sys/success";
	}
}
