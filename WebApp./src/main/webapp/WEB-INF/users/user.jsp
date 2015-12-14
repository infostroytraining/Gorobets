<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Users Log In Page</title>
</head>
<body>







<form action="" method="POST">
    <p>
        <label for="email">Email</label>
        <input type="text" id="email" name="email" value=""/>
    </p>

    <p>
        <label for="password">Password</label>
        <input type="text" id="password" name="password" value=""/>
    </p>

    <p>

    <p>
        <label for="name">User name</label>
        <input type="text" id="name" name="name" value=""/>
    </p>

    <p>
        <label for="surname">User surname</label>
        <input type="text" id="surname" name="surname" value=""/>
    </p>

    <input type="submit" value="Add user">
</form>

</body>
</html>