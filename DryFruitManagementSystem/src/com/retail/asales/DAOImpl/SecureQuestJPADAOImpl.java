package com.retail.asales.DAOImpl;

import java.util.HashMap;
import java.util.Map;

import com.retail.asales.DAO.SecureQuestJPADAO;
import com.retail.asales.entity.SecureQuest;

public class SecureQuestJPADAOImpl extends GenericJPADAO<Integer, SecureQuest> implements SecureQuestJPADAO{
	public SecureQuestJPADAOImpl(){
		super(SecureQuest.class);
	}

	@Override
	public SecureQuest findQuestion(String question) {
		// Auto-generated method stub
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("question", question);

		return findSingleResultFromNamedQuery(SecureQuest.FIND_BY_QUESTION, parameters);
	}

}
