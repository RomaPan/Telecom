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

public class UnblockUserCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(UnblockUserCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User user;
		String userID;

		HttpSession session = request.getSession(false);

		if (session.getAttribute("FindUserByIDMessage") != null) {
			session.removeAttribute("FindUserByIDMessage");
		} else if (session.getAttribute("ChangeUserRoleMessage") != null) {
			session.removeAttribute("ChangeUserRoleMessage");
		}

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
				user = userService.unblockUser(userID);

				if (user == null) {

					session.setAttribute("BlockUserMessage", "User was NOT unblocked");
					response.sendRedirect("controller?command=go-to-user-management-page");
				} else {
					if (session.getAttribute("BlockUserMessage") != null) {
						session.removeAttribute("BlockUserMessage");
					}
					session.setAttribute("user", user);
					session.setAttribute("BlockUserMessage", "User successfully unblocked");
					response.sendRedirect("controller?command=go-to-user-management-page");
				}
			} catch (ServiceException e) {
				LOGGER.error("Service Exception occurred: user was not unblocked: " + userID);
			}
		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}
	}

}
