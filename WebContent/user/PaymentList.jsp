<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br>
<div class="row">
	<div class="container">
		<h3 class="text-center">Payment History</h3>
		<hr>
		<br>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Description</th>
					<th>Amount</th>
					<th>Payment Status</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="payment" items="${payments}">

					<tr>
						<td><c:out value="${payment.id}" /></td>
						<td><c:out value="${payment.description}" /></td>
						<td><c:out value="₹${payment.amount}" /></td>
						<td><div class="alert alert-${payment.status}" role="alert">${payment.message}</div></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>