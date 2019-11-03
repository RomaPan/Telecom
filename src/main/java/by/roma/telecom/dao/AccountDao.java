package by.roma.telecom.dao;

import java.util.List;

import by.roma.telecom.bean.Account;

/**
 * AccountDao is the main interface to provide guidelines for Account data manipulation and CRUD pathways.
 * @author Roman
 * @since 10-18-2019
 */
public interface AccountDao {
	/**
	 * Executes Select query, assembles and returns bean Account type object. 
     * @param accountID	    		Account id as an integer.
     * @return 			   			Account type object.
     * @throws DaoException 		If SQLException occurs.
	 */
	public Account getAccountDetails (int accountID) throws DaoException;
	/**
	 * Executes Select, Insert, Update queries in several tables, if account has an active call plan, that call
	 * plan will be ceased. New call plan with callPlanID passed into the method will be assign 
	 * to the account instead. 
     * @param accountID	    		Account id as an integer.
     * @param callPlanID	    	Call plan id as an integer.
     * @throws DaoException 		If SQLException occurs.
	 */
	public void addCallPlan(int accountID, int callPlanID) throws DaoException;
	/**
	 * Executes Update query. Updates account balance, returns updated bean Account object.
     * @param accountID	    		Account id as an integer.
     * @param amount			    Amount of charge as float.
     * @return 			   			Account type object.
     * @throws DaoException 		If SQLException occurs.
	 */
	public void chargeToAccount (int accountID, float amount) throws DaoException;
	/**
	 * Executes Select query. Assembles list of all accounts from data base.
     * @return 			   			Account type List.
     * @throws DaoException 		If SQLException occurs.
	 */
	public List<Account> getListOfAllAccounts() throws DaoException;
	/**
	 * Executes Select query. Searches for an account with specific phone number that is passed into the method.
	 * @param phoneNumber	    	Phone number as String.
     * @return 			   			Account type object.
     * @throws DaoException 		If SQLException occurs.
	 */
	public Account findAccountByPhoneNumber(String phoneNumber) throws DaoException;
	
	public boolean isActivePhoneNumberConnectedToAccount(int accountID) throws DaoException;
	
	public int getPhoneNumberID(String lineNumber) throws DaoException;
	
	public boolean updatePhoneNumberAvailableStatus(int phoneNumberID, boolean setAvailable) throws DaoException;
	
	public void connectPhoneNumberToAccount(int accountID, int phoneNumberID) throws DaoException;
	
	public boolean isActiveCallPlanConnectedToAccount(int accountID) throws DaoException;
	
	public float getCallPlanRate(int planID) throws DaoException;
	
	public void cancelAccountCallPlan(int accountID) throws DaoException;
	
	public int getConnectedToAccountPhoneNumberID(int accountID) throws DaoException;
	
	public void disconnectPhoneNumberFromAccount(int accountID) throws DaoException;
	
	public void changeAccountBlockStatus(int accountID, boolean status) throws DaoException;
	
	public float getAccountBalanceByID(int accoundID) throws DaoException;
	
	public void deleteAccount(int accountID) throws DaoException;
	
	public void insertAccount(int accountID) throws DaoException;
}
