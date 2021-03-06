<%@include file="/WEB-INF/templates/common/taglibs.jsp" %>
<h1>User listing</h1>
<p>These are the users</p>
<table class="table">
	<tr>
		<th>Username</th>
		<th>Email</th>
		<th>Authority</th>
		<th>Enabled</th>
		<th>Actions</th>
	</tr>
	<c:forEach var="user" items="${users}">
		<tr class="row-${user.enabled}">
			<td><c:out value="${user.username}"></c:out></td>
			<td><c:out value="${user.email}"></c:out></td>
			<td><c:out value="${user.authority}"></c:out></td>
			<td><c:out value="${user.enabled}"></c:out></td>
			<c:url value="/admin/users/edit/${user.username}" var="userEditUrl" />
			<td><a
				href='<c:out value="${userEditUrl}"></c:out>'
				class="btn btn-default">Edit</a></td>
		</tr>
	</c:forEach>

</table>
