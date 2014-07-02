package by.epam.news.database.dao;

import java.util.List;

import by.epam.news.entity.News;
import by.epam.news.exception.DaoLayerException;

public interface INewsDAO {

	List<News> getList() throws DaoLayerException;

	News save(News news) throws DaoLayerException;

	News findById(int id) throws DaoLayerException;

	void update(News news) throws DaoLayerException;

	void deleteList(List<Integer> newsList) throws DaoLayerException;

}
