package com.retail.asales.service;

import com.retail.asales.DAO.RegisterJPADAO;
import com.retail.asales.DAO.SecureQuestJPADAO;
import com.retail.asales.DAO.UserJPADAO;
import com.retail.asales.DAOImpl.RegisterJPADAOImpl;
import com.retail.asales.DAOImpl.SecureQuestJPADAOImpl;
import com.retail.asales.DAOImpl.UserJPADAOImpl;
import com.retail.asales.entity.User;
import com.retail.asales.exceptions.DryFruitBusinessException;
import com.retail.asales.models.RegisterBean;

public class RegisterService {
	public void addNewUser(RegisterBean registerBean) throws DryFruitBusinessException, Exception{
		RegisterJPADAO registerJPADAO = new RegisterJPADAOImpl();
		UserJPADAO userJPADAO = new UserJPADAOImpl();
		try {
			registerJPADAO.beginTransaction();
			User user = userJPADAO.read(registerBean.getUserId().trim());
			if(user==null){
				User newUser = new User();
				newUser.setUserId(registerBean.getUserId().trim());
				newUser.setUserName(registerBean.getUserName().trim());
				newUser.setUserRole("User");
				newUser.setPassword(registerBean.getPassword());
				newUser.setStatus("Requested");
				SecureQuestJPADAO secureQuestJPADAO=new SecureQuestJPADAOImpl();
				newUser.setSecureQuest(secureQuestJPADAO.findQuestion(registerBean.getSecureQuest()));
				newUser.setSecurityAnswer(registerBean.getSecAnswer().trim());
				registerJPADAO.persist(newUser);
			}
			else{
				throw new DryFruitBusinessException("This User Id is already exists, please use another one");
			}

		} 
		catch(DryFruitBusinessException e)
		{
			e.printStackTrace();
			throw new DryFruitBusinessException(e.getMessage());
		}
		catch (Exception exception) {
			exception.printStackTrace();
			System.out.println(exception.getMessage());
			throw new Exception(exception.getMessage());
		} finally {
			registerJPADAO.commitAndCloseTransaction();
		}
	}
}
