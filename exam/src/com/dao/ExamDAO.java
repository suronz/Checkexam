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
import com.vo.ExamVO;
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
		String sqlQuery = "INSERT INTO "+DBHelper.DB_NAME+"t_question (question_type, question_desc, option_1, option_2, option_3, option_4, answer) "
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
	
	public List<ExamPaperVO> getAllExamPaper(String userId) {
		List<ExamPaperVO> examPaperVOList = new ArrayList<ExamPaperVO>();
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = null;
		try {
			if (DBHelper.ANGEL_INST_ADMIN1.equals(userId) || DBHelper.ANGEL_INST_ADMIN2.equals(userId)) {
				sqlQuery = "select * from "+DBHelper.DB_NAME+"t_exampaper where created_by = ?";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, userId);
			} else {
				sqlQuery = "select * from "+DBHelper.DB_NAME+"t_exampaper";
				ps = conn.prepareStatement(sqlQuery);
			}

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ExamPaperVO examPaperVO = new ExamPaperVO();
				// Retrieve by column name
				examPaperVO.setExamName(rs.getString("name"));
				examPaperVO.setPaperNo(rs.getString("paper_no"));
				examPaperVO.setClassCd(rs.getString("class"));
				examPaperVO.setBatchCd(rs.getString("batch"));
				examPaperVO.setExamDate(rs.getString("start_date"));
				examPaperVO.setExamTime(rs.getString("start_time"));
				examPaperVO.setExamDuration(rs.getString("exam_time"));
				examPaperVO.setExamPaperLinkVal(rs.getString("name") + "$"
						+ rs.getString("paper_no"));
				examPaperVO.setExamSecurityKey(rs.getString("exam_paper_key"));

				examPaperVOList.add(examPaperVO);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return examPaperVOList;
	}
	
	//temp testing purpose added
	public List<ExamPaperVO> getAllExamPaperForStudent(String userId) {
		List<ExamPaperVO> examPaperVOList= new ArrayList<ExamPaperVO>();
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "select * from "+DBHelper.DB_NAME+"t_exampaper";
		try {
			ps = conn.prepareStatement(sqlQuery);
			//ps.setString(1, userId);
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
					examPaperVO.setExamSecurityKey(rs.getString("exam_paper_key"));
					
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
		String sqlQuery = "SELECT * FROM "+DBHelper.DB_NAME+"t_exampaper WHERE name=? and paper_no = ?";
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
	
	public boolean authenticateExamPaper(String examName, String paperNo, String examKey) {
		boolean isAuthenticate = false;
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "SELECT * FROM "+DBHelper.DB_NAME+"t_exampaper WHERE name=? and paper_no = ? and exam_paper_key = ?";
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, examName);
			ps.setString(2, paperNo);
			ps.setString(3, examKey);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
		        //Retrieve by column name
				isAuthenticate = true;	
		       }
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isAuthenticate;
	}
	
	public int insertExamPaper(ExamPaperVO examPaperVO,
			List<String> examPaperQuesList) {
		int rowCount = 0;
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "INSERT INTO "+DBHelper.DB_NAME+"t_exampaper (name, paper_no, class, batch, start_date, start_time, time_type, exam_time, crtn_tms, exam_paper_key) "
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
			ps.setString(9, examPaperVO.getExamDuration());

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

	public void setStudExamAns(String studId, List<ExamVO> examVOList, String examName, String paperNo, String totalMarks) {
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "INSERT INTO "+DBHelper.DB_NAME+"t_stud_exam_ans (stud_id,exam_name,paper_no,question_id,sub_question_id,stud_ans,stud_ans_status) values (?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sqlQuery);
			
			for (ExamVO examVO : examVOList) {
				String quesId = examVO.getQuesId();
				String subQuesId = "";
				if(StringUtils.contains(quesId, ExamConstants.DOT_SYMBOL)){
					String[] quesIdArr = quesId.split("\\.");
					quesId = quesIdArr[0];
					subQuesId = quesIdArr[1];
				}
				ps.setString(1, studId);
				ps.setString(2, examName);
				ps.setString(3, paperNo);
				ps.setString(4, quesId);
				ps.setString(5, subQuesId);
				ps.setString(6, examVO.getExamAns());
				ps.setString(7, examVO.getExamResult());
				ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
			
			sqlQuery = "INSERT INTO "+DBHelper.DB_NAME+"t_stud_exam_result (stud_id,exam_name,paper_no,total_marks) values (?,?,?,?)";
			ps = conn.prepareStatement(sqlQuery);
			
			ps.setString(1, studId);
			ps.setString(2, examName);
			ps.setString(3, paperNo);
			ps.setString(4, totalMarks);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean checkExamPerformed(String examName, String paperNo, String studId) {
		boolean isExamPerformed = false;
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		int count = 0;
		String sqlQuery = "select count(*) from "+DBHelper.DB_NAME+"t_stud_exam_result where exam_name = ? and paper_no = ? and stud_id = ?";
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, examName);
			ps.setString(2, paperNo);
			ps.setString(3, studId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
		        //Retrieve by column name
				count = rs.getInt(1);	
		       }
			if(count != 0){
				isExamPerformed = true;
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isExamPerformed;
	}

}
