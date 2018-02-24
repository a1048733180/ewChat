package com.ewei.chat.pojo;

/**
 * 简单登录对象
 *
 */
public class User {
	private String userid; //用户id
	private String password; //用户密码
	private String nickname; //用户名称
	private String lasttime; //最后登录时间
	
	//get和set方法
	
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
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
