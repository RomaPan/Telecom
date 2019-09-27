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

public class FindAccountByIDCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("admin") != null) {

			Account account;
			String accountID;

			accountID = request.getParameter(RequestParameterName.REQ_PARAM_ACCOUNT_ID);

			if (accountID == null || accountID.isBlank()) {
				session.setAttribute("FindAccountByIDMessage", "Please enter account ID");
				response.sendRedirect("controller?command=go-to-admin-auth-page");
				return;
			} else {
				
				if (session.getAttribute("account") != null ) {
					session.removeAttribute("account");
				}
				if (session.getAttribute("FindAccountByIDMessage") != null ) {
					session.removeAttribute("FindAccountByIDMessage");
				}
				
				if (session.getAttribute("AccountBlockMessage") != null ) {
					session.removeAttribute("AccountBlockMessage");
				}

				AccountService accountService = ServiceProvider.getInstance().getAccountService();

				try {
					account = accountService.getAccountDetails(accountID);

					if (null == account) {
						session.setAttribute("FindAccountByIDMessage", "No account found with that ID");
						response.sendRedirect("controller?command=go-to-admin-auth-page");
						return;
					}
					
					session.setAttribute("account", account);
					response.sendRedirect("controller?command=go-to-admin-auth-page");
				} catch (ServiceException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
