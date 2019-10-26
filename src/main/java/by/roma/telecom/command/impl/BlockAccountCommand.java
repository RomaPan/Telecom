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
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;


public class BlockAccountCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(SetUserAdminRoleCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Account account;
		String accountID;

		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("admin") != null) {

			SessionMessageCleaner.cleanMessageAttributes(session);
			
			accountID = request.getParameter(RequestParameterName.REQ_PARAM_ACCOUNT_ID);
			System.out.println(accountID);
			if (accountID.isEmpty()) {
				session.setAttribute("BlockAccountMessage", "No account ID or phone number selected");
				response.sendRedirect("controller?command=go-to-account-management-page");
				return;
			}
			
			try {
				AccountService accountService = ServiceProvider.getInstance().getAccountService();
				
				account = accountService.blockAccount(accountID);
				account = accountService.getAccountDetails(accountID);
				
				session.setAttribute("BlockAccountMessage", "Account is blocked");
				session.setAttribute("account", account);
				response.sendRedirect("controller?command=go-to-account-management-page");

			} catch (ServiceException e) {
				LOGGER.error("Service Exception occurred:  failed to block an account: " + accountID);
			}

		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
