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

public class UnblockUserCommand implements Command {
	private UserService userService = ServiceProvider.getInstance().getUserService();
	private static final Logger LOGGER = Logger.getLogger(UnblockUserCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred: user was not unblocked: ";
	private static final String BLOCK_USER = "BlockUserMessage";
	private static final String BLOCK_USER_S = "User successfully unblocked";
	private static final String BLOCK_USER_F = "User was NOT unblocked";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User user;
		String userID;

		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		userID = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);
		if (userID == null || userID.isEmpty()) {
			session.setAttribute(BLOCK_USER, BLOCK_USER_F);
			response.sendRedirect(RedirectCommandName.GO_TO_USER_MANAGEMENT_PAGE);
			return;
		}
		try {
			user = userService.changeUserBlockStatus(userID, false);
			if (user == null) {
				session.setAttribute(BLOCK_USER, BLOCK_USER_F);
				response.sendRedirect(RedirectCommandName.GO_TO_USER_MANAGEMENT_PAGE);
			} else {
				session.setAttribute("user", user);
				session.setAttribute(BLOCK_USER, BLOCK_USER_S);
				response.sendRedirect(RedirectCommandName.GO_TO_USER_MANAGEMENT_PAGE);
			}
		} catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE + userID);
		}
	}
}
