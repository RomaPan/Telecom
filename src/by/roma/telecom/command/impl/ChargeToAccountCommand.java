package by.roma.telecom.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.roma.telecom.bean.Account;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;
import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.AccountService;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;

public class ChargeToAccountCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Account account;

		int accountID;
		float amount;

		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("user") != null) {

			accountID = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_ACCOUNT_ID));
			amount = Float.parseFloat(request.getParameter(RequestParameterName.REQ_PARAM_CHARGE_AMOUNT));

			AccountService accountService = ServiceProvider.getInstance().getAccountService();

			try {
				account = accountService.chargeToAccount(accountID, amount);
				session.setAttribute("account", account);
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
