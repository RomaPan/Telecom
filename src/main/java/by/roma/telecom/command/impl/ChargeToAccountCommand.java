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

public class ChargeToAccountCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(ChargeToAccountCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Account account;

		String accountID;
		float amount;

		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("user") != null) {

			accountID = request.getParameter(RequestParameterName.REQ_PARAM_ACCOUNT_ID);
			amount = Float.parseFloat(request.getParameter(RequestParameterName.REQ_PARAM_CHARGE_AMOUNT));

			AccountService accountService = ServiceProvider.getInstance().getAccountService();

			try {
				account = accountService.chargeToAccount(accountID, amount);
				session.setAttribute("account", account);
				response.sendRedirect("controller?command=go-to-user-auth-page");
			} catch (ServiceException e) {
				LOGGER.error("Service Exception occurred:  Failed to apply charges to an accountID = " + accountID);
			}
		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}
	}

}
