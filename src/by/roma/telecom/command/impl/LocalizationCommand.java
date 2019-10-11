package by.roma.telecom.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.roma.telecom.command.Command;
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
			response.sendRedirect("controller?command=go-to-index-page");
		} else if (location.equals("plans")) {
			session.setAttribute("local", language);
			response.sendRedirect("controller?command=go-to-plans-page");
		} else if (location.equals("contacts")) {
			session.setAttribute("local", language);
			response.sendRedirect("controller?command=go-to-contacts-page");
		} else if (location.equals("registration")) {
			session.setAttribute("local", language);
			response.sendRedirect("controller?command=go-to-registration-page");
		} else if (location.equals("userpage")) {
			session.setAttribute("local", language);
			response.sendRedirect("controller?command=go-to-user-auth-page");
		} else if (location.equals("adminpage")) {
			session.setAttribute("local", language);
			response.sendRedirect("controller?command=go-to-admin-auth-page");
		} else if (location.equals("signin")) {
			session.setAttribute("local", language);
			response.sendRedirect("controller?command=go-to-login-page");
		} else if (location.equals("pagination")) {
			session.setAttribute("local", language);
			response.sendRedirect("controller?command=go-to-pagination");
		} 
		

	}

}
