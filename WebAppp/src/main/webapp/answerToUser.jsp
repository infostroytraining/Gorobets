<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/include/main.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User account</title>
</head>
<body>
${userDTO.surname} , thanks for log in!



<table>

	<p>
		User email:<tr><td>${userDTO.email}</td></tr>
	</p>
	<%--<p>--%>
		<%--User password:<tr><td>${userDTO.password}</td></tr>--%>
	<%--</p>--%>
	<p>
		User name:<tr><td>${userDTO.name}</td></tr>

	</p>
	<p>
		User surname:<tr><td>${userDTO.surname}</td></tr>
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