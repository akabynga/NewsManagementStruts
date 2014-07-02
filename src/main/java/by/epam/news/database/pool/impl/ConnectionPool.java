package by.epam.news.database.pool.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import by.epam.news.database.pool.IConnectionPool;
import by.epam.news.exception.TechnicalException;

public final class ConnectionPool implements IConnectionPool {

	private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
	private static BlockingQueue<Connection> freeConnections = null;
	private List<Connection> allConnections = new ArrayList<Connection>();
	private String driverClass;
	private String url;
	private String user;
	private String password;
	private int poolSize;

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
			freeConnections = new ArrayBlockingQueue<Connection>(poolSize);
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
		freeConnections.add(connection);
		allConnections.add(connection);
	}

	public Connection getConnection() throws TechnicalException {
		try {
			return freeConnections.take();
		} catch (InterruptedException e) {
			throw new TechnicalException();
		}
	}

	public void closeConnection(Connection connection) {
		try {
			if (!connection.isClosed()) {
				freeConnections.add(connection);
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
		for (Iterator<Connection> iterator = allConnections.iterator(); iterator
				.hasNext();) {
			Connection connection = iterator.next();
			try {
				connection.close();
			} catch (SQLException e) {
				LOG.error("Some problem with ConnectionPool.destroy:", e);
			}
		}
		freeConnections.removeAll(freeConnections);
		LOG.info("Pool succesfully cleared.");
	}
}
