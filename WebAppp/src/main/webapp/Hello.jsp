<%@include file="WEB-INF/include/main.jsp" %>
<html>
<head>
    <title>Hello</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.js"></script>
    <script src="resources/js/addRemoveUsersAndErrors.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="form-group">
            <div class="col-sm-1 col-sm-8">
                <h1>Hello ${user.name} ,Thank you for visiting as! </h1>
            </div>
        </div>
    </div>
</div>


</body>
</html>
