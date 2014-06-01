<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="page" value="page.addnews" scope="session" />
<title><bean:message key="layout.title.addnews" /></title>

<html:form action="/news?method=save"
	onsubmit="return validateNewsMessage('newsForm');" styleClass="message">
	<html:hidden property="method" value="none" />

	<table>
		<col width="150px">
		<col>
		<tr>
			<td><b><bean:message key="layout.form.title" /></b></td>
			<td><html:text name="newsForm" property="newsMessage.title"
					maxlength="100" size="100" value="${newsForm.newsMessage.title}" /></td>
		</tr>
		<tr>
			<jsp:useBean id="date" class="java.util.Date" />
			<fmt:formatDate value="${date}" pattern="yyyy-MM-dd" var="currDate" />
			<c:if test="${newsForm.newsMessage.currentDate ne null}">
				<fmt:formatDate value="${newsForm.newsMessage.currentDate }"
					pattern="yyyy-MM-dd" var="currDate" />
			</c:if>
			<td><b><bean:message key="layout.form.newsdate" /></b></td>
			<td><html:text name="newsForm"
					property="newsMessage.currentDate" value="${currDate }" /></td>
		</tr>

		<tr>
			<td><b><bean:message key="layout.form.brief" /></b></td>
			<td class="textfield"><html:textarea rows="10" cols="80"
					name="newsForm" property="newsMessage.brief"
					value="${newsForm.newsMessage.brief}" /></td>
		</tr>
		<tr>
			<td><b><bean:message key="layout.form.content" /></b></td>
			<td class="textfield"><html:textarea rows="20" cols="80"
					name="newsForm" property="newsMessage.content"
					value="${newsForm.newsMessage.content}" /></td>
		</tr>
	</table>
	<p style="width: 100%; text-align: center;">
		<html:submit onclick="return validateNewsMessage('newsForm');">
			<bean:message key="layout.button.save" />
		</html:submit>
		<html:button property="cancel"
			onclick="location='/newsproject/news.do?method=cancel'">
			<bean:message key="layout.button.cancel" />
		</html:button>
	</p>
</html:form>
