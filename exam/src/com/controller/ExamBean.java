/**
 * LoginBean.java
 * 
 */

package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;

import com.dao.ExamDAO;
import com.dao.QuestionDAO;
import com.util.SessionHelper;
import com.vo.ExamPaperVO;
import com.vo.ExamVO;
import com.vo.QuestionVO;

public class ExamBean {
	private QuestionVO questionVO = new QuestionVO();
	private String timeLeft;

	private String examAns;
	private int quesSeqNo = 0;
	private boolean isPopulate = true;
	private String renderButton;
	private String examObtainedMarks;
	private ExamPaperVO examPaperVO = new ExamPaperVO();
	private String isExamTimeUp = null;

	public ExamBean() {
		String isPopulate = String.valueOf(SessionHelper.getValueFromSession("isPopulate"));
		if (!StringUtils.equals(isPopulate, "N")) {
			System.out.println("Inside the isPopulate");
			populateExamQuestionSet();
			SessionHelper.setValueToSession("isPopulate", "N");
			SessionHelper.setValueToSession("examVOList", new ArrayList<ExamVO>());
			setRenderButton("Next");
		}

	}

	private void populateExamQuestionSet() {
		try {
			String examName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("examName");
			String paperNo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("paperNo");
			SessionHelper.setValueToSession("examName", examName);
			SessionHelper.setValueToSession("paperNo", paperNo);
			System.out.println("--- "+examName+" -- "+paperNo );
			ExamDAO examDAO = new ExamDAO();
			examPaperVO = examDAO.getExamInfoFromNameAndPaperNo(examName,paperNo);
			QuestionDAO questionDAO = new QuestionDAO();
			List<QuestionVO> examQuestionList = questionDAO.getExamQuestions(examName,paperNo);
			System.out.println("Exam list size :::"+examQuestionList.size());
			SessionHelper.setValueToSession("EXAMINFO", examPaperVO);
			SessionHelper.setValueToSession("examQuestionList", examQuestionList);
			//setQuestionVOList(examQuestionList);
			setQuestionVO(examQuestionList.get(0));
			//setPopulate(false);

			

			setTimeLeft(examPaperVO.getExamDuration());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String populateNextQuestion() {
		List<ExamVO> examVOList = new ArrayList<ExamVO>();
		List<QuestionVO> questionVOList = new ArrayList<QuestionVO>();
		//if (StringUtils.equals("Submit", getRenderButton())) {
		//	return "error";
		//} else {
		examPaperVO = (ExamPaperVO)SessionHelper.getValueFromSession("EXAMINFO");
		String quesSeqStr = String.valueOf(SessionHelper.getValueFromSession("quesSeqNo"));
		int quesSeq;
		if(StringUtils.isNumeric(quesSeqStr))
		{
			quesSeq = Integer.valueOf(quesSeqStr);
			//int quesSeq = getQuesSeqNo();
		}
		else
		{
			quesSeq = 0;
		}
		questionVOList = (List<QuestionVO>) SessionHelper.getValueFromSession("examQuestionList");
		QuestionVO currentQuestionVO = questionVOList.get(quesSeq);
		ExamVO examVO = new ExamVO();
		examVO.setQuesId(currentQuestionVO.getQuestionId());
		examVO.setExamAns(getExamAns());
		if (StringUtils.equals(currentQuestionVO.getAnswer(), getExamAns())) {
			examVO.setExamResult("Correct");
		} else {
			examVO.setExamResult("Wrong");
		}
		examVOList = (List<ExamVO>) SessionHelper.getValueFromSession("examVOList");
		examVOList.add(quesSeq, examVO);
		SessionHelper.removeValueFromSession("examVOList");
		SessionHelper.setValueToSession("examVOList", examVOList);

		quesSeq++;
		SessionHelper.setValueToSession("quesSeqNo", quesSeq);
		//setQuesSeqNo(quesSeq);

		if (questionVOList.size() > quesSeq) {
			setQuestionVO(questionVOList.get(quesSeq));
		}
		setExamAns(null);
		for (ExamVO examVO1 : examVOList) {
			System.out.println("Ques Id: " + examVO1.getQuesId());
			System.out.println(examVO1.getExamAns());
			System.out.println(examVO1.getExamResult());
		}

		if(isExamTimeUp.equals("Y"))
		{
			return saveStudentAns(examVOList);
		}

		if (questionVOList.size() == quesSeq + 1) {
			setRenderButton("Submit");
		}
		else if (questionVOList.size() > quesSeq + 1) 
		{
			setRenderButton("Next");	
		}
		else
		{
			return saveStudentAns(examVOList);
		}
		

		return null;
		//}
	}

	/**
	 * @param examVOList
	 * @return
	 */
	private String saveStudentAns(List<ExamVO> examVOList) {
		int examMarks = 0;
		for (ExamVO examVO1 : examVOList) {
			if(StringUtils.equals("Correct", examVO1.getExamResult()))
			{
				examMarks++;
			}
		}
		setExamObtainedMarks(String.valueOf(examMarks));
		SessionHelper.setValueToSession("examObtainedMarks", String.valueOf(examMarks));
		//Save student answer in DB
		ExamDAO examDAO = new ExamDAO();
		String examName = (String) SessionHelper.getValueFromSession("examName");
		String paperNo = (String) SessionHelper.getValueFromSession("paperNo");
		String studId = (String) SessionHelper.getValueFromSession("userID");
		examDAO.setStudExamAns(studId,examVOList,examName,paperNo,getExamObtainedMarks());
		return "examResultPage";
	}

	public QuestionVO getQuestionVO() {
		return questionVO;
	}

	public void setQuestionVO(QuestionVO questionVO) {
		this.questionVO = questionVO;
	}

	public int getQuesSeqNo() {
		return quesSeqNo;
	}

	public void setQuesSeqNo(int quesSeqNo) {
		this.quesSeqNo = quesSeqNo;
	}

	public String getExamAns() {
		return examAns;
	}

	public void setExamAns(String examAns) {
		this.examAns = examAns;
	}

	public boolean isPopulate() {
		return isPopulate;
	}

	public void setPopulate(boolean isPopulate) {
		this.isPopulate = isPopulate;
	}

	public String getRenderButton() {
		return renderButton;
	}

	public void setRenderButton(String renderButton) {
		this.renderButton = renderButton;
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

	public ExamPaperVO getExamPaperVO() {
		return examPaperVO;
	}

	public void setExamPaperVO(ExamPaperVO examPaperVO) {
		this.examPaperVO = examPaperVO;
	}

	public String getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(String timeLeft) {
		this.timeLeft = timeLeft;
	}

	public String getIsExamTimeUp() {
		return isExamTimeUp;
	}

	public void setIsExamTimeUp(String isExamTimeUp) {
		this.isExamTimeUp = isExamTimeUp;
	}



}
