package by.epam.news.presentation.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import by.epam.news.exception.ActionLayerException;
import by.epam.news.exception.DaoLayerException;
import by.epam.news.presentation.form.NewsForm;

public final class NewsAction extends DispatchAction {

	static {
		Locale.setDefault(Locale.ENGLISH);
	}
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
			throws ActionLayerException {
		List<News> news = null;
		try {
			news = newsDAO.getList();
		} catch (DaoLayerException e) {
			LOG.error("Some problem with NewsAction.list ", e);
			throw new ActionLayerException(
					"Some problem with NewsAction.list ", e);
		}
		NewsForm newsForm = (NewsForm) form;
		newsForm.setNewsList(news);
		return mapping.findForward(NewsConstant.PARAM_NAME_LIST_SUCCESS);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ActionLayerException {
		NewsForm newsForm = (NewsForm) form;
		int id = newsForm.getNewsMessage().getId();
		News news = null;
		try {
			news = newsDAO.findById(id);
		} catch (DaoLayerException e) {
			LOG.error("Some problem with in NewsAction.view ", e);
			throw new ActionLayerException(
					"Some problem with in NewsAction.view ", e);
		}
		newsForm.setNewsMessage(news);
		return mapping.findForward(NewsConstant.PARAM_NAME_VIEW_SUCCESS);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ActionLayerException {
		NewsForm newsForm = (NewsForm) form;
		int id = newsForm.getNewsMessage().getId();
		News news = null;
		try {
			news = newsDAO.findById(id);
			newsForm.setNewsId(id);
		} catch (DaoLayerException e) {
			LOG.error("Some problem with in NewsAction.edit ", e);
			throw new ActionLayerException(
					"Some problem with in NewsAction.edit ", e);
		}
		newsForm.setNewsMessage(news);
		return mapping.findForward(NewsConstant.PARAM_NAME_MODIFY_SUCCESS);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ActionLayerException {
		int[] listNewsId = ((NewsForm) form).getListNewsId();
		int id = ((NewsForm) form).getNewsId();
		try {
			if (id != 0) {
				newsDAO.delete(id);
				((NewsForm) form).setNewsId(0);
				return mapping
						.findForward(NewsConstant.PARAM_NAME_DELETE_SUCCESS);
			}
			if (listNewsId != null) {
				newsDAO.deleteList(arrayToList(listNewsId));
			} else {
				LOG.warn("Haven't news for remove");
				return mapping
						.findForward(NewsConstant.PARAM_NAME_LIST_SUCCESS);
			}

		} catch (DaoLayerException e) {
			LOG.error("Some problem with in NewsAction.delete ", e);
			throw new ActionLayerException(
					"Some problem with in NewsAction.delete ", e);
		}
		return mapping.findForward(NewsConstant.PARAM_NAME_DELETE_SUCCESS);
	}

	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ActionLayerException, DaoLayerException {

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
			throws ActionLayerException {
		NewsForm newsForm = (NewsForm) form;
		News news = newsForm.getNewsMessage();
		int id = news.getId();
		try {
			if (id != 0) {
				news.setId(id);
				newsDAO.update(news);
			} else {
				news = newsDAO.save(news);
			}
		} catch (DaoLayerException e) {
			LOG.error("Some problem with in NewsAction.save ", e);
			throw new ActionLayerException(
					"Some problem with in NewsAction.save ", e);
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
