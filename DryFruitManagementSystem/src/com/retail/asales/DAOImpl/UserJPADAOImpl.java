package com.retail.asales.DAOImpl;

import com.retail.asales.DAO.UserJPADAO;
import com.retail.asales.entity.User;

public class UserJPADAOImpl extends GenericJPADAO<String, User> implements UserJPADAO {

	public UserJPADAOImpl() {
		super(User.class);
		// Auto-generated constructor stub
	}

}