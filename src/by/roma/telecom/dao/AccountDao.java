package by.roma.telecom.dao;

import java.util.List;

import by.roma.telecom.bean.Account;


public interface AccountDao {

	public Account getAccountDetails (int accountID) throws DaoException;
	
	public Account addCallPlan(int accountID, int callPlanID) throws DaoException;

	public Account connectPhoneNumber(int accountID, String phoneNumber) throws DaoException;

	public Account changePhoneNumber(int accountID, String newPhoneNumber) throws DaoException;
	
	public Account chargeToAccount (int accountID, float amount) throws DaoException;
	
	public Account blockAccount (int accountID) throws DaoException;
	
	public Account unblockAccount (int accountID) throws DaoException;
	
	public List<Account> getListOfAllAccounts() throws DaoException;
}
