package by.epam.news.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.news.database.dao.INewsDAO;
import by.epam.news.database.exception.DaoLayerException;
import by.epam.news.database.pool.impl.ConnectionPool;
import by.epam.news.entity.News;

public class NewsDAO implements INewsDAO {

	private static final Logger LOG = Logger.getLogger(NewsDAO.class);

	private ConnectionPool dataSource;

	private static final String PARAM_NAME_ID = "ID";
	private static final String PARAM_NAME_TITLE = "TITLE";
	private static final String PARAM_NAME_CONTENT = "CONTENT";
	private static final String PARAM_NAME_BRIEF = "BRIEF";
	private static final String PARAM_NAME_DATE = "NEWSDATE";

	private static final String SELECT_ALL_NEWS = "SELECT * FROM NEWS ORDER BY NEWSDATE DESC";
	private static final String SELECT_NEWS_BY_ID = "";
	private static final String UPDATE_NEWS = "";
	private static final String DELETE_NEWS_BY_ID = "";
	private static final String CREATE_NEWS = "";

	public ConnectionPool getDataSource() {
		return dataSource;
	}

	public void setDataSource(ConnectionPool dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<News> getList() throws DaoLayerException {
		List<News> listNews = new ArrayList<News>();
		News news = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = dataSource.getConnection();
		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL_NEWS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				news = new News();
				news.setId(resultSet.getInt(PARAM_NAME_ID));
				news.setTitle(resultSet.getString(PARAM_NAME_TITLE));
				news.setContent(resultSet.getString(PARAM_NAME_CONTENT));
				news.setCurrentDate(resultSet.getDate(PARAM_NAME_DATE));
				news.setBrief(resultSet.getString(PARAM_NAME_BRIEF));
				listNews.add(news);
			}
		} catch (SQLException e) {
			throw new DaoLayerException();
		} finally {
			closeOperation(connection, preparedStatement);
		}
		return listNews;
	}

	@Override
	public News save() throws DaoLayerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public News findById() throws DaoLayerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() throws DaoLayerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() throws DaoLayerException {
		// TODO Auto-generated method stub

	}

	private void closeOperation(Connection connection, PreparedStatement preparedStatement)
			throws DaoLayerException {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			throw new DaoLayerException();
		}
		if (connection != null) {
			dataSource.closeConnection(connection);
		}

	}

}
