/**
 * 
 */
package com.vo;

/**
 * @author DIBYA
 *
 */
public class QuestionVO {
	private String questionId;
	private String topic;
	private String quesTitle;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String option5;
	private String answer;
	private String questionCategory;
	
	private boolean selectedSingleQues;
	private boolean selectedParaQues;
	private boolean selectedImgQues;
	
	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(String questionCategory) {
		this.questionCategory = questionCategory;
	}

	public String getOption5() {
		return option5;
	}

	public void setOption5(String option5) {
		this.option5 = option5;
	}

	public String getQuesTitle() {
		return quesTitle;
	}

	public void setQuesTitle(String quesTitle) {
		this.quesTitle = quesTitle;
	}

	public boolean isSelectedSingleQues() {
		return selectedSingleQues;
	}

	public void setSelectedSingleQues(boolean selectedSingleQues) {
		this.selectedSingleQues = selectedSingleQues;
	}

	public boolean isSelectedParaQues() {
		return selectedParaQues;
	}

	public void setSelectedParaQues(boolean selectedParaQues) {
		this.selectedParaQues = selectedParaQues;
	}

	public boolean isSelectedImgQues() {
		return selectedImgQues;
	}

	public void setSelectedImgQues(boolean selectedImgQues) {
		this.selectedImgQues = selectedImgQues;
	}
	
}
