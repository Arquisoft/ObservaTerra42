package persistence.impl;

import java.sql.SQLException;
import java.util.List;

import models.User;
import persistence.UserDao;

public class UserJdbcDao implements UserDao{

	private UserJdbc userJdbc;
	
	public UserJdbcDao(){
		userJdbc=new UserJdbc();
	}

	public List<User> getAllUsers() throws SQLException {
		return userJdbc.getAllUsers();
	}

	public int insertUser(User user) throws SQLException {
		return userJdbc.insertUser(user);
		
	}

	public int updateUser(User user) throws SQLException {
		return userJdbc.updateUser(user);
		
	}

	public int deleteUser(String idUser) throws SQLException {
		return userJdbc.deleteUser(idUser);
	}

	@Override
	public User getUser(String idUser) throws SQLException {
		return userJdbc.getUser(idUser);
	}
	
}
