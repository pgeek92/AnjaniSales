package com.retail.asales.util;
import com.retail.asales.entity.User;


public class SessionUtility {
	private static User user;
	private static boolean isUserLoggedIn;
	
	public static boolean isUserLoggedIn() {
		return isUserLoggedIn;
	}
	
	public static void setUserLoggedIn(boolean isUserLoggedIn) {
		SessionUtility.isUserLoggedIn = isUserLoggedIn;
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		SessionUtility.user = user;
	}

	//not used now.
	public User getSessionUserObject()
	{
		//FacesContext context = FacesContext.getCurrentInstance();
		//this.user= (User) context.getExternalContext().getSessionMap().get("user");
		
		return null;
	}
}
