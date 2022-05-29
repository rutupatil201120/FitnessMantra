<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br>
<div class="row">
	<div class="container">
		<div class="row justify-content-between px-3">
			<h3 class="text-center">List of Plans</h3>
			<a href="<%=request.getContextPath()%>/admin/new-plan">Add New</a>
		</div>
		<hr>
		<br>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Price</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="plan" items="${plans}">

					<tr>
						<td><c:out value="${plan.id}" /></td>
						<td><c:out value="${plan.name}" /></td>
						<td><c:out value="${plan.price}" /></td>
						<td><a
							href="<%=request.getContextPath()%>/admin/update-plan?id=<c:out value='${plan.id}' />">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="<%=request.getContextPath()%>/admin/delete-plan?id=<c:out value='${plan.id}' />">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>