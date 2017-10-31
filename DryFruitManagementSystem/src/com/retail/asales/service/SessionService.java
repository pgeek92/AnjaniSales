package com.retail.asales.service;

import javax.servlet.http.HttpSession;

import com.retail.asales.entity.User;

public class SessionService {
	public User confirmSession(HttpSession httpSession) 
	{
		try{
			if (httpSession == null)
			{
				return null;
			}
			else {

				User user = (User) httpSession.getAttribute("login");
				if (user == null) {
					return null;
				}
				else
				{
					return user;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}