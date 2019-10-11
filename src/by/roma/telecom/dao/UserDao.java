package by.roma.telecom.dao;

import java.sql.SQLException;
import java.util.List;


import by.roma.telecom.bean.User;

public interface UserDao {


	public User authorization(String login, String password) throws DaoException;

	public User registration(String name, String surname, String email, String addressL1, String addressL2,
			String addressL3, String pass) throws DaoException;

	public User updateUser(int id, String name, String surname, String email, String addressL1, String addressL2,
			String addressL3) throws DaoException;

	public User changePass(String id, String passOld, String passNew) throws DaoException;
	
	public User searchUserByID(int id) throws DaoException;
	
	public User searchUserByEmail(String email) throws DaoException;
	
	public User changeUserRole(int id) throws DaoException;
	
	public List<String> getAvailablePhoneNumbers() throws DaoException;
	
	public User blockUser(int id) throws DaoException;
	
	public User unblockUser(int id) throws DaoException;
	
	public List<User> getListOfAllUsers() throws DaoException;
	
	public void deleteUser(int id) throws SQLException, ClassNotFoundException, DaoException;



}
