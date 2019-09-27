package by.roma.telecom.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.roma.telecom.bean.User;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;
import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.service.UserService;

public class ChangePassCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String id;
		String passOld;
		String passNew;

		id = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);
		passOld = request.getParameter(RequestParameterName.REQ_PARAM_PASS);
		passNew = request.getParameter(RequestParameterName.REQ_PARAM_PASS_NEW);

		UserService userService = ServiceProvider.getInstance().getUserService();
		User user;

		try {
			user = userService.changePass(id, passOld, passNew);
			if (null == user) {
				request.setAttribute("PasswordUpdate", "Incorrect existing password, please try again.");
				request.setAttribute("user", user);
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_AUTH_PAGE);
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("PasswordUpdate", "Password has been changed");
				request.setAttribute("user", user);
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_AUTH_PAGE);
				dispatcher.forward(request, response);
			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
