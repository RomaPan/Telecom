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

	private static final UserDataValidator validator = UserDataValidator.getInstane();

	@Override
	public Account connectPhoneNumber(String accountID, String phoneNumber) throws ServiceException {
		AccountDao accountDao = DaoProvider.getInstance().getAccountDao();
		Account account;
		int id;
		if (!validator.checkPhoneNumber(phoneNumber)) {
			return null;
		} else {
			try {
				id = Integer.parseInt(accountID);
				account = accountDao.connectPhoneNumber(id, phoneNumber);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
			return account;
		}
	}

	@Override
	public Account addCallPlan(String accountID, String callPlanID) throws ServiceException {
		AccountDao accountDao = DaoProvider.getInstance().getAccountDao();
		Account account;
		int accID;
		int cpID;
		try {
			accID = Integer.parseInt(accountID);
			cpID = Integer.parseInt(callPlanID);
			account = accountDao.addCallPlan(accID, cpID);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return account;
	}

	@Override
	public Account changePhoneNumber(String accountID, String newPhoneNumber) throws ServiceException {
		AccountDao accountDao = DaoProvider.getInstance().getAccountDao();
		Account account;
		int id;
		if (!validator.checkPhoneNumber(newPhoneNumber)) {
			return null;
		} else {
			try {
				id = Integer.parseInt(accountID);
				account = accountDao.changePhoneNumber(id, newPhoneNumber);
				return account;
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
	}

	@Override
	public Account getAccountDetails(String accountID) throws ServiceException {
		AccountDao accountDao = DaoProvider.getInstance().getAccountDao();
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
	public Account chargeToAccount(int accountID, float amount) throws ServiceException {
		AccountDao accountDao = DaoProvider.getInstance().getAccountDao();
		Account account;
		try {
			account = accountDao.chargeToAccount(accountID, amount);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return account;
	}

	@Override
	public Account blockAccount(String accountID) throws ServiceException {
		AccountDao accountDao = DaoProvider.getInstance().getAccountDao();
		Account account;
		int id;
		try {
			id = Integer.parseInt(accountID);
			account = accountDao.blockAccount(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return account;
	}

	@Override
	public Account unblockAccount(String accountID) throws ServiceException {
		AccountDao accountDao = DaoProvider.getInstance().getAccountDao();
		Account account;
		int id;
		try {
			id = Integer.parseInt(accountID);
			account = accountDao.unblockAccount(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return account;
	}

	@Override
	public List<Account> getListOfAllAccounts() throws ServiceException {
		
		return null;
	}

	
	

	
}
