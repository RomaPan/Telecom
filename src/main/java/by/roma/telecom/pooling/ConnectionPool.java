package by.roma.telecom.pooling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.apache.log4j.Logger;
import by.roma.telecom.property.ReadPropertyFile;

public final class ConnectionPool {

	private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
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
			LOGGER.info("Connection Pool has been created");
		} catch (ClassNotFoundException e) {
			LOGGER.error("Connection Pool Exception: ClassNotFoundException. ",e);
			throw new RuntimeException(e);
		} catch (SQLException e) {
			LOGGER.error("Connection Pool Exception: SQLException. ",e);
			throw new RuntimeException(e);
		} catch (NumberFormatException e){
			LOGGER.error("Connection Pool Exception: Failed to read pool size value from property file.",e);
			this.poolSizeDB = 5;
		} 
	}
	public void createPoolManually() {
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
			LOGGER.debug("Connection Pool has been created");
		} catch (ClassNotFoundException e) {
			LOGGER.error("Connection Pool Exception: ClassNotFoundException. ",e);
			throw new RuntimeException(e);
		} catch (SQLException e) {
			LOGGER.error("Connection Pool Exception: SQLException. ",e);
			throw new RuntimeException(e);
		} catch (NumberFormatException e){
			LOGGER.error("Connection Pool Exception: Failed to read pool size value from property file.",e);
			this.poolSizeDB = 5;
		} 
	}

	public Connection takeConnection() {
		Connection connection = null;
		try {
			connection = availableConnectionQueue.take();
			takenConnectionQueue.add(connection);
			return connection;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.error("Connection Pool Exception: InterruptedException. ",e);
			throw new RuntimeException(e);
		}
	}

	public void releaseConnection(Connection connection) {
		try {
			connection.setAutoCommit(true);
			takenConnectionQueue.remove(connection);
			availableConnectionQueue.offer(connection);
		} catch (SQLException e) {
			LOGGER.error("Connection Pool Exception : Release Connection : SQLException. ",e);
		}
	}

	public void terminateAllConnectionQueues() {
		try {
			ceaseConnectionQueues(takenConnectionQueue);
			ceaseConnectionQueues(availableConnectionQueue);
			LOGGER.info("Connection Pool has been terminated.");
		}catch (SQLException e) {
			LOGGER.error("Connection Pool Exception : Release Connection : SQLException. ",e);
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
