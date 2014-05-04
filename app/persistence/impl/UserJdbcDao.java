package persistence.impl;

import java.util.List;

import models.User;
import persistence.UserDao;

public class UserJdbcDao implements UserDao{

	private UserJdbc userJdbc;
	
	public UserJdbcDao(){
		userJdbc=new UserJdbc();
	}

	public List<User> getAllUsers() {
		return userJdbc.getAllUsers();
	}

	public void insertUser(User user) {
		// TODO Auto-generated method stub
		
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}
	
}
