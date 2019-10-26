package by.roma.telecom.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;

public class GoToUserPaginationCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("admin") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_PAGINATION);
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("LoginError", "Please sign in to access this part of the website.");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}
		
	}

}
