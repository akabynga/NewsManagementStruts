package by.epam.news.database.pool;

import java.sql.Connection;

import by.epam.news.exception.TechnicalException;

public interface IConnectionPool {

	void init() throws Exception;

	void destroy();

	Connection getConnection() throws TechnicalException;

	void closeConnection(Connection connection);
}
