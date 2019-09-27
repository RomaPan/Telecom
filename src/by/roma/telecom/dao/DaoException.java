package by.roma.telecom.dao;

import java.sql.SQLException;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DaoException(Exception e) {
		super(e);
	}

	public DaoException(SQLException e) {
		super(e);
	}
	
	public DaoException (String message) {
		super(message);
	}

}
