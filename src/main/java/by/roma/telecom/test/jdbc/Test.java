package by.roma.telecom.test.jdbc;

import by.roma.telecom.bean.User;
import by.roma.telecom.dao.DaoException;
import by.roma.telecom.dao.DaoProvider;
import by.roma.telecom.dao.UserDao;

public class Test {

	public static void main(String[] args) throws DaoException {
		
		UserDao userDao = DaoProvider.getInstance().getUserDao();
		User user;
		
		user = userDao.searchUserByID(10);
		
		System.out.println(user.toString());

	}

}
