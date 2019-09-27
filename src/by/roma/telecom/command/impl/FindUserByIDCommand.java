package by.roma.telecom.command.impl;

import java.io.IOException;

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

public class FindUserByIDCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		String userID;

		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("admin") != null) {
			
			if (session.getAttribute("ChangeUserRoleMessage") != null) {
				session.removeAttribute("ChangeUserRoleMessage");
			}
			
			if (session.getAttribute("BlockUserMessage") != null) {
				session.removeAttribute("BlockUserMessage");
			}
			if (session.getAttribute("UsersLisCleartMessage") != null) {
				session.removeAttribute("UsersLisCleartMessage");
			}
			
			userID = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);
			System.out.println(userID);
			if (userID == null) {
				session.setAttribute("FindUserByIDMessage", "Please enter users ID");
				response.sendRedirect("controller?command=go-to-admin-auth-page");
				return;
			} else {
				UserService userService = ServiceProvider.getInstance().getUserService();

				try {
					user = userService.searchByID(userID);

					if (user == null) {
						session.removeAttribute("user");
						session.setAttribute("FindUserByIDMessage", "No user found with this ID");
						RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_AUTH_PAGE);
						dispatcher.forward(request, response);
					} else {
						if (session.getAttribute("FindUserByIDMessage") != null) {
							session.removeAttribute("FindUserByIDMessage");
						}
						session.setAttribute("user", user);
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
