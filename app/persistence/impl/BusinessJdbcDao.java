package persistence.impl;

import java.sql.SQLException;
import java.util.List;

import models.Business;
import persistence.BusinessDao;

public class BusinessJdbcDao implements BusinessDao{

	private BusinessJdbc businessJdbc;
	
	public BusinessJdbcDao(){
		businessJdbc=new BusinessJdbc();
	}

	public List<Business> getAllBusinesses() throws SQLException {
		return businessJdbc.getAllBusinesses();
	}

	public int insertBusiness(Business business) throws SQLException {
		return businessJdbc.insertBusiness(business);
		
	}

	public int updateBusiness(Business business) throws SQLException {
		return businessJdbc.updateBusiness(business);
		
	}

	public int deleteBusiness(String idBusiness) throws SQLException {
		return businessJdbc.deleteBusiness(idBusiness);
	}

	public Business getBusiness(String idBusiness) throws SQLException {
		return businessJdbc.getBusiness(idBusiness);
	}
	
}
