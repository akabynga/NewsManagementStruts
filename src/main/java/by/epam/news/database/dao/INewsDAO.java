package by.epam.news.database.dao;

import java.util.List;

import by.epam.news.database.exception.DaoLayerException;
import by.epam.news.entity.News;

public interface INewsDAO {

	List<News> getList() throws DaoLayerException;

	News save() throws DaoLayerException;

	News findById() throws DaoLayerException;

	void delete() throws DaoLayerException;

	void update() throws DaoLayerException;

}
