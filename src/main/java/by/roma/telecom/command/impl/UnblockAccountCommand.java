package by.roma.telecom.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import by.roma.telecom.bean.Account;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;
import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.AccountService;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class UnblockAccountCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(UnblockAccountCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Account account;
		String accountID;

		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("admin") != null) {

			SessionMessageCleaner.cleanMessageAttributes(session);

			accountID = request.getParameter(RequestParameterName.REQ_PARAM_ACCOUNT_ID);

			AccountService accountService = ServiceProvider.getInstance().getAccountService();

			if (accountID.isEmpty()) {
				session.setAttribute("UnblockAccountMessage", "No account ID selected");
				response.sendRedirect("controller?command=go-to-account-management-page");
				return;
			}

			try {
				account = accountService.unblockAccount(accountID);

				session.setAttribute("account", account);
				session.setAttribute("UnblockAccountMessage", "Account is unblocked");
				response.sendRedirect("controller?command=go-to-account-management-page");

			} catch (ServiceException e) {
				LOGGER.error("Service Exception occurred:  failed to unblock an account: " + accountID);
			}

		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
