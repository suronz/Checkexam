/**
 * LoginBean.java
 * 
 */

package com.tutorial;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.dao.RegisLoginDAO;

public class LoginBean
{
	private String name;
	private String password;
	RegisLoginDAO regisLoginDao;


	public String validateCredential() {
		System.out.println("Inside validate");
		String actioneStr = null;
		regisLoginDao = new RegisLoginDAO();
		String uName = getName();
		String pass = getPassword();
		System.out.println("\nuName : "+uName+"\nPass "+pass);
		String result  = regisLoginDao.checkUserAvail(uName, pass);
		System.out.println("Present "+result);
		if(result != null)
		{
			if(result.equals("admin"))
				actioneStr = "admin";
			else if(result.equals("student"))
				actioneStr = "student";
		}else
			FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage(FacesMessage.SEVERITY_ERROR,"please enter a valid username or password", null));
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

}