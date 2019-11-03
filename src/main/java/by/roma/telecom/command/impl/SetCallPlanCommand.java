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

public class SetCallPlanCommand implements Command {
	private AccountService accountService = ServiceProvider.getInstance().getAccountService();
	private static final Logger LOGGER = Logger.getLogger(SetCallPlanCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to set call plan for an account: ";
	private static final String CALL_PLAN = "CallPlanMessage";
	private static final String CALL_PLAN_S = "New Call Plan has been set";
	private static final String CALL_PLAN_F = "There was a problem changing your Call Plan. Please contact our Customer Care department.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Account account;
		String accountID;
		String callPlanID;
		accountID = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);
		callPlanID = request.getParameter(RequestParameterName.REQ_PARAM_CALL_PLAN);

		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		try {
			account = accountService.addCallPlan(accountID, callPlanID);
			if (null == account) {
				session.setAttribute(CALL_PLAN, CALL_PLAN_F);
				session.setAttribute("account", account);
				response.sendRedirect(RedirectCommandName.GO_TO_USER_AUTH_PAGE);
			} else {
				session.setAttribute("account", account);
				session.setAttribute(CALL_PLAN, CALL_PLAN_S);
				response.sendRedirect(RedirectCommandName.GO_TO_USER_AUTH_PAGE);
			}
		} catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE + accountID);
		}
	}
}
