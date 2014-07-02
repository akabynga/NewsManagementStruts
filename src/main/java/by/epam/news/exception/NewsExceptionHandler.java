package by.epam.news.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

public final class NewsExceptionHandler extends ExceptionHandler {
	
	private static final Logger LOG = Logger
			.getLogger(NewsExceptionHandler.class);

	public NewsExceptionHandler() {
		super();
	}

	@Override
	public ActionForward execute(Exception ex, ExceptionConfig ae,
			ActionMapping mapping, ActionForm formInstance,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		LOG.error("Exception handing", ex);
		return super.execute(ex, ae, mapping, formInstance, request, response);
	}
}
