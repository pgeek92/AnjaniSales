package com.retail.asales.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.retail.asales.DAO.UserJPADAO;
import com.retail.asales.DAOImpl.UserJPADAOImpl;
import com.retail.asales.entity.User;

public class LoginService {
	private static final Log logger = LogFactory.getLog(LoginService.class);
	public User confirmLogin(String userId, String password) {
		// BasicConfigurator.configure();
		// PropertyConfigurator.configure("resources/log4j.properties");
		UserJPADAO userDao = new UserJPADAOImpl();
		try {
			userDao.beginTransaction();
			User user = userDao.read(userId);

			logger.info("Keshav" + user);
			if (user == null) {
				return null;
			} else if (user.getPassword().equals(password)) {
				System.out.println(user.getUserId());
				System.out.println(user.getPassword());
				return user;
			}else{
				return null;
			}

		} catch (Exception e) {
			// handle exception
			e.printStackTrace();
			return null;
		}
		finally{
			userDao.closeTransaction();
		}
	}
}
