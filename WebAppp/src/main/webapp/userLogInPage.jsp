<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="WEB-INF/include/main.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.js"></script>
    <script src="resources/js/addRemoveUsersAndErrors.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>

<div class="container">

    <div class="row">
        <form id="login-form" class="form-horizontal" action="/login" method="post">

            <div id="form-email" class="form-group">
                <label class="col-sm-1 control-label" for="email">Email</label>

                <div id="email" class="col-sm-8">
                    <input type="text" id="email" name="email" class="form-control" value="${user.email}"
                           placeholder="email"/>
                </div>
            </div>

            <div id="form-password" class="form-group ">
                <label class="col-sm-1 control-label" for="password">Password</label>

                <div id="password" class="col-sm-8">
                    <input type="text" id="password" name="password" class="form-control" value="${user.password}"
                           placeholder="password"/>
                </div>
            </div>
            <div class="col-sm-offset-1 col-sm-8">
                <input class="btn btn-info btn-sm login-user" type="button" value="Log in">
            </div>

        </form>
    </div>
</div>
</form>
</body>
</html>