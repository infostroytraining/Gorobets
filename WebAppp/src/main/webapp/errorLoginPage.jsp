<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/include/main.jsp" %>


<head>
  <title>Users Log In</title>
</head>
<body>

<c:forEach items="${errors}" var="error">
<font color="red">${error}</font>
</c:forEach>

<form action="/login" method="post">
  <p>
    <label for="email">Email</label>
    <input type="text" id="email" name="email" value="${user.email}"/>
  </p>

  <p>
    <label for="password">Password</label>
    <input type="text" id="password" name="password" value="${user.password}"/>
  </p>


  <table>
    <tr>
      <td>
      </td>
      <td>
        <input type="submit" value="LogIn" />
      </td>
    </tr>
  </table>
</form>
