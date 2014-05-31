package by.epam.news.database.pool.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import by.epam.news.database.pool.IConnectionPool;

public final class ConnectionPool implements IConnectionPool {

	public static final Logger LOG = Logger.getLogger(ConnectionPool.class);
	private static BlockingQueue<Connection> pool;

	private String driverClass;
	private String url;
	private String user;
	private String password;
	private int poolSize;
	private boolean flag = true;

	private ConnectionPool() {
		super();
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	public void init() {
		LOG.info("Trying to create pool of connections...");
		try {
			Class.forName(driverClass);
			pool = new ArrayBlockingQueue<Connection>(poolSize);
			for (int i = 0; i < poolSize; i++) {
				createConnection();
			}
			LOG.info("Connection pool succesfully initialized.");
		} catch (SQLException e) {
			LOG.error("Problem with init pool");
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			LOG.error("Problem with driver name");
			throw new RuntimeException(e);
		}
	}

	private void createConnection() throws SQLException {
		Connection connection = null;
		connection = DriverManager.getConnection(url, user, password);
		pool.offer(connection);
	}

	public Connection getConnection() {
		Connection connection = null;
		if (flag) {
			connection = pool.poll();
		}
		return connection;
	}

	public void closeConnection(Connection connection) {
		try {
			if (!connection.isClosed()) {
				pool.offer(connection);
			} else {
				LOG.warn("Connection already closed. Possible `leakage` of connections.");
			}
		} catch (SQLException e) {
			LOG.warn(
					"SQLException at conection isClosed () checking. Connection not closed.",
					e);
		}
	}

	public void destroy() {
		flag = false;
		Connection connection = null;
		int realSize = poolSize;
		while (realSize > 0) {
			try {
				connection = pool.take();
			} catch (InterruptedException e) {
				LOG.warn("Waiting connection, interrupt exception.", e);
			}
			if (connection != null) {
				try {
					if (!connection.isClosed()) {
						connection.close();
					}
				} catch (SQLException e) {
					LOG.warn("Problem with connection closing", e);
				}
				realSize--;
			}
		}
		LOG.info("Pool succesfully cleared.");
	}
}
