package com.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.util.DBHelper;
import com.vo.RegisterVO;


public class RegisLoginDAO {
	public String checkUserAvail(String uName, String pass) {
		String result = null;
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sql = "select * from epariksh_exam_db.t_user where username=? and password=?";
		try {
			ps = conn.prepareStatement(sql);
			System.out.println("Prepared statement executed");
			ps.setString(1, uName);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String status  = rs.getString("status");
				String typeId  = rs.getString("type_id");
				if(status.equals("active") && typeId.equals("1")) {
					result = "admin";					
				} else if (status.equals("active") && typeId.equals("2")) { 
					result = "student";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//release the connection
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public void registerStudent(RegisterVO registerVO) {
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sqlQuery = "INSERT INTO epariksh_exam_db.t_user(username,password,created_at,updated_at,type_id,status) VALUES (?,?,current_timestamp(),current_timestamp(),?,?)";
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, registerVO.getUserName());
			ps.setString(2, registerVO.getPassword());
			ps.setString(3, "2");
			ps.setString(4, "active");
			ps.executeUpdate();
			
			ps.close();
			
			sqlQuery = "INSERT INTO epariksh_exam_db.t_user_profile(user_id,first_name,last_name,gurdain_name,age,gender,"
					+ "address,last_exam_marks,board,phone,email) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			
			ps = conn.prepareStatement(sqlQuery);
			
			ps.setString(1, registerVO.getUserName());
			ps.setString(2, registerVO.getFirstName());
			ps.setString(3, registerVO.getLastName());
			ps.setString(4, registerVO.getGurdainName());
			ps.setString(5, registerVO.getAge());
			ps.setString(6, registerVO.getGender());
			ps.setString(7, registerVO.getAddress());
			ps.setString(8, registerVO.getLastExamMarks());
			ps.setString(9, registerVO.getBorad());
			ps.setString(10, registerVO.getPhone());
			ps.setString(11, registerVO.getEmail());
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
