package by.roma.telecom.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminAuthFilter implements Filter{
	private static final AdminAuthFilterHelper adminHelper = AdminAuthFilterHelper.getInstance();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        String command = httpRequest.getParameter("command");
        boolean AdminCommand = adminHelper.checkAccessSet(command);
        
        HttpSession session = httpRequest.getSession(false);

        if (session.getAttribute("admin") == null && AdminCommand) {
        	RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGIN_PAGE);
    		dispatcher.forward(request, response);
        } else {
        	chain.doFilter(request, response);
        } 
	}	
}
