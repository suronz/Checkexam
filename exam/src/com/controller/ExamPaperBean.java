/**
 * LoginBean.java
 * 
 */

package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.dao.QuestionDAO;
import com.util.SessionHelper;
import com.vo.ExamPaperVO;
import com.vo.QuestionVO;

public class ExamPaperBean {
	private List<QuestionVO> questionVOList = new ArrayList<QuestionVO>();
	private ExamPaperVO examPaperVO = new ExamPaperVO();
	public boolean divHide = false;
	private List<String> availableExamPaperNames = new ArrayList<String>(); 
	
	public ExamPaperBean() {
		populateAllQestion();
		divHide = false;
		populateAvailableExamPaperName();
	}

	private void populateAllQestion() {
		try {
			QuestionDAO questionDAO = new QuestionDAO();
			setQuestionVOList(questionDAO.getAllQuestion());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void populateAvailableExamPaperName() {
		try {
			QuestionDAO questionDAO = new QuestionDAO();
			setAvailableExamPaperNames(questionDAO.getAllAvailableExamPaperName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String createExamPaper() {
		String actioneStr = null;
		try {

			QuestionDAO questionDAO = new QuestionDAO();
			ExamPaperVO examPaperVO = getExamPaperVO();
			List<QuestionVO> examPaperVOList = getQuestionVOList();

			if((examPaperVO.getBatchCd().equals("")) || (examPaperVO.getClassCd().equals("")) || 
					(examPaperVO.getExamDate().equals("")) || (examPaperVO.getExamDuration().equals("")) 
					|| (examPaperVO.getExamName().equals("")) || (examPaperVO.getExamTime().equals("")) 
					|| (examPaperVO.getExamTimeType().equals("")) || (examPaperVO.getPaperNo().equals("")))

			{
				FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage(FacesMessage.SEVERITY_ERROR,"PLEASE FILLUP ALL MANDATORY FIELDS", null));
			}else
			{
				//  FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage(FacesMessage.SEVERITY_INFO,"QUESTION PAPER SUCESSESFULLY CREATED", null));
				List<String> questionList = new ArrayList<String>();
				for (QuestionVO questionVO : examPaperVOList) {
					if(questionVO.isSelectedQues())
					{
						System.out.println(questionVO.getQuestion());
						System.out.println(questionVO.getQuestionId());
						questionList.add(questionVO.getQuestionId());
					}
				}
				//if(questionList.size() >= 5)
				//{
				String examSecurityKey = Long.toHexString(Double.doubleToLongBits(Math.random()));
				String userId = (String) SessionHelper.getValueFromSession("userID");
				int result = questionDAO.insertExamPaper(examPaperVO, questionList, examSecurityKey, userId);
				FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage(FacesMessage.SEVERITY_INFO,"QUESTION PAPER SUCESSESFULLY CREATED", null));	
				//}
				//else
					//FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage(FacesMessage.SEVERITY_ERROR,"PLEASE SELECT AT LEAST 5 QUESTION", null));


				
			}
			clearExamVO();
			divHide = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actioneStr;
	}

	private void clearExamVO() {
		this.examPaperVO.setClassCd("");
		this.examPaperVO.setBatchCd("");
		this.examPaperVO.setExamDate("");
		this.examPaperVO.setExamDuration("");
		this.examPaperVO.setExamName("");
		this.examPaperVO.setExamTime("");
		this.examPaperVO.setExamTimeType("");
		this.examPaperVO.setPaperNo("");
		this.examPaperVO.setExamPaperLinkVal("");
		//this.setDisableCheckBox(true);
		List<QuestionVO> examPaperVOList = getQuestionVOList();
		for(QuestionVO questionvo : examPaperVOList)
		{
			if(questionvo.isSelectedQues())
				questionvo.setSelectedQues(false);
		}
		
	}

	public List<QuestionVO> getQuestionVOList() {
		return questionVOList;
	}

	public void setQuestionVOList(List<QuestionVO> questionVOList) {
		this.questionVOList = questionVOList;
	}

	public ExamPaperVO getExamPaperVO() {
		return examPaperVO;
	}

	public void setExamPaperVO(ExamPaperVO examPaperVO) {
		this.examPaperVO = examPaperVO;
	}

	public boolean isDivHide() {
		return divHide;
	}

	public void setDivHide(boolean divHide) {
		this.divHide = divHide;
	}

	public List<String> getAvailableExamPaperNames() {
		return availableExamPaperNames;
	}

	public void setAvailableExamPaperNames(List<String> availableExamPaperNames) {
		this.availableExamPaperNames = availableExamPaperNames;
	}
}