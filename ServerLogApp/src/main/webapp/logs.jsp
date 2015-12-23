
<%@include file="WEB-INF/include/head.jsp" %>
<html>
<head>
    <title>LOGS</title>
</head>
<body>
<form action="/logs" method="GET">
  <p>
    <label for="logValue">LogsFormat :</label>
    <input type="text" id="logValue" name="logValue" value="${logValue}"/>
  </p>
</form>
</body>
</html>
