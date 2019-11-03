package by.roma.telecom.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;

public class GoToError404PageCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ERROR_NOT_FOUND_PAGE);
		dispatcher.forward(request, response);
		
	}

}
