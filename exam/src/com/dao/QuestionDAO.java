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

import com.util.DBHelper;
import com.vo.ExamPaperVO;
import com.vo.QuestionVO;

/**
 * @author DIBYA
 *
 */
public class QuestionDAO {

	public int insertQuestion(QuestionVO questionVO) {
		int rowCount = 0;
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "INSERT INTO exam_db.t_question (question_type, question_desc, option_1, option_2, option_3, option_4, answer) "
				+ "VALUES (?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sqlQuery);
			if(!(questionVO.getTopic().equals("")))
			{
				ps.setString(1, questionVO.getTopic());
				
			}else
				return rowCount;
			if(!(questionVO.getQuestion().equals("")))
			{
				ps.setString(2, questionVO.getQuestion());
				
			}else
				return rowCount;
			if(!(questionVO.getOption1().equals("")))
			{
				ps.setString(3, questionVO.getOption1());
				
			}else
				return rowCount;
			if(!(questionVO.getOption2().equals("")))
			{
				ps.setString(4, questionVO.getOption2());
				
			}else
				return rowCount;
			if(!(questionVO.getOption3().equals("")))
			{
			ps.setString(5, questionVO.getOption3());
			
			}else
				return rowCount;
			if(!(questionVO.getOption4().equals("")))
			{
			ps.setString(6, questionVO.getOption4());
			
			}else
				return rowCount;
			if(!(questionVO.getAnswer().equals("")))
			{
			ps.setString(7, questionVO.getAnswer());
			
			}else
				return rowCount;
			rowCount = ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rowCount;
	}
	
	public List<QuestionVO> getAllQuestion() {
		List<QuestionVO> questionVOList= new ArrayList<QuestionVO>();
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "select * from exam_db.t_question order by question_type";
		try {
			ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
					QuestionVO questionVO = new QuestionVO();
		          //Retrieve by column name
					questionVO.setQuestionId(rs.getString("question_id"));
					questionVO.setTopic(rs.getString("question_type"));
					questionVO.setQuestion(rs.getString("question_desc"));
					questionVO.setOption1(rs.getString("option_1"));
					questionVO.setOption2(rs.getString("option_2"));
					questionVO.setOption3(rs.getString("option_3"));
					questionVO.setOption4(rs.getString("option_4"));
					questionVO.setAnswer(rs.getString("answer"));
					
					questionVOList.add(questionVO);
		       }
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return questionVOList;
	}
	
	public int insertExamPaper(ExamPaperVO examPaperVO,
			List<String> examPaperQuesList, String examKey) {
		int rowCount = 0;
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "INSERT INTO exam_db.t_exampaper (name, paper_no, class, batch, start_date, start_time, time_type, exam_time, crtn_tms, exam_paper_key) "
				+ "VALUES (?,?,?,?,?,?,?,?,current_timestamp(),?)";
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
			
			rowCount = ps.executeUpdate();
			ps.close();
			
			sqlQuery = "INSERT INTO exam_db.t_exam_paper_ques_list (name,paper_no,question_id) values (?,?,?)";
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
		String sqlQuery = "select * from exam_db.t_question where question_id "
				+ "in (select question_id from exam_db.t_exam_paper_ques_list where name = ? and paper_no = ?)";
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, examName);
			ps.setString(2, paperNo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
					QuestionVO questionVO = new QuestionVO();
		          //Retrieve by column name
					questionVO.setQuestionId(rs.getString("question_id"));
					questionVO.setTopic(rs.getString("question_type"));
					questionVO.setQuestion(rs.getString("question_desc"));
					questionVO.setOption1(rs.getString("option_1"));
					questionVO.setOption2(rs.getString("option_2"));
					questionVO.setOption3(rs.getString("option_3"));
					questionVO.setOption4(rs.getString("option_4"));
					questionVO.setAnswer(rs.getString("answer"));
					
					questionVOList.add(questionVO);
		       }
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return questionVOList;
	}


}
