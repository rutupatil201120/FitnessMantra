<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br>
<div class="container col-md-5">
	<form action="<%=request.getContextPath()%>/admin/update-user"
		method="post">

		<h2 style="text-align: center;">Edit User</h2>

		<input type="hidden" name="id" value="<c:out value='${user.id}' />" />

		<fieldset class="form-group">
			<label>First Name</label> <input type="text"
				value="<c:out value='${user.firstName}' />" class="form-control"
				name="firstName" required="required">
		</fieldset>

		<fieldset class="form-group">
			<label>Last Name</label> <input type="text"
				value="<c:out value='${user.lastName}' />" class="form-control"
				name="lastName">
		</fieldset>

		<button type="submit" class="btn btn-success">Save</button>
		&nbsp;&nbsp;&nbsp;&nbsp;<a
			href="<%=request.getContextPath()%>/admin/user-list">Cancel</a>
	</form>
</div>