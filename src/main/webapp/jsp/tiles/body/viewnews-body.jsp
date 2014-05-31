<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="page" value="page.viewnews" scope="session" />
<html:form action="/news">
	<html:hidden property="method" value="none" />
	<html:hidden property="newsId" value="${newsForm.newsMessage.id}" />
	<div class="message">
		<table>
			<col width="150px">
			<col>
			<tr>
				<td><b><bean:message key="layout.form.title" /></b></td>
				<td>${newsForm.newsMessage.title}</td>
			</tr>
			<tr>
				<td><b><bean:message key="layout.form.newsdate" /></b></td>
				<td>${newsForm.newsMessage.currentDate}</td>
			</tr>
			<tr>
				<td><b><bean:message key="layout.form.brief" /></b></td>
				<td class="textfield">${newsForm.newsMessage.brief}</td>
			</tr>
			<tr>
				<td><b><bean:message key="layout.form.content" /></b></td>
				<td class="textfield">${newsForm.newsMessage.content}</td>
			</tr>
		</table>
		<p style="width: 100%; text-align: right">
			<html:link action="/news.do?method=edit">
				<bean:message key="layout.button.edit" />
			</html:link>
			<bean:define id="id" property="newsId"
				value="${newsForm.newsMessage.id}" />
			<html:link action="/news.do?method=delete"
				onclick="return confirm(msgRemoveGroupConfirmMessage);"
				paramId="newsId" paramName="id">
				<bean:message key="layout.button.delete" />
			</html:link>
		</p>
	</div>
</html:form>