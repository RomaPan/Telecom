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
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.service.UserService;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class FindUserByEmailCommand implements Command {
	private UserService userService = ServiceProvider.getInstance().getUserService();
	private static final Logger LOGGER = Logger.getLogger(FindUserByEmailCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to find user with email: ";
	private static final String FIND_BY_EMAIL = "FindUserByEmailMessage";
	private static final String FIND_BY_EMAIL_MESSAGE = "No user found with this Email";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User user;
		String userEmail;

		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		userEmail = request.getParameter(RequestParameterName.REQ_PARAM_EMAIL);
		if (userEmail == null || userEmail.isBlank()) {
			session.setAttribute(FIND_BY_EMAIL, FIND_BY_EMAIL_MESSAGE);
			response.sendRedirect(RedirectCommandName.GO_TO_USER_MANAGEMENT_PAGE);
			return;
		} else {
			try {
				user = userService.searchByEmail(userEmail);
				if (user == null) {
					session.setAttribute(FIND_BY_EMAIL, FIND_BY_EMAIL_MESSAGE);
					response.sendRedirect(RedirectCommandName.GO_TO_USER_MANAGEMENT_PAGE);
				} else {
					session.setAttribute("user", user);
					response.sendRedirect(RedirectCommandName.GO_TO_USER_MANAGEMENT_PAGE);
				}
			} catch (ServiceException e) {
				LOGGER.error(LOGGER_MESSAGE + userEmail);
			}
		}
	}
}
