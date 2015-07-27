package com.util;

import javax.faces.context.FacesContext;

public class SessionHelper {

	//This method is used to get a value from Session
	public static Object getValueFromSession(String sessionKey) {
		Object sessionVal = null;
		try {
			sessionVal = FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().get(sessionKey);
			System.out.println("Session value is fetched for Key :: "
					+ sessionVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionVal;
	}
	
	//This method is used to set a value in Session
	public static void setValueToSession(String sessionKey, Object sessionVal) {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put(sessionKey, sessionVal);
			System.out.println("Session value is stored for Key :: "
					+ sessionKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//This method is used to remove a value from Session
		public static void removeValueFromSession(String sessionKey) {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap().remove(sessionKey);
				System.out.println("Session value is removed for Key :: "
						+ sessionKey);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		public static void invalidateSession()
		{
			try
			{
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}

}
