package by.roma.telecom.dao;

import java.util.List;
/**
 * UtilDao is the main interface to provide guidelines for data manipulation and CRUD pathways that are not
 * covered by other interfaces.
 * @author Roman
 * @since 10-18-2019
 */
public interface UtilDao {
	/**
	 * Executes Select query, assembles a List type String of available phone numbers from database. 
     * @return 			   			String type List of phone numbers.
     * @throws DaoException 		If SQLException occurs.
	 */
	public List<String> getAvailablePhoneNumbers() throws DaoException;
}
