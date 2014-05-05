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
		// TODO Llamada a insert user JDBC
		
	}

	public void updateUser(User user) {
		// TODO Llamada a update user JDCB
		
	}

	public void deleteUser(User user) {
		// TODO Llamada a delete user JDBC
		
	}
	
}
