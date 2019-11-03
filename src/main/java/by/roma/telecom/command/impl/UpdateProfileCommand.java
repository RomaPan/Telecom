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

public class UpdateProfileCommand implements Command {
	private UserService userService = ServiceProvider.getInstance().getUserService();
	private static final Logger LOGGER = Logger.getLogger(UpdateProfileCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to update profile data for userID: ";
	private static final String LOGGER_NFM_MESSAGE = "Number Format Exception occurred: command Update Profile, UID: ";
	private static final String PROFILE_UPDATE = "ProfileUpdate";
	private static final String UPDATE_S = "Profile details has been changed";
	private static final String UPDATE_F = "Profile details was NOT updated";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		int userID = 0;
		String name;
		String surname;
		String email;
		String addressL1;
		String addressL2;
		String addressL3;

		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		try {
			userID = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_USER_ID));
		} catch (NumberFormatException e) {
			LOGGER.error(LOGGER_NFM_MESSAGE + userID);
		}
		name = request.getParameter(RequestParameterName.REQ_PARAM_NAME);
		surname = request.getParameter(RequestParameterName.REQ_PARAM_SURNAME);
		email = request.getParameter(RequestParameterName.REQ_PARAM_EMAIL);
		addressL1 = request.getParameter(RequestParameterName.REQ_PARAM_ADDRESSL1);
		addressL2 = request.getParameter(RequestParameterName.REQ_PARAM_ADDRESSL2);
		addressL3 = request.getParameter(RequestParameterName.REQ_PARAM_ADDRESSL3);
		try {
			user = userService.updateUser(userID, name, surname, email, addressL1, addressL2, addressL3);
			if (null == user) {
				user = (User) session.getAttribute("user");
				session.setAttribute("user", user);
				session.setAttribute(PROFILE_UPDATE, UPDATE_F);
				response.sendRedirect(RedirectCommandName.GO_TO_USER_AUTH_PAGE);
				return;
			}
			session.setAttribute("user", user);
			session.setAttribute(PROFILE_UPDATE, UPDATE_S);
			response.sendRedirect(RedirectCommandName.GO_TO_USER_AUTH_PAGE);
		} catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE + userID);
		}
	}
}
