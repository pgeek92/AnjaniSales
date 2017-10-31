package com.retail.asales.service;

import java.util.ArrayList;
import java.util.List;

import com.retail.asales.DAO.SecureQuestJPADAO;
import com.retail.asales.DAOImpl.SecureQuestJPADAOImpl;
import com.retail.asales.entity.SecureQuest;

public class SecureQuestService {
	public List<String> getAllSecureQuests(){
		SecureQuestJPADAO secureQuestJPADAO = new SecureQuestJPADAOImpl();
		List<String> secureQuestions = new ArrayList<String>();
		try{
			secureQuestJPADAO.beginTransaction();
			List<SecureQuest> secureQuests = secureQuestJPADAO.findAll();
			for (SecureQuest secureQuest : secureQuests) {
				secureQuestions.add(secureQuest.getSecurityQuestion());
			}
		}
		catch (Exception exception) {
			exception.printStackTrace();
			System.out.println(exception.getMessage());
		}
		finally{
			secureQuestJPADAO.commitAndCloseTransaction();
		}
		return secureQuestions;
	}
}
