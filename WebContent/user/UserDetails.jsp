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
		
		const userId = '${user.id}';
		if(userId) {
			let editFlag = false;
			const toggleEditMode = () => {
				editFlag = !editFlag;
				$('.form-control').attr('disabled', editFlag);
				$('.edit-toggle').toggle();
			};
			$('#editToggle,#cancel').on('click', toggleEditMode);	
			toggleEditMode();
		}
		
		const showPrice = () => {
			const option = $('#planSelect').find(":selected");
			const name = option.text();
			if(name) {
				const price = option.attr('plan-price');
				$("#planNote").text(name + ' will cost ₹' + price + '/month.');
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

	<c:if test="${user.id != null}">
		<div class="row justify-content-between px-3 edit-toggle"
			style="display: none;">
			<h2 style="text-align: center;">My Details</h2>
			<input type="button" id="editToggle" class="btn btn-primary"
				value="Edit">
		</div>

		<div class="row justify-content-center px-3 edit-toggle">
			<h2 style="text-align: center;">Edit Details</h2>
		</div>

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
		<div class="alert alert-primary mt-1 mb-0" role="alert">
			<ul class="mb-0">
				<li id="planNote"></li>
				<c:choose>
					<c:when test="${user.id != null}">
						<li>Note: Any change in active plan will be applied from next
							month.</li>
					</c:when>
					<c:otherwise>
						<li>Note: Subscribing to a plan before mid of month (15th
							date) will incur full month charges and after will incur half the
							price.</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
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

	<button type="submit" class="btn btn-success edit-toggle">
		<c:choose>
			<c:when test="${user.id != null}">
			Update
		</c:when>
			<c:otherwise>
			Register
		</c:otherwise>
		</c:choose>
	</button>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<c:choose>
		<c:when test="${user.id != null}">
			<a href="#" id="cancel" class="edit-toggle">Cancel</a>
		</c:when>
		<c:otherwise>
			<a href="<%=request.getContextPath()%>">Cancel</a>
		</c:otherwise>
	</c:choose>

	</form>
</div>