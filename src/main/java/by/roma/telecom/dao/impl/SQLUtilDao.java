package by.roma.telecom.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import by.roma.telecom.dao.DaoException;
import by.roma.telecom.dao.UtilDao;
import by.roma.telecom.pooling.ConnectionPool;
import by.roma.telecom.property.ReadPropertyFile;

public class SQLUtilDao implements UtilDao {
	private static final ReadPropertyFile rpf = ReadPropertyFile.getInstance();
	private static ConnectionPool cp = ConnectionPool.getInstance();

	@Override
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
			return numbers;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if(st != null) {
					st.close();
				}
				if (rs != null) {
					rs.close();
				}
				con.commit();
				cp.releaseConnection(con);
			}catch (SQLException e) {
				throw new DaoException(e);
			}
			
		}
	}

}
