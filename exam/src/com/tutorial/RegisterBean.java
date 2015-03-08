package com.tutorial;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.dao.RegisLoginDAO;
import com.vo.RegisterVO;
import com.vo.UserVO;

public class RegisterBean {
	
	private RegisterVO registerVO = new RegisterVO();
	private UserVO userVO = new UserVO();
	RegisLoginDAO regisLoginDao = new RegisLoginDAO();
	int result;
	
	
	
	public String createUser()
	{System.out.println("create user called");
	String actionStr = null;
	if((registerVO.getEmail().equals(""))|| (registerVO.getFirstName().equals(""))
	   || (registerVO.getLastName().equals("")) || (registerVO.getAge() == 0) || (registerVO.getAddress().equals(""))
	   || (registerVO.getBorad().equals("")) ||  (registerVO.getGurdainName().equals(""))
	   || (registerVO.getPassword().equals("")) || (registerVO.getPhone().equals("")))
	{
		System.out.println("Inside else part");
		FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage(FacesMessage.SEVERITY_ERROR,"PLEASE FILLUP ALL MANDATORY FIELD", null));
		clearRegisterVO();	
	}else
	{
		registerVO.setUserName(registerVO.getEmail());
		result  = regisLoginDao.insertUserProfile(registerVO);
	    FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage(FacesMessage.SEVERITY_INFO,"REGISTRATION SUCCESSFULLY", null));
		actionStr = "register";	
	}
	
	
	System.out.println("************Registration info : \n\n\n\n*********"+registerVO.toString());
	System.out.println("************User info : \n\n\n\n*********"+userVO.toString());
	return actionStr;
	}

	public void clearRegisterVO()
	{
		this.registerVO.setFirstName("");
		this.registerVO.setLastName("");
		this.registerVO.setGurdainName("");
		this.registerVO.setAge(0);
		this.registerVO.setAddress("");
		this.registerVO.setLastExamMarks("");
		this.registerVO.setBorad("");
		this.registerVO.setPhone("");
		this.registerVO.setEmail("");
		this.registerVO.setPassword("");
	}
	public RegisterVO getRegisterVO() {
		return registerVO;
	}
	public void setRegisterVO(RegisterVO registerVO) {
		this.registerVO = registerVO;
	}
	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

}
