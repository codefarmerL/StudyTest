package com.studytest.entity;

public class User {
	private String id;
	private String userName;
	private String passWord;
	
	public User(String username,String password){
		setUserName(username);
		setPassWord(password);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
