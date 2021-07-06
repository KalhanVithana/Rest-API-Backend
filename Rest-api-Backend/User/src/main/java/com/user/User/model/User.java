package com.user.User.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	private int id;

	private String name;
	private String email;
	private String mobile;
	private String password;
	private String token;

	public void setToken(String token) {
		this.token = token;
	}

	public int getId() {
		return id;
	}

	public User(String name, String email) {

		this.name = name;
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getToken() {
		return token;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {

	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", password="
				+ password + "]";
	}
	
	

}

