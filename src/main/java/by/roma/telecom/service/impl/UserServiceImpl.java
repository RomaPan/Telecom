package by.roma.telecom.service.impl;

import java.util.List;

import by.roma.telecom.bean.User;
import by.roma.telecom.dao.UserDao;
import by.roma.telecom.dao.DaoException;
import by.roma.telecom.dao.DaoProvider;
import by.roma.telecom.service.UserService;
import by.roma.telecom.service.hashing.HashConversion;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.validation.UserDataValidator;

public class UserServiceImpl implements UserService {
	private UserDao userDao = DaoProvider.getInstance().getUserDao();

	private static final UserDataValidator validator = UserDataValidator.getInstane();

	@Override
	public User authorization(String login, String pass) throws ServiceException {
		User user;
		if (!validator.check(login, pass)) {
			return null;
		} else {
			pass = HashConversion.cipher(pass);
			try {
				user = userDao.authorization(login, pass);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
			return user;
		}
	}

	@Override
	public User registration(String name, String surname, String email, String addressL1, String addressL2,
			String addressL3, String pass) throws ServiceException {
		User user;
		if (!validator.check(email, pass)) {
			return null;
		} else {
			pass = HashConversion.cipher(pass);
			try {
				userDao.insertUser(name, surname, email, addressL1, addressL2, addressL3, pass);
				user = userDao.searchUserByEmailAndPassword(email, pass);
				userDao.insertUserAccount(user.getUserID());
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
			return user;
		}
	}

	@Override
	public User updateUser(int id, String name, String surname, String email, String addressL1, String addressL2,
			String addressL3) throws ServiceException {
		User user;
		if (!validator.checkProfileData(name, surname, email, addressL1, addressL2, addressL3)) {
			return null;
		}
		try {
			userDao.updateUser(id, name, surname, email, addressL1, addressL2, addressL3);
			user = userDao.searchUserByID(id);
			return user;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User changePass(String id, String passOld, String passNew) throws ServiceException {
		User user;
		int userID;
		
		if (!validator.checkPassUpdate(passOld, passNew)) {
			return null;
		} else {
			passOld = HashConversion.cipher(passOld);
			passNew = HashConversion.cipher(passNew);
			userID = Integer.parseInt(id);
			try {
				userDao.changePass(id, passOld, passNew);
				user = userDao.searchUserByID(userID);
				return user;
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
	}

	@Override
	public User searchByID(String userID) throws ServiceException {
		User user;
		int id;
		id = Integer.parseInt(userID);
		try {
			user = userDao.searchUserByID(id);
			return user;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User changeUserRole(String userID) throws ServiceException {
		User user;
		int id;
		id = Integer.parseInt(userID);
		try {
			userDao.changeUserRoleToAdmin(id);
			user = userDao.searchUserByID(id);
			return user;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User changeUserBlockStatus (String userID, boolean status) throws ServiceException {
		User user;
		int id;
		id = Integer.parseInt(userID);
		try {
			userDao.changeUserBlockStatus(id, status);
			user = userDao.searchUserByID(id);
			return user;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> getListOfAllUsers() throws ServiceException {
		List<User> usersList;
		try {
			usersList = userDao.getListOfAllUsers();
			return usersList;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User searchByEmail(String userEmail) throws ServiceException {
		User user;
		try {
			user = userDao.searchUserByEmail(userEmail);
			return user;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteUser(String userID) throws ServiceException {
		int id;
		id = Integer.parseInt(userID);
		try {
			userDao.deleteUser(id);
		}catch (DaoException e) {
			throw new ServiceException(e);
		}
	}


}
