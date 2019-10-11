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
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class UpdateProfileCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int userID = 0;
		String name;
		String surname;
		String email;
		String addressL1;
		String addressL2;
		String addressL3;

		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("user") != null) {
			
			SessionMessageCleaner.cleanMessageAttributes(session);
			
			try {
				userID = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_USER_ID));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			name = request.getParameter(RequestParameterName.REQ_PARAM_NAME);
			surname = request.getParameter(RequestParameterName.REQ_PARAM_SURNAME);
			email = request.getParameter(RequestParameterName.REQ_PARAM_EMAIL);
			addressL1 = request.getParameter(RequestParameterName.REQ_PARAM_ADDRESSL1);
			addressL2 = request.getParameter(RequestParameterName.REQ_PARAM_ADDRESSL2);
			addressL3 = request.getParameter(RequestParameterName.REQ_PARAM_ADDRESSL3);

			UserService userService = ServiceProvider.getInstance().getUserService();

			User user;

			try {
				user = userService.updateUser(userID, name, surname, email, addressL1, addressL2, addressL3);
				
				if (null == user) {
					
					user = (User) session.getAttribute("user");
					session.setAttribute("user", user);
					session.setAttribute("ProfileUpdate", "Profile details was NOT updated");
					response.sendRedirect("controller?command=go-to-user-auth-page");
					return;
				}

				session.setAttribute("user", user);
				session.setAttribute("ProfileUpdate", "Profile details has been changed");
				response.sendRedirect("controller?command=go-to-user-auth-page");

			} catch (ServiceException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}
		
		
	}
}
