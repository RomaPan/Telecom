package by.roma.telecom.service;

import by.roma.telecom.bean.Account;



public interface AccountService {
	
	Account getAccountDetails (String accountID) throws ServiceException;
	
	Account addCallPlan(String accountID, String callPlanID) throws ServiceException;

	Account connectPhoneNumber(String accountID, String phoneNumber) throws ServiceException;
	
	Account changePhoneNumber(String accountID, String newPhoneNumber) throws ServiceException;
	
	Account chargeToAccount (int accountID, float amount) throws ServiceException;
	
	Account blockAccount (String accountID) throws ServiceException;
	
	Account unblockAccount (String accountID) throws ServiceException;
	
}
