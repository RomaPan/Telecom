package by.roma.telecom.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.roma.telecom.command.Command;
import by.roma.telecom.controller.RedirectCommandName;
import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.service.UserService;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class DeleteUserCommand implements Command {
	private UserService userService = ServiceProvider.getInstance().getUserService();
	private static final Logger LOGGER = Logger.getLogger(DeleteUserCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred: failed to delete the user: ";
	private static final String DELETE_USER = "DeleteUserMessage";
	private static final String DELETE_USER_S = "User has been deleted.";
	private static final String DELETE_USER_F = "Failed to delete user. Please try again.";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String userID;
		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		userID = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);
		if (userID == null || userID.isEmpty()) {
			session.setAttribute(DELETE_USER, DELETE_USER_F);
			response.sendRedirect(RedirectCommandName.GO_TO_USER_MANAGEMENT_PAGE);
			return;
		}
		try {
			userService.deleteUser(userID);
			session.setAttribute(DELETE_USER, DELETE_USER_S);
			response.sendRedirect(RedirectCommandName.GO_TO_USER_MANAGEMENT_PAGE);
			
		}catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE + userID);
		}

	}

}
