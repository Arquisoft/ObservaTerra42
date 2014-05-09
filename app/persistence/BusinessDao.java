package persistence;

import java.sql.SQLException;
import java.util.List;

import models.Business;

public interface BusinessDao {
	List<Business> getAllBusinesses() throws SQLException;
	
	Business getBusiness(String idBusiness) throws SQLException;

	int insertBusiness(Business business) throws SQLException;

	int updateBusiness(Business business) throws SQLException;
	
	int deleteBusiness(String idBusiness) throws SQLException;
}
