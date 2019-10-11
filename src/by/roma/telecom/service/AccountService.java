package by.roma.telecom.service;

import java.util.List;

import by.roma.telecom.bean.Account;



public interface AccountService {
	
	public Account getAccountDetails (String accountID) throws ServiceException;
	
	public Account addCallPlan(String accountID, String callPlanID) throws ServiceException;

	public Account connectPhoneNumber(String accountID, String phoneNumber) throws ServiceException;
	
	public Account changePhoneNumber(String accountID, String newPhoneNumber) throws ServiceException;
	
	public Account chargeToAccount (int accountID, float amount) throws ServiceException;
	
	public Account blockAccount (String accountID) throws ServiceException;
	
	public Account unblockAccount (String accountID) throws ServiceException;

	public List<Account> getListOfAllAccounts() throws ServiceException;
	
}
