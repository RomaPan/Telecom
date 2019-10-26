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

public class RegistrationCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String name;
		String surname;
		String email;
		String addressL1;
		String addressL2;
		String addressL3;
		String pass;

		name = request.getParameter(RequestParameterName.REQ_PARAM_NAME);
		surname = request.getParameter(RequestParameterName.REQ_PARAM_SURNAME);
		email = request.getParameter(RequestParameterName.REQ_PARAM_EMAIL);
		addressL1 = request.getParameter(RequestParameterName.REQ_PARAM_ADDRESSL1);
		addressL2 = request.getParameter(RequestParameterName.REQ_PARAM_ADDRESSL2);
		addressL3 = request.getParameter(RequestParameterName.REQ_PARAM_ADDRESSL3);
		pass = request.getParameter(RequestParameterName.REQ_PARAM_PASS);

		UserService userService = ServiceProvider.getInstance().getUserService();

		User user;

		try {
			user = userService.registration(name, surname, email, addressL1, addressL2, addressL3, pass);

			if (null == user) {
				request.setAttribute("message",
						"Failed to create an account, Please check your details and try again.");
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_REGISTRATION);
				dispatcher.forward(request, response);
				return;
			}
			request.getSession().invalidate();

			HttpSession session = request.getSession(true);

			session.setAttribute("user", user);
			request.setAttribute("user", user);
			response.sendRedirect("controller?command=get-phone-numbers");

		} catch (ServiceException e) {
			LOGGER.error("Service Exception occurred:  Failed to complete registration for a user: " + email);
		}

	}

}
