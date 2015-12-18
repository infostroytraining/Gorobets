
<%@include file="WEB-INF/include/head.jsp" %>
<html>
<head>
    <title>LOGS</title>
</head>
<body>
<form action="/logs" method="GET">
  <p>
    <label for="logEvent">LogsFormat :</label>
    <input type="text" id="logEvent" name="logEvent" value="${logEvent}"/>
  </p>
</form>
</body>
</html>
