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
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class FindAccountByPhoneNumberCommand implements Command {
	private AccountService accountService = ServiceProvider.getInstance().getAccountService();
	private static final Logger LOGGER = Logger.getLogger(FindAccountByPhoneNumberCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to find account with phone number: ";
	private static final String FIND_ACCOUNT = "FindAccountByPhoneNumberMessage";
	private static final String FIND_ACCOUNT_MESSAGE = "No account found with that phone number.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Account account;
		String phoneNumber;
		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		phoneNumber = request.getParameter(RequestParameterName.REQ_PARAM_PHONE_NUMBER);

		if (phoneNumber == null || phoneNumber.isBlank()) {
			session.setAttribute(FIND_ACCOUNT, FIND_ACCOUNT_MESSAGE);
			response.sendRedirect(RedirectCommandName.GO_TO_ACCOUNT_MANAGEMENT_PAGE);
			return;
		} else {
			if (session.getAttribute("account") != null) {
				session.removeAttribute("account");
			}
			try {
				account = accountService.findAccountByPhoneNumber(phoneNumber);
				if (null == account) {
					session.setAttribute(FIND_ACCOUNT, FIND_ACCOUNT_MESSAGE);
					response.sendRedirect(RedirectCommandName.GO_TO_ACCOUNT_MANAGEMENT_PAGE);
					return;
				}
				session.setAttribute("account", account);
				response.sendRedirect(RedirectCommandName.GO_TO_ACCOUNT_MANAGEMENT_PAGE);
			} catch (ServiceException e) {
				LOGGER.error(LOGGER_MESSAGE + phoneNumber);
			}
		}
	}
}
