package by.roma.telecom.command;

import java.util.HashMap;
import java.util.Map;
import by.roma.telecom.command.impl.AuthorizationCommand;
import by.roma.telecom.command.impl.BlockAccountCommand;
import by.roma.telecom.command.impl.BlockUserCommand;
import by.roma.telecom.command.impl.ChangePassCommand;
import by.roma.telecom.command.impl.ChangePhoneNumberCommand;
import by.roma.telecom.command.impl.ClearListOfAllAccountsCommand;
import by.roma.telecom.command.impl.ClearListOfAllUsersCommand;
import by.roma.telecom.command.impl.ConnectPhoneNumberCommand;
import by.roma.telecom.command.impl.DeleteAccountCommand;
import by.roma.telecom.command.impl.DeleteUserCommand;
import by.roma.telecom.command.impl.FindAccountByIDCommand;
import by.roma.telecom.command.impl.FindAccountByPhoneNumberCommand;
import by.roma.telecom.command.impl.FindUserByEmailCommand;
import by.roma.telecom.command.impl.FindUserByIDCommand;
import by.roma.telecom.command.impl.GetListOfAllAccountsCommand;
import by.roma.telecom.command.impl.GetListOfAllUsersCommand;
import by.roma.telecom.command.impl.GetListOfAvailableNumbersCommand;
import by.roma.telecom.command.impl.GoToAccountManagementPageCommand;
import by.roma.telecom.command.impl.GoToAccountPaginationCommand;
import by.roma.telecom.command.impl.GoToAuthAdminPageCommand;
import by.roma.telecom.command.impl.GoToAuthUserPageCommand;
import by.roma.telecom.command.impl.GoToContactUsPageCommand;
import by.roma.telecom.command.impl.GoToError403PageCommand;
import by.roma.telecom.command.impl.GoToError404PageCommand;
import by.roma.telecom.command.impl.GoToIndexPageCommand;
import by.roma.telecom.command.impl.GoToLoginPageCommand;
import by.roma.telecom.command.impl.GoToUserPaginationCommand;
import by.roma.telecom.command.impl.GoToPlansPageCommand;
import by.roma.telecom.command.impl.GoToRegStepThreeCommand;
import by.roma.telecom.command.impl.GoToRegStepTwoCommand;
import by.roma.telecom.command.impl.GoToRegistrationPageCommand;
import by.roma.telecom.command.impl.GoToUserManagementPageCommand;
import by.roma.telecom.command.impl.LocalizationCommand;
import by.roma.telecom.command.impl.UpdateProfileCommand;
import by.roma.telecom.command.impl.ViewAvailablePhoneNumberCommand;
import by.roma.telecom.command.impl.RegistrationCommand;
import by.roma.telecom.command.impl.SetCallPlanCommand;
import by.roma.telecom.command.impl.SetUserAdminRoleCommand;
import by.roma.telecom.command.impl.SignOutCommand;
import by.roma.telecom.command.impl.UnblockAccountCommand;
import by.roma.telecom.command.impl.UnblockUserCommand;

public class CommandHelper {

	private static final CommandHelper instance = new CommandHelper();
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandHelper() {
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.UPDATE_PROFILE, new UpdateProfileCommand());
		commands.put(CommandName.CHANGE_PASSWORD, new ChangePassCommand());
		commands.put(CommandName.SET_CALL_PLAN, new SetCallPlanCommand());
		commands.put(CommandName.SIGN_OUT, new SignOutCommand());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
		commands.put(CommandName.GO_TO_INDEX_PAGE, new GoToIndexPageCommand());
		commands.put(CommandName.GET_PHONE_NUMBERS, new ViewAvailablePhoneNumberCommand());
		commands.put(CommandName.CONNECT_PHONE_NUMBER, new ConnectPhoneNumberCommand());
		commands.put(CommandName.CHANGE_PHONE_NUMBER, new ChangePhoneNumberCommand());
		commands.put(CommandName.GO_TO_LOGIN_PAGE, new GoToLoginPageCommand());
		commands.put(CommandName.GO_TO_REG_STEP_TWO_PAGE, new GoToRegStepTwoCommand());
		commands.put(CommandName.GO_TO_REG_STEP_THREE_PAGE, new GoToRegStepThreeCommand());
		commands.put(CommandName.GO_TO_USER_AUTH_PAGE, new GoToAuthUserPageCommand());
		commands.put(CommandName.GO_TO_ADMIN_AUTH_PAGE, new GoToAuthAdminPageCommand());
		commands.put(CommandName.GO_TO_PLANS_PAGE, new GoToPlansPageCommand());
		commands.put(CommandName.GO_TO_CONTACTS_PAGE, new GoToContactUsPageCommand());
		commands.put(CommandName.GO_TO_ERROR_403_PAGE, new GoToError403PageCommand());
		commands.put(CommandName.GO_TO_ERROR_404_PAGE, new GoToError404PageCommand());
		commands.put(CommandName.GET_LIST_OF_AVAILABLE_NUMBERS, new GetListOfAvailableNumbersCommand());
		commands.put(CommandName.FIND_USER_BY_ID, new FindUserByIDCommand());
		commands.put(CommandName.GET_LIST_OF_ALL_USERS, new GetListOfAllUsersCommand());
		commands.put(CommandName.GET_LIST_OF_ALL_ACCOUNTS, new GetListOfAllAccountsCommand());
		commands.put(CommandName.CHANGE_USER_ROLE, new SetUserAdminRoleCommand());
		commands.put(CommandName.BLOCK_USER, new BlockUserCommand());
		commands.put(CommandName.UNBLOCK_USER, new UnblockUserCommand());
		commands.put(CommandName.CLEAR_LIST_OF_ALL_USERS, new ClearListOfAllUsersCommand());
		commands.put(CommandName.CLEAR_LIST_OF_ALL_ACCOUNTS, new ClearListOfAllAccountsCommand());
		commands.put(CommandName.FIND_ACCOUNT_BY_ID, new FindAccountByIDCommand());
		commands.put(CommandName.BLOCK_ACCOUNT, new BlockAccountCommand());
		commands.put(CommandName.UNBLOCK_ACCOUNT, new UnblockAccountCommand());
		commands.put(CommandName.LOCALIZE, new LocalizationCommand());
		commands.put(CommandName.GO_TO_USER_PAGINATION, new GoToUserPaginationCommand());
		commands.put(CommandName.FIND_USER_BY_EMAIL, new FindUserByEmailCommand());
		commands.put(CommandName.FIND_ACCOUNT_BY_PHONE_NUMBER, new FindAccountByPhoneNumberCommand());
		commands.put(CommandName.GO_TO_ACCOUNT_MANAGEMENT_PAGE, new GoToAccountManagementPageCommand());
		commands.put(CommandName.GO_TO_USER_MANAGEMENT_PAGE, new GoToUserManagementPageCommand());
		commands.put(CommandName.GO_TO_ACCOUNT_PAGINATION, new GoToAccountPaginationCommand());
		commands.put(CommandName.DELETE_ACCOUNT, new DeleteAccountCommand());
		commands.put(CommandName.DELETE_USER, new DeleteUserCommand());
	}

	public static CommandHelper getInstance() {
		return instance;
	}

	public Command getCommand(String commandName) {

		Command command;
		CommandName name;

		if (commandName == null || commandName.isEmpty()) {
			command = commands.get(CommandName.GO_TO_ERROR_404_PAGE);
			return command;
		} else if (!contains(commandName)) {
			command = commands.get(CommandName.GO_TO_ERROR_404_PAGE);
			return command;
		} else {
			name = CommandName.valueOf(commandName.toUpperCase().replaceAll("-", "_"));
			if (null != name) {
				command = commands.get(name);
			} else {
				command = commands.get(CommandName.GO_TO_ERROR_404_PAGE);
			}
			return command;
		}
	}

	public static boolean contains(String commandName) {

		for (CommandName cn : CommandName.values()) {
			if (cn.name().equals(commandName.toUpperCase().replaceAll("-", "_"))) {
				return true;
			}
		}
		return false;
	}

}
