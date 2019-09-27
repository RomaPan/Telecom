package by.roma.telecom.pooling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import by.roma.telecom.property.ReadPropertyFile;

public final class ConnectionPool {

	private final static ConnectionPool instance = new ConnectionPool();
	private BlockingQueue<Connection> availableConnectionQueue;
	private BlockingQueue<Connection> takenConnectionQueue;

	private String driverDB;
	private String url;
	private String loginDB;
	private String pass;
	private int poolSizeDB;

	private ConnectionPool() {
		ReadPropertyFile rpf = ReadPropertyFile.getInstance();
		this.driverDB = rpf.getDriverDB();
		this.url = rpf.getUrl();
		this.loginDB = rpf.getLoginDB();
		this.pass = rpf.getPass();
		this.poolSizeDB = rpf.getPoolSizeDB();

		try {
			Class.forName(driverDB);
			this.availableConnectionQueue = new ArrayBlockingQueue<Connection>(poolSizeDB);
			for (int i = 0; i < poolSizeDB; i++) {
				Connection connection = DriverManager.getConnection(url, loginDB, pass);
				availableConnectionQueue.add(connection);
			}
			takenConnectionQueue = new ArrayBlockingQueue<Connection>(poolSizeDB);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection takeConnection() {
		Connection connection = null;
		try {
			connection = availableConnectionQueue.take();
			takenConnectionQueue.add(connection);
			return connection;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void releaseConnection(Connection connection, Statement statement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			connection.setAutoCommit(true);
			takenConnectionQueue.remove(connection);
			availableConnectionQueue.offer(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void releaseConnection(Connection connection, Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
			connection.setAutoCommit(true);
			takenConnectionQueue.remove(connection);
			availableConnectionQueue.offer(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void releaseConnection(Connection connection, PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			connection.setAutoCommit(true);
			takenConnectionQueue.remove(connection);
			availableConnectionQueue.offer(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ceaseConnectionQueues(BlockingQueue<Connection> queue) throws SQLException {
		Connection connection;

		while ((connection = queue.poll()) != null) {
			if (!connection.getAutoCommit()) {
				connection.commit();
				connection.close();
			}
		}
	}

	public static ConnectionPool getInstance() {
		return instance;
	}

}
