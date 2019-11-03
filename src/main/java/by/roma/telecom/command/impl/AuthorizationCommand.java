package by.roma.telecom.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import by.roma.telecom.bean.User;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.RedirectCommandName;
import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.UserService;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;

public class AuthorizationCommand implements Command {
	private UserService userService = ServiceProvider.getInstance().getUserService();
	private static final Logger LOGGER = Logger.getLogger(AuthorizationCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to authorize user: ";
	private static final String LOG_IN_FAILED = "The username or password is incorrect. Please try again.";
	private static final String USER_BLOCKED = "Your account is suspended, please contact our customer support team.";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		User user;
		String login;
		String password;
		login = request.getParameter(RequestParameterName.REQ_PARAM_EMAIL);
		password = request.getParameter(RequestParameterName.REQ_PARAM_PASS);

		HttpSession session = request.getSession(true);
		try {
			user = userService.authorization(login, password);
			if (null == user) {
				session.setAttribute("LogInFailed", LOG_IN_FAILED);
				response.sendRedirect(RedirectCommandName.GO_TO_LOGIN_PAGE);
				return;
			} else if (user.isBlocked()) {
				session.setAttribute("LogInFailedUserBlocked", USER_BLOCKED);
				response.sendRedirect(RedirectCommandName.GO_TO_LOGIN_PAGE);
			} else if (user.isAdmin()) {
				session.setAttribute("admin", user);
				response.sendRedirect(RedirectCommandName.GO_TO_ADMIN_AUTH_PAGE);
			} else {
				session.setAttribute("user", user);
				session.setAttribute("userID", user.getUserID());
				response.sendRedirect(RedirectCommandName.GO_TO_USER_AUTH_PAGE);
			}
		} catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE + login);
		}
	}
}
