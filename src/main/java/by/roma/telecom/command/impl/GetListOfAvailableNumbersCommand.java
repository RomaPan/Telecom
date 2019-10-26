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
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.ServiceProvider;
import by.roma.telecom.service.UtilService;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class GetListOfAvailableNumbersCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(GetListOfAvailableNumbersCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<String> numbers;

		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);

		if (session != null && session.getAttribute("user") != null) {
			UtilService utilService = ServiceProvider.getInstance().getUtilService();

			try {
				numbers = utilService.getAvailablePhoneNumbers();

				if (null == numbers) {
					session.setAttribute("NumbersMessage", "Something went wrong, please try again later");
					response.sendRedirect("controller?command=go-to-user-auth-page");
					return;
				}
				numbers.toString();
				session.setAttribute("numbers", numbers);
				session.setAttribute("NumbersMessage", "Please choose your new phone number below");
				response.sendRedirect("controller?command=go-to-user-auth-page");

			} catch (ServiceException e) {
				LOGGER.error("Service Exception occurred:  Failed to obtain list of available phone numbers from DB");
			}
		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
