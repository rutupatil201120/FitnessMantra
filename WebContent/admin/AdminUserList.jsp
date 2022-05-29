<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br>
<div class="row">
	<div class="container">
		<h3 class="text-center">List of Users</h3>
		<hr>
		<br>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="user" items="${users}">

					<tr>
						<td><c:out value="${user.id}" /></td>
						<td><c:out value="${user.firstName}" /></td>
						<td><c:out value="${user.lastName}" /></td>
						<td><c:out value="${user.email}" /></td>
						<td><c:if test="${user.active}">
								<a
									href="<%=request.getContextPath()%>/admin/update-user?id=<c:out value='${user.id}' />">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if> <a
							href="<%=request.getContextPath()%>/admin/update-user-status?id=<c:out value='${user.id}' />&status=<c:out value='${!user.active}' />">
								<c:choose>
									<c:when test="${user.active}">
										Deactivate
									</c:when>
									<c:otherwise>
										Activate
									</c:otherwise>
								</c:choose>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>