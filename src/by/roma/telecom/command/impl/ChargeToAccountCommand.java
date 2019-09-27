package by.roma.telecom.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.roma.telecom.bean.Account;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.AccountService;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;

public class ChargeToAccountCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Account account;

		int accountID;
		float amount;
		
		accountID = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_ACCOUNT_ID));
		amount = Float.parseFloat(request.getParameter(RequestParameterName.REQ_PARAM_CHARGE_AMOUNT));
		
		AccountService accountService = ServiceProvider.getInstance().getAccountService();
		
		try {
			account = accountService.chargeToAccount(accountID, amount);
			account.toString();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}

}
