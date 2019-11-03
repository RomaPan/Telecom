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
import by.roma.telecom.controller.RedirectCommandName;
import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.AccountService;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.service.UserService;

public class RegistrationCommand implements Command {
	private UserService userService = ServiceProvider.getInstance().getUserService();
	private AccountService accountService = ServiceProvider.getInstance().getAccountService();
	private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to complete registration for a user: ";
	private static final String MESSAGE = "message";
	private static final String MESSAGE_FAIL = "Failed to create an account, Please check your details and try again.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User user;
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

		try {
			user = userService.registration(name, surname, email, addressL1, addressL2, addressL3, pass);
			if (null == user) {
				request.setAttribute(MESSAGE, MESSAGE_FAIL);
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_REGISTRATION);
				dispatcher.forward(request, response);
				return;
			}
			accountService.insertAccount(user.getUserID());
			
			request.getSession().invalidate();
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			request.setAttribute("user", user);
			response.sendRedirect(RedirectCommandName.GET_PHONE_NUMBERS);
		} catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE + email);
		}
	}
}
