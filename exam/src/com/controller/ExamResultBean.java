/**
 * LoginBean.java
 * 
 */

package com.controller;

import com.util.SessionHelper;

public class ExamResultBean {
	private String examObtainedMarks;

	public ExamResultBean() {
		String examMarks = (String) SessionHelper.getValueFromSession("examObtainedMarks");
		setExamObtainedMarks(examMarks);
	}

	/**
	 * @return the examObtainedMarks
	 */
	public String getExamObtainedMarks() {
		return examObtainedMarks;
	}

	/**
	 * @param examObtainedMarks the examObtainedMarks to set
	 */
	public void setExamObtainedMarks(String examObtainedMarks) {
		this.examObtainedMarks = examObtainedMarks;
	}
	public String redirectToHome()
	{
		System.out.println("Inside redirect to home");
		String actionStr = null;
		SessionHelper.invalidateSession();
		actionStr = "login";
		return actionStr;
	}

	
}