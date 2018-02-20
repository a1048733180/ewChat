package com.ewei.chat.pojo;

import java.io.Serializable;

public class Log implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@Override
	public String toString() {
		return "Log [id=" + id + ", userid=" + userid + ", time=" + time + ", type=" + type + ", detail=" + detail
				+ ", ip=" + ip + "]";
	}
	private String userid;
	private String time;
	private String type;
	private String detail;
	private String ip;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
