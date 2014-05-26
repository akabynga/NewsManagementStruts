package by.epam.news.presentation.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import by.epam.news.entity.News;

public class NewsForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private News newsMessage = new News();
	private List<News> newsList = new ArrayList<News>();

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

}
