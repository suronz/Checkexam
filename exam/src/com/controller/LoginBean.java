/**
 * LoginBean.java
 * 
 */

package com.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.dao.RegisLoginDAO;
import com.util.SessionHelper;

public class LoginBean
{
	private String name;
	private String password;
	RegisLoginDAO regisLoginDao;


	public String validateCredential() {
		String actioneStr = null;
		regisLoginDao = new RegisLoginDAO();
		String uName = getName();
		String pass = getPassword();
		System.out.println("\nuName : "+uName+"\nPass "+pass);
		String result  = regisLoginDao.checkUserAvail(uName, pass);
		System.out.println("Present "+result);
		SessionHelper.setValueToSession("isPopulate", null);
		SessionHelper.setValueToSession("userID", uName);
		if(result != null)
		{
			if(result.equals("admin"))
				actioneStr = "admin";
			else if(result.equals("student"))
				actioneStr = "student";
			else if(result.equals("not active"))
				FacesContext.getCurrentInstance().addMessage(null, 
						new javax.faces.application.FacesMessage(
								FacesMessage.SEVERITY_ERROR,"Contact to admin to activate your profile", null));
		}else
			FacesContext.getCurrentInstance().addMessage(
					null, new javax.faces.application.FacesMessage(
							FacesMessage.SEVERITY_ERROR, "please enter a valid username or password", null));
			//result = "login";
		
		return actioneStr;
	}

	/* public void handleFileUpload(FileUploadEvent event) {
    	try {
    		InputStream file;
    		ExcelReader excelReader = new ExcelReader();
    		List<LoginBean> beans = new ArrayList<LoginBean>();
    		file = event.getFile().getInputstream();
    		beans = excelReader.fetchExcelData(file);
    		for (LoginBean loginBean : beans) {
				System.out.println(loginBean.getName()+"----"+loginBean.getPassword());
			}
    		 } catch (IOException e) {
    		 }

    }*/

	public String getName ()
	{
		return name;
	}


	public void setName (final String name)
	{
		this.name = name;
	}


	public String getPassword ()
	{
		return password;
	}


	public void setPassword (final String password)
	{
		this.password = password;
	}

	public RegisLoginDAO getRegisLoginDao() {
		return regisLoginDao;
	}

	public void setRegisLoginDao(RegisLoginDAO regisLoginDao) {
		this.regisLoginDao = regisLoginDao;
	}

	
}