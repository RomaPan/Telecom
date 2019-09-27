package by.roma.telecom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import by.roma.telecom.bean.User;
import by.roma.telecom.dao.UserDao;
import by.roma.telecom.pooling.ConnectionPool;
import by.roma.telecom.property.ReadPropertyFile;
import by.roma.telecom.dao.DaoException;

public class SQLUserDao implements UserDao {

	private static final ReadPropertyFile rpf = ReadPropertyFile.getInstance();
	private static ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public User authorization(String email, String password) throws DaoException {

		User user;
		String searchUserByEmail;
		searchUserByEmail = rpf.getUserSearchByEmail();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cp = ConnectionPool.getInstance();
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(searchUserByEmail);

			ps.setString(1, email);
			ps.setString(2, password);

			rs = ps.executeQuery();

			rs.next();// if(rs.next() ==false){}
			if (rs.getRow() == 0) {
				System.out.println("---------ResultSet is empty-----------");
				return null;
			} else {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getBoolean(9), rs.getBoolean(10));
				System.out.println(user.toString());
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.commit();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cp.releaseConnection(con, ps);
		}
		return null;
	}

	@Override
	public User registration(String name, String surname, String email, String addressL1, String addressL2,
			String addressL3, String pass) throws DaoException {

		String insertUserCommand;
		String insertAccountCommand;
		String insertUserAccountCommand;
		String searchUserByEmail;

		Connection con = null;
		PreparedStatement ps = null;

		ResultSet rs = null;
		User user;

		Date dt;
		SimpleDateFormat sdf;
		String currentTime;

		dt = new Date();
		sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		currentTime = sdf.format(dt);

		insertUserCommand = rpf.getInsertUser();
		insertAccountCommand = rpf.getInsertAccount();
		insertUserAccountCommand = rpf.getInsertUserAccount();
		searchUserByEmail = rpf.getUserSearchByEmail();

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);

			ps = con.prepareStatement(insertUserCommand); // inserting user to "users" table
			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setString(3, addressL1);
			ps.setString(4, addressL2);
			ps.setString(5, addressL3);
			ps.setString(6, email);
			ps.setString(7, pass);
			ps.setBoolean(8, false);
			ps.setBoolean(9, false);
			ps.executeUpdate();
			ps.close();

			con.commit();

			con.setAutoCommit(false);

			ps = con.prepareStatement(searchUserByEmail);
			ps.setString(1, email);
			ps.setString(2, pass);

			rs = ps.executeQuery(); // obtaining user's new ID in DB

			rs.next();
			user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getBoolean(9), rs.getBoolean(10));
			user.toString();

			rs.close();
			ps.close();
			con.commit();

			con.setAutoCommit(false);
			ps = con.prepareStatement(insertAccountCommand); // inserting new account to "accounts" table using user's
																// ID
			ps.setInt(1, user.getUserID());
			ps.setFloat(2, 0.00F);
			ps.setBoolean(3, false);

			ps.executeUpdate();
			con.commit();
			ps.close();

			con.setAutoCommit(false);
			ps = con.prepareStatement(insertUserAccountCommand);// inserting new users_accounts data to DB
			ps.setInt(1, user.getUserID());
			ps.setInt(2, user.getUserID());
			ps.setString(3, currentTime);
			ps.executeUpdate();
			con.commit();
			ps.close();

			return user;

		} catch (SQLException e) {

			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		} finally {
			try {
				ps.close();
				rs.close();
				cp.releaseConnection(con, ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public User searchUserByID(int id) throws DaoException {

		String searchUserByID;
		searchUserByID = rpf.getUserSearchByID();
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;
		User user;

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(searchUserByID + id);

			rs.next();
			if (rs.getRow() == 0) {
				System.out.println("---------ResultSet is empty-----------");
				return null;
			} else {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getBoolean(9), rs.getBoolean(10));
				System.out.println(user.toString());
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				con.commit();
				cp.releaseConnection(con, st);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public User updateUser(int id, String name, String surname, String email, String addressL1, String addressL2,
			String addressL3) throws DaoException {

		String updateUserDetails;
		String searchUserByID;

		updateUserDetails = rpf.getUserUpdateProfile();
		searchUserByID = rpf.getUserSearchByID();

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user;

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(updateUserDetails + id);

			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setString(3, addressL1);
			ps.setString(4, addressL2);
			ps.setString(5, addressL3);
			ps.setString(6, email);
			ps.executeUpdate();
			ps.close();

			con.commit();

			con.setAutoCommit(false);

			st = con.createStatement();
			rs = st.executeQuery(searchUserByID + id);
			rs.next();

			user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getBoolean(9), rs.getBoolean(10));
			user.toString();
			System.out.println("DAO-" + user);
			return user;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				con.commit();
				cp.releaseConnection(con, st);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	@Override
	public User changePass(String id, String passOld, String passNew) throws DaoException {

		String userChangePass;
		String userSearchByID;
		int count;

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user;

		userChangePass = rpf.getUserChangePass();
		userSearchByID = rpf.getUserSearchByID();

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(userChangePass);

			ps.setString(1, passNew);
			ps.setString(2, id);
			ps.setString(3, passOld);

			count = ps.executeUpdate();
			ps.close();
			con.commit();

			System.out.println(count);

			if (count == 0) {
				return null;
			}

			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(userSearchByID + id);

			rs.next();
			if (rs.getRow() == 0) {
				System.out.println("---------ResultSet is empty-----------");
				return null;
			} else {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getBoolean(9), rs.getBoolean(10));
				System.out.println(user.toString());
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				con.commit();
				cp.releaseConnection(con, st);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public List<String> getAvailablePhoneNumbers() throws DaoException {
		List<String> numbers;
		String viewAvailablePhoneNumbers;
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;

		viewAvailablePhoneNumbers = rpf.getViewAvailablePhoneNumbers();

		numbers = new ArrayList<>();

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);

			st = con.createStatement();
			rs = st.executeQuery(viewAvailablePhoneNumbers);

			while (rs.next()) {
				numbers.add(rs.getString(2));
			}
			System.out.println(numbers);
			return numbers;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return numbers;
	}

	@Override
	public void deleteUser(int id) throws SQLException, ClassNotFoundException, DaoException {
	}


	@Override
	public User changeUserRole(int id) throws DaoException {

		String changeUserRoleToAdmin;
		String userSearchByID;

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user;

		changeUserRoleToAdmin = rpf.getChangeUserRoleToAdmin();
		userSearchByID = rpf.getUserSearchByID();

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(changeUserRoleToAdmin);

			ps.setBoolean(1, true);
			ps.setInt(2, id);

			ps.executeUpdate();
			ps.close();
			con.commit();

			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(userSearchByID + id);

			rs.next();
			
			if (rs.getRow() == 0) {
				System.out.println("---------ResultSet is empty-----------");
				return null;
			} else {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getBoolean(9), rs.getBoolean(10));
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				con.commit();
				cp.releaseConnection(con, st);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public User blockUser(int id) throws DaoException {

		String changeUserBlockStatus;
		String userSearchByID;

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user;

		changeUserBlockStatus = rpf.getChangeUserBlockStatus();
		userSearchByID = rpf.getUserSearchByID();

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(changeUserBlockStatus);

			ps.setBoolean(1, true);
			ps.setInt(2, id);

			ps.executeUpdate();
			ps.close();
			con.commit();

			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(userSearchByID + id);

			rs.next();
			
			if (rs.getRow() == 0) {
				System.out.println("---------ResultSet is empty-----------");
				return null;
			} else {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getBoolean(9), rs.getBoolean(10));
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				con.commit();
				cp.releaseConnection(con, st);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public User unblockUser(int id) throws DaoException {
		String changeUserBlockStatus;
		String userSearchByID;

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user;

		changeUserBlockStatus = rpf.getChangeUserBlockStatus();
		userSearchByID = rpf.getUserSearchByID();

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(changeUserBlockStatus);

			ps.setBoolean(1, false);
			ps.setInt(2, id);

			ps.executeUpdate();
			ps.close();
			con.commit();

			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(userSearchByID + id);

			rs.next();
			
			if (rs.getRow() == 0) {
				System.out.println("---------ResultSet is empty-----------");
				return null;
			} else {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getBoolean(9), rs.getBoolean(10));
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				con.commit();
				cp.releaseConnection(con, st);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<User> getListOfAllUsers() throws DaoException {
		
		List<User> usersList = new ArrayList<>();
		User user;
		
		String getListOfAllUsers;
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;

		getListOfAllUsers = rpf.getListOfAllUsers();

		usersList = new ArrayList<>();

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);

			st = con.createStatement();
			rs = st.executeQuery(getListOfAllUsers);

			while (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getBoolean(9), rs.getBoolean(10));
				System.out.println(user);
				usersList.add(user);
			}
			System.out.println(usersList);
			return usersList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usersList;
	}
	
	
	
	
	
	
	
}
