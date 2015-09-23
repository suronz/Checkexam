package com.vo;

public class StudentVO {
	
	private String userId;
	private String name;
	private String email;
	private String status;
	private String registredDate;
	private boolean selectedStudent;
	private String statImageName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegistredDate() {
		return registredDate;
	}
	public void setRegistredDate(String registredDate) {
		this.registredDate = registredDate;
	}
	
	public boolean isSelectedStudent() {
		return selectedStudent;
	}
	public void setSelectedStudent(boolean selectedStudent) {
		this.selectedStudent = selectedStudent;
	}
	
	
	public String getStatImageName() {
		return statImageName;
	}
	public void setStatImageName(String statImageName) {
		this.statImageName = statImageName;
	}
	@Override
	public String toString() {
		return "StudentVO [userId=" + userId + ", name=" + name + ", email="
				+ email + ", status=" + status + ", registredDate="
				+ registredDate + "]";
	}
	
	
	
	

}
