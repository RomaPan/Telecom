package by.roma.telecom.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.roma.telecom.bean.User;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;

public class GoToRegStepTwoCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		
		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		request.setAttribute("user", user);
		session.setAttribute("user", user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_REGISTRATION_STEP_TWO);
		dispatcher.forward(request, response);
		
	}

}
