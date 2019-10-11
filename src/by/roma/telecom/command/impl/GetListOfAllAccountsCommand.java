package by.roma.telecom.command.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.roma.telecom.bean.Account;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;
import by.roma.telecom.service.AccountService;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class GetListOfAllAccountsCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Account> accountsList;

		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("admin") != null) {

			SessionMessageCleaner.cleanMessageAttributes(session);

			if (session.getAttribute("accountsList") != null) {
				session.removeAttribute("accountsList");
			}

			try {

				AccountService accountService = ServiceProvider.getInstance().getAccountService();
				accountsList = accountService.getListOfAllAccounts();

				if (null == accountsList) {
					session.setAttribute("AccountsListMessage", "Something went wrong, please try again later");
					response.sendRedirect("controller?command=go-to-admin-auth-page");
					return;
				}
				session.setAttribute("accountsList", accountsList);
				session.setAttribute("AccountsListMessage", "List of All accounts in DB");
				response.sendRedirect("controller?command=go-to-admin-auth-page");

			} catch (ServiceException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
