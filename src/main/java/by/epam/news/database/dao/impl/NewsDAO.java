package by.epam.news.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.news.database.dao.AbstractDAO;
import by.epam.news.database.dao.INewsDAO;
import by.epam.news.entity.News;
import by.epam.news.exception.DaoLayerException;

public final class NewsDAO extends AbstractDAO implements INewsDAO {

	private static final Logger LOG = Logger.getLogger(NewsDAO.class);

	private static final String PARAM_NAME_ID = "ID";
	private static final String PARAM_NAME_TITLE = "TITLE";
	private static final String PARAM_NAME_CONTENT = "CONTENT";
	private static final String PARAM_NAME_BRIEF = "BRIEF";
	private static final String PARAM_NAME_DATE = "NEWSDATE";

	private static final String SELECT_ALL_NEWS = "SELECT * FROM NEWS ORDER BY NEWSDATE DESC";
	private static final String SELECT_NEWS_BY_ID = "SELECT * FROM NEWS WHERE ID = ?";
	private static final String UPDATE_NEWS = "UPDATE NEWS SET TITLE=?,NEWSDATE=?, BRIEF=?, CONTENT=? where ID=?";
	private static final String CREATE_NEWS = "INSERT INTO NEWS "
			+ "(ID,TITLE,CONTENT, NEWSDATE, BRIEF ) VALUES (AUTOINC_SEQ.nextval, ?, ?, ?, ?)";
	private static final String DELETE_NEWS = "DELETE FROM NEWS WHERE ID IN (?)";

	@Override
	public List<News> getList() throws DaoLayerException {
		LOG.info("DAO. Getting list...");
		List<News> listNews = new ArrayList<News>();
		News news = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		connection = getConnection();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_ALL_NEWS);
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
			throw new DaoLayerException("Some problem with getList() method", e);
		} finally {
			closeOperation(connection, statement);
		}
		return listNews;
	}

	@Override
	public News save(News news) throws DaoLayerException {
		LOG.info("DAO. Saving news...");
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(CREATE_NEWS,
					new String[] { "id" });
			preparedStatement.setString(1, news.getTitle());
			preparedStatement.setString(2, news.getContent());
			preparedStatement.setDate(3, news.getCurrentDate());
			preparedStatement.setString(4, news.getBrief());
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				news.setId(resultSet.getInt(1));
			}
			return news;
		} catch (SQLException e) {
			throw new DaoLayerException("Some problem with save() method", e);
		} finally {
			closeOperation(connection, preparedStatement);
		}

	}

	@Override
	public News findById(int id) throws DaoLayerException {
		LOG.info("DAO. Finding news by id...");
		News news = null;
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(SELECT_NEWS_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				news = new News();
				news.setId(resultSet.getInt(PARAM_NAME_ID));
				news.setTitle(resultSet.getString(PARAM_NAME_TITLE));
				news.setBrief(resultSet.getString(PARAM_NAME_BRIEF));
				news.setCurrentDate(resultSet.getDate(PARAM_NAME_DATE));
				news.setContent(resultSet.getString(PARAM_NAME_CONTENT));
			}
		} catch (SQLException e) {
			throw new DaoLayerException("Some problem with findById() method",
					e);
		} finally {
			closeOperation(connection, preparedStatement);
		}
		return news;
	}

	@Override
	public void update(News news) throws DaoLayerException {
		LOG.info("DAO. Updating news...");
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(UPDATE_NEWS);
			preparedStatement.setString(1, news.getTitle());
			preparedStatement.setDate(2, news.getCurrentDate());
			preparedStatement.setString(3, news.getBrief());
			preparedStatement.setString(4, news.getContent());
			preparedStatement.setInt(5, news.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoLayerException("Some problem with update method", e);
		} finally {
			closeOperation(connection, preparedStatement);
		}
	}

	@Override
	public void deleteList(List<Integer> newsListId) throws DaoLayerException {
		LOG.info("DAO. Deleting news list...");
		int listSize = newsListId.size();
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(preparedQuery(listSize));
			int index = 1;
			for (Integer id : newsListId) {
				preparedStatement.setInt(index, id);
				index++;
			}
			preparedStatement.execute();
		} catch (SQLException e) {
			throw new DaoLayerException(
					"Some problem with deleteList() method", e);
		} finally {
			closeOperation(connection, preparedStatement);
		}
	}

	private String preparedQuery(int number) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < number - 1; i++) {
			builder.append("?,");
		}
		builder.append("?");
		String args = builder.toString();
		return DELETE_NEWS.replace("?", args);
	}
}
