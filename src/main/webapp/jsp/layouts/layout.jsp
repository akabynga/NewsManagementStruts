<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html>
<head>
<script src="jsp/messages.jsp" type="text/javascript"></script>
<script src="js/validation.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta charset="utf-8">
<title><bean:message key="layout.lable.title" /></title>

</head>
<body>
	<tiles:insert attribute="header" />
	<div class="mainpart">
		<tiles:insert attribute="menu" />
		<div class="content">
			<tiles:insert attribute="body" />
		</div>
	</div>
	<tiles:insert attribute="footer" />
</body>
</html>

<c:if test="${currentPage != page }">
	<c:set var="previousPage" value="${currentPage}" scope="session" />
	<c:set var="currentPage" value="${page}" scope="session" />
</c:if>