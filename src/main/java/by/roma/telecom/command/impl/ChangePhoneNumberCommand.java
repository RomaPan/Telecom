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

public class ChangePhoneNumberCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(ChangePhoneNumberCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String accountID;
		String phoneNumber;
		Account account;

		accountID = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);
		phoneNumber = request.getParameter(RequestParameterName.REQ_PARAM_PHONE_NUMBER);

		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("user") != null) {

			if (session.getAttribute("numbers") != null) {
				session.removeAttribute("numbers");
			}

			SessionMessageCleaner.cleanMessageAttributes(session);

			AccountService accountService = ServiceProvider.getInstance().getAccountService();
			try {
				account = accountService.changePhoneNumber(accountID, phoneNumber);

				if (null == account) {
					session.setAttribute("ChangePhoneNumberFailed", "Something went wrong. Number hasn't been changed. ");
					response.sendRedirect("controller?command=go-to-user-auth-page");
					return;
				}
				account = accountService.chargeToAccount(accountID, 9.99f);
				session.setAttribute("account", account);
				session.setAttribute("ChangePhoneNumberSuccess", "New number has been connected to your account. ");
				response.sendRedirect("controller?command=go-to-user-auth-page");

			} catch (ServiceException e) {
				LOGGER.error("Service Exception occurred:  Failed to change phone number: accountID=" + accountID
						+ " new phone number: " + phoneNumber);
			}

		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
