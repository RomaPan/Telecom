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

public class FindAccountByIDCommand implements Command {
	private AccountService accountService = ServiceProvider.getInstance().getAccountService();
	private static final Logger LOGGER = Logger.getLogger(FindAccountByIDCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to find accountID:";
	private static final String FIND_ACC_MESSAGE = "FindAccountByIDMessage";
	private static final String FIND_ACC_FAIL = "No account found with that ID";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Account account;
		String accountID;
		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		accountID = request.getParameter(RequestParameterName.REQ_PARAM_ACCOUNT_ID);
		if (accountID == null || accountID.isBlank()) {
			session.setAttribute(FIND_ACC_MESSAGE, FIND_ACC_FAIL);
			response.sendRedirect(RedirectCommandName.GO_TO_ACCOUNT_MANAGEMENT_PAGE);
			return;
		}
		try {
			account = accountService.getAccountDetails(accountID);
			if (null == account) {
				if (session.getAttribute("account") != null) {
					session.removeAttribute("account");
				}
				session.setAttribute(FIND_ACC_MESSAGE, FIND_ACC_FAIL);
				response.sendRedirect(RedirectCommandName.GO_TO_ACCOUNT_MANAGEMENT_PAGE);
				return;
			}
			session.setAttribute("account", account);
			response.sendRedirect(RedirectCommandName.GO_TO_ACCOUNT_MANAGEMENT_PAGE);
		} catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE + accountID);
		}
	}
}
