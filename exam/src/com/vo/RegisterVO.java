package com.vo;

public class RegisterVO {
	
	private int userId;
	private String firstName;
	private String lastName;
	private String gurdainName;
	private String age;
	private String gender;
	private String address;
	private String lastExamMarks;
	private String borad;
	private String phone;
	private String email;
	private String password;
	private String userName;
	
	public String getFirstName() {
		return firstName;
	}
	public void setName(String firstName) {
		this.firstName = firstName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLastExamMarks() {
		return lastExamMarks;
	}
	public void setLastExamMarks(String lastExamMarks) {
		this.lastExamMarks = lastExamMarks;
	}
	public String getBorad() {
		return borad;
	}
	public void setBorad(String borad) {
		this.borad = borad;
	}
	public String getGurdainName() {
		return gurdainName;
	}
	public void setGurdainName(String gurdainName) {
		this.gurdainName = gurdainName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	

	@Override
	public String toString() {
		return "RegisterVO [userId=" + userId + ", firstName=" + firstName +",lastName = "+lastName+ ", address="
				+ address + ", age=" + age + ", gender=" + gender
				+ ", lastExamMarks=" + lastExamMarks + ", borad=" + borad
				+ ", gurdainName=" + gurdainName + ", phone=" + phone
				+ ", email=" + email + "]";
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
}
