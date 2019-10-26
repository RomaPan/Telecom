package by.roma.telecom.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.roma.telecom.bean.User;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.service.UserService;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class GetListOfAllUsersCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(GetListOfAllUsersCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("admin") != null) {

			SessionMessageCleaner.cleanMessageAttributes(session);

			try {

				List<User> usersList;
				UserService userService = ServiceProvider.getInstance().getUserService();
				usersList = userService.getListOfAllUsers();

				if (null == usersList) {
					session.setAttribute("UsersListMessage", "Something went wrong, please try again later");
					response.sendRedirect("controller?command=go-to-user-pagination");
					return;
				}

				session.setAttribute("usersList", usersList);
				session.setAttribute("UsersListMessage", "List of All users in Data Base");
				response.sendRedirect("controller?command=go-to-user-pagination");
			} catch (ServiceException e) {
				LOGGER.error("Service Exception occurred:  Failed to obtain list of all users from DB ");
			}
		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}
	}

}
