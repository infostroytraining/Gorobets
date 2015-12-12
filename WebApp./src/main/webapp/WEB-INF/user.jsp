<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/main.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thank you</title>
</head>
<body>
${surname} , thanks for login!

<p>
	User email:
</p>

<table>
	<tr>
		<th>Email</th>
		<th>User Surname</th>
	</tr>
	
	<c:forEach var="entry" items="${statisticMap}">
		<tr>
			<td>${entry.key}</td>
			<td>${entry.value}</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>