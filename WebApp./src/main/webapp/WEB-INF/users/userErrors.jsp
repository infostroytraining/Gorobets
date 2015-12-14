<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/include/main.jsp" %>
<head>
    <title>Users errors</title>
</head>
<body>


<%--<c:forEach items="${errors}" var="error">--%>
    <%--<font color="red">${error}</font>--%>
<%--</c:forEach>--%>

<form action="" method="POST">
    <p>
        <label for="email">Email</label>
        <input type="text" id="email" name="email" value="${user.email}"/>
    </p>

    <p>
        <label for="password">Password</label>
        <input type="text" id="password" name="password" value="${user.password}"/>
    </p>

    <p>

    <p>
        <label for="name">User name</label>
        <input type="text" id="name" name="name" value="${user.name}"/>
    </p>

    <p>
        <label for="surname">User surname</label>
        <input type="text" id="surname" name="surname" value="${user.surname}"/>
    </p>

    <%--<p>--%>
        <%--<label for="avatar">Avatar</label>--%>
        <%--<input type="image" id="avatar" name="avatar" value="${userDTO.avatar}"/>--%>
    <%--</p>--%>

    <input type="submit" value="Add user">
</form>

</body>
</html>