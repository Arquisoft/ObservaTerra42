package persistence;

import java.sql.SQLException;
import java.util.List;

import models.User;

public interface UserDao {
	List<User> getAllUsers() throws SQLException;
	
	User getUser(String idUser) throws SQLException;

	int insertUser(User user) throws SQLException;

	int updateUser(User user) throws SQLException;
	
	int deleteUser(String idUser) throws SQLException;
}
