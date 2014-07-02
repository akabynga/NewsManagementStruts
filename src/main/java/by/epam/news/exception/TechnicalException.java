package by.epam.news.exception;

public class TechnicalException extends Exception {

	private static final long serialVersionUID = 3722038838509547519L;

	public TechnicalException() {
		super();
	}

	public TechnicalException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public TechnicalException(String arg0) {
		super(arg0);
	}

	public TechnicalException(Throwable arg0) {
		super(arg0);
	}
}
