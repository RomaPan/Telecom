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

public class FindAccountByPhoneNumberCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(FindAccountByPhoneNumberCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Account account;
		String phoneNumber;

		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("admin") != null) {

			SessionMessageCleaner.cleanMessageAttributes(session);

			phoneNumber = request.getParameter(RequestParameterName.REQ_PARAM_PHONE_NUMBER);

			if (phoneNumber == null || phoneNumber.isBlank()) {
				session.setAttribute("FindAccountByPhoneNumberMessage", "Please enter account phone number");
				response.sendRedirect("controller?command=go-to-account-management-page");
				return;
			} else {

				if (session.getAttribute("account") != null) {
					session.removeAttribute("account");
				}

				AccountService accountService = ServiceProvider.getInstance().getAccountService();

				try {
					account = accountService.findAccountByPhoneNumber(phoneNumber);

					if (null == account) {
						session.setAttribute("FindAccountByPhoneNumberMessage",
								"No account found with that phone number");
						response.sendRedirect("controller?command=go-to-account-management-page");
						return;
					}
					System.out.println(account.toString());
					session.setAttribute("account", account);
					response.sendRedirect("controller?command=go-to-account-management-page");
				} catch (ServiceException e) {
					LOGGER.error(
							"Service Exception occurred:  Failed to find account with phone number: " + phoneNumber);
				}
			}
		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
