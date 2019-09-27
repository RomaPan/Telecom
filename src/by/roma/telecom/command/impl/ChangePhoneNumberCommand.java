package by.roma.telecom.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.roma.telecom.command.Command;
import by.roma.telecom.controller.RequestParameterName;

public class ChangePhoneNumberCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String command = request.getParameter(RequestParameterName.REQ_PARAM_COMMAND_NAME);
		System.out.println(command);
		
		String accountID;
		accountID = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);
		System.out.println(accountID);
		
		String phoneNumber = request.getParameter(RequestParameterName.REQ_PARAM_PHONE_NUMBER);
		System.out.println(phoneNumber);
		
		
		
	}

}
