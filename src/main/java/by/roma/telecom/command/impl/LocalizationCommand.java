package by.roma.telecom.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.RedirectCommandName;
import by.roma.telecom.controller.RequestParameterName;

public class LocalizationCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String language;
		String location;
		language = request.getParameter(RequestParameterName.REQ_PARAM_LANGUAGE);
		location = request.getParameter(RequestParameterName.REQ_PARAM_REQUEST_LOCATION);
		HttpSession session = request.getSession();
		if (session.getAttribute("local") != null) {
			session.removeAttribute("local");
		}
		if (location.equals("index")) {
			session.setAttribute("local", language);
			response.sendRedirect(RedirectCommandName.GO_TO_INDEX_PAGE);
		} else if (location.equals("plans")) {
			session.setAttribute("local", language);
			response.sendRedirect(RedirectCommandName.GO_TO_PLANS_PAGE);
		} else if (location.equals("contacts")) {
			session.setAttribute("local", language);
			response.sendRedirect(RedirectCommandName.GO_TO_CONTACTS_PAGE);
		} else if (location.equals("registration")) {
			session.setAttribute("local", language);
			response.sendRedirect(RedirectCommandName.GO_TO_REGISTRATION_PAGE);
		} else if (location.equals("userpage")) {
			session.setAttribute("local", language);
			response.sendRedirect(RedirectCommandName.GO_TO_USER_AUTH_PAGE);
		} else if (location.equals("adminpage")) {
			session.setAttribute("local", language);
			response.sendRedirect(RedirectCommandName.GO_TO_ADMIN_AUTH_PAGE);
		} else if (location.equals("signin")) {
			session.setAttribute("local", language);
			response.sendRedirect(RedirectCommandName.GO_TO_LOGIN_PAGE);
		} else if (location.equals("user-pagination")) {
			session.setAttribute("local", language);
			response.sendRedirect(RedirectCommandName.GO_TO_USER_PAGINATION);
		} else if (location.equals("account-management")) {
			session.setAttribute("local", language);
			response.sendRedirect(RedirectCommandName.GO_TO_ACCOUNT_MANAGEMENT_PAGE);
		} else if (location.equals("user-management")) {
			session.setAttribute("local", language);
			response.sendRedirect(RedirectCommandName.GO_TO_USER_MANAGEMENT_PAGE);
		} else if (location.equals("account-pagination")) {
			session.setAttribute("local", language);
			response.sendRedirect(RedirectCommandName.GO_TO_ACCOUNT_PAGINATION);
		}
	}

}
