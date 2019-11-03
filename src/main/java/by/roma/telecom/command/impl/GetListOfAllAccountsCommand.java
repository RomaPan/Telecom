package by.roma.telecom.command.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import by.roma.telecom.bean.Account;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.RedirectCommandName;
import by.roma.telecom.service.AccountService;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class GetListOfAllAccountsCommand implements Command {
	private AccountService accountService = ServiceProvider.getInstance().getAccountService();
	private static final Logger LOGGER = Logger.getLogger(GetListOfAllAccountsCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to obtain list of all accounts from DB";
	private static final String ACCOUNT_LIST = "AccountsListMessage";
	private static final String ACCOUNT_LIST_MESSAGE_SUCCESS = "List of All accounts in DB";
	private static final String ACCOUNT_LIST_MESSAGE_FAIL = "Something went wrong, please try again later";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Account> accountsList;
		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		try {
			accountsList = accountService.getListOfAllAccounts();
			if (null == accountsList) {
				session.setAttribute(ACCOUNT_LIST, ACCOUNT_LIST_MESSAGE_FAIL);
				response.sendRedirect(RedirectCommandName.GO_TO_ACCOUNT_PAGINATION);
				return;
			}
			session.setAttribute("accountsList", accountsList);
			session.setAttribute(ACCOUNT_LIST, ACCOUNT_LIST_MESSAGE_SUCCESS);
			response.sendRedirect(RedirectCommandName.GO_TO_ACCOUNT_PAGINATION);
		} catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE);
		}
	}
}
