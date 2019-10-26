package by.roma.telecom.controller;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class ProjectRequestListener implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent arg0) {
		HttpServletRequest request = (HttpServletRequest) arg0.getServletRequest();
		System.out.println("Request from " + request.getContextPath() + " was destroyed.");
	}

	public void requestInitialized(ServletRequestEvent arg0) {
		HttpServletRequest request = (HttpServletRequest) arg0.getServletRequest();
		System.out.println("Request from " + request.getContextPath() + " was created.");
	}
}
