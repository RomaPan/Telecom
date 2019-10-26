package by.roma.telecom.service;

import java.util.List;

import by.roma.telecom.bean.Account;


/**
 * AccountService is the main interface to provide guidelines for an account data manipulation and CRUD pathways.
 * @author Roman
 * @since 10-18-2019
 */
public interface AccountService {
	
	
	/**
	 * Executes Select query, assembles and returns bean account as an object, information gathered across several 
	 * tables in data base.
     * @param accountID    			Account ID as a String.
     * @return 			   			Account type object.
     * @throws ServiceException 	If DAOExeption occurs.
	 */
	public Account getAccountDetails (String accountID) throws ServiceException;
	
	
	/**
	 * Executes Insert query, across two tables, updates and returns account bean object.
	 * @param accountID    			Account ID as a String.
	 * @param callPlanID   			Call plan ID.
	 * @return 			   			Account type object.
     * @throws ServiceException 	If DAOExeption occurs.
	 */
	public Account addCallPlan(String accountID, String callPlanID) throws ServiceException;

	
	/**
	 * Executes Insert query, across two tables, updates and returns account bean object.
     * @param accountID    			Account ID as a String.
     * @param phoneNumber  			Line number as a String.
     * @return 			   			Account type object.
     * @throws ServiceException 	If DAOExeption occurs.
	 */
	public Account connectPhoneNumber(String accountID, String phoneNumber) throws ServiceException;
	
	
	/**
	 * Executes Insert, Update, Select queries, several tables involved, updates and returns account bean object.
     * @param accountID   	  		Account ID as a String.
     * @param newPhoneNumber  		New line number as a String.
     * @return 			      		Account type object.
     * @throws ServiceException 	If DAOExeption occurs.
	 */
	public Account changePhoneNumber(String accountID, String newPhoneNumber) throws ServiceException;
	
	
	/**
	 * Executes Update query, accounts table involved, updates and returns account bean object.
     * @param accountID   	  		Account ID as a String.
     * @param amount		  		Amount as float to be charged to an account.
     * @return 			      		Account type object.
     * @throws ServiceException 	If DAOExeption occurs.
	 */
	public Account chargeToAccount (String accountID, float amount) throws ServiceException;
	
	
	/**
	 * Executes Update query, accounts table involved, updates and returns account bean object.
     * @param accountID   	 		Account ID as a String.
     * @return 			      		Account type object.
     * @throws ServiceException 	If DAOExeption occurs.
	 */
	public Account blockAccount (String accountID) throws ServiceException;
	
	
	/**
	 * Executes Update query, accounts table involved, updates and returns account bean object.
     * @param accountID   	  		Account ID as a String.
     * @return 			      		Account type object.
     * @throws ServiceException 	If DAOExeption occurs.
	 */
	public Account unblockAccount (String accountID) throws ServiceException;

	
	/**
	 * Executes Select query, several tables involved, returns list of all active accounts from DB.
     * @return 			      		Account type list.
     * @throws ServiceException 	If DAOExeption occurs.
	 */
	public List<Account> getListOfAllAccounts() throws ServiceException;
	
	
	/**
	 * Executes Select query, several tables involved, returns account bean object.
     * @param phoneNumber   	  	Phone number as a String.
     * @return 			    	    Account type object.
     * @throws ServiceException 	If DAOExeption occurs.
	 */
	public Account findAccountByPhoneNumber(String phoneNumber) throws ServiceException;
	
}
