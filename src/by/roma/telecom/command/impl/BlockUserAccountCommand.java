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


public class BlockUserAccountCommand implements Command{

	private static final Logger LOGGER = Logger.getLogger(SetUserAdminRoleCommand.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String accountID;
		
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("admin") != null) {
			
			Account account;
			
			accountID = request.getParameter(RequestParameterName.REQ_PARAM_ACCOUNT_ID);
			
			AccountService accountService = ServiceProvider.getInstance().getAccountService();
			
			if (session.getAttribute("AccountBlockMessage") != null ) {
				session.removeAttribute("AccountBlockMessage");
			}
			
			if (accountID.isEmpty()) {
				session.setAttribute("AccountBlockMessage", "No account ID selected");
				response.sendRedirect("controller?command=go-to-admin-auth-page");
				return;
			}
			
			try {
				account = accountService.blockAccount(accountID);
				
				session.setAttribute("account", account);
				session.setAttribute("AccountBlockMessage", "Account is blocked");
				response.sendRedirect("controller?command=go-to-admin-auth-page");
				
			} catch (ServiceException e) {
				LOGGER.error("Service Exception occurred:  failed to block account.");
			}
			
			
			
			
		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}
		
	}

}
