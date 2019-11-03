package by.roma.telecom.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.RedirectCommandName;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class ClearListOfAllUsersCommand implements Command {
	private static final String USER_LIST = "UsersLisCleartMessage";
	private static final String USER_LIST_MESSAGE = "UsersLisCleartMessage";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		if (session.getAttribute("usersList") != null) {
			session.removeAttribute("usersList");
		}
		session.setAttribute(USER_LIST, USER_LIST_MESSAGE);
		response.sendRedirect(RedirectCommandName.GO_TO_USER_PAGINATION);
	}

}
