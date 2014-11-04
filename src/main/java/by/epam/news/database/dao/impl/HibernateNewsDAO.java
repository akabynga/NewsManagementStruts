package by.epam.news.database.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.epam.news.constant.NewsConstant;
import by.epam.news.database.dao.INewsDAO;
import by.epam.news.entity.News;

@Repository
@Transactional
public final class HibernateNewsDAO implements INewsDAO {

	
	
	private SessionFactory sessionFactory;

	public HibernateNewsDAO() {
		super();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> getList() throws DataAccessException {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(NewsConstant.SELECT_ALL_NEWS).list();
	}

	@Override
	public News save(News news) throws DataAccessException {
		int id = (Integer) sessionFactory.getCurrentSession().save(news);
		news.setId(id);
		return news;
	}

	@Override
	public News findById(int id) throws DataAccessException {
		Session session = sessionFactory.getCurrentSession();
		News news = (News) session.get(News.class, id);
		return news;
	}

	@Override
	public void update(News news) throws DataAccessException {
		Session session = sessionFactory.getCurrentSession();
		session.update(news);
	}

	@Override
	public void deleteList(List<Integer> newsList) throws DataAccessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(NewsConstant.DELETE_BY_ID);
		query.setParameterList(NewsConstant.DELETE_NEWS, newsList);
		query.executeUpdate();

	}

}
