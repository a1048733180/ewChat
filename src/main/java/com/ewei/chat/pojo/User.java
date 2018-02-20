package com.ewei.chat.pojo;

/**
 * 简单登录对象
 *
 */
public class User {
	private int userid; //用户id
	private String password; //用户密码
	private String nickname; //用户名称
	
	
	//get and set方法
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
}
