package by.roma.telecom.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;


public class SignOutCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		request.getSession().invalidate();
		request.setAttribute("SignOut", "You’ve been signed out successfully.");
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
		dispatcher.forward(request, response);
	}

}
