package persistence.impl;

import java.sql.SQLException;
import java.util.List;

import models.Collaborator;
import persistence.CollaboratorDao;

public class CollaboratorJdbcDao implements CollaboratorDao{

	private CollaboratorJdbc collaboratorJdbc;
	
	public CollaboratorJdbcDao(){
		collaboratorJdbc=new CollaboratorJdbc();
	}

	public List<Collaborator> getAllCollaborators() throws SQLException {
		return collaboratorJdbc.getAllCollaboratores();
	}

	public Collaborator getCollaborator(String idCollaborator)
			throws SQLException {
		return collaboratorJdbc.getCollaborator(idCollaborator);
	}

	public int insertCollaborator(Collaborator collaborator)
			throws SQLException {
		return collaboratorJdbc.insertCollaborator(collaborator);
	}

	public int updateCollaborator(Collaborator collaborator)
			throws SQLException {
		return collaboratorJdbc.updateCollaborator(collaborator);
	}

	public int deleteCollaborator(String idCollaborator) throws SQLException {
		return collaboratorJdbc.deleteCollaborator(idCollaborator);
	}

}
