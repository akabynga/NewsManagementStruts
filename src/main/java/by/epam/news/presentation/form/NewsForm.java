package by.epam.news.presentation.form;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import by.epam.news.entity.News;
import by.epam.news.exception.TechnicalException;
import by.epam.news.util.DateConverter;

public final class NewsForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private News newsMessage = new News();
	private List<News> newsList = new ArrayList<News>();
	private int[] listNewsId;
	private int newsId;

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int id) {
		this.newsId = id;
	}

	public NewsForm() {
		super();
	}

	public News getNewsMessage() {
		return newsMessage;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsMessage(News newsMessage) {
		this.newsMessage = newsMessage;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public int[] getListNewsId() {
		return listNewsId;
	}

	public void setListNewsId(int[] listNewsId) {
		this.listNewsId = listNewsId;
	}

	public String getDateAsString() {
		return DateConverter.convertDateToString(newsMessage.getCurrentDate());
	}

	public void setDateAsString(String dateAsString) throws TechnicalException {
		try {
			newsMessage.setCurrentDate(DateConverter
					.convertStringToDate(dateAsString));
		} catch (ParseException e) {
			throw new TechnicalException("Some problem with date convert", e);
		}
	}
}
