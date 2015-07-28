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
		boolean authentic = false;
		String result = null;
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sql = "select * from exam_db.t_user where username=? and password=?";
		try {
			ps = conn.prepareStatement(sql);
			System.out.println("Prepared statement executed");
			ps.setString(1, uName);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String status  = rs.getString("status");
				int typeId = rs.getInt("type_id");
				if(status.equals("active")) {
					result = getUserType(typeId);					
				} else { 
					result = status;
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
	
	public String getUserType(int typeID) {
		String userType = null;
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sql = "select name from exam_db.t_user_type where id=?";
		try {
			ps  = conn.prepareStatement(sql);
			ps.setInt(1, typeID);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				userType = rs.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userType;
	}
	public int insertUserProfile(RegisterVO registerVO) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		int userId = 0;
		int typeId = insertIntoType(); 
		if(typeId != 0) {
			userId = insertUser(typeId, registerVO);
			if(userId != 0) {
				try {
					String sql = "INSERT INTO exam_db.t_user_profile(user_id,first_name,last_name,gurdain_name,age,gender,"
							+ "address,last_exam_marks,board,phone,email) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
					conn = DBHelper.getConnection();
					ps = conn.prepareStatement(sql);
					ps.setInt(1, userId);
					ps.setString(2, registerVO.getFirstName());
					ps.setString(3, registerVO.getLastName());
					ps.setString(4, registerVO.getGurdainName());
					ps.setInt(5, registerVO.getAge());
					ps.setString(6, registerVO.getGender());
					ps.setString(7, registerVO.getAddress());
					ps.setString(8, registerVO.getLastExamMarks());
					ps.setString(9, registerVO.getBorad());
					ps.setString(10, registerVO.getPhone());
					ps.setString(11, registerVO.getEmail());
					result = ps.executeUpdate();					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("User Id "+userId);
			}else
				System.out.println("Failed to insert into user");
		} else {
			System.out.println("Failed to insert into type");
		}
		return result;
	}
	public int insertIntoType() {
		int typeId = 0;
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		String sql = "INSERT INTO exam_db.t_user_type (name) VALUES (?)";
		try {
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, "student");
			int rowCount = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				typeId = rs.getInt(1);
			}
			System.out.println("Type id "+typeId);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return typeId;
	}
	
	public int insertUser(int typeId, RegisterVO registerVO) {
		int userId  = 0;
		PreparedStatement ps = null;
		Connection conn = DBHelper.getConnection();
		String sql = "INSERT INTO exam_db.t_user(username,password,created_at,updated_at,type_id,status) VALUES (?,?,?,?,?,?)";
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy  hh:mm:ss");
		String currentDate = df.format(new Date());
		try {
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, registerVO.getUserName());
			ps.setString(2, registerVO.getPassword());
			ps.setString(3, currentDate);
			ps.setString(4, currentDate);
			ps.setInt(5, typeId);
			ps.setString(6, "active");
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
				userId = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return userId;
	}
}
