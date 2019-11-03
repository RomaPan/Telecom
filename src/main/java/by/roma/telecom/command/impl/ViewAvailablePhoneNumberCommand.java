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
import by.roma.telecom.controller.RedirectCommandName;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.service.UtilService;

public class ViewAvailablePhoneNumberCommand implements Command {
	private UtilService utilService = ServiceProvider.getInstance().getUtilService();
	private static final Logger LOGGER = Logger.getLogger(ViewAvailablePhoneNumberCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to aquire list of available phone numbers from DB";
	private static final String NUMBER = "NumbersMessage";
	private static final String NUMBER_S = "Please choose your new phone number";
	private static final String NUMBER_F = "Something went wrong, please try again later";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<String> numbers;
		try {
			numbers = utilService.getAvailablePhoneNumbers();
			if (null == numbers) {
				request.setAttribute(NUMBER, NUMBER_F);
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_REGISTRATION_STEP_TWO);
				dispatcher.forward(request, response);
				return;
			}
			HttpSession session = request.getSession(false);
			session.setAttribute("numbers", numbers);
			request.setAttribute(NUMBER, NUMBER_S);
			response.sendRedirect(RedirectCommandName.GO_TO_REG_STEP_TWO);
		} catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE);
		}
	}
}
