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

public class SetUserAdminRoleCommand implements Command {
	private UserService userService = ServiceProvider.getInstance().getUserService();
	private static final Logger LOGGER = Logger.getLogger(SetUserAdminRoleCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred: admin rights were not granted for user ID: ";
	private static final String CHANGE = "ChangeUserRoleMessage";
	private static final String CHANGE_S = "Role was changed";
	private static final String CHANGE_F = "Role was NOT changed";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		String userID;
		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		userID = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);

		if (userID == null || userID.isEmpty()) {
			session.setAttribute(CHANGE, CHANGE_F);
			response.sendRedirect(RedirectCommandName.GO_TO_USER_MANAGEMENT_PAGE);
			return;
		}
		try {
			user = userService.changeUserRole(userID);
			if (user == null) {
				session.setAttribute(CHANGE, CHANGE_F);
				response.sendRedirect(RedirectCommandName.GO_TO_USER_MANAGEMENT_PAGE);
			} else {
				session.setAttribute("userAdm", user);
				session.setAttribute(CHANGE, CHANGE_S);
				response.sendRedirect(RedirectCommandName.GO_TO_USER_MANAGEMENT_PAGE);
			}
		} catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE + userID);
		}
	}
}
