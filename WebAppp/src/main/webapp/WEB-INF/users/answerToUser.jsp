<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/include/main.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thank you</title>
</head>
<body>
${user.surname} , thanks for login!



<table>

	<p>
		User email:<tr><td>${user.email}</td></tr>
	</p>
	<p>
		User password:<tr><td>${user.password}</td></tr>
	</p>
	<p>
		User name:<tr><td>${user.name}</td></tr>

	</p>
	<p>
		User surname:<tr><td>${user.surname}</td></tr>
	</p>
	<%--<c:forEach var="entry" items="${statisticMap}">--%>
		<%--<tr>--%>
			<%--<td>${entry.key}</td>--%>
			<%--<td>${entry.value} </td>--%>
		<%--</tr>--%>
	<%--</c:forEach>--%>
</table>

</body>
</html>