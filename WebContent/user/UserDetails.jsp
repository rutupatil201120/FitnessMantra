<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br>
<div class="container col-md-5">
	<c:if test="${user != null}">
		<form action="<%=request.getContextPath()%>/user/update" method="post">
	</c:if>
	<c:if test="${user == null}">
		<form action="<%=request.getContextPath()%>/user/new" method="post"
			oninput='confirmPassword.setCustomValidity(confirmPassword.value != password.value ? "Passwords do not match." : "")'>
	</c:if>

	<h2 style="text-align: center;">
		<c:if test="${user != null}">
            			Edit User
            		</c:if>
		<c:if test="${user == null}">
            			New User
            		</c:if>
	</h2>

	<c:if test="${user != null}">
		<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
	</c:if>

	<fieldset class="form-group">
		<label>First Name</label> <input type="text"
			value="<c:out value='${user.firstName}' />" class="form-control"
			name="firstName" required="required">
	</fieldset>

	<fieldset class="form-group">
		<label>Last Name</label> <input type="text"
			value="<c:out value='${user.lastName}' />" class="form-control"
			name="lastName" required="required">
	</fieldset>

	<fieldset class="form-group">
		<label>Email (Email id will be used as user name in login.)</label> <input
			type="email" value="<c:out value='${user.email}' />"
			class="form-control" name="email" required="required">
	</fieldset>

	<c:if test="${user == null}">
		<fieldset class="form-group">
			<label>Password</label> <input type="password" class="form-control"
				name="password" required="required">
		</fieldset>

		<fieldset class="form-group">
			<label>Confirm Password</label> <input type="password"
				class="form-control" name="confirmPassword" required="required">
		</fieldset>
	</c:if>

	<button type="submit" id="submit" onclick="submitCheck"
		class="btn btn-success">
		<c:choose>
			<c:when test="${user != null}">
			Update
		</c:when>
			<c:otherwise>
			Save
		</c:otherwise>
		</c:choose>
	</button>

	</form>
</div>