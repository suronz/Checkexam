/**
 * LoginBean.java
 * 
 */

package com.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.dao.ExamDAO;
import com.util.SessionHelper;
import com.vo.ExamPaperVO;

public class ExamPaperInfoBean {
	private ExamPaperVO examPaperVO = new ExamPaperVO();
	private String examSecurityKey;
	

	public ExamPaperInfoBean() {
		String examName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("examName");
		String paperNo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("paperNo");
		System.out.println("--- "+examName+" -- "+paperNo );
		examPaperVO.setExamName(examName);
		examPaperVO.setPaperNo(paperNo);
		populateExamInfo();
	}

	private void populateExamInfo() {
		try {
			ExamDAO examDAO = new ExamDAO();
			examPaperVO = examDAO.getExamInfoFromNameAndPaperNo(examPaperVO.getExamName(), examPaperVO.getPaperNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//This method is used to redirect to exam page
	public String navigateExamPage() {
		boolean isAuthenticate = false;
		boolean isExamPerformed = false;
		String studId = (String) SessionHelper.getValueFromSession("userID");
		ExamDAO examDAO = new ExamDAO();
		isExamPerformed = examDAO.checkExamPerformed(examPaperVO.getExamName(), examPaperVO.getPaperNo(),studId);
		if(isExamPerformed){
			FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage(FacesMessage.SEVERITY_ERROR,"You have already performed this examination.", null));
			return null;
		}
		isAuthenticate = examDAO.authenticateExamPaper(examPaperVO.getExamName(), examPaperVO.getPaperNo(), examSecurityKey);
		if(isAuthenticate){
			return "examPage";
		}
		FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid Security Key !", null));
		return null;
	}
	
	//This method is used to redirect to exam list page
	public String navigateExamListPage() {
		return "examListPage";
	}

	/**
	 * @return the examPaperVO
	 */
	public ExamPaperVO getExamPaperVO() {
		return examPaperVO;
	}

	/**
	 * @param examPaperVO the examPaperVO to set
	 */
	public void setExamPaperVO(ExamPaperVO examPaperVO) {
		this.examPaperVO = examPaperVO;
	}

	public String getExamSecurityKey() {
		return examSecurityKey;
	}

	public void setExamSecurityKey(String examSecurityKey) {
		this.examSecurityKey = examSecurityKey;
	}

}