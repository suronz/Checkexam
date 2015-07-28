package com.vo;


public class ExamPaperVO {
	private String classCd;
	private String batchCd;
	private String examDate;
	private String examTime;
	private String examName;
	private String paperNo;
	private String examTimeType;
	private String examDuration;
	private String examSecurityKey;
	
	private String examPaperLinkVal;
	
	public String getClassCd() {
		return classCd;
	}
	public void setClassCd(String classCd) {
		this.classCd = classCd;
	}
	public String getBatchCd() {
		return batchCd;
	}
	public void setBatchCd(String batchCd) {
		this.batchCd = batchCd;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getPaperNo() {
		return paperNo;
	}
	public void setPaperNo(String paperNo) {
		this.paperNo = paperNo;
	}
	public String getExamTimeType() {
		return examTimeType;
	}
	public void setExamTimeType(String examTimeType) {
		this.examTimeType = examTimeType;
	}
	public String getExamDuration() {
		return examDuration;
	}
	public void setExamDuration(String examDuration) {
		this.examDuration = examDuration;
	}
	public String getExamPaperLinkVal() {
		return examPaperLinkVal;
	}
	public void setExamPaperLinkVal(String examPaperLinkVal) {
		this.examPaperLinkVal = examPaperLinkVal;
	}
	public String getExamSecurityKey() {
		return examSecurityKey;
	}
	public void setExamSecurityKey(String examSecurityKey) {
		this.examSecurityKey = examSecurityKey;
	}
	
}
