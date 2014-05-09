package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;
import utils.Jdbc;

public class UserJdbc {

	private static Connection c;
	
	private static final String GET_USERS = "SELECT * FROM USER";
	private static final String GET_USER = "SELECT * FROM USER WHERE ID = ? AND TYPE = 'user'";
	private static final String ADD_USER = "INSERT INTO USER ( \"ID\", \"NAME\", \"PASSWORD\", \"EMAIL\", \"TYPE\", \"ACTIVE\" ) VALUES ( ?, ?, ?, ?, 'user', false)";
	private static final String UPDATE_USER = "UPDATE USER NAME = ?, PASSWORD = ?, EMAIL = ?, ACTIVE = ? WHERE ID = ?";
	private static final String DELETE_USER = "DELETE FROM USER WHERE ID = ? AND TYPE = 'user'";

	private static void createConnection() {
		try {
			c = Jdbc.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<User> getAllUsers() throws SQLException {
		List<User> users = new ArrayList<User>();

		createConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;

		pst = c.prepareStatement(GET_USERS);
		rs = pst.executeQuery();
		while (rs.next()) {
			User user = new User(rs.getString(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getBoolean(6));
			users.add(user);
		}

		Jdbc.close(rs, pst, c);

		if (users.isEmpty())
			return null;
		return users;

	}

	public int insertUser(User user) throws SQLException {
		createConnection();
		PreparedStatement pst = null;
		int cambio = 0;

		pst = c.prepareStatement(ADD_USER);
		pst.setString(1, user.id);
		pst.setString(2, user.name);
		pst.setString(3, user.password);
		pst.setString(4, user.email);
		cambio = pst.executeUpdate();

		Jdbc.close(c, pst);

		return cambio;
	}

	public int updateUser(User user) throws SQLException {
		createConnection();
		PreparedStatement pst = null;
		int cambio = 0;

		pst = c.prepareStatement(UPDATE_USER);
		pst.setString(1, user.name);
		pst.setString(2, user.password);
		pst.setString(3, user.email);
		pst.setBoolean(4, user.active);
		pst.setString(5, user.id);
		cambio = pst.executeUpdate();
		Jdbc.close(c, pst);

		return cambio;
	}

	public int deleteUser(String idUser) throws SQLException {
		createConnection();
		PreparedStatement pst = null;
		int cambio = 0;

		pst = c.prepareStatement(DELETE_USER);
		pst.setString(1, idUser);
		cambio = pst.executeUpdate();
		Jdbc.close(c, pst);

		return cambio;
	}

	public User getUser(String idUser) throws SQLException {
		User user = null;

		createConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;

		pst = c.prepareStatement(GET_USER);
		pst.setString(1, idUser);
		rs = pst.executeQuery();
		while (rs.next())
			user = new User(rs.getString(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getBoolean(6));

		Jdbc.close(rs, pst, c);

		return user;
	}
}
