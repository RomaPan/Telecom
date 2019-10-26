package by.roma.telecom.command.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;
import by.roma.telecom.controller.RequestParameterName;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.service.UtilService;

public class ViewAvailablePhoneNumberCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(ViewAvailablePhoneNumberCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<String> numbers;

		System.out.println(request.getParameter(RequestParameterName.REQ_PARAM_COMMAND_NAME));

		UtilService utilService = ServiceProvider.getInstance().getUtilService();

		try {
			numbers = utilService.getAvailablePhoneNumbers();

			if (null == numbers) {
				request.setAttribute("NumbersMessage", "Something went wrong, please try again later");
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_REGISTRATION_STEP_TWO);
				dispatcher.forward(request, response);
				return;
			}
			numbers.toString();
			HttpSession session = request.getSession(false);
			session.setAttribute("numbers", numbers);
			request.setAttribute("NumbersMessage", "Please choose your new phone number");
			response.sendRedirect("controller?command=go-to-reg-step-two-page");
		} catch (ServiceException e) {
			LOGGER.error("Service Exception occurred:  Failed to aquire list of available phone numbers from DB");
		}

	}

}
