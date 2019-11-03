package by.roma.telecom.service.impl;

import java.util.List;

import by.roma.telecom.bean.Account;
import by.roma.telecom.dao.AccountDao;
import by.roma.telecom.dao.DaoException;
import by.roma.telecom.dao.DaoProvider;
import by.roma.telecom.service.AccountService;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.validation.UserDataValidator;

public class AccountServiceImpl implements AccountService {
	private AccountDao accountDao = DaoProvider.getInstance().getAccountDao();
	private static final UserDataValidator validator = UserDataValidator.getInstane();

	@Override
	public Account connectPhoneNumber(String accountID, String phoneNumber) throws ServiceException {

		Account account;
		int accID;
		int phoneNum;
		boolean hasActiveNumber;
		if (!validator.checkPhoneNumber(phoneNumber)) {
			return null;
		} else {
			try {
				accID = Integer.parseInt(accountID);
				hasActiveNumber = accountDao.isActivePhoneNumberConnectedToAccount(accID);
				if (hasActiveNumber) {
					return null;
				} else {
					phoneNum = accountDao.getPhoneNumberID(phoneNumber);
					accountDao.updatePhoneNumberAvailableStatus(phoneNum, false);
					accountDao.connectPhoneNumberToAccount(accID, phoneNum);
					account = accountDao.getAccountDetails(accID);
					return account;
				}
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
	}

	@Override
	public Account addCallPlan(String accountID, String callPlanID) throws ServiceException {
		Account account;
		int accID;
		int cpID;
		boolean accountHasActivePlan;
		float planRate;
		account = null;
		try {
			accID = Integer.parseInt(accountID);
			cpID = Integer.parseInt(callPlanID);
			accountHasActivePlan = accountDao.isActiveCallPlanConnectedToAccount(accID);
			if (accountHasActivePlan) {
				accountDao.cancelAccountCallPlan(accID);
			}
			planRate = accountDao.getCallPlanRate(cpID);
			accountDao.addCallPlan(accID, cpID);
			accountDao.chargeToAccount(accID, planRate);
			account = accountDao.getAccountDetails(accID);
			return account;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Account changePhoneNumber(String accountID, String newPhoneNumber) throws ServiceException {
		Account account;
		int accID;
		int oldPhoneNumberID;
		int newPhoneNumberID;
		if (!validator.checkPhoneNumber(newPhoneNumber)) {
			return null;
		} else {
			accID = Integer.parseInt(accountID);
			try {
				oldPhoneNumberID = accountDao.getConnectedToAccountPhoneNumberID(accID);
				newPhoneNumberID = accountDao.getPhoneNumberID(newPhoneNumber);
				accountDao.disconnectPhoneNumberFromAccount(accID);
				accountDao.updatePhoneNumberAvailableStatus(oldPhoneNumberID, true);
				accountDao.updatePhoneNumberAvailableStatus(newPhoneNumberID, false);
				accountDao.connectPhoneNumberToAccount(accID, newPhoneNumberID);
				account = accountDao.getAccountDetails(accID);
				return account;
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
	}

	@Override
	public Account getAccountDetails(String accountID) throws ServiceException {
		Account account;
		try {
			int id = Integer.parseInt(accountID);
			account = accountDao.getAccountDetails(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return account;
	}

	@Override
	public Account chargeToAccount(String accountID, float amount) throws ServiceException {
		float tempBalance;
		int accID;
		Account account;
		try {
			accID = Integer.parseInt(accountID);
			tempBalance = accountDao.getAccountBalanceByID(accID);
			tempBalance = tempBalance + amount;
			accountDao.chargeToAccount(accID, tempBalance);
			account = accountDao.getAccountDetails(accID);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return account;
	}

	@Override
	public Account changeAccountBlockStatus(String accountID, boolean status) throws ServiceException {
		Account account;
		int id;
		try {
			id = Integer.parseInt(accountID);
			accountDao.changeAccountBlockStatus(id, status);
			account = accountDao.getAccountDetails(id);
			return account;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Account> getListOfAllAccounts() throws ServiceException {
		List<Account> accountsList;
		try {
			accountsList = accountDao.getListOfAllAccounts();
			return accountsList;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Account findAccountByPhoneNumber(String phoneNumber) throws ServiceException {
		Account account;
		if (!validator.checkPhoneNumber(phoneNumber)) {
			return null;
		} else {
			try {
				account = accountDao.findAccountByPhoneNumber(phoneNumber);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
			return account;
		}
	}

	@Override
	public void deleteAccount(String accountID) throws ServiceException {
		int accID;
		boolean hasActiveNumber;
		int phoneNumber;
		accID = Integer.parseInt(accountID);
		phoneNumber = 0;
		try {
			hasActiveNumber = accountDao.isActivePhoneNumberConnectedToAccount(accID);
			if (hasActiveNumber) {
				phoneNumber = accountDao.getConnectedToAccountPhoneNumberID(accID);
				accountDao.disconnectPhoneNumberFromAccount(accID);
				accountDao.updatePhoneNumberAvailableStatus(phoneNumber, true);
			}
			accountDao.deleteAccount(accID);
		}catch (DaoException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void insertAccount(int accountID) throws ServiceException {

		try {
			accountDao.insertAccount(accountID);
		}catch (DaoException e) {
			throw new ServiceException(e);
		}
	}


}
