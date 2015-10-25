/**
 * 
 */
package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.util.DBHelper;
import com.util.ExamConstants;
import com.vo.ExamPaperVO;
import com.vo.QuestionVO;

/**
 * @author DIBYA
 *
 */
public class QuestionDAO {

	public int insertSingleQuestion(QuestionVO questionVO) {
		int rowCount = 0;
		int questionId = 0;
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "select max(question_id)+1 from "+DBHelper.DB_NAME+"t_question";
		try {
			ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				questionId = Integer.valueOf(rs.getString(1));
			}
		
			sqlQuery = "INSERT INTO "+DBHelper.DB_NAME+"t_question (question_id, question_type, question_desc, option_1, option_2, option_3, option_4, option_5, answer, ques_category) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		
			ps = conn.prepareStatement(sqlQuery);

			ps.setString(1, String.valueOf(questionId));
			ps.setString(2, questionVO.getTopic());
			ps.setString(3, questionVO.getQuestion());
			ps.setString(4, questionVO.getOption1());
			ps.setString(5, questionVO.getOption2());
			ps.setString(6, questionVO.getOption3());
			ps.setString(7, questionVO.getOption4());
			ps.setString(8, questionVO.getOption5());
			ps.setString(9, questionVO.getAnswer());
			ps.setString(10, questionVO.getQuestionCategory());
			
			rowCount = ps.executeUpdate();
			ps.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} 
		return rowCount;
	}
	
	public int insertParaQuestion(QuestionVO paraQuestionVO, List<QuestionVO> subQuestionVOList) {
		int rowCount = 0;
		int questionId = 0;
		int subQuestionId = 1;
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "select max(question_id)+1 from "+DBHelper.DB_NAME+"t_question";
		try {
			ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				questionId = Integer.valueOf(rs.getString(1));
			}
		
			sqlQuery = "INSERT INTO "+DBHelper.DB_NAME+"t_question (question_id, question_type, question_desc, ques_category, ques_title) "
				+ "VALUES (?,?,?,?,?)";
		
			ps = conn.prepareStatement(sqlQuery);

			ps.setString(1, String.valueOf(questionId));
			ps.setString(2, paraQuestionVO.getTopic());
			ps.setString(3, paraQuestionVO.getQuestion());
			ps.setString(4, paraQuestionVO.getQuestionCategory());
			ps.setString(5,paraQuestionVO.getQuesTitle());
			
			rowCount = ps.executeUpdate();
			ps.close();
			
			sqlQuery = "INSERT INTO "+DBHelper.DB_NAME+"t_sub_question (question_id, sub_question_id, question_desc, option_1, option_2, option_3, option_4, option_5, answer) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sqlQuery);
			
			for (QuestionVO questionVO : subQuestionVOList) {
				ps.setString(1, String.valueOf(questionId));
				ps.setString(2, String.valueOf(subQuestionId));
				ps.setString(3, questionVO.getQuestion());
				ps.setString(4, questionVO.getOption1());
				ps.setString(5, questionVO.getOption2());
				ps.setString(6, questionVO.getOption3());
				ps.setString(7, questionVO.getOption4());
				ps.setString(8, questionVO.getOption5());
				ps.setString(9, questionVO.getAnswer());
				ps.addBatch();
				subQuestionId++;
			}
			ps.executeBatch();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			rowCount = 0;
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public List<List<QuestionVO>> getAllQuestion() {
		List<List<QuestionVO>> allQuestionVOList= new ArrayList<List<QuestionVO>>();
		List<QuestionVO> singleQuestionVOList = new ArrayList<QuestionVO>();
		List<QuestionVO> paraQuestionVOList = new ArrayList<QuestionVO>();
		List<QuestionVO> imgQuestionVOList = new ArrayList<QuestionVO>();
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "select * from "+DBHelper.DB_NAME+"t_question order by question_type";
		try {
			ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			String quesCategory = null;
			while(rs.next()){
					QuestionVO questionVO = new QuestionVO();
		          //Retrieve by column name
					quesCategory = rs.getString("ques_category");
					if(StringUtils.isNotEmpty(quesCategory) && StringUtils.equals(quesCategory, "Single")) {
						questionVO.setQuestionId(rs.getString("question_id"));
						questionVO.setTopic(rs.getString("question_type"));
						questionVO.setQuestion(rs.getString("question_desc"));
						questionVO.setOption1(rs.getString("option_1"));
						questionVO.setOption2(rs.getString("option_2"));
						questionVO.setOption3(rs.getString("option_3"));
						questionVO.setOption4(rs.getString("option_4"));
						questionVO.setOption5(rs.getString("option_5"));
						questionVO.setAnswer(rs.getString("answer"));
						
						singleQuestionVOList.add(questionVO);
					} else if (StringUtils.isNotEmpty(quesCategory) && StringUtils.equals(quesCategory, "Paragraph")) {
						questionVO.setQuestionId(rs.getString("question_id"));
						questionVO.setTopic(rs.getString("question_type"));
						questionVO.setQuesTitle(rs.getString("ques_title"));
						
						paraQuestionVOList.add(questionVO);
					} else if (StringUtils.isNotEmpty(quesCategory) && StringUtils.equals(quesCategory, "Image")) {
						questionVO.setQuestionId(rs.getString("question_id"));
						questionVO.setTopic(rs.getString("question_type"));
						questionVO.setQuesTitle(rs.getString("ques_title"));
						
						imgQuestionVOList.add(questionVO);
					}
		       }
			allQuestionVOList.add(singleQuestionVOList);
			allQuestionVOList.add(paraQuestionVOList);
			allQuestionVOList.add(imgQuestionVOList);
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return allQuestionVOList;
	}
	
	public List<String> getAllAvailableExamPaperName() {
		List<String> availableExamNameList = new ArrayList<String>();
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "select name,paper_no from "+DBHelper.DB_NAME+"t_exampaper";
		try {
			ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
					String examName;
		          //Retrieve by column name
					examName = rs.getString("name");
					examName = examName + rs.getString("paper_no");
					
					availableExamNameList.add(examName.toUpperCase());
		       }
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return availableExamNameList;
	}
	
	public int insertExamPaper(ExamPaperVO examPaperVO,
			List<String> examPaperQuesList, String examKey, String userId) {
		int rowCount = 0;
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "INSERT INTO "+DBHelper.DB_NAME+"t_exampaper (name, paper_no, class, batch, start_date, start_time, time_type, exam_time, crtn_tms, exam_paper_key, created_by) "
				+ "VALUES (?,?,?,?,?,?,?,?,current_timestamp(),?,?)";
		try {
			ps = conn.prepareStatement(sqlQuery);

			ps.setString(1, examPaperVO.getExamName());
			ps.setString(2, examPaperVO.getPaperNo());
			ps.setString(3, examPaperVO.getClassCd());
			ps.setString(4, examPaperVO.getBatchCd());
			ps.setString(5, examPaperVO.getExamDate());
			ps.setString(6, examPaperVO.getExamTime());
			ps.setString(7, examPaperVO.getExamTimeType());
			ps.setString(8, examPaperVO.getExamDuration());
			ps.setString(9, examKey);
			ps.setString(10, userId);
			
			rowCount = ps.executeUpdate();
			ps.close();
			
			sqlQuery = "INSERT INTO "+DBHelper.DB_NAME+"t_exam_paper_ques_list (name,paper_no,question_id) values (?,?,?)";
			ps = conn.prepareStatement(sqlQuery);
			
			for (String questionId : examPaperQuesList) {
				ps.setString(1, examPaperVO.getExamName());
				ps.setString(2, examPaperVO.getPaperNo());
				ps.setString(3, questionId);
				ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public List<QuestionVO> getExamQuestions(String examName, String paperNo) {
		List<QuestionVO> questionVOList= new ArrayList<QuestionVO>();
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "select * from "+DBHelper.DB_NAME+"t_question where question_id "
				+ "in (select question_id from "+DBHelper.DB_NAME+"t_exam_paper_ques_list where name = ? and paper_no = ?)";
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, examName);
			ps.setString(2, paperNo);
			ResultSet rs = ps.executeQuery();
			String quesCategory = null;
			while(rs.next()){
					QuestionVO questionVO = new QuestionVO();
					
					quesCategory = rs.getString("ques_category");
					if(ExamConstants.QUES_TYPE_SINGLE.equals(quesCategory)){
						questionVO.setQuestionCategory(ExamConstants.QUES_TYPE_SINGLE);
						questionVO.setQuestionId(rs.getString("question_id"));
						questionVO.setTopic(rs.getString("question_type"));
						questionVO.setQuestion(rs.getString("question_desc"));
						questionVO.setOption1(rs.getString("option_1"));
						questionVO.setOption2(rs.getString("option_2"));
						questionVO.setOption3(rs.getString("option_3"));
						questionVO.setOption4(rs.getString("option_4"));
						questionVO.setOption5(rs.getString("option_5"));
						questionVO.setAnswer(rs.getString("answer"));
						
						questionVOList.add(questionVO);
					} else if(ExamConstants.QUES_TYPE_PARA.equals(quesCategory)){
						questionVO.setQuestionCategory(ExamConstants.QUES_TYPE_PARA);
						questionVO.setTopic(rs.getString("question_type"));
						questionVO.setQuestion(rs.getString("question_desc"));
						questionVOList.add(questionVO);
						
						getParaAndImgQuestions(rs.getString("question_id"),questionVOList, conn);
					} else if(ExamConstants.QUES_TYPE_IMG.equals(quesCategory)){
						questionVO.setQuestionCategory(ExamConstants.QUES_TYPE_IMG);
						questionVO.setTopic(rs.getString("question_type"));
						questionVO.setQuestion(rs.getString("question_desc"));
						questionVOList.add(questionVO);
						
						getParaAndImgQuestions(rs.getString("question_id"),questionVOList, conn);
					}
					
		       }
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return questionVOList;
	}

	private void getParaAndImgQuestions(String quesId, List<QuestionVO> questionVOList, Connection conn) {
		//Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "select * from "+DBHelper.DB_NAME+"t_sub_question where question_id = ?";
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, quesId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
					QuestionVO questionVO = new QuestionVO();
					
					questionVO.setQuestionId(quesId+ExamConstants.DOT_SYMBOL+rs.getString("sub_question_id"));
					//questionVO.setTopic(rs.getString("question_type"));
					questionVO.setQuestion(rs.getString("question_desc"));
					questionVO.setOption1(rs.getString("option_1"));
					questionVO.setOption2(rs.getString("option_2"));
					questionVO.setOption3(rs.getString("option_3"));
					questionVO.setOption4(rs.getString("option_4"));
					questionVO.setOption5(rs.getString("option_5"));
					questionVO.setAnswer(rs.getString("answer"));
					
					questionVOList.add(questionVO);
		       }
			ps.close();
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public int getNextQuestionId() {
		int rowCount = 0;
		int nextQuestionId = 0;
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "select max(question_id)+1 from "+DBHelper.DB_NAME+"t_question";
		try {
			ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				nextQuestionId = Integer.valueOf(rs.getString(1));
			}
			conn.close();
		} catch (SQLException e) {
			rowCount = 0;
			e.printStackTrace();
		}
		return nextQuestionId;
	}
	
	public int insertImgQuestion(QuestionVO paraQuestionVO, List<QuestionVO> subQuestionVOList, int questionId) {
		int rowCount = 0;
		int subQuestionId = 1;
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = null;
		try {
			sqlQuery = "INSERT INTO "+DBHelper.DB_NAME+"t_question (question_id, question_type, question_desc, ques_category, ques_title) "
				+ "VALUES (?,?,?,?,?)";
		
			ps = conn.prepareStatement(sqlQuery);

			ps.setString(1, String.valueOf(questionId));
			ps.setString(2, paraQuestionVO.getTopic());
			ps.setString(3, paraQuestionVO.getQuestion());
			ps.setString(4, paraQuestionVO.getQuestionCategory());
			ps.setString(5,paraQuestionVO.getQuesTitle());
			
			rowCount = ps.executeUpdate();
			ps.close();
			
			sqlQuery = "INSERT INTO "+DBHelper.DB_NAME+"t_sub_question (question_id, sub_question_id, question_desc, option_1, option_2, option_3, option_4, option_5, answer) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sqlQuery);
			
			for (QuestionVO questionVO : subQuestionVOList) {
				ps.setString(1, String.valueOf(questionId));
				ps.setString(2, String.valueOf(subQuestionId));
				ps.setString(3, questionVO.getQuestion());
				ps.setString(4, questionVO.getOption1());
				ps.setString(5, questionVO.getOption2());
				ps.setString(6, questionVO.getOption3());
				ps.setString(7, questionVO.getOption4());
				ps.setString(8, questionVO.getOption5());
				ps.setString(9, questionVO.getAnswer());
				ps.addBatch();
				subQuestionId++;
			}
			ps.executeBatch();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			rowCount = 0;
			e.printStackTrace();
		}
		return rowCount;
	}
}
