package by.roma.telecom.command.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.roma.telecom.command.Command;

import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.service.UserService;

public class GetListOfAvailableNumbersCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<String> numbers;

		System.out.println(request.getParameter(RequestParameterName.REQ_PARAM_COMMAND_NAME));

		UserService userService = ServiceProvider.getInstance().getUserService();

		try {
			numbers = userService.getAvailablePhoneNumbers();

			if (null == numbers) {
				HttpSession session = request.getSession(false);
				session.setAttribute("numbers", numbers);
				session.setAttribute("NumbersMessage", "Something went wrong, please try again later");
				response.sendRedirect("controller?command=go-to-user-auth-page");
				return;
			}
			numbers.toString();
			HttpSession session = request.getSession(false);
			session.setAttribute("numbers", numbers);
			session.setAttribute("NumbersMessage", "Please choose your new phone number below");
			response.sendRedirect("controller?command=go-to-user-auth-page");

		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

}
