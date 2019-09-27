package by.roma.telecom.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;
import by.roma.telecom.controller.RequestParameterName;

public class GoToLoginPageCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String command;
		command = request.getParameter(RequestParameterName.REQ_PARAM_COMMAND_NAME);
		System.out.println(command);
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
		dispatcher.forward(request, response);
		
	}

}
