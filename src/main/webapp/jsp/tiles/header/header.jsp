<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<div class="header">
	<span id="headertitle"> <bean:message key="layout.lable.title" />
	</span>
	<div id="langbar">
		<html:link action="/locale.do?language=en">
			<bean:message key="layout.button.english" />
		</html:link>

		<html:link action="/locale.do?language=ru">
			<bean:message key="layout.button.russian" />
		</html:link>
	</div>
</div>

