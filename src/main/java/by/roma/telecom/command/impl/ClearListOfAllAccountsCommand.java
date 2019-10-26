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

public class ClearListOfAllAccountsCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("admin") != null) {

			SessionMessageCleaner.cleanMessageAttributes(session);

			if (session.getAttribute("accountsList") != null) {
				session.removeAttribute("accountsList");
			}

			session.setAttribute("AccountsListClearMessage", "Search results cleared.");
			response.sendRedirect("controller?command=go-to-account-pagination");

		} else {
			request.setAttribute("Message", "Session timed out, please sign in");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}
	}
}
