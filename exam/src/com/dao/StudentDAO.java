package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBHelper;
import com.vo.ExamPaperVO;
import com.vo.StudentVO;

public class StudentDAO {

	public List<StudentVO> getALLStudents()
	{
		List<StudentVO> studentList = new ArrayList<StudentVO>();
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "select first_name,last_name,username,password,created_at,status from "
				+DBHelper.DB_NAME+"t_user,"+DBHelper.DB_NAME+"t_user_profile"+" where "
				+DBHelper.DB_NAME+"t_user_profile.user_id = "+DBHelper.DB_NAME+"t_user.username"
				+" and type_id=2";

		try {
			ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				StudentVO studentVO = new StudentVO();
				String username = rs.getString("username");
				studentVO.setUserId(username);
				studentVO.setName(rs.getString("first_name")+rs.getString("last_name"));
				studentVO.setEmail(username);
				String status = rs.getString("status");
				studentVO.setStatus(status);
				if(status.equals("active"))
					studentVO.setStatImageName("stat_active.png");
				else if(status.equals("deactive"))
					studentVO.setStatImageName("stat_deactive.png");
				studentVO.setRegistredDate(rs.getString("created_at"));
				studentList.add(studentVO);
			}
			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return studentList;
	}

	public void chnageStudentStatus(List<String> studentIdList, String action)
	{
		
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = null;

		try {
			sqlQuery = "update "+DBHelper.DB_NAME+"t_user set status = ? where username = ?";
			ps = conn.prepareStatement(sqlQuery);
			for(String studentId : studentIdList)
			{
				ps.setString(1, action);
				ps.setString(2, studentId);
				ps.addBatch();			
			}
			ps.executeBatch();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
