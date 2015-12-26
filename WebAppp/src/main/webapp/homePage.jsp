<%@ page import="com.captcha.Captchas" %>
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

    <%--<div class="row">--%>
        <%--<form action="/login" method="get">--%>
            <%--<div class="form-group">--%>
                <%--<div class="col-sm-offset-1 col-sm-8">--%>
                    <%--<input class="btn btn-info btn-sm login-user" type="button" value="Log in">--%>
                    <%--<br>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</form>--%>
    <%--</div>--%>

    <div class="row">
        <form id="user-form" class="form-horizontal">

            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-8">
                    <input class="btn btn-info btn-sm login-user" type="button" value="Log in">
                    <br>
                </div>
            </div>

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
            <div id="form-passwordc" class="form-group ">
                <label class="col-sm-1 control-label" for="passwordc">The CAPTCHA password:</label>
                <%
                    // Construct the captchas object (Default Values)
                    Captchas captchas = new Captchas(
                            request.getSession(true),     // Ensure session
                            "demo",                       // client
                            "secret"                      // secret
                    );
                %>
                <%--<form method="get" action="<%=response.encodeUrl("captchaCheck.jsp")%>">--%>


                <div id="passwordc" class="col-sm-8">
                    <input type="text" id="passwordc" name="passwordc" class="form-control"
                           value="${passwordc}"
                           placeholder="Are you a robot?" size="16"/>
                </div>

                <div class=" col-sm-4">

                    <%--
                    % it's possible to set a  random in captchas.image("xyz"),
                    % captchas.imageUrl("xyz") and captchas.audioUrl("xyz").
                    % This is only needed at the first request
                    --%>
                    <%= captchas.image() %><br>
                    <a href="<%= captchas.audioUrl() %>">Phonetic spelling (mp3)</a>
                </div>

            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-8">
                    <input class="btn btn-primary add-user" type="button" value="Add user">
                </div>
            </div>
        </form>
    </div>



<%--<div class="row">--%>
<%--<form action="/checkCaptcha" method="get" id="captcha-form" class="form-horizontal">--%>
<%--<div class="form-group">--%>
<%--<%--%>
<%--// Construct the captchas object (Default Values)--%>
<%--Captchas captchas = new Captchas(--%>
<%--request.getSession(true),     // Ensure session--%>
<%--"demo",                       // client--%>
<%--"secret"                      // secret--%>
<%--);--%>
<%--%>--%>
<%--<form method="get" action="<%=response.encodeUrl("captchaCheck.jsp")%>">--%>

<%--<div id="form-captcha" class="form-group ">--%>
<%--<label class="col-sm-1 control-label" for="name">The CAPTCHA password:</label>--%>

<%--<div id="passwordc" class="col-sm-8">--%>
<%--<input type="text" id="passwordc" name="passwordc" class="form-control"--%>
<%--value="${passwordc}"--%>
<%--placeholder="Are you a robot?" size="16"/>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<div class=" col-sm-4">--%>

<%--&lt;%&ndash;--%>
<%--% it's possible to set a  random in captchas.image("xyz"),--%>
<%--% captchas.imageUrl("xyz") and captchas.audioUrl("xyz").--%>
<%--% This is only needed at the first request--%>
<%--&ndash;%&gt;--%>
<%--<%= captchas.image() %><br>--%>
<%--<a href="<%= captchas.audioUrl() %>">Phonetic spelling (mp3)</a>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<div class="col-sm-offset-1 col-sm-8">--%>
<%--<input class="btn btn-primary captcha" type="button" value="Submit">--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<%--</form>--%>
<%--</div>--%>


<div class="row">
    <div id ="users" class=" form-group ">
        <label class="col-sm-1 control-label" for="users"><h4><b>All users:</b></h4></label>
        <%--<h4><b>All users:</b></h4>--%>
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
</div>


<!-- Uncomment to use modal window
    <div id="myModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Confirmation</h4>
                </div>
                <div class="modal-body">
                    <p>Do you really want to delete this answer?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" id="" class="btn btn-primary remove" data-dismiss="modal">Delete</button>
                </div>
            </div>
        </div>
    </div>
 -->
</body>
</html>