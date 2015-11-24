/**
 * LoginBean.java
 * 
 */

package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import com.dao.QuestionDAO;
import com.util.ExamConstants;
import com.util.FileUploadUtil;
import com.vo.QuestionVO;

public class AddQuestionBean {
	private QuestionVO questionVO = new QuestionVO();
	private String questionCategory = "Single";
	private String quesParagraph = null;
	private String quesParaTitle = null;
	private String quesImgTitle = null;
	private UploadedFile uploadImage = null;
	
	private String paraQuestionsStr = null;
	private String paraOption1Str = null;
	private String paraOption2Str = null;
	private String paraOption3Str = null;
	private String paraOption4Str = null;
	private String paraOption5Str = null;
	private String paraAnsStr = null;
	
	private String imgQuestionsStr = null;
	private String imgOption1Str = null;
	private String imgOption2Str = null;
	private String imgOption3Str = null;
	private String imgOption4Str = null;
	private String imgOption5Str = null;
	private String imgAnsStr = null;

	public AddQuestionBean() {
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
			int result = 0;
			if(StringUtils.equals(getQuestionCategory(), "Single")){
				getQuestionVO().setQuestionCategory(getQuestionCategory());
				result = questionDAO.insertSingleQuestion(getQuestionVO());
			} else if(StringUtils.equals(getQuestionCategory(), "Paragraph")) {
				QuestionVO paraQuestionVO = new QuestionVO();
				paraQuestionVO.setTopic(getQuestionVO().getTopic());
				paraQuestionVO.setQuesTitle(getQuesParaTitle());
				paraQuestionVO.setQuestion(quesParagraph);
				paraQuestionVO.setQuestionCategory(getQuestionCategory());
				
				List<QuestionVO> subQuestionVOList = new ArrayList<QuestionVO>();
				String[] paraQuestionDescArr = paraQuestionsStr.split(ExamConstants.QUES_SEPARATOR);
				String[] paraOption1Arr = paraOption1Str.split(ExamConstants.OPTION1_SEPARATOR);
				String[] paraOption2Arr = paraOption2Str.split(ExamConstants.OPTION2_SEPARATOR);
				String[] paraOption3Arr = paraOption3Str.split(ExamConstants.OPTION3_SEPARATOR);
				String[] paraOption4Arr = paraOption4Str.split(ExamConstants.OPTION4_SEPARATOR);
				String[] paraOption5Arr = paraOption5Str.split(ExamConstants.OPTION5_SEPARATOR);
				String[] paraAnsArr = paraAnsStr.split(ExamConstants.ANS_SEPARATOR);
				QuestionVO subQuestionVO = null;
				
				for (int i = 0; i < paraQuestionDescArr.length; i++) {
					subQuestionVO = new QuestionVO();
					subQuestionVO.setQuestion(paraQuestionDescArr[i]);
					subQuestionVO.setOption1(paraOption1Arr[i]);
					subQuestionVO.setOption2(paraOption2Arr[i]);
					subQuestionVO.setOption3(paraOption3Arr[i]);
					subQuestionVO.setOption4(paraOption4Arr[i]);
					subQuestionVO.setOption5(paraOption5Arr[i]);
					subQuestionVO.setAnswer(paraAnsArr[i]);
					
					subQuestionVOList.add(subQuestionVO);
				}
				
				result = questionDAO.insertParaQuestion(paraQuestionVO, subQuestionVOList );
			} else if (StringUtils.equals(getQuestionCategory(), "Image")) {
				String fileName = null;
				int nextQuesId = 0;
				nextQuesId = questionDAO.getNextQuestionId();
				if(nextQuesId > 0) {
					fileName = "img_ques_"+nextQuesId+".jpg";
					boolean imageUploadSuccessful = FileUploadUtil.uploadImage(uploadImage, fileName);
					
					if(imageUploadSuccessful){
						QuestionVO imgQuestionVO = new QuestionVO();
						imgQuestionVO.setTopic(getQuestionVO().getTopic());
						imgQuestionVO.setQuesTitle(getQuesImgTitle());
						imgQuestionVO.setQuestion(fileName);
						imgQuestionVO.setQuestionCategory(getQuestionCategory());
						
						List<QuestionVO> subQuestionVOList = new ArrayList<QuestionVO>();
						String[] imgQuestionDescArr = imgQuestionsStr.split(ExamConstants.QUES_SEPARATOR);
						String[] imgOption1Arr = imgOption1Str.split(ExamConstants.OPTION1_SEPARATOR);
						String[] imgOption2Arr = imgOption2Str.split(ExamConstants.OPTION2_SEPARATOR);
						String[] imgOption3Arr = imgOption3Str.split(ExamConstants.OPTION3_SEPARATOR);
						String[] imgOption4Arr = imgOption4Str.split(ExamConstants.OPTION4_SEPARATOR);
						String[] imgOption5Arr = imgOption5Str.split(ExamConstants.OPTION5_SEPARATOR);
						String[] imgAnsArr = imgAnsStr.split(ExamConstants.ANS_SEPARATOR);
						QuestionVO subQuestionVO = null;
						
						for (int i = 0; i < imgQuestionDescArr.length; i++) {
							subQuestionVO = new QuestionVO();
							subQuestionVO.setQuestion(imgQuestionDescArr[i]);
							subQuestionVO.setOption1(imgOption1Arr[i]);
							subQuestionVO.setOption2(imgOption2Arr[i]);
							subQuestionVO.setOption3(imgOption3Arr[i]);
							subQuestionVO.setOption4(imgOption4Arr[i]);
							subQuestionVO.setOption5(imgOption5Arr[i]);
							subQuestionVO.setAnswer(imgAnsArr[i]);
							
							subQuestionVOList.add(subQuestionVO);
						}
						
						result = questionDAO.insertImgQuestion(imgQuestionVO, subQuestionVOList, nextQuesId);
					} else {
						result = 0;
					}
				} else {
					result = 0;
				}
			}
			
			if (result != 0)
				FacesContext.getCurrentInstance().addMessage(
						null,
						new javax.faces.application.FacesMessage(
								FacesMessage.SEVERITY_INFO,
								"Successfully saved", null));
			else
				FacesContext.getCurrentInstance().addMessage(
						null,
						new javax.faces.application.FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"Problem during saving", null));
			clearQuestionVO();
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
		this.questionVO.setOption5("");
		this.questionVO.setAnswer("");
		this.quesParaTitle = "";
		this.quesParagraph = "";

		return actionStr;

	}

	public QuestionVO getQuestionVO() {
		return questionVO;
	}

	public void setQuestionVO(QuestionVO questionVO) {
		this.questionVO = questionVO;
	}

	public String getQuesParagraph() {
		return quesParagraph;
	}

	public void setQuesParagraph(String quesParagraph) {
		this.quesParagraph = quesParagraph;
	}

	public String getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(String questionCategory) {
		this.questionCategory = questionCategory;
	}

	public String getParaQuestionsStr() {
		return paraQuestionsStr;
	}

	public void setParaQuestionsStr(String paraQuestionsStr) {
		this.paraQuestionsStr = paraQuestionsStr;
	}

	public String getParaOption1Str() {
		return paraOption1Str;
	}

	public void setParaOption1Str(String paraOption1Str) {
		this.paraOption1Str = paraOption1Str;
	}

	public String getParaOption2Str() {
		return paraOption2Str;
	}

	public void setParaOption2Str(String paraOption2Str) {
		this.paraOption2Str = paraOption2Str;
	}

	public String getParaOption3Str() {
		return paraOption3Str;
	}

	public void setParaOption3Str(String paraOption3Str) {
		this.paraOption3Str = paraOption3Str;
	}

	public String getParaOption4Str() {
		return paraOption4Str;
	}

	public void setParaOption4Str(String paraOption4Str) {
		this.paraOption4Str = paraOption4Str;
	}

	public String getParaOption5Str() {
		return paraOption5Str;
	}

	public void setParaOption5Str(String paraOption5Str) {
		this.paraOption5Str = paraOption5Str;
	}

	public String getParaAnsStr() {
		return paraAnsStr;
	}

	public void setParaAnsStr(String paraAnsStr) {
		this.paraAnsStr = paraAnsStr;
	}

	public String getQuesParaTitle() {
		return quesParaTitle;
	}

	public void setQuesParaTitle(String quesParaTitle) {
		this.quesParaTitle = quesParaTitle;
	}

	public String getImgQuestionsStr() {
		return imgQuestionsStr;
	}

	public void setImgQuestionsStr(String imgQuestionsStr) {
		this.imgQuestionsStr = imgQuestionsStr;
	}

	public String getImgOption1Str() {
		return imgOption1Str;
	}

	public void setImgOption1Str(String imgOption1Str) {
		this.imgOption1Str = imgOption1Str;
	}

	public String getImgOption2Str() {
		return imgOption2Str;
	}

	public void setImgOption2Str(String imgOption2Str) {
		this.imgOption2Str = imgOption2Str;
	}

	public String getImgOption3Str() {
		return imgOption3Str;
	}

	public void setImgOption3Str(String imgOption3Str) {
		this.imgOption3Str = imgOption3Str;
	}

	public String getImgOption4Str() {
		return imgOption4Str;
	}

	public void setImgOption4Str(String imgOption4Str) {
		this.imgOption4Str = imgOption4Str;
	}

	public String getImgOption5Str() {
		return imgOption5Str;
	}

	public void setImgOption5Str(String imgOption5Str) {
		this.imgOption5Str = imgOption5Str;
	}

	public String getImgAnsStr() {
		return imgAnsStr;
	}

	public void setImgAnsStr(String imgAnsStr) {
		this.imgAnsStr = imgAnsStr;
	}

	public UploadedFile getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(UploadedFile uploadImage) {
		this.uploadImage = uploadImage;
	}

	public String getQuesImgTitle() {
		return quesImgTitle;
	}

	public void setQuesImgTitle(String quesImgTitle) {
		this.quesImgTitle = quesImgTitle;
	}
	

}