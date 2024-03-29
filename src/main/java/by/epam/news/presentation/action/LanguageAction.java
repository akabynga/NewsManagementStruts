package by.epam.news.presentation.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import by.epam.news.constant.NewsConstant;
import by.epam.news.presentation.form.LanguageForm;

public final class LanguageAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		LanguageForm languageForm = (LanguageForm) form;
		String language = languageForm.getLanguage();
		request.getSession().setAttribute(Globals.LOCALE_KEY,
				new Locale(language));

		return getCurrentPage(mapping, request);
	}

	private ActionForward getCurrentPage(ActionMapping mapping,
			HttpServletRequest request) {
		String pageURL = (String) request
				.getHeader(NewsConstant.PARAM_NAME_REFERER);
		ActionForward page = null;
		if (pageURL != null) {
			page = new ActionForward(pageURL, true);
		} else {
			page = mapping.findForward(NewsConstant.PARAM_NAME_LIST_SUCCESS);
		}
		return page;
	}
}
