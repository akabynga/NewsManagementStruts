<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<c:set var="page" value="page.error" scope="session" />
<title><bean:message key="layout.title.error" /></title>


<link rel="stylesheet" type="text/css" href="css/style.css">
<div class="error">
	<bean:message key="layout.lable.error" />
</div>
<html:link action="/news.do?method=list">
	<bean:message key="error.redirect" />
</html:link>