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
import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.UserService;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;

public class AuthorizationCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String login;
		String password;

		login = request.getParameter(RequestParameterName.REQ_PARAM_EMAIL);
		password = request.getParameter(RequestParameterName.REQ_PARAM_PASS);
		UserService userService = ServiceProvider.getInstance().getUserService();
		User user;

		try {
			user = userService.authorization(login, password);
			if (null == user) {
				
				request.setAttribute("LogInFailed", "The username or password is incorrect. Please try again.");				
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
				dispatcher.forward(request, response);
				return;
			}
			if (user.isBlocked()) {
				
				request.setAttribute("LogInFailed", "Your account is suspended, please contact our technical support");
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
				dispatcher.forward(request, response);
			}
			
			
			if (user.isAdmin()) {
				HttpSession session = request.getSession(true);
				session.setAttribute("admin", user);
				response.sendRedirect("controller?command=go-to-admin-auth-page");
				
			} else {
				HttpSession session = request.getSession(true);
				session.setAttribute("user", user);
				session.setAttribute("userID", user.getUserID());
				response.sendRedirect("controller?command=go-to-user-auth-page");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

}
