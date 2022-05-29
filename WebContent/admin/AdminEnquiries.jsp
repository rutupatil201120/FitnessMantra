<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br>
<div class="row">
	<div class="container">
		<h3 class="text-center">Enquiry</h3>
		<hr>
		<br>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Full Name</th>
					<th>Contact No</th>
					<th>Email</th>
					<th>Message</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="Enquiry" items="${Enquiries}">

					<tr>
						<td><c:out value="${Enquiry.id}" /></td>
						<td><c:out value="${Enquiry.fullName}" /></td>
						<td><c:out value="₹${Enquiry.contactNo}" /></td>
						<td><c:out value="₹${Enquiry.email}" /></td>
						<td><c:out value="₹${Enquiry.message}" /></td>
						<td><div class="alert alert-${Enquiry.status}" role="alert">${Enquiry.message}</div></td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>