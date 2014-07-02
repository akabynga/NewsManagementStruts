package by.epam.news.presentation.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import by.epam.news.constant.NewsConstant;
import by.epam.news.database.dao.INewsDAO;
import by.epam.news.entity.News;
import by.epam.news.exception.DaoLayerException;
import by.epam.news.presentation.form.NewsForm;

public final class NewsAction extends DispatchAction {

	private static final Logger LOG = Logger.getLogger(NewsAction.class);
	private INewsDAO newsDAO;

	public INewsDAO getNewsDAO() {
		return newsDAO;
	}

	public void setNewsDAO(INewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	public NewsAction() {
		super();
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws DaoLayerException {
		List<News> news = null;
		news = newsDAO.getList();
		NewsForm newsForm = (NewsForm) form;
		newsForm.setNewsList(news);
		newsForm.setListNewsId(null);
		return mapping.findForward(NewsConstant.PARAM_NAME_LIST_SUCCESS);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws DaoLayerException {
		NewsForm newsForm = (NewsForm) form;
		int id = newsForm.getNewsMessage().getId();
		News news = newsDAO.findById(id);
		newsForm.setNewsMessage(news);
		return mapping.findForward(NewsConstant.PARAM_NAME_VIEW_SUCCESS);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws DaoLayerException {
		NewsForm newsForm = (NewsForm) form;
		int id = newsForm.getNewsMessage().getId();
		News news = newsDAO.findById(id);
		newsForm.setNewsId(id);
		newsForm.setNewsMessage(news);
		return mapping.findForward(NewsConstant.PARAM_NAME_MODIFY_SUCCESS);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws DaoLayerException {
		int[] listNewsId = ((NewsForm) form).getListNewsId();
		if (listNewsId != null) {
			newsDAO.deleteList(arrayToList(listNewsId));
		} else {
			LOG.warn("Haven't news for remove");
			return mapping.findForward(NewsConstant.PARAM_NAME_LIST_SUCCESS);
		}
		return mapping.findForward(NewsConstant.PARAM_NAME_DELETE_SUCCESS);
	}

	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws DaoLayerException {
		NewsForm newsForm = (NewsForm) form;
		if (newsForm.getNewsId() != 0) {
			int id = ((NewsForm) form).getNewsId();
			News news = newsDAO.findById(id);
			newsForm.setNewsMessage(news);
		}

		return getPreviousPage(mapping, request);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		News news = new News();
		NewsForm newsForm = (NewsForm) form;
		newsForm.setNewsMessage(news);
		return mapping.findForward(NewsConstant.PARAM_NAME_CREATE_NEWS);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws DaoLayerException {
		NewsForm newsForm = (NewsForm) form;
		News news = newsForm.getNewsMessage();
		int id = news.getId();
		if (id != 0) {
			news.setId(id);
			newsDAO.update(news);
		} else {
			news = newsDAO.save(news);
		}
		newsForm.getNewsList().add(news);
		return mapping.findForward(NewsConstant.PARAM_NAME_SAVE_SUCCESS);
	}

	private ActionForward getPreviousPage(ActionMapping mapping,
			HttpServletRequest request) {
		String pageURL = (String) request.getSession().getAttribute(
				NewsConstant.PARAM_NAME_PREVIOUS_PAGE);
		ActionForward page = null;
		if (pageURL != null) {
			page = new ActionForward(pageURL);
		} else {
			page = mapping.findForward(NewsConstant.PARAM_NAME_LIST_SUCCESS);
		}
		return page;
	}

	private List<Integer> arrayToList(int[] array) {
		List<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++)
			l.add(array[i]);
		return l;
	}
}
