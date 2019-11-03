package by.roma.telecom.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class GoToAuthAdminPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_AUTH_PAGE);
		dispatcher.forward(request, response);

	}

}
