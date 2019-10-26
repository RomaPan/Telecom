package by.roma.telecom.command.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.roma.telecom.bean.Account;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;
import by.roma.telecom.service.AccountService;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class GetListOfAllAccountsCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(GetListOfAllAccountsCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Account> accountsList;

		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("admin") != null) {

			SessionMessageCleaner.cleanMessageAttributes(session);

			try {

				AccountService accountService = ServiceProvider.getInstance().getAccountService();

				accountsList = accountService.getListOfAllAccounts();

				if (null == accountsList) {
					session.setAttribute("AccountsListMessage", "Something went wrong, please try again later");
					response.sendRedirect("controller?command=go-to-account-pagination");
					return;
				}
				session.setAttribute("accountsList", accountsList);
				session.setAttribute("AccountsListMessage", "List of All accounts in DB");
				response.sendRedirect("controller?command=go-to-account-pagination");

			} catch (ServiceException e) {
				LOGGER.error("Service Exception occurred:  Failed to obtain list of all accounts from DB");
			}
		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
