package by.roma.telecom.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import by.roma.telecom.bean.Account;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.RedirectCommandName;
import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.AccountService;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class UnblockAccountCommand implements Command {
	private AccountService accountService = ServiceProvider.getInstance().getAccountService();
	private static final Logger LOGGER = Logger.getLogger(UnblockAccountCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  failed to unblock an account: ";
	private static final String UNBLOCK_ACCOUNT = "UnblockAccountMessage";
	private static final String UNBLOCK_ACCOUNT_F = "No account ID selected";
	private static final String UNBLOCK_ACCOUNT_S = "Account is unblocked";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Account account;
		String accountID;
		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		accountID = request.getParameter(RequestParameterName.REQ_PARAM_ACCOUNT_ID);
		if (accountID == null || accountID.isEmpty()) {
			session.setAttribute(UNBLOCK_ACCOUNT, UNBLOCK_ACCOUNT_F);
			response.sendRedirect(RedirectCommandName.GO_TO_ACCOUNT_MANAGEMENT_PAGE);
			return;
		}
		try {
			account = accountService.changeAccountBlockStatus(accountID, false);
			session.setAttribute("account", account);
			session.setAttribute(UNBLOCK_ACCOUNT, UNBLOCK_ACCOUNT_S);
			response.sendRedirect(RedirectCommandName.GO_TO_ACCOUNT_MANAGEMENT_PAGE);
		} catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE + accountID);
		}
	}
}
