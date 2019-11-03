package by.roma.telecom.controller;

import java.util.HashSet;
import java.util.Set;
import by.roma.telecom.command.CommandName;

public class AdminAuthFilterHelper {

	private static final AdminAuthFilterHelper instance = new AdminAuthFilterHelper();
	private Set<CommandName> adminAccess = new HashSet<>();

	public AdminAuthFilterHelper() {
		adminAccess.add(CommandName.BLOCK_ACCOUNT);
		adminAccess.add(CommandName.BLOCK_USER);
		adminAccess.add(CommandName.CHANGE_USER_ROLE);
		adminAccess.add(CommandName.CLEAR_LIST_OF_ALL_ACCOUNTS);
		adminAccess.add(CommandName.CLEAR_LIST_OF_ALL_USERS);
		adminAccess.add(CommandName.DELETE_USER);
		adminAccess.add(CommandName.DELETE_ACCOUNT);
		adminAccess.add(CommandName.FIND_ACCOUNT_BY_ID);
		adminAccess.add(CommandName.FIND_ACCOUNT_BY_PHONE_NUMBER);
		adminAccess.add(CommandName.FIND_USER_BY_EMAIL);
		adminAccess.add(CommandName.FIND_USER_BY_ID);
		adminAccess.add(CommandName.GET_LIST_OF_ALL_ACCOUNTS);
		adminAccess.add(CommandName.GET_LIST_OF_ALL_USERS);
		adminAccess.add(CommandName.GET_LIST_OF_AVAILABLE_NUMBERS);
		adminAccess.add(CommandName.GO_TO_ACCOUNT_MANAGEMENT_PAGE);
		adminAccess.add(CommandName.GO_TO_ACCOUNT_PAGINATION);
		adminAccess.add(CommandName.GO_TO_ADMIN_AUTH_PAGE);
		adminAccess.add(CommandName.GO_TO_USER_MANAGEMENT_PAGE);
		adminAccess.add(CommandName.GO_TO_USER_PAGINATION);
		adminAccess.add(CommandName.UNBLOCK_ACCOUNT);
		adminAccess.add(CommandName.UNBLOCK_USER);
	}

	public static AdminAuthFilterHelper getInstance() {
		return instance;
	}

	public boolean checkAccessSet(String commandName) {
		if (adminAccess.contains(CommandName.valueOf(commandName.toUpperCase().replaceAll("-", "_")))) {
			return true;
		}
		return false;
	}

}
