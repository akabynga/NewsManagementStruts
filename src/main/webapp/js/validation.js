function validateNewsMessage(formName) {

	var form = document.forms[formName];
	var message = "";
	var index = 1;
	var titleMaxLength = 100;
	var briefMaxLength = 500;
	var contentMaxLength = 1024;
	var date_pattern = "^(20[0-9][0-9])-(0[1-9]|[10-12])-(0[1-9]|[10-31])$";

	var element = null;
	element = form.elements["newsMessage.title"];
	if (markIfEmpty(element)) {
		message += index++ + ") " + msgTitleRequired + "<br>";
	} else if (markIfBigger(element, titleMaxLength)) {
		message += index++ + ") " + msgTitleIsBiger + "<br>";
	}
	element = form.elements["newsMessage.currentDate"];
	if (markIfEmpty(element)) {
		message += index++ + ") " + msgDateRequired + "<br>";
	} else if (markIfInvalid(date_pattern, element.value, element)) {
		message += index++ + ") " + msgDateFormat + "<br>";
	}
	element = form.elements["newsMessage.brief"];
	if (markIfEmpty(element)) {
		message += index++ + ") " + msgBriefRequired + "<br>";
	} else if (markIfBigger(element, briefMaxLength)) {
		message += index++ + ") " + msgBriefIsBiger + "<br>";
	}
	element = form.elements["newsMessage.content"];
	if (markIfEmpty(element)) {
		message += index++ + ") " + msgContentRequired + "<br>";
	} else if (markIfBigger(element, contentMaxLength)) {
		message += index++ + ") " + msgContentIsBiger + "<br>";
	}
	errorMessage(form, message, formName);
	if (message) {
		return false;
	} else {
		return true;
	}
}
function markIfInvalid(regexp, testValue, element) {
	var dateRegExp = new RegExp(regexp);
	if (!dateRegExp.test(testValue)) {
		element.className = "invalid";
		return true;
	} else {
		element.className = "";
		return false;
	}
}
function markIfEmpty(element) {
	if (!element.value) {
		element.className = "invalid";
		return true;
	} else {
		element.className = "";
		return false;
	}
}
function markIfBigger(element, maxLength) {
	if (element.value.length > maxLength) {
		element.className = "invalid";
		return true;
	} else {
		element.className = "";
		return false;
	}
}
function errorMessage(element, message, formName) {
	var form = document.forms[formName];
	var errorMessage = document.getElementById("error-message");
	if (errorMessage) {
		form.removeChild(errorMessage);
	}
	if (message) {
		var errorElement = document.createElement('div');
		errorElement.id = 'error-message';
		errorElement.innerHTML = message;
		element.insertBefore(errorElement, null);
	}
}

function validateRemove(form) {
	var checkbox;
	for (var i = 0; i < form.elements.length; i++) {
		checkbox = form.elements[i];
		if (checkbox.checked) {
			return confirm(msgRemoveGroupConfirmMessage);
		}
	}
	alert(msgNothingNewsForDelete);
	return false;
}