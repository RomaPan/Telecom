package by.roma.telecom.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.RedirectCommandName;
import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.AccountService;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class DeleteAccountCommand implements Command {
	private AccountService accountService = ServiceProvider.getInstance().getAccountService();
	private static final Logger LOGGER = Logger.getLogger(DeleteAccountCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to delete an account: ";
	private static final String DELETE_ACCOUNT = "DeleteAccount";
	private static final String DELETE_ACCOUNT_F = "No account ID selected";
	private static final String DELETE_ACCOUNT_S = "No account ID selected";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String accountID;
		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		accountID = request.getParameter(RequestParameterName.REQ_PARAM_ACCOUNT_ID);
		if (accountID == null || accountID.isEmpty()) {
			session.setAttribute(DELETE_ACCOUNT, DELETE_ACCOUNT_F);
			response.sendRedirect(RedirectCommandName.GO_TO_ACCOUNT_MANAGEMENT_PAGE);
			return;
		}
		try {
			accountService.deleteAccount(accountID);
			session.setAttribute(DELETE_ACCOUNT, DELETE_ACCOUNT_S);
			response.sendRedirect(RedirectCommandName.GO_TO_ACCOUNT_MANAGEMENT_PAGE);
		}catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE + accountID);
		}
	}

}
