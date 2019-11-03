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

public class ConnectPhoneNumberCommand implements Command {
	private AccountService accountService = ServiceProvider.getInstance().getAccountService();
	private static final Logger LOGGER = Logger.getLogger(ConnectPhoneNumberCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to connect phone number to account = ";
	private static final String CONN_PHONE = "connPhoneNumberMessage";
	private static final String CONN_PHONE_MESSAGE = "You already have a number associated with your account.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String accountID;
		Account account;
		String phoneNumber = request.getParameter(RequestParameterName.REQ_PARAM_PHONE_NUMBER);
		accountID = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);
		try {
			account = accountService.connectPhoneNumber(accountID, phoneNumber);
			if (null == account) {
				request.setAttribute(CONN_PHONE, CONN_PHONE_MESSAGE);
				response.sendRedirect(RedirectCommandName.GO_TO_REG_STEP_TWO);
				return;
			}
			HttpSession session = request.getSession(false);
			session.setAttribute("account", account);
			request.setAttribute("account", account);
			response.sendRedirect(RedirectCommandName.GO_TO_REG_STEP_THREE);
		} catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE + accountID);
		}
	}
}
