<%@include file="WEB-INF/include/head.jsp" %>
<head>
    <title>Home Page</title>
</head>
<body>
<div class="container">

    <div class="row">
        <form id="format-form" class="form-horizontal"  action="/home" method="POST">

            <div id="form-format" class="form-group">
                <label class="col-sm-1 control-label" for="format">Add logsFormat :</label>

                <div id="format" class="col-sm-8">
                    <input type="text" name="format" class="form-control" value="${format}"
                           placeholder="format"/>
                </div>
            </div>


            <div class="row">
                <div id="logEvents" class=" form-group ">
                    <label class="col-sm-1 control-label" for="logEvents"><h4><b>All logs:</b></h4></label>
                </div>
                <table id="content" class="table table-bordered">
                    <tr>
                        <th>Logs</th>

                    </tr>
                    <c:forEach var="logEvent" items="${logEventsJson}">
                        <tr>
                            <td>${logEvent}</td>

                        </tr>
                    </c:forEach>
                </table>

            </div>
            <div class="row">
                <div id="logEventsSTR" class=" form-group ">
                    <label class="col-sm-1 control-label" for="logEventsSTR"><h4><b>All logs:</b></h4></label>
                </div>
                <table id="contents" class="table table-bordered">
                    <tr>
                        <th>Logs</th>

                    </tr>
                    <c:forEach var="logEvent" items="${logEventsString}">
                        <tr>
                            <td>${logEvent}</td>

                        </tr>
                    </c:forEach>
                </table>

            </div>


            <div class="col-sm-offset-1 col-sm-8">
                <input class="btn btn-info btn-sm format" type="button" value="Push">
            </div>

        </form>
    </div>
</div>

</body>
</html>