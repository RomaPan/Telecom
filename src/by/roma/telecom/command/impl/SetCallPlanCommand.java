package by.roma.telecom.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.roma.telecom.bean.Account;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.AccountService;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;

public class SetCallPlanCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String accountID;
		String callPlanID;

		accountID = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);
		callPlanID = request.getParameter(RequestParameterName.REQ_PARAM_CALL_PLAN);
		System.out.println(accountID);
		System.out.println(callPlanID);

		AccountService accountService = ServiceProvider.getInstance().getAccountService();

		Account account;

		try {
			account = accountService.addCallPlan(accountID, callPlanID);

			if (null == account) {
				HttpSession session = request.getSession(false);
				session.setAttribute("CallPlanMessage",
						"There was a problem changing your Call Plan. Please contact our Customer Care department.");
				session.setAttribute("account", account);
				request.setAttribute("account", account);
				response.sendRedirect("controller?command=go-to-user-auth-page");

			} else {

				HttpSession session = request.getSession(false);
				session.setAttribute("account", account);
				request.setAttribute("account", account);
				session.setAttribute("CallPlanMessage", "New Call Plan has been set");
				request.setAttribute("CallPlanMessage", "New Call Plan has been set");
				response.sendRedirect("controller?command=go-to-user-auth-page");

			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

}
