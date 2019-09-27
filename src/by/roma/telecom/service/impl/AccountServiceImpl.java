package by.roma.telecom.service.impl;

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
		
		if(!validator.checkPhoneNumber(phoneNumber)) {
			return null;
		} else {
			try {
				account = accountDao.connectPhoneNumber(accountID, phoneNumber);
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
		try {
			account = accountDao.addCallPlan(accountID, callPlanID);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return account;
	}

	@Override
	public Account changePhoneNumber(String accountID, String newPhoneNumber) throws ServiceException {
		// 
		return null;
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
		
		try {
			int id = Integer.parseInt(accountID);
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
		
		try {
			int id = Integer.parseInt(accountID);
			account = accountDao.unblockAccount(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return account;
	}
}
