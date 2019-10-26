package by.roma.telecom.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.roma.telecom.bean.Account;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.AccountService;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;

public class ConnectPhoneNumberCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(ConnectPhoneNumberCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String accountID;
		Account account;

		String phoneNumber = request.getParameter(RequestParameterName.REQ_PARAM_PHONE_NUMBER);
		accountID = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);
		AccountService accountService = ServiceProvider.getInstance().getAccountService();

		try {
			account = accountService.connectPhoneNumber(accountID, phoneNumber);

			if (null == account) {
				request.setAttribute("connPhoneNumberMessage",
						"You already have a number associated with your account.");
				response.sendRedirect("controller?command=go-to-reg-step-two-page");
				return;
			}
			HttpSession session = request.getSession(false);
			session.setAttribute("account", account);
			request.setAttribute("account", account);

			response.sendRedirect("controller?command=go-to-reg-step-three-page");

		} catch (ServiceException e) {
			LOGGER.error("Service Exception occurred:  Failed to connect phone number to account = " + accountID);
		}

	}

}
