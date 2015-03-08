package com.vo;

public class UserVO {
	
	private int userId;
	private String uName;
	private String pass;
	
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", uName=" + uName + ", pass="
				+ pass + "]";
	}
	

}
