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

public class FindAccountByIDCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(FindAccountByIDCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Account account;
		String accountID;

		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("admin") != null) {

			SessionMessageCleaner.cleanMessageAttributes(session);

			accountID = request.getParameter(RequestParameterName.REQ_PARAM_ACCOUNT_ID);

			if (accountID == null || accountID.isBlank()) {
				session.setAttribute("FindAccountByIDMessage", "Please enter account ID");
				response.sendRedirect("controller?command=go-to-account-management-page");
				return;
			} else {
				AccountService accountService = ServiceProvider.getInstance().getAccountService();

				try {
					account = accountService.getAccountDetails(accountID);

					if (null == account) {
						if (session.getAttribute("account") != null) {
							session.removeAttribute("account");
						}
						session.setAttribute("FindAccountByIDMessage", "No account found with that ID");
						response.sendRedirect("controller?command=go-to-account-management-page");
						return;
					}

					session.setAttribute("account", account);
					response.sendRedirect("controller?command=go-to-account-management-page");
				} catch (ServiceException e) {
					LOGGER.error("Service Exception occurred:  Failed to find accountID: " + accountID);
				}
			}
		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
