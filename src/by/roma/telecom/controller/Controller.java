package by.roma.telecom.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.roma.telecom.command.Command;
import by.roma.telecom.command.CommandHelper;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final CommandHelper helper = CommandHelper.getInstance();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String commandName;
		Command command;
		
		commandName = request.getParameter(RequestParameterName.REQ_PARAM_COMMAND_NAME);
		
		command = helper.getCommand(commandName);
		
		command.execute(request,response);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String commandName;
		Command command;
		
		commandName = request.getParameter(RequestParameterName.REQ_PARAM_COMMAND_NAME);
		command = helper.getCommand(commandName);
		
		command.execute(request,response);
	}
}
