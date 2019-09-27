package by.roma.telecom.service;

import by.roma.telecom.service.impl.AccountServiceImpl;
import by.roma.telecom.service.impl.UserServiceImpl;

public class ServiceProvider {

	private static final ServiceProvider instance = new ServiceProvider();
	private static final UserService userService = new UserServiceImpl();
	private static final AccountService accountService = new AccountServiceImpl();

	private ServiceProvider() {
	};

	public static ServiceProvider getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}
	
	public AccountService getAccountService() {
		return accountService;
	}

}
