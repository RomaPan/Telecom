package by.roma.telecom.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.RedirectCommandName;
import by.roma.telecom.session.message.cleaner.SessionMessageCleaner;

public class ClearListOfAllAccountsCommand implements Command {
	private static final String ACCOUNT_LIST = "AccountsListClearMessage";
	private static final String ACCOUNT_LIST_MESSAGE = "Search results cleared.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession(false);
		SessionMessageCleaner.cleanMessageAttributes(session);
		if (session.getAttribute("accountsList") != null) {
			session.removeAttribute("accountsList");
		}
		session.setAttribute(ACCOUNT_LIST, ACCOUNT_LIST_MESSAGE);
		response.sendRedirect(RedirectCommandName.GO_TO_ACCOUNT_PAGINATION);
	}
}
