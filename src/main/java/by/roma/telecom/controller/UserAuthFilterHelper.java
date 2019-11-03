package by.roma.telecom.controller;

import java.util.HashSet;
import java.util.Set;

import by.roma.telecom.command.CommandName;

public class UserAuthFilterHelper {

	private static final UserAuthFilterHelper instance = new UserAuthFilterHelper();
	private Set<CommandName> userAccess = new HashSet<>();

	public UserAuthFilterHelper() {

		userAccess.add(CommandName.CHANGE_PASSWORD);
		userAccess.add(CommandName.CHANGE_PHONE_NUMBER);
		userAccess.add(CommandName.CONNECT_PHONE_NUMBER);
		userAccess.add(CommandName.GET_LIST_OF_AVAILABLE_NUMBERS);
		userAccess.add(CommandName.GO_TO_USER_AUTH_PAGE);
		userAccess.add(CommandName.SET_CALL_PLAN);
		userAccess.add(CommandName.UPDATE_PROFILE);
	}

	public static UserAuthFilterHelper getInstance() {
		return instance;
	}

	public boolean checkAccessSet(String commandName) {
		if (userAccess.contains(CommandName.valueOf(commandName.toUpperCase().replaceAll("-", "_")))) {
			return true;
		}
		return false;
	}
}
