package by.epam.news.database.pool;

import java.sql.Connection;

public interface IConnectionPool {

	void init() throws Exception;

	void destroy();

	Connection getConnection();

	void closeConnection(Connection connection);
}
