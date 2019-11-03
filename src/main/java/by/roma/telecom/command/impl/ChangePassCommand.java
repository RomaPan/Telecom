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

public class ChangePassCommand implements Command {
	private UserService userService = ServiceProvider.getInstance().getUserService();
	private static final Logger LOGGER = Logger.getLogger(ChangePassCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to change password for user: ";
	private static final String PASSWORD_UPDATE = "PasswordUpdate";
	private static final String PASSWORD_UPDATE_FAIL = "Incorrect existing password, please try again.";
	private static final String PASSWORD_UPDATE_SUCCESS = "Password has been changed";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String id;
		String passOld;
		String passNew;
		id = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);
		passOld = request.getParameter(RequestParameterName.REQ_PARAM_PASS);
		passNew = request.getParameter(RequestParameterName.REQ_PARAM_PASS_NEW);
		User user;
		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		try {
			user = userService.changePass(id, passOld, passNew);
			if (null == user) {
				request.setAttribute(PASSWORD_UPDATE, PASSWORD_UPDATE_FAIL);
				request.setAttribute("user", user);
				response.sendRedirect(RedirectCommandName.GO_TO_USER_AUTH_PAGE);
			} else {
				request.setAttribute(PASSWORD_UPDATE, PASSWORD_UPDATE_SUCCESS);
				request.setAttribute("user", user);
				response.sendRedirect(RedirectCommandName.GO_TO_USER_AUTH_PAGE);
			}
		} catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE + id);
		}
	}
}
