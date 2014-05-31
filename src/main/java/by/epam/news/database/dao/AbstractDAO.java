package by.epam.news.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.epam.news.database.pool.impl.ConnectionPool;
import by.epam.news.exception.DaoLayerException;

public abstract class AbstractDAO {

	ConnectionPool dataSource;

	public ConnectionPool getDataSource() {
		return dataSource;
	}

	public void setDataSource(ConnectionPool dataSource) {
		this.dataSource = dataSource;
	}

	protected Connection getConnection() {
		return dataSource.getConnection();
	}

	protected void closeOperation(Connection connection,
			PreparedStatement preparedStatement) throws DaoLayerException {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			throw new DaoLayerException();
		} finally {
			if (connection != null) {
				dataSource.closeConnection(connection);
			}
		}
	}
}
