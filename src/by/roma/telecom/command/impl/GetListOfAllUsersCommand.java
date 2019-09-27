package by.roma.telecom.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.roma.telecom.bean.User;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.service.UserService;

public class GetListOfAllUsersCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("admin") != null) {
			try {
				
				List<User> usersList;
				UserService userService = ServiceProvider.getInstance().getUserService();
				usersList = userService.getListOfAllUsers();
				
				
				if (null == usersList) {
					session.setAttribute("UsersListMessage", "Something went wrong, please try again later");
					response.sendRedirect("controller?command=go-to-admin-auth-page");
					return;
				}
				if (session.getAttribute("UsersLisCleartMessage") != null) {
					session.removeAttribute("UsersLisCleartMessage");
				}
				session.setAttribute("usersList", usersList);
				session.setAttribute("UsersListMessage", "List of All users in DB");
				response.sendRedirect("controller?command=go-to-admin-auth-page");
			}catch (ServiceException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("LoginError", "Please sign in to access this part of the website.");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}
	}

}
