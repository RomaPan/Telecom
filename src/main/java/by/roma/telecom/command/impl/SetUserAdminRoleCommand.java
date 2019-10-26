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

public class SetUserAdminRoleCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(SetUserAdminRoleCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		String userID;

		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("admin") != null) {

			SessionMessageCleaner.cleanMessageAttributes(session);

			userID = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);

			if (userID.isEmpty()) {
				session.setAttribute("ChangeUserRoleMessage", "No user ID selected");
				response.sendRedirect("controller?command=go-to-user-management-page");
				return;
			}

			UserService userService = ServiceProvider.getInstance().getUserService();

			try {
				user = userService.changeUserRole(userID);

				if (user == null) {

					session.setAttribute("ChangeUserRoleMessage", "Role was NOT changed");
					response.sendRedirect("controller?command=go-to-user-management-page");
				} else {

					session.setAttribute("userAdm", user);
					session.setAttribute("ChangeUserRoleMessage", "Role was changed");
					response.sendRedirect("controller?command=go-to-user-management-page");
				}
			} catch (ServiceException e) {
				LOGGER.error("Service Exception occurred: admin rights were not granted for user ID: " + userID);
			}
		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
