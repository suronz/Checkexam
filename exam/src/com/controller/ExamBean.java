/**
 * LoginBean.java
 * 
 */

package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang.StringUtils;

import com.dao.ExamDAO;
import com.dao.QuestionDAO;
import com.util.ExamConstants;
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
	private String examQuesPara = null;
	private String quesParaStyle = null;
	private String quesImgStyle = null;
	private String examQuesImg = null;

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
			QuestionVO firstQuestionVO = examQuestionList.get(0);
			if(ExamConstants.QUES_TYPE_SINGLE.equals(firstQuestionVO.getQuestionCategory())){
				setExamQuesPara("");
				setQuestionVO(firstQuestionVO);
				setQuesParaStyle("display : none;");
				setQuesImgStyle("display : none;");
				SessionHelper.setValueToSession("quesSeqNo", 0);
				SessionHelper.setValueToSession("currentQuesType", ExamConstants.QUES_TYPE_SINGLE);
			} else if (ExamConstants.QUES_TYPE_PARA.equals(firstQuestionVO.getQuestionCategory())){
				setExamQuesPara(firstQuestionVO.getQuestion());
				setQuestionVO(examQuestionList.get(1));
				setQuesParaStyle("display : inline;");
				setQuesImgStyle("display : none;");
				SessionHelper.setValueToSession("examQuesPara", getExamQuesPara());
				SessionHelper.setValueToSession("quesSeqNo", 1);
				SessionHelper.setValueToSession("currentQuesType", ExamConstants.QUES_TYPE_PARA);
			} else if (ExamConstants.QUES_TYPE_IMG.equals(firstQuestionVO.getQuestionCategory())){
				setExamQuesImg(firstQuestionVO.getQuestion());
				setQuestionVO(examQuestionList.get(1));
				setQuesParaStyle("display : none;");
				setQuesImgStyle("display : inline;");
				SessionHelper.setValueToSession("examQuesImg", getExamQuesImg());
				SessionHelper.setValueToSession("quesSeqNo", 1);
				SessionHelper.setValueToSession("currentQuesType", ExamConstants.QUES_TYPE_IMG);
			}
			
			//setPopulate(false);

			

			setTimeLeft(examPaperVO.getExamDuration());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*@SuppressWarnings("unchecked")
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

		if(StringUtils.equals(isExamTimeUp, "Y"))
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
	}*/

	/**
	 * @param examVOList
	 * @return
	 * @throws IOException 
	 */
	private void saveStudentAns(List<ExamVO> examVOList) throws IOException {
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
		//return "examResultPage";
		FacesContext.getCurrentInstance().getExternalContext().redirect("examResult.xhtml");
	}
	
	@SuppressWarnings("unchecked")
	public void generateNextQuestion(AjaxBehaviorEvent event) throws IOException {
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
		examVOList.add(examVO);
		SessionHelper.removeValueFromSession("examVOList");
		SessionHelper.setValueToSession("examVOList", examVOList);
		
		quesSeq++;
		
		//setQuesSeqNo(quesSeq);

		if (questionVOList.size() > quesSeq) {
			if(ExamConstants.QUES_TYPE_PARA.equals(questionVOList.get(quesSeq).getQuestionCategory())){
				setExamQuesPara(questionVOList.get(quesSeq).getQuestion());
				SessionHelper.setValueToSession("examQuesPara", getExamQuesPara());
				quesSeq++;
				setQuestionVO(questionVOList.get(quesSeq));
				setQuesParaStyle("display : inline;");
				setQuesImgStyle("display : none;");
				SessionHelper.setValueToSession("currentQuesType", ExamConstants.QUES_TYPE_PARA);
			} else if(ExamConstants.QUES_TYPE_IMG.equals(questionVOList.get(quesSeq).getQuestionCategory())){
				setExamQuesImg(questionVOList.get(quesSeq).getQuestion());
				SessionHelper.setValueToSession("examQuesImg", getExamQuesImg());
				quesSeq++;
				setQuestionVO(questionVOList.get(quesSeq));
				setQuesParaStyle("display : none;");
				setQuesImgStyle("display : inline;");
				SessionHelper.setValueToSession("currentQuesType", ExamConstants.QUES_TYPE_IMG);
			} else if(ExamConstants.QUES_TYPE_SINGLE.equals(questionVOList.get(quesSeq).getQuestionCategory())){
				setExamQuesPara("");
				setQuestionVO(questionVOList.get(quesSeq));
				setQuesParaStyle("display : none;");
				setQuesImgStyle("display : none;");
			} else {
				String currentQuesType = (String) SessionHelper.getValueFromSession("currentQuesType");
				if(StringUtils.equals(ExamConstants.QUES_TYPE_PARA, currentQuesType)){
					String quesPara = (String) SessionHelper.getValueFromSession("examQuesPara");
					setExamQuesPara(quesPara);
					setQuestionVO(questionVOList.get(quesSeq));
					setQuesParaStyle("display : inline;");
					setQuesImgStyle("display : none;");
				} else if(StringUtils.equals(ExamConstants.QUES_TYPE_IMG, currentQuesType)){
					String quesImg = (String) SessionHelper.getValueFromSession("examQuesImg");
					setExamQuesImg(quesImg);
					setQuestionVO(questionVOList.get(quesSeq));
					setQuesParaStyle("display : none;");
					setQuesImgStyle("display : inline;");
				}
				
			}
		}
		
		
		SessionHelper.setValueToSession("quesSeqNo", quesSeq);
		setExamAns(null);
		for (ExamVO examVO1 : examVOList) {
			System.out.println("Ques Id: " + examVO1.getQuesId());
			System.out.println(examVO1.getExamAns());
			System.out.println(examVO1.getExamResult());
		}

		if(StringUtils.equals(isExamTimeUp, "Y"))
		{
			saveStudentAns(examVOList);
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
			saveStudentAns(examVOList);
		}
		

		//return null;
		//}
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

	public String getExamQuesPara() {
		return examQuesPara;
	}

	public void setExamQuesPara(String examQuesPara) {
		this.examQuesPara = examQuesPara;
	}

	public String getQuesParaStyle() {
		return quesParaStyle;
	}

	public void setQuesParaStyle(String quesParaStyle) {
		this.quesParaStyle = quesParaStyle;
	}

	public String getQuesImgStyle() {
		return quesImgStyle;
	}

	public void setQuesImgStyle(String quesImgStyle) {
		this.quesImgStyle = quesImgStyle;
	}

	public String getExamQuesImg() {
		return examQuesImg;
	}

	public void setExamQuesImg(String examQuesImg) {
		this.examQuesImg = examQuesImg;
	}



}
