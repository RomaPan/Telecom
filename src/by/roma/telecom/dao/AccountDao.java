package by.roma.telecom.dao;

import by.roma.telecom.bean.Account;


public interface AccountDao {

	public Account getAccountDetails (int accountID) throws DaoException;
	
	public Account addCallPlan(String accountID, String callPlanID) throws DaoException;

	public Account connectPhoneNumber(String accountID, String phoneNumber) throws DaoException;

	public Account changePhoneNumber(String accountID, String newPhoneNumber) throws DaoException;
	
	public Account chargeToAccount (int accountID, float amount) throws DaoException;
	
	public Account blockAccount (int accountID) throws DaoException;
	
	public Account unblockAccount (int accountID) throws DaoException;
}
