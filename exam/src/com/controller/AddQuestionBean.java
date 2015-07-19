/**
 * LoginBean.java
 * 
 */

package com.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.dao.QuestionDAO;
import com.vo.QuestionVO;


public class AddQuestionBean
{
	private QuestionVO questionVO = new QuestionVO();
	public boolean hideDiv = false;
	
	public AddQuestionBean()
	{
		hideDiv = false;
	}

	public String createQuestion() {
		String actioneStr = null;
		try {
			System.out.println(getQuestionVO().getTopic());
			System.out.println(getQuestionVO().getQuestion());
			System.out.println(getQuestionVO().getOption1());
			System.out.println(getQuestionVO().getOption2());
			System.out.println(getQuestionVO().getOption3());
			System.out.println(getQuestionVO().getOption4());
			System.out.println(getQuestionVO().getAnswer());

			QuestionDAO questionDAO = new QuestionDAO();
			int result = questionDAO.insertQuestion(getQuestionVO());
			if(result != 0)
				FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage(FacesMessage.SEVERITY_INFO,"Successfully saved", null));
			else
				FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage(FacesMessage.SEVERITY_ERROR,"Problem during saving", null));
			clearQuestionVO();
			hideDiv = true;
			actioneStr = "Save";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actioneStr;
	}

	

	public String clearQuestionVO() {
		String actionStr = null;
		this.questionVO.setQuestion("");
		this.questionVO.setTopic("");
		this.questionVO.setOption1("");
		this.questionVO.setOption2("");
		this.questionVO.setOption3("");
		this.questionVO.setOption4("");
		this.questionVO.setAnswer("");
		
		return actionStr;


	}

	public QuestionVO getQuestionVO() {
		return questionVO;
	}

	public void setQuestionVO(QuestionVO questionVO) {
		this.questionVO = questionVO;
	}
	public boolean isDivHide() {
		return hideDiv;
	}

	public void setDivHide(boolean divHide) {
		this.hideDiv = divHide;
	}
}