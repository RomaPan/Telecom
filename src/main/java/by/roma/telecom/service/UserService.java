package by.roma.telecom.service;

import java.util.List;

import by.roma.telecom.bean.User;


public interface UserService {

	public User authorization(String login, String pass) throws ServiceException;

	public User registration(String name, String surname, String email, String addressL1, String addressL2, String addressL3,
			String pass) throws ServiceException;

	public User updateUser(int id, String name, String surname, String email, String addressL1, String addressL2, String addressL3)
			throws ServiceException;

	public User changePass(String id, String passOld, String passNew) throws ServiceException;
	
	public User searchByID(String userID) throws ServiceException;
	
	public User searchByEmail(String userEmail) throws ServiceException;
	
	public User changeUserRole(String userID) throws ServiceException;
	
	public User changeUserBlockStatus (String userID, boolean status) throws ServiceException;
	
	public List<User> getListOfAllUsers() throws ServiceException;
	
	public void deleteUser(String userID) throws ServiceException;
}
