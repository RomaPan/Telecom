package by.roma.telecom.dao;

import by.roma.telecom.dao.impl.SQLAccountDao;
import by.roma.telecom.dao.impl.SQLOrderDao;
import by.roma.telecom.dao.impl.SQLUserDao;

public class DaoProvider {

	private static final DaoProvider instance = new DaoProvider();

	private final AccountDao accountDao = new SQLAccountDao();

	private final UserDao userDao = new SQLUserDao();
	
	private final OrderDao orderDao = new SQLOrderDao();

	public static DaoProvider getInstance() {
		return instance;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}
	
	public OrderDao getOrderDao() {
		return orderDao;
	}
}
