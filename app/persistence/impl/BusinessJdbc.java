package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Business;
import utils.Jdbc;

public class BusinessJdbc {

	private static Connection c;

	private static final String GET_BUSINESSES = "SELECT * FROM BUSINESS";
	private static final String GET_BUSINESS = "SELECT * FROM BUSINESS WHERE ID = ?";
	private static final String ADD_BUSINESS = "INSERT INTO BUSINESS ( \"ID\", \"NAME\", \"PASSWORD\", \"EMAIL\", \"TYPE\", \"ACTIVE\", \"NIF\", \"DESCRIPTION\", \"PHONE\", \"ADDRESS\", \"WEB_SITE\" ) VALUES ( ?, ?, ?, ?, 'business', false, ?, ?, ?, ?, ?)";
	private static final String UPDATE_BUSINESS = "UPDATE BUSINESS NAME = ?, PASSWORD = ?, EMAIL = ?, ACTIVE = ?, NIF = ?, DESCRIPTION = ?, PHONE = ?, ADDRESS = ?, WEB_SITE = ? WHERE ID = ?";
	private static final String DELETE_BUSINESS = "DELETE FROM BUSINESS WHERE ID = ? ";

	private static void createConnection() {
		try {
			c = Jdbc.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Business> getAllBusinesses() throws SQLException {
		List<Business> businesses = new ArrayList<Business>();

		createConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;

		pst = c.prepareStatement(GET_BUSINESSES);
		rs = pst.executeQuery();
		while (rs.next()) {
			Business business = new Business(rs.getString(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getBoolean(6), rs.getString(7), rs.getString(8),
					rs.getString(9), rs.getString(10), rs.getString(11));

			businesses.add(business);
		}

		Jdbc.close(rs, pst, c);

		if (businesses.isEmpty())
			return null;
		return businesses;

	}

	public int insertBusiness(Business business) throws SQLException {
		createConnection();
		PreparedStatement pst = null;
		int cambio = 0;
		pst = c.prepareStatement(ADD_BUSINESS);
		pst.setString(1, business.id);
		pst.setString(2, business.name);
		pst.setString(3, business.password);
		pst.setString(4, business.email);
		pst.setString(5, business.nif);
		pst.setString(6, business.description);
		pst.setString(7, business.phone);
		pst.setString(8, business.address);
		pst.setString(9, business.webSite);
		cambio = pst.executeUpdate();

		Jdbc.close(c, pst);

		return cambio;
	}

	public int updateBusiness(Business business) throws SQLException {
		createConnection();
		PreparedStatement pst = null;
		int cambio = 0;

		pst = c.prepareStatement(UPDATE_BUSINESS);
		pst.setString(1, business.name);
		pst.setString(2, business.password);
		pst.setString(3, business.email);
		pst.setBoolean(4, business.active);
		pst.setString(5, business.nif);
		pst.setString(6, business.description);
		pst.setString(7, business.phone);
		pst.setString(8, business.address);
		pst.setString(9, business.webSite);
		pst.setString(10, business.id);
		cambio = pst.executeUpdate();
		Jdbc.close(c, pst);

		return cambio;
	}

	public int deleteBusiness(String idBusiness) throws SQLException {
		createConnection();
		PreparedStatement pst = null;
		int cambio = 0;

		pst = c.prepareStatement(DELETE_BUSINESS);
		pst.setString(1, idBusiness);
		cambio = pst.executeUpdate();
		Jdbc.close(c, pst);

		return cambio;
	}

	public Business getBusiness(String idBusiness) throws SQLException {
		Business business = null;

		createConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;

		pst = c.prepareStatement(GET_BUSINESS);
		pst.setString(1, idBusiness);
		rs = pst.executeQuery();
		while (rs.next())
			
			business = new Business(rs.getString(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getBoolean(6), rs.getString(7), rs.getString(8),
					rs.getString(9), rs.getString(10), rs.getString(11));
		Jdbc.close(rs, pst, c);

		return business;
	}
}
