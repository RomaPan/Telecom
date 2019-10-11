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

	private static final UserDataValidator validator = UserDataValidator.getInstane();

	@Override
	public User authorization(String login, String pass) throws ServiceException {

		if (!validator.check(login, pass)) {

//			throw new ServiceException("Invalid Login or Password");
			return null;

		} else {
			pass = HashConversion.cipher(pass);

			UserDao userDao = DaoProvider.getInstance().getUserDao();
			User user;

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

		if (!validator.check(email, pass)) {

//			throw new ServiceException("Invalid Login or Password");
			return null;

		} else {
			pass = HashConversion.cipher(pass);

			UserDao userDao = DaoProvider.getInstance().getUserDao();
			User user;

			try {
				user = userDao.registration(name, surname, email, addressL1, addressL2, addressL3, pass);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}

			return user;
		}

	}

	@Override
	public User updateUser(int id, String name, String surname, String email, String addressL1, String addressL2,
			String addressL3) throws ServiceException {
		if (!validator.checkProfileData(name, surname, email, addressL1, addressL2, addressL3)) {

//			throw new ServiceException("Invalid Login or Password");
			return null;

		} else {

			UserDao userDao = DaoProvider.getInstance().getUserDao();
			User user;

			try {
				user = userDao.updateUser(id, name, surname, email, addressL1, addressL2, addressL3);
				return user;
			} catch (DaoException e) {
				throw new ServiceException(e);
			}

		}

	}

	@Override
	public User changePass(String id, String passOld, String passNew) throws ServiceException {

		if (!validator.checkPassUpdate(passOld, passNew)) {
			return null;
		} else {
			passOld = HashConversion.cipher(passOld);
			passNew = HashConversion.cipher(passNew);

			UserDao userDao = DaoProvider.getInstance().getUserDao();
			User user;

			try {
				user = userDao.changePass(id, passOld, passNew);
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
		UserDao userDao = DaoProvider.getInstance().getUserDao();

		try {
			user = userDao.searchUserByID(id);
			return user;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<String> getAvailablePhoneNumbers() throws ServiceException {

		UserDao userDao = DaoProvider.getInstance().getUserDao();

		List<String> numbers;

		try {
			numbers = userDao.getAvailablePhoneNumbers();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return numbers;
	}

	@Override
	public User changeUserRole(String userID) throws ServiceException {
		User user;
		int id;
		id = Integer.parseInt(userID);
		UserDao userDao = DaoProvider.getInstance().getUserDao();

		try {
			user = userDao.changeUserRole(id);
			return user;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User blockUser(String userID) throws ServiceException {
		User user;
		int id;
		id = Integer.parseInt(userID);
		UserDao userDao = DaoProvider.getInstance().getUserDao();

		try {
			user = userDao.blockUser(id);
			return user;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User unblockUser(String userID) throws ServiceException {
		User user;
		int id;
		id = Integer.parseInt(userID);
		UserDao userDao = DaoProvider.getInstance().getUserDao();

		try {
			user = userDao.unblockUser(id);
			return user;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> getListOfAllUsers() throws ServiceException {
		List<User> usersList;
		UserDao userDao = DaoProvider.getInstance().getUserDao();

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

		UserDao userDao = DaoProvider.getInstance().getUserDao();

		try {
			user = userDao.searchUserByEmail(userEmail);
			return user;

		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User deleteUser(String userID) throws ServiceException {

		return null;
	}
}
