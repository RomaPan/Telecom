package by.roma.telecom.dao;

import java.util.List;

import by.roma.telecom.bean.User;
/**
 * UserDao is the main interface to provide guidelines for User data manipulation and CRUD pathways.
 * @author Roman
 * @since 10-18-2019
 */
public interface UserDao {
	/**
	 * Executes Select query, assembles and returns bean User type object. 
     * @param login	    			User login as a String.
     * @param password				User password as String.
     * @return 			   			User type object.
     * @throws DaoException 		If SQLException occurs.
	 */
	public User authorization(String login, String password) throws DaoException;
	/**
	 * Executes Select and Update queries, assembles and returns updated bean object type User.
	 * @param id	    			User id as an integer.
     * @param name	    			User name as a String.
     * @param surname				User surname as a String.
     * @param email					User email as a String.
     * @param addressL1				User house/street address as a String.
     * @param addressL2				User city name as a String.
     * @param addressL3				User ZIP/Postal Code as a String.
     * @return 			   			User type object.
     * @throws DaoException 		If SQLException occurs.
	 */
	public void updateUser(int id, String name, String surname, String email, String addressL1, String addressL2,
			String addressL3) throws DaoException;
	/**
	 * Executes Select and Update queries, assembles and returns updated bean User type object.
	 * @param id	    			User id as a String.
     * @param passOld				User existing password as String with simple SHA-256Hex hashing applied prior.
     * @param passNew				User new password as String with simple SHA-256Hex hashing applied prior.
     * @return 			   			User type object.
     * @throws DaoException 		If SQLException occurs.
	 */
	public void changePass(String id, String passOld, String passNew) throws DaoException;
	/**
	 * Executes Select query, assembles and returns bean User type object.
	 * @param id	    			User id as an integer.
     * @return 			   			User type object.
     * @throws DaoException 		If SQLException occurs.
	 */
	public User searchUserByID(int id) throws DaoException;
	/**
	 * Executes Select query, assembles and returns bean User type object.
	 * @param email	    			User email as a String.
     * @return 			   			User type object.
     * @throws DaoException 		If SQLException occurs.
	 */
	public User searchUserByEmail(String email) throws DaoException;
	/**
	 * Executes Update query, applies administrator rights to user with ID passed to the method. 
	 * Assembles and returns bean User type object.
	 * @param id	    			User id as an integer.
     * @return 			   			User type object.
     * @throws DaoException 		If SQLException occurs.
	 */
	public void changeUserRoleToAdmin(int id) throws DaoException;
	/**
	 * Executes Update query, applies changes value to "true" in database column name isBlocked. Blocked users
	 * unable to login into the web application. Assembles and returns updated bean User type object.
	 * @param id	    			User id as an integer.
     * @return 			   			User type object.
     * @throws DaoException 		If SQLException occurs.
	 */
	public void changeUserBlockStatus(int id, boolean status) throws DaoException;
	/**
	 * Executes Select query, gathers all records of users from database except those that are marked 
	 * as administrators. Assembles a parameterized list type User, returns List type User.
     * @return 			   			User type List.
     * @throws DaoException 		If SQLException occurs.
	 */
	public List<User> getListOfAllUsers() throws DaoException;
	/**
	 * Executes Insert query.
     * @param name	    			User name as a String.
     * @param surname				User surname as a String.
     * @param email					User email as a String.
     * @param addressL1				User house/street address as a String.
     * @param addressL2				User city name as a String.
     * @param addressL3				User ZIP/Postal Code as a String.
     * @param pass					User password as String.
     * @throws DaoException 		If SQLException occurs.
	 */
	public void insertUser(String name, String surname, String email, String addressL1, String addressL2,
			String addressL3, String pass) throws DaoException;
	/**
	 * Executes Insert query. Method used for ongoing or regressive testing purposes.
	 * @param id	    			User name as an integer.
     * @param name	    			User name as a String.
     * @param surname				User surname as a String.
     * @param email					User email as a String.
     * @param addressL1				User house/street address as a String.
     * @param addressL2				User city name as a String.
     * @param addressL3				User ZIP/Postal Code as a String.
     * @param pass					User password as String.
     * @param isAdmin				Administrator status as boolean.
     * @param isBlocked				User block/unblock status as boolean.
     * @return 			   			integer number from (Statement.RETURN_GENERATED_KEYS;) query.
     * @throws DaoException 		If SQLException occurs.
	 */
	public int insertUserWithoutAI (int id, String name, String surname, String email, String addressL1, String addressL2,
			String addressL3, String pass, boolean isAdmin, boolean isBlocked) throws DaoException;
	/**
	 * Executes Delete query. Method used for ongoing or regressive testing purposes. Delete row from database
	 * with the an ID that is passed into the method.
	 * @param id	    			User name as an integer.
     * @throws DaoException 		If SQLException occurs.
	 */
	public void deleteUser(int id) throws DaoException;
	
	public User searchUserByEmailAndPassword(String email, String password) throws DaoException;

	public void insertUserAccount(int accountID) throws DaoException;

}
