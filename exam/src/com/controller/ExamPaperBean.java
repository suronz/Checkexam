/**
 * LoginBean.java
 * 
 */

package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.dao.QuestionDAO;
import com.util.SessionHelper;
import com.vo.ExamPaperVO;
import com.vo.QuestionVO;

public class ExamPaperBean {
	private List<QuestionVO> singleQuestionVOList = new ArrayList<QuestionVO>();
	private List<QuestionVO> paraQuestionVOList = new ArrayList<QuestionVO>();
	private List<QuestionVO> imgQuestionVOList = new ArrayList<QuestionVO>();
	private ExamPaperVO examPaperVO = new ExamPaperVO();
	public boolean divHide = false;
	private List<String> availableExamPaperNames = new ArrayList<String>(); 
	private String quesParagraph = null;
	private String viewParaQuesId = null;
	
	public ExamPaperBean() {
		populateAllQestion();
		divHide = false;
		populateAvailableExamPaperName();
	}

	private void populateAllQestion() {
		try {
			QuestionDAO questionDAO = new QuestionDAO();
			List<List<QuestionVO>> allQuestionList = questionDAO.getAllQuestion();
			if(allQuestionList != null){
				if(allQuestionList.size()>0){
					singleQuestionVOList = allQuestionList.get(0);
				}
				if(allQuestionList.size()>1){
					paraQuestionVOList = allQuestionList.get(1);
				}
				if(allQuestionList.size()>2){
					imgQuestionVOList = allQuestionList.get(2);
				}
			}
			//setQuestionVOList(questionDAO.getAllQuestion());
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
			List<QuestionVO> examPaperSingleVOList = getSingleQuestionVOList();
			List<QuestionVO> examPaperParaVOList = getParaQuestionVOList();
			List<QuestionVO> examPaperImgVOList = getImgQuestionVOList(); 

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
				for (QuestionVO questionVO : examPaperSingleVOList) {
					if(questionVO.isSelectedSingleQues())
					{
						System.out.println(questionVO.getQuestion());
						System.out.println(questionVO.getQuestionId());
						questionList.add(questionVO.getQuestionId());
					}
				}
				
				for (QuestionVO questionVO : examPaperParaVOList) {
					if(questionVO.isSelectedParaQues())
					{
						System.out.println(questionVO.getQuestion());
						System.out.println(questionVO.getQuestionId());
						questionList.add(questionVO.getQuestionId());
					}
				}
				
				for (QuestionVO questionVO : examPaperImgVOList) {
					if(questionVO.isSelectedImgQues())
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
		List<QuestionVO> examPaperVOList = getSingleQuestionVOList();
		for(QuestionVO questionvo : examPaperVOList)
		{
			if(questionvo.isSelectedSingleQues())
				questionvo.setSelectedSingleQues(false);
		}
		
		examPaperVOList = getParaQuestionVOList();
		for(QuestionVO questionvo : examPaperVOList)
		{
			if(questionvo.isSelectedParaQues())
				questionvo.setSelectedParaQues(false);
		}
		
	}
	
/*	public void showParagraph(AjaxBehaviorEvent event) {
		System.out.println("Successful Ajax Call........");
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String action = params.get("paraQuestionId");
		event.getComponent().getAttributes().get("paraQuestionId");
		quesParagraph = action;
	}*/

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

	public List<QuestionVO> getSingleQuestionVOList() {
		return singleQuestionVOList;
	}

	public void setSingleQuestionVOList(List<QuestionVO> singleQuestionVOList) {
		this.singleQuestionVOList = singleQuestionVOList;
	}

	public List<QuestionVO> getParaQuestionVOList() {
		return paraQuestionVOList;
	}

	public void setParaQuestionVOList(List<QuestionVO> paraQuestionVOList) {
		this.paraQuestionVOList = paraQuestionVOList;
	}

	public String getQuesParagraph() {
		return quesParagraph;
	}

	public void setQuesParagraph(String quesParagraph) {
		this.quesParagraph = quesParagraph;
	}

	public String getViewParaQuesId() {
		return viewParaQuesId;
	}

	public void setViewParaQuesId(String viewParaQuesId) {
		this.viewParaQuesId = viewParaQuesId;
	}

	public List<QuestionVO> getImgQuestionVOList() {
		return imgQuestionVOList;
	}

	public void setImgQuestionVOList(List<QuestionVO> imgQuestionVOList) {
		this.imgQuestionVOList = imgQuestionVOList;
	}
}