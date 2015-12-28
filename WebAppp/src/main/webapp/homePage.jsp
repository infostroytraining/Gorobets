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
<%--<div class="container">--%>
    <%--<div class="row">--%>


        <%--<div class="login-form">--%>
            <%--<div class="col-sm-offset-1 col-sm-8">--%>
                <%--<a class="log-in" href="http://localhost:8080/login">Log in</a>--%>

            <%--</div>--%>
        <%--</div>--%>

    <%--</div>--%>
<%--</div>--%>
<div class="container">
    <div class="row">
        <form id="user-form" class="form-horizontal" >


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

            <div id="form-name" class="form-group ">
                <label class="col-sm-1 control-label" for="name">Name</label>

                <div id="name" class="col-sm-8">
                    <input type="text" id="name" name="name" class="form-control" value="${user.name}"
                           placeholder="name"/>
                </div>
            </div>

            <div id="form-surname" class="form-group ">
                <label class="col-sm-1 control-label" for="surname">Surname</label>

                <div id="surname" class="col-sm-8">
                    <input type="text" id="surname" name="surname" class="form-control" value="${user.surname}"
                           placeholder="surname"/>
                </div>
            </div>

            <div id="form-code" class="form-group ">
                <label class="col-sm-1 control-label" for="code">The CAPTCHA password:</label>

                <div id="code" class="col-sm-8">
                    <input type="text" id="code" name="code" class="form-control" placeholder="Are you a robot?"/>
                </div>
                <br>

                <div class=" col-sm-4">
                    <img src="http://localhost:8080/captcha">
                </div>


            </div>

            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-8">
                    <input class="btn btn-primary add-user" type="button" value="Add user">
                </div>
            </div>
        </form>
    </div>

</div>
<div class="row">
    <div id="users" class=" form-group ">
        <label class="col-sm-1 control-label" for="users"><h4><b>All users:</b></h4></label>
    </div>
    <table id="content" class="table table-bordered">
        <tr>
            <th>Email</th>
            <th>Password</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Action</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr id="${user.id}">
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.name}</td>
                <td>${user.sername}</td>
                <td><a id="${user.id}" class="remove-user">remove</a></td>
            </tr>
        </c:forEach>
    </table>

</div>

<br><br>

</body>
</html>