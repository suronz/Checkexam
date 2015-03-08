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
public class ExamDAO {

	public int insertQuestion(QuestionVO questionVO) {
		int rowCount = 0;
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "INSERT INTO exam_db.t_question (question_type, question_desc, option_1, option_2, option_3, option_4, answer) "
				+ "VALUES (?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, questionVO.getTopic());
			ps.setString(2, questionVO.getQuestion());
			ps.setString(3, questionVO.getOption1());
			ps.setString(4, questionVO.getOption2());
			ps.setString(5, questionVO.getOption3());
			ps.setString(6, questionVO.getOption4());
			ps.setString(7, questionVO.getAnswer());
			rowCount = ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rowCount;
	}
	
	public List<ExamPaperVO> getAllExamPaper() {
		List<ExamPaperVO> examPaperVOList= new ArrayList<ExamPaperVO>();
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "select * from exam_db.t_exampaper";
		try {
			ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
					ExamPaperVO examPaperVO = new ExamPaperVO();
		          //Retrieve by column name
					examPaperVO.setExamName(rs.getString("name"));
					examPaperVO.setPaperNo(rs.getString("paper_no"));
					examPaperVO.setClassCd(rs.getString("class"));
					examPaperVO.setBatchCd(rs.getString("batch"));
					examPaperVO.setExamDate(rs.getString("start_date"));
					examPaperVO.setExamTime(rs.getString("start_time"));
					examPaperVO.setExamDuration(rs.getString("exam_time"));
					examPaperVO.setExamPaperLinkVal(rs.getString("name")+"$"+rs.getString("paper_no"));
					
					examPaperVOList.add(examPaperVO);
		       }
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return examPaperVOList;
	}
	
	public ExamPaperVO getExamInfoFromNameAndPaperNo(String examName, String paperNo) {
		ExamPaperVO examPaperVO = new ExamPaperVO();
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "SELECT * FROM exam_db.t_exampaper WHERE name=? and paper_no = ?";
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, examName);
			ps.setString(2, paperNo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
		          //Retrieve by column name
					examPaperVO.setExamName(rs.getString("name"));
					examPaperVO.setPaperNo(rs.getString("paper_no"));
					examPaperVO.setClassCd(rs.getString("class"));
					examPaperVO.setBatchCd(rs.getString("batch"));
					examPaperVO.setExamDate(rs.getString("start_date"));
					examPaperVO.setExamTime(rs.getString("start_time"));
					examPaperVO.setExamDuration(rs.getString("exam_time"));
					examPaperVO.setExamPaperLinkVal(rs.getString("name")+"$"+rs.getString("paper_no"));
		       }
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return examPaperVO;
	}
	
	public int insertExamPaper(ExamPaperVO examPaperVO,
			List<String> examPaperQuesList) {
		int rowCount = 0;
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "INSERT INTO exam_db.t_exampaper (name, paper_no, class, batch, start_date, start_time, time_type, exam_time, crtn_tms) "
				+ "VALUES (?,?,?,?,?,?,?,?,current_timestamp())";
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

}
