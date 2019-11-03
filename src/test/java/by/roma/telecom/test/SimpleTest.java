package by.roma.telecom.test;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import by.roma.telecom.bean.User;
import by.roma.telecom.dao.DaoException;
import by.roma.telecom.dao.DaoProvider;
import by.roma.telecom.dao.UserDao;
import by.roma.telecom.pooling.ConnectionPool;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SimpleTest {
	private static final Logger LOGGER = Logger.getLogger(SimpleTest.class);
	private static UserDao userDao = DaoProvider.getInstance().getUserDao();

	@Test
	public void addUserDetails() throws DaoException {
		int testID;
		try {
			testID = userDao.insertUserWithoutAI(10, "testName", "testSurname", "testAddress", "testCity", "testZip",
					"test@test.com", "testPassword", false, false);
			Assert.assertTrue(testID == 10);
		} catch (DaoException e) {
			LOGGER.error("Failed to insert into database.", e);
		}
	}

	@Test
	public void getUserDetails() {
		User user;
		try {
			user = userDao.searchUserByID(10);
			Assert.assertEquals("testName", user.getName());
		} catch (DaoException e) {
			LOGGER.error("Failed to read from database.", e);
		}
	}

	@Test
	public void modifyUserDetails() {
		User user;
		try {
			userDao.changeUserBlockStatus(10, true);
			user = userDao.searchUserByID(10);
			Assert.assertTrue(user.isBlocked());
		} catch (DaoException e) {
			LOGGER.error("Failed to update row in a database.", e);
		}
	}

	@Test
	public void rollbackUserInsert() {
		try {
			userDao.deleteUser(10);
			User user = userDao.searchUserByID(10);
			Assert.assertNull(user);
		} catch (DaoException e) {
			LOGGER.error("Failed to delete row from database.", e);
		}
	}

	@AfterClass
	public static void afterTests() {
		ConnectionPool.getInstance().terminateAllConnectionQueues();
	}
}
