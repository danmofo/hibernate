<%@include file="/WEB-INF/templates/common/taglibs.jsp" %>
<c:choose>
	<c:when test="${empty offer}">
		<div class="alert alert-danger">
			<p>No offer was found for this ID.</p>
		</div>
	</c:when>
	<c:otherwise>
		<c:if test='${success}'>
			<div class="alert alert-success">
				<p>Offer was updated / added successfully.
			</div>
		</c:if>	
		<h1>
			Single offer:
			<c:out value="${offer.id}"></c:out>
		</h1>
		<p>
			Some information about the offer.. <a
				href='<c:url value="/"></c:url>'>Go back</a>
		</p>
		<table class="table">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Description</th>
				<th>Actions</th>
			</tr>
			<tr>
				<td><c:out value="${offer.id}"></c:out></td>
				<td><c:out value="${offer.user.name}"></c:out></td>
				<td><c:out value="${offer.user.email}"></c:out></td>
				<td><c:out value="${offer.text}"></c:out></td>
				<td><a href="<c:url value='/offers/edit/${offer.id}'></c:url>" class="btn btn-default">Edit this offer</a></td>
			</tr>
		</table>
	</c:otherwise>

</c:choose>
