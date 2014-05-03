package persistence.impl;

import java.util.List;

import models.User;
import persistence.UserDao;

public class UserJdbcDao implements UserDao{

	private UserJdbc userJdbc;
	
	public UserJdbcDao(){
		userJdbc=new UserJdbc();
	}
	
	@Override
	public List<User> getAllUsers() {
		return userJdbc.getAllUsers();
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

	
}
