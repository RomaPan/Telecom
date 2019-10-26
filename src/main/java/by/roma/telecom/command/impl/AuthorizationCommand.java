package by.roma.telecom.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import by.roma.telecom.bean.User;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.UserService;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;

public class AuthorizationCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(AuthorizationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String login;
		String password;

		login = request.getParameter(RequestParameterName.REQ_PARAM_EMAIL);
		password = request.getParameter(RequestParameterName.REQ_PARAM_PASS);
		UserService userService = ServiceProvider.getInstance().getUserService();
		User user;
		
		HttpSession session = request.getSession(true);
		try {
			user = userService.authorization(login, password);
			if (null == user) {
				session.setAttribute("LogInFailed", "The username or password is incorrect. Please try again.");
				response.sendRedirect("controller?command=go-to-login-page");
				return;
			} else if (user.isBlocked()) {
				session.setAttribute("LogInFailedUserBlocked",
						"Your account is suspended, please contact our customer support team.");
				response.sendRedirect("controller?command=go-to-login-page");
			} else if (user.isAdmin()) {
				session.setAttribute("admin", user);
				response.sendRedirect("controller?command=go-to-admin-auth-page");
			} else {
				session.setAttribute("user", user);
				session.setAttribute("userID", user.getUserID());
				response.sendRedirect("controller?command=go-to-user-auth-page");
			}
		} catch (ServiceException e) {
			LOGGER.error("Service Exception occurred:  Failed to authorize user: " + login);
		}

	}

}
