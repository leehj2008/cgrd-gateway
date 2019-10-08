package com.app.login;

public class User {

	private int userid;
	private String username;
	private String passwd;
	private String winuser;
	private String winpass;
	private String appuser;
	private String apppass;
	private String expire_date;
	private String status;
	
	
	public String getWinuser() {
		return winuser;
	}
	public void setWinuser(String winuser) {
		this.winuser = winuser;
	}
	public String getWinpass() {
		return winpass;
	}
	public void setWinpass(String winpass) {
		this.winpass = winpass;
	}
	public String getAppuser() {
		return appuser;
	}
	public void setAppuser(String appuser) {
		this.appuser = appuser;
	}
	public String getApppass() {
		return apppass;
	}
	public void setApppass(String apppass) {
		this.apppass = apppass;
	}
	public String getExpire_date() {
		return expire_date;
	}
	public void setExpire_date(String expire_date) {
		this.expire_date = expire_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
