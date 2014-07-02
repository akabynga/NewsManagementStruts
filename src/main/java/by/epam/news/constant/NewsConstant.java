package by.epam.news.constant;

public final class NewsConstant {

	private NewsConstant() {
		super();
	}

	public static final String PARAM_NAME_REFERER = "referer";
	public static final String PARAM_NAME_LIST_SUCCESS = "listsuccess";
	public static final String PARAM_NAME_VIEW_SUCCESS = "viewsuccess";
	public static final String PARAM_NAME_MODIFY_SUCCESS = "modifysuccess";
	public static final String PARAM_NAME_DELETE_SUCCESS = "deletesuccess";
	public static final String PARAM_NAME_SAVE_SUCCESS = "savesuccess";
	public static final String PARAM_NAME_PREVIOUS_PAGE = "previousPage";
	public static final String PARAM_NAME_CREATE_NEWS = "createsuccess";
	public static final String PARAM_DATE_FORMAT_OUT = "MM/dd/yyyy";
	public static final String PARAM_DATE_FORMAT_IN = "yyyy-MM-dd";
	public static final String SELECT_ALL_NEWS = "from News order by NEWSDATE desc";
	public static final String DELETE_BY_ID = "delete from News where id in (:id)";
	public static final String DELETE_NEWS = "id";
}
