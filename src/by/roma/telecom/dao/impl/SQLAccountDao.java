package by.roma.telecom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import by.roma.telecom.bean.Account;
import by.roma.telecom.dao.AccountDao;
import by.roma.telecom.dao.DaoException;
import by.roma.telecom.pooling.ConnectionPool;
import by.roma.telecom.property.ReadPropertyFile;

public class SQLAccountDao implements AccountDao {

	private static final ReadPropertyFile rpf = ReadPropertyFile.getInstance();
	private static ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public Account addCallPlan(String accountID, String callPlanID) throws DaoException {

		String accountAddCallPlan;
		String accountCallPlanInfo;
		String accountUpdateCallPlan;
		String selectAccountAndCallPlan;
		String selectCallPlanRate;
		String chargeToAccount;

		int cpID;
		int accID;

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

		cpID = Integer.parseInt(callPlanID);
		accID = Integer.parseInt(accountID);

		accountAddCallPlan = rpf.getAccountAddCallPlan();
		accountCallPlanInfo = rpf.getAccountCallPlanInfo();
		accountUpdateCallPlan = rpf.getAccountUpdateCallPlan();
		selectAccountAndCallPlan = rpf.getSelectAccountAndCallPlan();
		selectCallPlanRate = rpf.getSelectCallPlanRate();
		chargeToAccount = rpf.getChargeToAccount();

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(accountCallPlanInfo);
			ps.setInt(1, accID);
			rs = ps.executeQuery();

			rs.next();

			if (rs.getRow() == 0) {

				System.out.println("Result set is null");
				con.commit();
				rs.close();
				ps.close();

				con.setAutoCommit(false);
				ps = con.prepareStatement(accountAddCallPlan);
				ps.setInt(1, cpID);
				ps.setInt(2, accID);
				ps.setString(3, currentTime);
				ps.executeUpdate();
				ps.close();

				con.commit();

				con.setAutoCommit(false);
				ps = con.prepareStatement(selectAccountAndCallPlan);
				ps.setInt(1, accID);
				rs = ps.executeQuery();
				rs.next();

				account = new Account(accID, rs.getFloat(2), rs.getBoolean(3), rs.getInt(4), rs.getString(5),
						rs.getString(6));

				con.commit();
				rs.close();
				ps.close();

				con.setAutoCommit(false);
				ps = con.prepareStatement(selectCallPlanRate);
				ps.setInt(1, cpID);
				rs = ps.executeQuery();
				rs.next();

				account.setAccountBalance(rs.getFloat(1));
				con.commit();
				rs.close();
				ps.close();

				con.setAutoCommit(false);
				ps = con.prepareStatement(chargeToAccount);
				ps.setFloat(1, account.getAccountBalance());
				ps.setInt(2, accID);
				ps.executeUpdate();
				ps.close();
				con.commit();

				return account;

			} else {
				con.commit();
				rs.close();
				ps.close();

				con.setAutoCommit(false);
				ps = con.prepareStatement(accountUpdateCallPlan);

				ps.setString(1, currentTime);
				ps.setInt(2, accID);
				ps.executeUpdate();
				ps.close();

				con.commit();

				con.setAutoCommit(false);

				ps = con.prepareStatement(accountAddCallPlan);

				ps.setInt(1, cpID);
				ps.setInt(2, accID);
				ps.setString(3, currentTime);
				ps.executeUpdate();
				ps.close();

				con.commit();

				con.setAutoCommit(false);
				ps = con.prepareStatement(selectAccountAndCallPlan);
				ps.setInt(1, accID);

				rs = ps.executeQuery();

				rs.next();

				account = new Account(accID, rs.getFloat(2), rs.getBoolean(3), rs.getInt(4), rs.getString(5),
						rs.getString(6));

				con.commit();
				rs.close();
				ps.close();

				con.setAutoCommit(false);
				ps = con.prepareStatement(selectCallPlanRate);
				ps.setInt(1, cpID);
				rs = ps.executeQuery();
				rs.next();

				account.setAccountBalance(rs.getFloat(1));
				con.commit();
				rs.close();
				ps.close();

				System.out.println(account.toString());

				return account;
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
	public Account connectPhoneNumber(String accountID, String phoneNumber) throws DaoException {

		String selectPhoneNumber;
		String connectPhoneNumberToAccount;
		String reservePhoneNumber;
		String selectAccountAndPhoneNumber;
		String selectPhoneNumberAndAccountPhoneNumber;
		int phoneNumberID;
		int lineNumber;
		int accID;

		lineNumber = Integer.parseInt(phoneNumber);
		accID = Integer.parseInt(accountID);

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

		selectPhoneNumber = rpf.getSelectPhoneNumber();
		connectPhoneNumberToAccount = rpf.getConnectPhoneNumberToAccount();
		reservePhoneNumber = rpf.getReservePhoneNumber();
		selectAccountAndPhoneNumber = rpf.getSelectAccountAndPhoneNumber();
		selectPhoneNumberAndAccountPhoneNumber = rpf.getSelectPhoneNumberAndAccountPhoneNumber();

		try {

			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(selectAccountAndPhoneNumber);
			ps.setInt(1, accID);
			rs = ps.executeQuery();

			rs.next();

			if (rs.getRow() == 0) {
				rs.close();
				ps.close();
				con.commit();

				con.setAutoCommit(false);
				ps = con.prepareStatement(selectPhoneNumber);
				ps.setInt(1, lineNumber);
				rs = ps.executeQuery();

				rs.next();

				phoneNumberID = rs.getInt(1);
				rs.close();
				ps.close();
				con.commit();

				con.setAutoCommit(false);
				ps = con.prepareStatement(reservePhoneNumber);
				ps.setBoolean(1, false);
				ps.setInt(2, phoneNumberID);
				ps.executeUpdate();
				ps.close();
				con.commit();

				con.setAutoCommit(false);
				ps = con.prepareStatement(connectPhoneNumberToAccount);
				ps.setInt(1, phoneNumberID);
				ps.setInt(2, accID);
				ps.setString(3, currentTime);
				ps.executeUpdate();
				ps.close();
				con.commit();

				con.setAutoCommit(false);
				ps = con.prepareStatement(selectPhoneNumberAndAccountPhoneNumber);
				ps.setInt(1, accID);
				rs = ps.executeQuery();

				rs.next();

				account = new Account();
				account.setAccountPhoneNumber(rs.getInt(2));
				account.setPhoneNumberID(rs.getInt(5));
				account.setAccountID(rs.getInt(6));
				account.setPhoneNumberConnectedAt(rs.getString(7));

				System.out.println(account);

				rs.close();
				ps.close();
				con.commit();

				return account;

			} else {
				rs.close();
				ps.close();
				con.commit();

				return null;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
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
	public Account changePhoneNumber(String accountID, String newPhoneNumber) throws DaoException {

		return null;
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
//				ResultSetMetaData rsmd = rs.getMetaData();
//			    int columnsNumber = rsmd.getColumnCount();
//				 while (rs.next()) {
//				        for (int i = 1; i <= columnsNumber; i++) {
//				            if (i > 1) System.out.print(" | ");
//				            System.out.print(rs.getString(i));
//				        }
//				        System.out.println("");
//				    }
				account = new Account();

				account.setUserID(rs.getInt(1));
				account.setAccountID(rs.getInt(1));
				account.setAccountBalance(rs.getFloat(2));
				account.setBlocked(rs.getBoolean(3));
				account.setCallPlanID(rs.getInt(5));
				account.setCallPlanCreatedAt(rs.getString(7));
				account.setCallPlanCeasedAt(rs.getString(8));
				account.setPhoneNumberID(rs.getInt(10));
				account.setPhoneNumberConnectedAt(rs.getString(12));
				account.setPhoneNumberCeasedAt(rs.getString(13));
				account.setAccountCreatedAt(rs.getString(16));
				account.setAccountCeasedAt(rs.getString(17));
				account.setAccountPhoneNumber(rs.getInt(19));
				account.setCallPlanName(rs.getString(22));
				account.setCallPlanRate(rs.getFloat(23));
				account.setCallPlanMinutesLeft(rs.getInt(24));

				return account;
			}

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				con.commit();
				cp.releaseConnection(con, ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public Account chargeToAccount(int accountID, float amount) throws DaoException {

		String chargeToAccount;

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Account account = null;

		chargeToAccount = rpf.getChargeToAccount();

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(chargeToAccount);
			ps.setFloat(1, amount);
			ps.setInt(2, accountID);
			ps.executeUpdate();
			ps.close();
			con.commit();

			account = getAccountDetails(accountID);

			return account;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
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
	public Account blockAccount(int accountID) throws DaoException {

		String changeAccountBlockStatus;

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Account account = null;

		changeAccountBlockStatus = rpf.getChangeAccountBlockStatus();


		try {
			
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(changeAccountBlockStatus);
			ps.setBoolean(1, true);
			ps.setInt(2, accountID);
			ps.executeUpdate();
			ps.close();
			con.commit();

			account = getAccountDetails(accountID);
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
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
	public Account unblockAccount(int accountID) throws DaoException {
		String changeAccountBlockStatus;

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Account account = null;

		changeAccountBlockStatus = rpf.getChangeAccountBlockStatus();


		try {
			
			con = cp.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(changeAccountBlockStatus);
			ps.setBoolean(1, false);
			ps.setInt(2, accountID);
			ps.executeUpdate();
			ps.close();
			con.commit();

			account = getAccountDetails(accountID);
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
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
}
