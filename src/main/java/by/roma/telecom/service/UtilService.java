package by.roma.telecom.service;

import java.util.List;
/**
 * UtilService is the main interface to provide guidelines for other data manipulation and CRUD pathways that
 * are not covered by other main interfaces.
 * @author Roman
 * @since 10-18-2019
 */

public interface UtilService {
	/**
	 * Executes Select query, gathers list of available phone numbers from database. Assembles a
	 * parameterized list of type String.
     * @return 			   			List of type String.
     * @throws ServiceException 	If DaoException occurs.
	 */
	public  List<String> getAvailablePhoneNumbers() throws ServiceException;
}
