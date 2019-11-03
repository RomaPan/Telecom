package by.roma.telecom.command.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.RedirectCommandName;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.service.UtilService;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class GetListOfAvailableNumbersCommand implements Command {
	private UtilService utilService = ServiceProvider.getInstance().getUtilService();
	private static final Logger LOGGER = Logger.getLogger(GetListOfAvailableNumbersCommand.class);
	private static final String LOGGER_MESSAGE = "Service Exception occurred:  Failed to obtain list of available phone numbers from DB";
	private static final String NUMBERS = "NumbersMessage";
	private static final String NUMBERS_SUCCESS = "Please choose your new phone number below";
	private static final String NUMBERS_FAIL = "Something went wrong, please try again later";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<String> numbers;
		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		try {
			numbers = utilService.getAvailablePhoneNumbers();
			if (null == numbers) {
				session.setAttribute(NUMBERS, NUMBERS_FAIL);
				response.sendRedirect(RedirectCommandName.GO_TO_USER_AUTH_PAGE);
				return;
			}
			session.setAttribute("numbers", numbers);
			session.setAttribute(NUMBERS, NUMBERS_SUCCESS);
			response.sendRedirect(RedirectCommandName.GO_TO_USER_AUTH_PAGE);
		} catch (ServiceException e) {
			LOGGER.error(LOGGER_MESSAGE);
		}
	}
}
