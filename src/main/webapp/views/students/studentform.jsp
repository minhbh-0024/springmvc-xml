<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container" style="margin: 0 auto;">

	<c:choose>
		<c:when test="${status == 'add'}">
			<h1>Add User</h1>
		</c:when>
		<c:otherwise>
			<h1>Update User</h1>
		</c:otherwise>
	</c:choose>
	<br />
	<div class="col-md-6"  style="margin: 0 auto;">
		<spring:url value="/student" var="userActionUrl" />
		<form:form class="form-horizontal" method="post" 
			modelAttribute="studentForm" action="${userActionUrl}">
			<form:hidden path="id" />
			<div class="form-group">
				<label class="col-sm-2 control-label">Name</label>
				<div class="col-sm-10">
					<form:input path="name" type="text" class="form-control " id="name" placeholder="Name" />
					<form:errors path="name" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<form:input path="email" class="form-control" id="email" placeholder="Email" />
					<form:errors path="email" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Sex</label>
				<div class="col-sm-10">
					<label class="radio-inline"> <form:radiobutton path="gender" value="0" /> Male
					</label>
					<label class="radio-inline"> <form:radiobutton path="gender" value="1" /> Female
					</label>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<c:choose>
						<c:when test="${status == 'add'}">
							<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
						</c:when>
						<c:otherwise>
							<button type="submit" class="btn-lg btn-primary pull-right">Update</button>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</div>
