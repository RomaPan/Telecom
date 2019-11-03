package by.roma.telecom.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.roma.telecom.bean.Account;
import by.roma.telecom.bean.User;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;
import by.roma.telecom.service.AccountService;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;

public class GoToAuthUserPageCommand implements Command {
	private AccountService accountService = ServiceProvider.getInstance().getAccountService();
	private static final Logger LOGGER = Logger.getLogger(GoToAuthUserPageCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to aquire account details for an account with ID: ";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Account account = null;
		User user;
		String profileUpdate;

		HttpSession session = request.getSession(false);
		if (session.getAttribute("user") != null) {

			user = (User) session.getAttribute("user");
			profileUpdate = (String) session.getAttribute("ProfileUpdate");
			int accID = user.getUserID();
			String accountID = Integer.toString(accID);
			try {
				account = accountService.getAccountDetails(accountID);
				session.setAttribute("user", user);
				session.setAttribute("account", account);
				request.setAttribute("ProfileUpdate", profileUpdate);
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_AUTH_PAGE);
				dispatcher.forward(request, response);
			} catch (ServiceException e) {
				LOGGER.error(LOGGER_MESSAGE + accID);
			}
		}
	}
}
