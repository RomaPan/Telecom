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

public class ChangePhoneNumberCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			
		String accountID;
		String phoneNumber;
		Account account;
		
		accountID = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);
		phoneNumber = request.getParameter(RequestParameterName.REQ_PARAM_PHONE_NUMBER);
		
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("user") != null) {
			
			if (session.getAttribute("numbers") != null) {
				session.removeAttribute("numbers");
			}
			
			SessionMessageCleaner.cleanMessageAttributes(session);
			
			AccountService accountService = ServiceProvider.getInstance().getAccountService();
			try {
				account = accountService.changePhoneNumber(accountID, phoneNumber);
				
				if (null == account) {
					session.setAttribute("ChangePhoneNumber", "Number wasn't changed ");
					response.sendRedirect("controller?command=go-to-user-auth-page");
					return;
				}

				session.setAttribute("account", account);
				session.setAttribute("ChangePhoneNumber", "New number connected to your account. ");
				response.sendRedirect("controller?command=go-to-user-auth-page");
					
				
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			
			
		}else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}
		
	}

}
