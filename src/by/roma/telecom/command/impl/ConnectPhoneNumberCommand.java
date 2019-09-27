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

public class ConnectPhoneNumberCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String command = request.getParameter(RequestParameterName.REQ_PARAM_COMMAND_NAME);
		System.out.println(command);

		String phoneNumber = request.getParameter(RequestParameterName.REQ_PARAM_PHONE_NUMBER);
		System.out.println(phoneNumber);

		String accountID;
		accountID = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);
		System.out.println(accountID);

		Account account;

		AccountService accountService = ServiceProvider.getInstance().getAccountService();

		try {
			account = accountService.connectPhoneNumber(accountID, phoneNumber);

			if (null == account) {
				request.setAttribute("connPhoneNumberMessage",
						"You already have a number associated with your account.");
				response.sendRedirect("controller?command=go-to-reg-step-two-page");
				return;
			}
			HttpSession session = request.getSession(false);
			session.setAttribute("account", account);
			request.setAttribute("account", account);

			response.sendRedirect("controller?command=go-to-reg-step-three-page");

		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

}
