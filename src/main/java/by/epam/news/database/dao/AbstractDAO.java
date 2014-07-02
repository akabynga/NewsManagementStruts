package by.epam.news.database.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import by.epam.news.database.pool.impl.ConnectionPool;
import by.epam.news.exception.DaoLayerException;
import by.epam.news.exception.TechnicalException;

public abstract class AbstractDAO {

	ConnectionPool dataSource;

	public ConnectionPool getDataSource() {
		return dataSource;
	}

	public void setDataSource(ConnectionPool dataSource) {
		this.dataSource = dataSource;
	}

	protected Connection getConnection() throws DaoLayerException {
		try {
			return dataSource.getConnection();
		} catch (TechnicalException e) {
			throw new DaoLayerException();
		}
	}

	protected void closeOperation(Connection connection,
			Statement preparedStatement) throws DaoLayerException {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			throw new DaoLayerException(
					"Some problem with closeOperation() method", e);
		} finally {
			if (connection != null) {
				dataSource.closeConnection(connection);
			}
		}
	}
}
