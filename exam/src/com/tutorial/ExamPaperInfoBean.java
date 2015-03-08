/**
 * LoginBean.java
 * 
 */

package com.tutorial;

import javax.faces.context.FacesContext;

import com.dao.ExamDAO;
import com.vo.ExamPaperVO;

public class ExamPaperInfoBean {
	private ExamPaperVO examPaperVO = new ExamPaperVO();
	

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
		return "examPage";
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



}