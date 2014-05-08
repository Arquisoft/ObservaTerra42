package persistence;

import java.util.List;

import models.User;

public interface UserDao {
	List<User> getAllUsers();
	
	User getUser(String idUser);

	int insertUser(User user);

	int updateUser(User user);
	
	int deleteUser(String idUser);
}
