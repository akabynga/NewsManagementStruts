<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="page" value="page.viewnews" scope="session" />
<html:hidden property="method" value="none" />
<html:hidden property="newsId" value="${newsForm.newsMessage.id}" />
<div class="message">
	<table>
		<col width="170px">
		<col>
		<tr>
			<td class="innertitle"><bean:message key="layout.form.title" /></td>
			<td class="innertext">${newsForm.newsMessage.title}</td>
		</tr>
		<tr>
			<td class="innertitle"><bean:message key="layout.form.newsdate" /></td>
			<td class="innertext">${newsForm.dateAsString}</td>
		</tr>
		<tr>
			<td class="innertitle"><bean:message key="layout.form.brief" /></td>
			<td class="innertext">${newsForm.newsMessage.brief}</td>
		</tr>
		<tr>
			<td class="innertitle"><bean:message key="layout.form.content" /></td>
			<td class="innertext">${newsForm.newsMessage.content}</td>
		</tr>
	</table>

	<div class="viewbtn">
		<html:form action="/news.do?method=delete"
			onsubmit="return confirm(msgRemoveGroupConfirmMessage);">
			<html:hidden name="newsForm" property="listNewsId"
				value="${newsForm.newsMessage.id}" />
			<html:submit>
				<bean:message key="layout.button.delete" />
			</html:submit>
		</html:form>
	</div>
		<div class="viewbtn">
		<html:form action="/news.do?method=edit">
			<html:hidden property="newsForm.newsMessage.id"
				value="${newsForm.newsMessage.id}" />
			<html:submit styleId="actionButton">
				<bean:message key="layout.button.edit" />
			</html:submit>
		</html:form>
	</div>
</div>