package by.roma.telecom.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.roma.telecom.bean.User;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;
import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.service.UserService;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class FindUserByEmailCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(FindUserByEmailCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User user;
		String userEmail;

		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("admin") != null) {

			SessionMessageCleaner.cleanMessageAttributes(session);

			if (session.getAttribute("user") != null) {
				session.removeAttribute("user");
			}
			userEmail = request.getParameter(RequestParameterName.REQ_PARAM_EMAIL);

			if (userEmail == null) {
				session.setAttribute("FindUserByEmailMessage", "Please enter user Email");
				response.sendRedirect("controller?command=go-to-user-management-page");
				return;
			} else {
				UserService userService = ServiceProvider.getInstance().getUserService();

				try {
					user = userService.searchByEmail(userEmail);

					if (user == null) {
						session.setAttribute("FindUserByEmailMessage", "No user found with this Email");
						response.sendRedirect("controller?command=go-to-user-management-page");
					} else {
						session.setAttribute("user", user);
						response.sendRedirect("controller?command=go-to-user-management-page");
					}
				} catch (ServiceException e) {
					LOGGER.error("Service Exception occurred:  Failed to find user with email: " + userEmail);
				}
			}
		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
