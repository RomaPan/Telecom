package by.roma.telecom.service;

public class ServiceException extends Exception{
	
	private static final long serialVersionUID = 10L;

	public ServiceException (Exception e) {
		super(e);
	}
	
	public ServiceException() {
		super();
	}
	
	public ServiceException (String message) {
		super(message);
	}

}
