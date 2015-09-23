package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.dao.StudentDAO;
import com.vo.StudentVO;

public class ManageStudentBean {
	
	List<StudentVO> studentList = new ArrayList<StudentVO>();
	StudentDAO stuDAO = new StudentDAO();
	
	public ManageStudentBean()
	{
		populateAllStudent();
	}

	private void populateAllStudent() {
		
		
		setStudentList(stuDAO.getALLStudents());
	}

	public List<StudentVO> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentVO> studentList) {
		this.studentList = studentList;
	}
	
	public String changeStatus()
	{
		String actionStr = "";
		Map<String,String> params = 
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String action = params.get("action");
		List<String> studentIdList = new ArrayList<String>();
		for(StudentVO student : getStudentList())
			if(student.isSelectedStudent())
				studentIdList.add(student.getUserId());
		if(studentIdList.size() > 0)
		{
			stuDAO.chnageStudentStatus(studentIdList, action);
			actionStr="statchange";
		}
		System.out.println("Action "+action);
		populateAllStudent();
			
		return actionStr;
	}
	

}
