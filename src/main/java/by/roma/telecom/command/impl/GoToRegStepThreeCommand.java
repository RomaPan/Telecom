package by.roma.telecom.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.roma.telecom.command.Command;
import by.roma.telecom.controller.JSPPageName;

public class GoToRegStepThreeCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		  
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_REGISTRATION_STEP_THREE);
		dispatcher.forward(request, response);
		
	}

}
