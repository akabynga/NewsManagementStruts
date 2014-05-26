<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta
	http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<c:forEach
			items="${newsForm.newsList}"
			var="news">
			<div class="newsBlock">

				<h2 class="title">
					<label>${news.newsTitle}</label>
				</h2>
				<p class="brief">
					<label>${news.brief}</label>
				</p>
			</div>
		</c:forEach>
	</div>
</body>
</html>