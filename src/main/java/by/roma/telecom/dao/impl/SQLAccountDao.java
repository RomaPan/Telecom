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
import by.roma.telecom.bean.Account;
import by.roma.telecom.dao.AccountDao;
import by.roma.telecom.dao.DaoException;
import by.roma.telecom.pooling.ConnectionPool;
import by.roma.telecom.property.ReadPropertyFile;

public class SQLAccountDao implements AccountDao {
	private static final ReadPropertyFile rpf = ReadPropertyFile.getInstance();
	private static ConnectionPool cp = ConnectionPool.getInstance();

	
	@Override
	public void addCallPlan(int accountID, int callPlanID) throws DaoException {

		String accountAddCallPlan;
		Connection con = null;
		PreparedStatement ps = null;
		Date dt;
		SimpleDateFormat sdf;
		String currentTime;
		dt = new Date();
		sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		currentTime = sdf.format(dt);
		accountAddCallPlan = rpf.getAccountAddCallPlan();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(accountAddCallPlan);
			ps.setInt(1, callPlanID);
			ps.setInt(2, accountID);
			ps.setString(3, currentTime);
			ps.executeUpdate();
			ps.close();
			con.commit();
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
	public void cancelAccountCallPlan(int accountID) throws DaoException {

		String accountUpdateCallPlan;
		String currentTime;
		Connection con = null;
		PreparedStatement ps = null;
		Date dt;
		SimpleDateFormat sdf;

		dt = new Date();
		sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		currentTime = sdf.format(dt);
		accountUpdateCallPlan = rpf.getAccountUpdateCallPlan();

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(accountUpdateCallPlan);
			ps.setString(1, currentTime);
			ps.setInt(2, accountID);
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
	public float getCallPlanRate(int planID) throws DaoException {
		String selectCallPlanRate;
		float planRate;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		selectCallPlanRate = rpf.getSelectCallPlanRate();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(selectCallPlanRate);
			ps.setInt(1, planID);
			rs = ps.executeQuery();
			rs.next();
			planRate = (rs.getFloat(1));
			return planRate;
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
	public boolean isActiveCallPlanConnectedToAccount(int accountID) throws DaoException {

		String accountCallPlanInfo;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		accountCallPlanInfo = rpf.getAccountCallPlanInfo();

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(accountCallPlanInfo);
			ps.setInt(1, accountID);
			rs = ps.executeQuery();
			if (rs.next() == false) {
				return false;
			}
			return true;
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
	public boolean isActivePhoneNumberConnectedToAccount(int accountID) throws DaoException {

		String selectAccountAndPhoneNumber;
		selectAccountAndPhoneNumber = rpf.getSelectAccountAndPhoneNumber();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(selectAccountAndPhoneNumber);
			ps.setInt(1, accountID);
			rs = ps.executeQuery();
			if (rs.next() == false) {
				return false;
			}
			return true;
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
	public Account getAccountDetails(int accountID) throws DaoException {
		String selectAccountByID;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Account account;
		selectAccountByID = rpf.getSelectAccountByID();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(selectAccountByID);
			ps.setInt(1, accountID);
			rs = ps.executeQuery();
			if (rs.next() == false) {
				return null;
			}
			account = new Account(rs.getInt(1), rs.getInt(1), rs.getFloat(2), rs.getBoolean(3), rs.getInt(5),
					rs.getString(7), rs.getString(8), rs.getInt(10), rs.getString(12), rs.getString(13),
					rs.getString(16), rs.getString(17), rs.getInt(19), rs.getString(22), rs.getFloat(23),
					rs.getInt(24));
			return account;
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
	public void chargeToAccount(int accountID, float amount) throws DaoException {
		String chargeToAccount;
		Connection con = null;
		PreparedStatement ps = null;
		chargeToAccount = rpf.getChargeToAccount();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(chargeToAccount);
			ps.setFloat(1, amount);
			ps.setInt(2, accountID);
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
	public void changeAccountBlockStatus(int accountID, boolean status) throws DaoException {
		String changeAccountBlockStatus;
		Connection con = null;
		PreparedStatement ps = null;
		changeAccountBlockStatus = rpf.getChangeAccountBlockStatus();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(changeAccountBlockStatus);
			ps.setBoolean(1, status);
			ps.setInt(2, accountID);
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
	public int getPhoneNumberID(String lineNumber) throws DaoException {
		String selectPhoneNumber;
		int phoneNumberID;
		selectPhoneNumber = rpf.getSelectPhoneNumber();
		phoneNumberID = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(selectPhoneNumber);
			ps.setString(1, lineNumber);
			rs = ps.executeQuery();
			if (rs.next() == false) {
				return phoneNumberID;
			}
			phoneNumberID = rs.getInt(1);
			return phoneNumberID;
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
	public boolean updatePhoneNumberAvailableStatus(int phoneNumberID, boolean setAvailable) throws DaoException {
		String reservePhoneNumber;
		int updateStatus;
		reservePhoneNumber = rpf.getReservePhoneNumber();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(reservePhoneNumber);
			ps.setBoolean(1, setAvailable);
			ps.setInt(2, phoneNumberID);
			updateStatus = ps.executeUpdate();
			if (updateStatus != 0) {
				return true;
			} else {
				return false;
			}
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
	public void disconnectPhoneNumberFromAccount(int accountID) throws DaoException {
		
		String disconnectPhoneNumberFromAccount;
		Connection con = null;
		PreparedStatement ps = null;
		Date dt;
		SimpleDateFormat sdf;
		String currentTime;
		dt = new Date();
		sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		currentTime = sdf.format(dt);
		disconnectPhoneNumberFromAccount = rpf.getDisconnectPhoneNumberFromAccount();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(disconnectPhoneNumberFromAccount);
			ps.setString(1, currentTime);
			ps.setInt(2, accountID);
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
	public int getConnectedToAccountPhoneNumberID(int accountID) throws DaoException {
		int connectedNumberID;
		String getPhoneNumberIDByAccountID;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		getPhoneNumberIDByAccountID = rpf.getPhoneNumberIDByAccountID();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(getPhoneNumberIDByAccountID);
			ps.setInt(1, accountID);
			rs = ps.executeQuery();
			rs.next();
			connectedNumberID = rs.getInt(2);
			return connectedNumberID;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
				con.commit();
				cp.releaseConnection(con);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}

	@Override
	public void connectPhoneNumberToAccount(int accountID, int phoneNumberID) throws DaoException {
		String connectPhoneNumberToAccount;
		Connection con = null;
		PreparedStatement ps = null;
		Date dt;
		SimpleDateFormat sdf;
		String currentTime;
		dt = new Date();
		sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		currentTime = sdf.format(dt);
		connectPhoneNumberToAccount = rpf.getConnectPhoneNumberToAccount();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(connectPhoneNumberToAccount);
			ps.setInt(1, phoneNumberID);
			ps.setInt(2, accountID);
			ps.setString(3, currentTime);
			ps.executeUpdate();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException exc) {
			}
			throw new DaoException(e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				con.commit();
				con.setAutoCommit(true);
				cp.releaseConnection(con);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}

	@Override
	public List<Account> getListOfAllAccounts() throws DaoException {

		List<Account> accountList;
		Account account;
		String getListOfAllAccounts;
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;
		getListOfAllAccounts = rpf.getListOfAllAccounts();
		accountList = new ArrayList<>();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(getListOfAllAccounts);
			while (rs.next()) {
				account = new Account(rs.getInt(1), rs.getInt(1), rs.getFloat(2), rs.getBoolean(3), rs.getInt(5),
						rs.getString(7), rs.getString(8), rs.getInt(10), rs.getString(12), rs.getString(13),
						rs.getString(16), rs.getString(17), rs.getInt(19), rs.getString(22), rs.getFloat(23),
						rs.getInt(24));
				System.out.println(account.toString());
				accountList.add(account);
			}
			return accountList;
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
	public Account findAccountByPhoneNumber(String phoneNumber) throws DaoException {

		String findAccountByPhoneNumber;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Account account;
		findAccountByPhoneNumber = rpf.getAccountByPhoneNumber();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(findAccountByPhoneNumber);
			ps.setString(1, phoneNumber);
			rs = ps.executeQuery();
			if (rs.next() == true) {
				account = new Account(rs.getInt(1), rs.getInt(1), rs.getFloat(2), rs.getBoolean(3), rs.getInt(5),
						rs.getString(7), rs.getString(8), rs.getInt(10), rs.getString(12), rs.getString(13),
						rs.getString(16), rs.getString(17), rs.getInt(19), rs.getString(22), rs.getFloat(23),
						rs.getInt(24));
				return account;
			}
			return null;
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
	public float getAccountBalanceByID(int accoundID) throws DaoException {
		String getAccountBalance;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		float accountBalance;
		
		getAccountBalance = rpf.getAccountBalance();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(getAccountBalance);
			ps.setInt(1, accoundID);
			rs = ps.executeQuery();
			rs.next();
			accountBalance = rs.getFloat(1);
			return accountBalance;
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

	@Override
	public void deleteAccount(int accountID) throws DaoException {
		String deleteAccount;
		Connection con = null;
		PreparedStatement ps = null;
		deleteAccount = rpf.getDeleteAccount();
		
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(deleteAccount);
			ps.setInt(1, accountID);
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
	public void insertAccount(int accountID) throws DaoException {
		String insertAccount;
		Connection con = null;
		PreparedStatement ps = null;
		insertAccount = rpf.getInsertAccount();
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(insertAccount);
			ps.setInt(1, accountID);
			ps.setFloat(2, 0.00F);
			ps.setBoolean(3, false);
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
	

}
