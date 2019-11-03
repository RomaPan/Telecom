package by.roma.telecom.command.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import by.roma.telecom.bean.User;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.RedirectCommandName;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.service.UserService;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class GetListOfAllUsersCommand implements Command {
	private UserService userService = ServiceProvider.getInstance().getUserService();
	private static final Logger LOGGER = Logger.getLogger(GetListOfAllUsersCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to obtain list of all users from DB ";
	private static final String USER_LIST = "UsersListMessage";
	private static final String USER_LIST_MESSAGE_SUCCESS = "List of All users in Data Base";
	private static final String USER_LIST_MESSAGE_FAIL = "Something went wrong, please try again later";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<User> usersList;
		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		try {
			usersList = userService.getListOfAllUsers();
			if (null == usersList) {
				session.setAttribute(USER_LIST, USER_LIST_MESSAGE_FAIL);
				response.sendRedirect(RedirectCommandName.GO_TO_USER_PAGINATION);
				return;
			}
			session.setAttribute("usersList", usersList);
			session.setAttribute(USER_LIST, USER_LIST_MESSAGE_SUCCESS);
			response.sendRedirect(RedirectCommandName.GO_TO_USER_PAGINATION);
		} catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE);
		}
	}
}
