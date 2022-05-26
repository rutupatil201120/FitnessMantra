<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(function() {
		const errorMessage = '${errorMessage}';
		
		if(errorMessage) {
			const errorField = $('[name=${errorField}]');
			const errorValue = errorField.val();
			const element = errorField.get(0);
			const setMessage = () => {
				element.setCustomValidity(errorField.val() == errorValue ? errorMessage : '');
				element.reportValidity();
			};
			setMessage();
			errorField.on('change', setMessage)
		}
		
		const showPrice = () => {
			const option = $('#planSelect').find(":selected");
			const name = option.text();
			if(name) {
				const price = option.attr('plan-price');
				$("#planNote").text(name + ' will cost ₹' + price + '/month');
			} else {
				$("#planNote").text('Please Select the plan to see price.');
			}
		};
		
		showPrice();
		$('select').on('change', showPrice);
	});
</script>
<br>
<div class="container col-md-5">
	<c:if test="${user.id != null}">
		<form action="<%=request.getContextPath()%>/user/update" method="post">
	</c:if>
	<c:if test="${user.id == null}">
		<form action="<%=request.getContextPath()%>/user/new" method="post"
			oninput='confirmPassword.setCustomValidity(confirmPassword.value != password.value ? "Passwords do not match." : "")'>
	</c:if>

	<h2 style="text-align: center;">
		<c:if test="${user.id != null}">
            			Edit User
            		</c:if>
		<c:if test="${user.id == null}">
            			New User
            		</c:if>
	</h2>

	<c:if test="${user.id != null}">
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
		<label>Contact Number</label> <input type="text"
			value="<c:out value='${user.contactNumber}' />" class="form-control"
			name="contactNumber" required="required">
	</fieldset>

	<fieldset class="form-group">
		<label>Email (Email id will be used as user name in login.)</label> <input
			type="email" value="<c:out value='${user.email}' />"
			class="form-control" name="email" required="required">
	</fieldset>

	<fieldset class="form-group">
		<label>Birth Date</label> <input type="date"
			value="<c:out value='${user.birthDate}' />" class="form-control"
			name="birthDate" required="required">
	</fieldset>

	<fieldset class="form-group">
		<label>Gender</label> <select name="gender" class="form-control"
			required="required">
			<c:forEach var="option" items="${genders}">
				<option value="${option.toLowerCase()}"
					<c:if test="${option.equalsIgnoreCase(user.gender)}">selected</c:if>>${option}</option>
			</c:forEach>
		</select>
	</fieldset>

	<fieldset class="form-group">
		<label>Time Slot</label> <select name="timeSlot" class="form-control"
			required="required">
			<c:forEach var="option" items="${timeSlots}">
				<option value="${option.toLowerCase()}"
					<c:if test="${option.equalsIgnoreCase(user.timeSlot)}">selected</c:if>>${option}</option>
			</c:forEach>
		</select>
	</fieldset>

	<fieldset class="form-group">
		<label>Plan</label> <select name="planId" class="form-control"
			id="planSelect" required="required">
			<option value=""></option>
			<c:forEach var="plan" items="${plans}">
				<option value="${plan.id}" plan-price="${plan.price}"
					<c:if test="${plan.id.equals(user.planId)}">selected</c:if>>${plan.name}</option>
			</c:forEach>
		</select>
		<div class="alert alert-primary mt-1 mb-0" role="alert" id="planNote"></div>
	</fieldset>

	<c:if test="${user.id == null}">
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
			<c:when test="${user.id != null}">
			Update
		</c:when>
			<c:otherwise>
			Save
		</c:otherwise>
		</c:choose>
	</button>

	</form>
</div>