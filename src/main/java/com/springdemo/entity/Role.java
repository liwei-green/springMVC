package com.springdemo.entity;


import java.util.Set;

public class Role {
	private Integer id;  
    private String rolename; 
    private Set<User> userList;//һ����ɫ��Ӧ����û�

	
	public Set<User> getUserList() {
		return userList;
	}
	public void setUserList(Set<User> userList) {
		this.userList = userList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
    
}
