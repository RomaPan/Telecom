package by.roma.telecom.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.roma.telecom.command.Command;

public class ClearListOfAllUsersCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("admin") != null) {
			if (session.getAttribute("usersList") != null) {
				session.removeAttribute("usersList");
			} 
			if (session.getAttribute("UsersListMessage") != null) {
				session.removeAttribute("UsersListMessage");
			}
			session.setAttribute("UsersLisCleartMessage", "Search results cleared.");
			response.sendRedirect("controller?command=go-to-admin-auth-page");
		}
		

	}

}
