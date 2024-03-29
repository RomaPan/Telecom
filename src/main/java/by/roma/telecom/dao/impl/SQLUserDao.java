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
		String searchUserByEmailAndPassword;
		searchUserByEmailAndPassword = rpf.getUserSearchByEmailAndPassword();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cp = ConnectionPool.getInstance();
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(searchUserByEmailAndPassword);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next() == false) {
				return null;
			} else {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getBoolean(9), rs.getBoolean(10));
				return user;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				con.commit();
				cp.releaseConnection(con);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}

	@Override
	public void insertUser(String name, String surname, String email, String addressL1, String addressL2,
			String addressL3, String pass) throws DaoException {
		String insertUser;
		Connection con = null;
		PreparedStatement ps = null;
		insertUser = rpf.getInsertUser();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(insertUser);
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
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				con.commit();
				cp.releaseConnection(con);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}

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
			if (rs.next() == false) {
				return null;
			} else {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getBoolean(9), rs.getBoolean(10));
				return user;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				con.commit();
				cp.releaseConnection(con);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}

	@Override
	public void updateUser(int id, String name, String surname, String email, String addressL1, String addressL2,
			String addressL3) throws DaoException {
		String userUpdateProfile;
		Connection con = null;
		PreparedStatement ps = null;
		userUpdateProfile = rpf.getUserUpdateProfile();

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(userUpdateProfile);
			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setString(3, addressL1);
			ps.setString(4, addressL2);
			ps.setString(5, addressL3);
			ps.setString(6, email);
			ps.setInt(7, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				con.commit();
				cp.releaseConnection(con);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}

	@Override
	public void changePass(String id, String passOld, String passNew) throws DaoException {
		String userChangePass;
		Connection con = null;
		PreparedStatement ps = null;
		userChangePass = rpf.getUserChangePass();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(userChangePass);
			ps.setString(1, passNew);
			ps.setString(2, id);
			ps.setString(3, passOld);
			ps.executeUpdate();		
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				con.commit();
				cp.releaseConnection(con);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}
	
	@Override
	public void changeUserRoleToAdmin(int id) throws DaoException {
		String changeUserRoleToAdmin;
		Connection con = null;
		PreparedStatement ps = null;
		changeUserRoleToAdmin = rpf.getChangeUserRoleToAdmin();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(changeUserRoleToAdmin);
			ps.setBoolean(1, true);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				con.commit();
				cp.releaseConnection(con);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}
	
	@Override
	public void changeUserBlockStatus(int id, boolean status) throws DaoException {
		String changeUserBlockStatus;
		Connection con = null;
		PreparedStatement ps = null;
		changeUserBlockStatus = rpf.getChangeUserBlockStatus();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(changeUserBlockStatus);
			ps.setBoolean(1, status);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				con.commit();
				cp.releaseConnection(con);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}

	@Override
	public List<User> getListOfAllUsers() throws DaoException {
		List<User> usersList;
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
				usersList.add(user);
			}
			return usersList;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				con.commit();
				cp.releaseConnection(con);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}
	
	@Override
	public User searchUserByEmail(String email) throws DaoException {
		String searchUserByEmail;
		searchUserByEmail = rpf.getUserSearchByEmail();
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;
		User user;
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(searchUserByEmail + " '" + email + "'");
			if (rs.next() == false) {
				return null;
			} else {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getBoolean(9), rs.getBoolean(10));
				return user;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				con.commit();
				cp.releaseConnection(con);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}
	
	@Override
	public int insertUserWithoutAI(int id, String name, String surname, String email, String addressL1,
			String addressL2, String addressL3, String pass, boolean isAdmin, boolean isBlocked) throws DaoException {
		String insertUserWithoutAutoIncrement;
		int lastID;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		insertUserWithoutAutoIncrement = rpf.getInsertUserWithoutAutoIncrement();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(insertUserWithoutAutoIncrement, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, surname);
			ps.setString(4, addressL1);
			ps.setString(5, addressL2);
			ps.setString(6, addressL3);
			ps.setString(7, email);
			ps.setString(8, pass);
			ps.setBoolean(9, false);
			ps.setBoolean(10, false);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			lastID = rs.getInt(1);
			return lastID;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				con.commit();
				cp.releaseConnection(con);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}

	}
	
	@Override
	public void deleteUser(int id) throws DaoException {
		String deleteUserJunitTest;
		Statement st = null;
		Connection con = null;
		deleteUserJunitTest = rpf.getDeleteUserJunitTest();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
			st.executeUpdate(deleteUserJunitTest + id);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				con.commit();
				cp.releaseConnection(con);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}
	
	@Override
	public void insertUserAccount(int accountID) throws DaoException {
		String insertUserAccount;
		Connection con = null;
		PreparedStatement ps = null;
		Date dt;
		SimpleDateFormat sdf;
		String currentTime;
		dt = new Date();
		sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		currentTime = sdf.format(dt);
		insertUserAccount = rpf.getInsertUserAccount();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(insertUserAccount);
			ps.setInt(1, accountID);
			ps.setInt(2, accountID);
			ps.setString(3, currentTime);
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				con.commit();
				cp.releaseConnection(con);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		
		
	}
	
	@Override
	public User searchUserByEmailAndPassword(String email, String password) throws DaoException {
		String searchUserByEmailAndPassword;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user;
		searchUserByEmailAndPassword = rpf.getUserSearchByEmailAndPassword();
		
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(searchUserByEmailAndPassword);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			rs.next();
			user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getBoolean(9), rs.getBoolean(10));
			return user;
		}catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				con.commit();
				cp.releaseConnection(con);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}
	

}
