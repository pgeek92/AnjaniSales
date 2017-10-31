package com.retail.asales.DAO;

import java.util.List;

import com.retail.asales.entity.SecureQuest;

public interface SecureQuestJPADAO {
	public SecureQuest findQuestion(String question);
	
	public List<SecureQuest> findAll();

	public void beginTransaction();

	public void commitTransaction();

	public void commitAndCloseTransaction();

	public void closeTransaction();
}
