package by.epam.news.presentation.form;

import org.apache.struts.action.ActionForm;

public final class LanguageForm extends ActionForm {

	private static final long serialVersionUID = -8944245971784123445L;

	private String language = "";

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
