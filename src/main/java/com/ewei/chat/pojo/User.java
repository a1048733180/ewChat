package com.ewei.chat.pojo;

/**
 * 简单登录对象
 *
 */
public class User {
	private String userid; //用户id
	private String password; //用户密码
	private String nickname; //用户名称

	private String sex;
	private String age;
	private String profile;
	private String profilehead;
	private String firsttime;
	private String lasttime;
	private String status;
	//get和set方法
	public String getFirsttime() {
		return firsttime;
	}
	public void setFirsttime(String firsttime) {
		this.firsttime = firsttime;
	}

	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}


	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getProfilehead() {
		return profilehead;
	}
	public void setProfilehead(String profilehead) {
		this.profilehead = profilehead;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
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
	@Override
	public String toString() {
		return "User [userid=" + userid + ", password=" + password + ", nickname=" + nickname + ", sex=" + sex
				+ ", age=" + age + ", profile=" + profile + ", profilehead=" + profilehead + ", firsttime=" + firsttime
				+ ", lasttime=" + lasttime + ", status=" + status + "]";
	}

}	


