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
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class SetCallPlanCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Account account;
		String accountID;
		String callPlanID;
		String command;
		
		command = request.getParameter(RequestParameterName.REQ_PARAM_COMMAND_NAME);
		accountID = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);
		callPlanID = request.getParameter(RequestParameterName.REQ_PARAM_CALL_PLAN);
		
		System.out.println(command + " acc id: " + accountID + " callPlan id: " + callPlanID);

		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("user") != null) {

			SessionMessageCleaner.cleanMessageAttributes(session);

			AccountService accountService = ServiceProvider.getInstance().getAccountService();

			try {
				account = accountService.addCallPlan(accountID, callPlanID);

				if (null == account) {
					session.setAttribute("CallPlanMessage",
							"There was a problem changing your Call Plan. Please contact our Customer Care department.");
					session.setAttribute("account", account);
					response.sendRedirect("controller?command=go-to-user-auth-page");

				} else {

					session.setAttribute("account", account);
					session.setAttribute("CallPlanMessage", "New Call Plan has been set");
					response.sendRedirect("controller?command=go-to-user-auth-page");

				}

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
