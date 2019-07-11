<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<div class="container" style="margin-top: 50px">
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>
		
		<div class="row md-col-8">
			<h2>Search From</h2>
			<spring:url value="/${student.id}" var="searchActionUrl" />
			<form action="searchActionUrl" method="GET" 
				class="form-horizontal">
				<table>
					<tr>
						<%-- <th>Name&nbsp;&nbsp;</th>
						<td><form:input path="name" cssClass="form-control" /> <form:errors
								path="name" cssClass="error" /></td> --%>
						<th style="padding-left: 28px;">Name Or Email: &nbsp;&nbsp;</th>
						<td><input name="info" class="form-control" value="${info}"/> <errors
								name="info" class="error" /></td>
						<td style="padding-left: 28px;"><button type="submit">Search</button></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="row" style="margin-top: 20px">
			<div class="md-col-8">
				<fieldset>
					<legend>
						<h2>Student List</h2>
					</legend>
					<table class="table table-hover table-dark">
						<thead class="bg-primary">
							<tr>
								<th scope="col">#</th>
								<th scope="col">Email</th>
								<th scope="col">Username</th>
								<th scope="col">Gender</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${students}" var="student" varStatus="count">
								<tr>
									<th scope="row">${count.index + 1}</th>
									<td>${student.email}</td>
									<td>${student.name}</td>
									<td>
										 <c:if test="${student.gender == 1}" >
										     Fmale
										 </c:if>
										 <c:if test="${student.gender == 0}" >
										     Male
										 </c:if>
										 
										 <c:if test="${student.gender != 0 && student.gender != 1}" >
										 	N/A
										 </c:if>
									</td>
									<td>
										<spring:url value="/${student.id}" var="detailActionUrl" />
										<button class="btn btn-info"
											onclick="location.href='${detailActionUrl}'">Detail</button>
											
										<spring:url value="/${student.id}/edit" var="editActionUrl" />
										<button class="btn btn-warning"
											onclick="location.href='${editActionUrl}'">Edit</button>
											
										<spring:url value="/${student.id}/delete" var="deleteActionUrl" />
										<button class="btn btn-danger"
											onclick="location.href='${deleteActionUrl}'">Delete</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</fieldset>
			</div>
		</div>
	</div>
