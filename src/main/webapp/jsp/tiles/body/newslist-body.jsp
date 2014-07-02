<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="layout.title.newslist" /></title>
<c:set var="page" value="page.newslist" scope="session" />

 
<logic:notEmpty name="newsForm" property="newsList">
	<html:form action="/news.do?method=delete"
		onsubmit="return validateRemove(this);">
		<c:forEach items="${newsForm.newsList}" var="news">
			<div class="newsMessage">
				<div>
					<div class="newsheader">
						<div class="newsdate">
							<fmt:formatDate value="${news.currentDate}" pattern="dd/MM/yyyy" />
						</div>
						<div class="newstitle">
							<bean:message key="layout.lable.news" />
							${news.title}
						</div>
					</div>
					<div class="newsbrief">
						<label>${news.brief}</label>
					</div>
					<div class="controlbar">
						<bean:define id="id" property="newsMessage.id" value="${news.id}" />
						<html:link action="/news.do?method=view" paramId="newsMessage.id"
							paramName="id">
							<bean:message key="layout.button.view" />
						</html:link>
						<html:link action="/news.do?method=edit" paramId="newsMessage.id"
							paramName="id">
							<bean:message key="layout.button.edit" />
						</html:link>
						<html:multibox property="listNewsId" value="${news.id}" />
					</div>
				</div>
			</div>
			<br>
		</c:forEach>

		<div id="deletebutton">
			<html:submit styleClass="control">
				<bean:message key="layout.button.delete" />
			</html:submit>
		</div>
	</html:form>
</logic:notEmpty>
