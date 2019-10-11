package by.roma.telecom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
	public Account addCallPlan(int accountID, int callPlanID) throws DaoException {

		String accountAddCallPlan;
		String accountCallPlanInfo;
		String accountUpdateCallPlan;
		String selectAccountAndCallPlan;
		String selectCallPlanRate;
		String chargeToAccount;
		String getAccountBalance;
		String updateAccountBalance;

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Account account;

		Date dt;
		SimpleDateFormat sdf;
		String currentTime;

		dt = new Date();
		sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		currentTime = sdf.format(dt);

		accountAddCallPlan = rpf.getAccountAddCallPlan();
		accountCallPlanInfo = rpf.getAccountCallPlanInfo();
		accountUpdateCallPlan = rpf.getAccountUpdateCallPlan();
		selectAccountAndCallPlan = rpf.getSelectAccountAndCallPlan();
		selectCallPlanRate = rpf.getSelectCallPlanRate();
		chargeToAccount = rpf.getChargeToAccount();
		getAccountBalance = rpf.getAccountBalance();
		updateAccountBalance = rpf.getUpdateAccountBalance();

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(accountCallPlanInfo);
			ps.setInt(1, accountID);
			rs = ps.executeQuery();

			rs.next();

			if (rs.getRow() == 0) {

				System.out.println("Result set is null");
				con.commit();
				rs.close();
				ps.close();

				con.setAutoCommit(false);
				ps = con.prepareStatement(accountAddCallPlan);
				ps.setInt(1, callPlanID);
				ps.setInt(2, accountID);
				ps.setString(3, currentTime);
				ps.executeUpdate();
				ps.close();

				con.commit();

				con.setAutoCommit(false);
				ps = con.prepareStatement(selectAccountAndCallPlan);
				ps.setInt(1, accountID);
				rs = ps.executeQuery();
				rs.next();

				account = new Account(accountID, rs.getFloat(2), rs.getBoolean(3), rs.getInt(4), rs.getString(5),
						rs.getString(6));

				con.commit();
				rs.close();
				ps.close();

				con.setAutoCommit(false);
				ps = con.prepareStatement(selectCallPlanRate);
				ps.setInt(1, callPlanID);
				rs = ps.executeQuery();
				rs.next();

				account.setCallPlanRate(rs.getFloat(1));
				con.commit();
				rs.close();
				ps.close();

				con.setAutoCommit(false);
				ps = con.prepareStatement(chargeToAccount);
				ps.setFloat(1, account.getCallPlanRate());
				ps.setInt(2, accountID);
				ps.executeUpdate();
				ps.close();
				con.commit();

				con.setAutoCommit(false);
				ps = con.prepareStatement(selectAccountAndCallPlan);
				ps.setInt(1, accountID);
				rs = ps.executeQuery();
				rs.next();

				account = new Account(accountID, rs.getFloat(2), rs.getBoolean(3), rs.getInt(4), rs.getString(5),
						rs.getString(6));

				con.commit();
				rs.close();
				ps.close();

				return account;

			} else {
				con.commit();
				rs.close();
				ps.close();

				con.setAutoCommit(false);
				ps = con.prepareStatement(accountUpdateCallPlan);
				ps.setString(1, currentTime);
				ps.setInt(2, accountID);
				ps.executeUpdate();
				ps.close();
				con.commit();

				con.setAutoCommit(false);

				ps = con.prepareStatement(accountAddCallPlan);
				ps.setInt(1, callPlanID);
				ps.setInt(2, accountID);
				ps.setString(3, currentTime);
				ps.executeUpdate();
				ps.close();
				con.commit();

				con.setAutoCommit(false);

				float tempCallPlanRate;
				ps = con.prepareStatement(selectCallPlanRate);
				ps.setInt(1, callPlanID);
				rs = ps.executeQuery();
				rs.next();
				tempCallPlanRate = rs.getFloat(1);
				con.commit();
				rs.close();
				ps.close();

				con.setAutoCommit(false);

				float accountBalance;
				ps = con.prepareStatement(getAccountBalance);
				ps.setInt(1, accountID);
				rs = ps.executeQuery();
				rs.next();
				accountBalance = rs.getFloat(1);
				rs.close();
				ps.close();
				con.commit();

				con.setAutoCommit(false);

				float updatedBalance = tempCallPlanRate + accountBalance;
				ps = con.prepareStatement(updateAccountBalance);
				ps.setFloat(1, updatedBalance);
				ps.setInt(2, accountID);
				ps.executeUpdate();
				ps.close();
				con.commit();

				con.setAutoCommit(false);

				ps = con.prepareStatement(selectAccountAndCallPlan);
				ps.setInt(1, accountID);
				rs = ps.executeQuery();
				rs.next();
				account = new Account(accountID, rs.getFloat(2), rs.getBoolean(3), rs.getInt(4), rs.getString(5),
						rs.getString(6));

				return account;
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
				if (ps != null) {
					ps.close();
				}
				con.commit();
				cp.releaseConnection(con, st);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}

	@Override
	public Account connectPhoneNumber(int accountID, String phoneNumber) throws DaoException {

		String selectAccountAndPhoneNumber;
		String selectPhoneNumberAndAccountPhoneNumber;
		int phoneNumberID;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Account account;

		selectAccountAndPhoneNumber = rpf.getSelectAccountAndPhoneNumber();
		selectPhoneNumberAndAccountPhoneNumber = rpf.getSelectPhoneNumberAndAccountPhoneNumber();

		try {

			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(selectAccountAndPhoneNumber);
			ps.setInt(1, accountID);
			rs = ps.executeQuery();

			rs.next();

			if (rs.getRow() == 0) {
				rs.close();
				ps.close();
				con.commit();

				phoneNumberID = getPhoneNumberID(phoneNumber);
				
				updatePhoneNumberAvailableStatus(phoneNumberID, false);
				
				
				
				connectPhoneNumberToAccount(accountID, phoneNumberID);

				con.setAutoCommit(false);
				ps = con.prepareStatement(selectPhoneNumberAndAccountPhoneNumber);
				ps.setInt(1, accountID);
				rs = ps.executeQuery();

				rs.next();

				account = new Account();
				account.setAccountPhoneNumber(rs.getInt(2));
				account.setPhoneNumberID(rs.getInt(5));
				account.setAccountID(rs.getInt(6));
				account.setPhoneNumberConnectedAt(rs.getString(7));

				return account;

			} else {
				return null;
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
	public Account changePhoneNumber(int accountID, String newPhoneNumber) throws DaoException {
		Account account;
		int currentPhoneNumberID;
		int newPhoneNumberID;

		currentPhoneNumberID = getConnectedToAccountPhoneNumberID(accountID);
		newPhoneNumberID = getPhoneNumberID(newPhoneNumber);

		disconnectPhoneNumberFromAccount(accountID);

		updatePhoneNumberAvailableStatus(currentPhoneNumberID, true);

		updatePhoneNumberAvailableStatus(newPhoneNumberID, false);

		connectPhoneNumberToAccount(accountID, newPhoneNumberID);

		account = getAccountDetails(accountID);
		return account;
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
	public Account chargeToAccount(int accountID, float amount) throws DaoException {

		String chargeToAccount;
		Connection con = null;
		PreparedStatement ps = null;
		Account account = null;
		chargeToAccount = rpf.getChargeToAccount();

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(chargeToAccount);
			ps.setFloat(1, amount);
			ps.setInt(2, accountID);
			ps.executeUpdate();

			account = getAccountDetails(accountID);
			return account;

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
	public Account blockAccount(int accountID) throws DaoException {

		String changeAccountBlockStatus;

		Connection con = null;
		PreparedStatement ps = null;
		Account account = null;

		changeAccountBlockStatus = rpf.getChangeAccountBlockStatus();

		try {

			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(changeAccountBlockStatus);
			ps.setBoolean(1, true);
			ps.setInt(2, accountID);
			ps.executeUpdate();

			account = getAccountDetails(accountID);
			return account;

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
	public Account unblockAccount(int accountID) throws DaoException {
		String changeAccountBlockStatus;

		Connection con = null;
		PreparedStatement ps = null;
		Account account = null;

		changeAccountBlockStatus = rpf.getChangeAccountBlockStatus();

		try {

			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(changeAccountBlockStatus);
			ps.setBoolean(1, false);
			ps.setInt(2, accountID);
			ps.executeUpdate();

			account = getAccountDetails(accountID);
			return account;

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

	public boolean disconnectPhoneNumberFromAccount(int accountID) throws DaoException {
		String disconnectPhoneNumberFromAccount;
		int updateStatus;
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
	public List<Account> getListOfAllAccounts() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
}
