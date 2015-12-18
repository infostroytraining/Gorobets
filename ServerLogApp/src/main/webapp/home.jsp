<%@include file="WEB-INF/include/head.jsp" %>
<head>
    <title>Home Page</title>
</head>
<body>

<form action="/home" method="POST">
    <p>
        <label for="format">Add logsFormat :</label>
        <input type="text" id="format" name="format" value="${format}"/>
    </p>

    <p>
    <table>

        <tr>
            <c:forEach var="logEvent" items="logEventsJson">
                <td>
                    <font color="#a52a2a"> ${logEventsJson}</font>
                </td>
            </c:forEach>
        </tr>
        <tr>
            <c:forEach var="logEvent" items="logEventsString">
                <td>
                    <font color="#a52a2a"> ${logEventsString}</font>
                </td>
            </c:forEach>

        </tr>

    </table>
    </p>
    <input type="submit" value="Show logEvents">
</form>
</body>
</html>