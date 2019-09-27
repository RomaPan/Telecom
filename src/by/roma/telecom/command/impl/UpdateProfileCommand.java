package by.roma.telecom.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.roma.telecom.bean.User;
import by.roma.telecom.command.Command;

import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.service.UserService;

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

		System.out.println(userID + " " + name + " " + surname + " " + email + " " + addressL1 + " " + addressL2 + " "
				+ addressL3);

		UserService userService = ServiceProvider.getInstance().getUserService();

		User user;

		try {
			user = userService.updateUser(userID, name, surname, email, addressL1, addressL2, addressL3);
			
			if (null == user) {
				HttpSession session = request.getSession(false);
				user = (User) session.getAttribute("user");
				session.setAttribute("user", user);
				request.setAttribute("ProfileUpdate", "Profile details was NOT updated");
				response.sendRedirect("controller?command=go-to-user-auth-page");
				return;
			}
			System.out.println(user.toString());
			HttpSession session = request.getSession(false);
			request.getSession().removeAttribute("user");
			session.setAttribute("user", user);
			request.setAttribute("user", user);
			session.setAttribute("ProfileUpdate", "Profile details has been changed");

			response.sendRedirect("controller?command=go-to-user-auth-page");

		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
