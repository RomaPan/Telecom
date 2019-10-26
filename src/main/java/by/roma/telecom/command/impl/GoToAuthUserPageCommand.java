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

	private static final Logger LOGGER = Logger.getLogger(GoToAuthUserPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Account account = null;
		User user;
		String profileUpdate;

		AccountService accountService = ServiceProvider.getInstance().getAccountService();

		HttpSession session = request.getSession(false);

		if (session != null) {

			if (session.getAttribute("user") != null) {

				user = (User) session.getAttribute("user");

				profileUpdate = (String) session.getAttribute("ProfileUpdate");

				int accID = user.getUserID();
				String accountID = Integer.toString(accID);

				try {
					account = accountService.getAccountDetails(accountID);

				} catch (ServiceException e) {
					LOGGER.error("Service Exception occurred:  Failed to aquire account details for an account with ID: " + accID);
				}
				session.setAttribute("user", user);
				session.setAttribute("account", account);
				request.setAttribute("ProfileUpdate", profileUpdate);
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_AUTH_PAGE);
				dispatcher.forward(request, response);

			} else if (session.getAttribute("admin") != null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_AUTH_PAGE);
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("LoginError", "Please sign in to access this part of the website.");
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("LoginError", "Access Denied. Please sign in to access this part of the website.");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ERROR_ACCESS_DENIED_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
