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
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class ChangePhoneNumberCommand implements Command {
	private AccountService accountService = ServiceProvider.getInstance().getAccountService();
	private static final Logger LOGGER = Logger.getLogger(ChangePhoneNumberCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to change phone number: accountID =";
	private static final String CHANGE_NUMBER = "Service Exception occurred: ";
	private static final String CHANGE_NUMBER_SUCCESS = "New number has been connected to your account.";
	private static final String CHANGE_NUMBER_FAIL = "Something went wrong. Number hasn't been changed.";
	private static final String REDIRECT_COMMAND = "controller?command=go-to-user-auth-page";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String accountID;
		String phoneNumber;
		Account account;
		accountID = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);
		phoneNumber = request.getParameter(RequestParameterName.REQ_PARAM_PHONE_NUMBER);

		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		if (session.getAttribute("numbers") != null) {
			session.removeAttribute("numbers");
		}
		try {
			account = accountService.changePhoneNumber(accountID, phoneNumber);
			if (null == account) {
				session.setAttribute(CHANGE_NUMBER, CHANGE_NUMBER_FAIL);
				response.sendRedirect(REDIRECT_COMMAND);
				return;
			}
			account = accountService.chargeToAccount(accountID, 9.99f);
			session.setAttribute("account", account);
			session.setAttribute(CHANGE_NUMBER, CHANGE_NUMBER_SUCCESS);
			response.sendRedirect(REDIRECT_COMMAND);
		} catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE + phoneNumber);
		}
	}
}
