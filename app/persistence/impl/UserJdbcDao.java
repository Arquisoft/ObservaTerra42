package persistence.impl;

import java.util.List;

import models.User;
import persistence.UserDao;

public class UserJdbcDao implements UserDao {

	private UserJdbc userJdbc;

	public UserJdbcDao() {
		userJdbc = new UserJdbc();
	}

	public List<User> getAllUsers() {
		return userJdbc.getAllUsers();
	}

	public int insertUser(User user) {
		return userJdbc.insertUser(user);

	}

	public int updateUser(User user) {
		return userJdbc.updateUser(user);
	}

	public int deleteUser(String idUser) {
		return userJdbc.deleteUser(idUser);
	}

	public User getUser(String idUser) {
		return userJdbc.getUser(idUser);
	}

}
