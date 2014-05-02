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

	private static void createConnection() {
		try {
			c = Jdbc.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();

		createConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = c.prepareStatement("GET_USERS");

			rs = pst.executeQuery();

			while (rs.next()) {

				// User user=new User(rs.getString(1), name, password, email,
				// type, active);
			}
		} catch (SQLException e) {

		} finally {
			Jdbc.close(rs, pst, c);
		}
		if (users.isEmpty())
			return null;
		return users;

	}
}
