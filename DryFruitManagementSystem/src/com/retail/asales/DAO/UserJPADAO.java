package com.retail.asales.DAO;

import com.retail.asales.entity.User;

public interface UserJPADAO {
	public User read(String userId);
	public void beginTransaction();
	public void commitTransaction();
	public void closeTransaction();
	
	
}
