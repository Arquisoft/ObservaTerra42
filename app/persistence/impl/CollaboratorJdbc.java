package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Collaborator;
import utils.Jdbc;

public class CollaboratorJdbc {

	private static Connection c;

	private static final String GET_COLLABORATORS = "SELECT * FROM BUSINESS";
	private static final String GET_COLLABORATOR = "SELECT * FROM BUSINESS WHERE ID = ?";
	private static final String ADD_COLLABORATOR = "INSERT INTO COLLABORATOR ( \"ID\", \"NAME\", \"PASSWORD\", \"EMAIL\", \"TYPE\", \"ACTIVE\", \"PHONE\", \"ADDRESS\", \"ORGANIZATION\", \"SPECIALIZATION\" ) VALUES ( ?, ?, ?, 'collaborator', false, ?, ?, ?, ?, ?)";
	private static final String UPDATE_COLLABORATOR = "UPDATE COLLABORATOR NAME = ?, PASSWORD = ?, EMAIL = ?, ACTIVE = ?, PHONE = ?, ADDRESS = ?, ORGANIZATION = ?, SPECIALIZATION = ? WHERE ID = ?";
	private static final String DELETE_COLLABORATOR = "DELETE FROM COLLABORATOR WHERE ID = ? ";

	private static void createConnection() {
		try {
			c = Jdbc.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Collaborator> getAllCollaboratores() throws SQLException {
		List<Collaborator> collaboratores = new ArrayList<Collaborator>();

		createConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;

		pst = c.prepareStatement(GET_COLLABORATORS);
		rs = pst.executeQuery();
		while (rs.next()) {
Collaborator collaborator = new Collaborator(rs.getString(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getBoolean(6), rs.getString(7), rs.getString(8),
					rs.getString(9), rs.getString(10));

			collaboratores.add(collaborator);
		}

		Jdbc.close(rs, pst, c);

		if (collaboratores.isEmpty())
			return null;
		return collaboratores;

	}

	public int insertCollaborator(Collaborator collaborator) throws SQLException {
		createConnection();
		PreparedStatement pst = null;
		int cambio = 0;
		pst = c.prepareStatement(ADD_COLLABORATOR);
		pst.setString(1, collaborator.id);
		pst.setString(2, collaborator.name);
		pst.setString(3, collaborator.password);
		pst.setString(4, collaborator.email);
		pst.setString(5, collaborator.phone);
		pst.setString(6, collaborator.address);
		pst.setString(7, collaborator.organization);
		pst.setString(8, collaborator.specialization);
		cambio = pst.executeUpdate();
			Jdbc.close(c, pst);

		return cambio;
	}

	public int updateCollaborator(Collaborator collaborator) throws SQLException {
		createConnection();
		PreparedStatement pst = null;
		int cambio = 0;

		pst = c.prepareStatement(UPDATE_COLLABORATOR);
		pst.setString(1, collaborator.name);
		pst.setString(2, collaborator.password);
		pst.setString(3, collaborator.email);
		pst.setBoolean(4, collaborator.active);
		pst.setString(5, collaborator.phone);
		pst.setString(6, collaborator.address);
		pst.setString(7, collaborator.organization);
		pst.setString(8, collaborator.specialization);
		pst.setString(9, collaborator.id);
		cambio = pst.executeUpdate();
		Jdbc.close(c, pst);

		return cambio;
	}

	public int deleteCollaborator(String idCollaborator) throws SQLException {
		createConnection();
		PreparedStatement pst = null;
		int cambio = 0;

		pst = c.prepareStatement(DELETE_COLLABORATOR);
		pst.setString(1, idCollaborator);
		cambio = pst.executeUpdate();
		Jdbc.close(c, pst);

		return cambio;
	}

	public Collaborator getCollaborator(String idCollaborator) throws SQLException {
		Collaborator collaborator = null;

		createConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;

		pst = c.prepareStatement(GET_COLLABORATOR);
		pst.setString(1, idCollaborator);
		rs = pst.executeQuery();
		while (rs.next())

			collaborator = new Collaborator(rs.getString(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getBoolean(6), rs.getString(7), rs.getString(8),
					rs.getString(9), rs.getString(10));

		Jdbc.close(rs, pst, c);

		return collaborator;
	}
}
