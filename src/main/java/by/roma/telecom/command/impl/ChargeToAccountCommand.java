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

public class ChargeToAccountCommand implements Command {
	private AccountService accountService = ServiceProvider.getInstance().getAccountService();
	private static final Logger LOGGER = Logger.getLogger(ChargeToAccountCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to apply charges to an accountID = ";
	private static final String CHARGE_ACCOUNT = "ChargeToAccount";
	private static final String CHARGE_ACCOUNT_FAIL = "No account ID selected";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Account account;
		String accountID;
		float amount;
		HttpSession session = request.getSession(false);
		accountID = request.getParameter(RequestParameterName.REQ_PARAM_ACCOUNT_ID);
		amount = Float.parseFloat(request.getParameter(RequestParameterName.REQ_PARAM_CHARGE_AMOUNT));
		if (accountID == null || accountID.isEmpty()) {
			session.setAttribute(CHARGE_ACCOUNT, CHARGE_ACCOUNT_FAIL);
			response.sendRedirect(RedirectCommandName.GO_TO_USER_AUTH_PAGE);
			return;
		}
		try {
			account = accountService.chargeToAccount(accountID, amount);
			session.setAttribute("account", account);
			response.sendRedirect(RedirectCommandName.GO_TO_USER_AUTH_PAGE);
		} catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE + accountID);
		}
	}
}
