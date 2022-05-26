<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<title>Fitness Mantra</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/img/fevicon.png">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/all.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>
<style>
* {
	margin: 0;
	padding: 0;
	list-style: none;
	text-decoration: none;
}

body {
	font-family: sans-serif;
	user-select: none;
	background: black
}

nav .logo {
	color: white;
	font-size: 33px;
	font-weight: bold;
	line-height: 70px;
	padding-left: 110px;
}

nav {
	height: 70px;
	background: #063247;
	box-shadow: 0 3px 15px rgba(0, 0, 0, .4);
}

nav ul {
	float: right;
	margin-right: 30px;
}

nav ul li {
	display: inline-block;
}

nav ul li a {
	color: white;
	display: block;
	padding: 0 15px;
	line-height: 70px;
	font-size: 20px;
	background: #063247;
	transition: .5s;
}

nav ul li a:hover, nav ul li a.active, body, .table {
	color: white;
}

nav ul ul {
	position: absolute;
	top: 85px;
	border-top: 3px solid #23dbdb;
	opacity: 0;
	visibility: hidden;
}

nav ul li:hover>ul {
	top: 70px;
	opacity: 1;
	visibility: visible;
	transition: .3s linear;
}

nav ul ul li {
	width: 150px;
	display: list-item;
	position: relative;
	border: 1px solid #042331;
	border-top: none;
}

nav ul ul li a {
	line-height: 50px;
}

nav ul ul ul {
	border-top: none;
}

nav ul ul ul li {
	position: relative;
	top: -70px;
	left: 150px;
}

nav ul ul li a i {
	margin-left: 45px;
}

section {
	background: url(${pageContext.request.contextPath}/img/background.jpeg);
	background-repeat: no-repeat;
	background-size: cover;
	height: 100vh;
	background-position: center;
}
</style>
<body>
	<nav>
		<ul>
			<li><a href="<%=request.getContextPath()%>/admin/user-list">User
					Details</a></li>
			<li><a href="#">Payment History</a></li>
			<li><a href="#">Equipment</a></li>
			<li><a href="<%=request.getContextPath()%>/admin/plan-list">Plans</a></li>
			<li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
		</ul>
	</nav>
	<section>
		<c:if test="${page != null}">
			<jsp:include page="${page}" />
		</c:if>
		<c:if test="${message != null}">
			<div class="alert alert-danger" role="alert">${message}</div>
		</c:if>
	</section>
</body>
</html>