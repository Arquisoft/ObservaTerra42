package persistence;

import java.util.List;

import models.User;

public interface UserDao {
	List<User> getAllUsers();

	void insertUser(User user);

	void updateUser(User user);
	
	void deleteUser(User user);
}
