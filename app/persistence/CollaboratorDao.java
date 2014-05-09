package persistence;

import java.sql.SQLException;
import java.util.List;

import models.Collaborator;

public interface CollaboratorDao {
	List<Collaborator> getAllCollaborators() throws SQLException;
	
	Collaborator getCollaborator(String idCollaborator) throws SQLException;

	int insertCollaborator(Collaborator collaborator) throws SQLException;

	int updateCollaborator(Collaborator collaborator) throws SQLException;
	
	int deleteCollaborator(String idCollaborator) throws SQLException;
}
