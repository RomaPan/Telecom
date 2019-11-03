package by.roma.telecom.service;

import java.util.List;

import by.roma.telecom.bean.Account;

public interface AccountService {

	public Account getAccountDetails (String accountID) throws ServiceException;
	
	public Account addCallPlan(String accountID, String callPlanID) throws ServiceException;

	public Account connectPhoneNumber(String accountID, String phoneNumber) throws ServiceException;
	
	public Account changePhoneNumber(String accountID, String newPhoneNumber) throws ServiceException;
	
	public Account chargeToAccount (String accountID, float amount) throws ServiceException;
	
	public Account changeAccountBlockStatus(String accountID, boolean status) throws ServiceException;

	public List<Account> getListOfAllAccounts() throws ServiceException;
	
	public Account findAccountByPhoneNumber(String phoneNumber) throws ServiceException;
	
	public void deleteAccount(String accountID) throws ServiceException;
	
	public void insertAccount(int accountID) throws ServiceException;
}
