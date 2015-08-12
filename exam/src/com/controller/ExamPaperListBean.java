/**
 * LoginBean.java
 * 
 */

package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.dao.ExamDAO;
import com.util.SessionHelper;
import com.vo.ExamPaperVO;

public class ExamPaperListBean {
	private List<ExamPaperVO> examPaperList = new ArrayList<ExamPaperVO>();

	public ExamPaperListBean() {
		populateAllExamPaper();
	}

	private void populateAllExamPaper() {
		try {
			ExamDAO examDAO = new ExamDAO();
			String userId = (String) SessionHelper.getValueFromSession("userID");
			System.out.println("User id" +userId);
			setExamPaperList(examDAO.getAllExamPaper(userId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getExamPaperQuestions() {
		return null;
	}
	
	public String navigateExamInfoPage() {
		return "examInfoPage";
	}

	public List<ExamPaperVO> getExamPaperList() {
		return examPaperList;
	}

	public void setExamPaperList(List<ExamPaperVO> examPaperList) {
		this.examPaperList = examPaperList;
	}

	

}