package by.roma.telecom.command.impl;

import java.io.IOException;
//import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.roma.telecom.bean.User;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;
import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.service.UserService;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class FindUserByIDCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		String userID;

		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("admin") != null) {

			SessionMessageCleaner.cleanMessageAttributes(session);

			userID = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);

			if (userID == null) {
				session.setAttribute("FindUserByIDMessage", "Please enter users ID");
				response.sendRedirect("controller?command=go-to-admin-auth-page");
				return;
			} else {
				UserService userService = ServiceProvider.getInstance().getUserService();

				try {
					user = userService.searchByID(userID);

					if (user == null) {
						session.setAttribute("FindUserByIDMessage", "No user found with this ID");
						RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_AUTH_PAGE);
						dispatcher.forward(request, response);
					} else {
						
						session.setAttribute("userAdm", user);
						RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_AUTH_PAGE);
						dispatcher.forward(request, response);
					}
				} catch (ServiceException e) {
					e.printStackTrace();
				}
			}
		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
