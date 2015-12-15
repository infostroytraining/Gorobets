<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.captcha.Captchas" %>
<%@include file="/WEB-INF/include/main.jsp" %>
<head>
    <title>Home page</title>
</head>
<body>





<form action="/registration" method="post">
    <p>
        <label for="email">Email</label>
        <input type="text" id="email" name="email" value="${userDTO.email}"/>
    </p>

    <p>
        <label for="password">Password</label>
        <input type="text" id="password" name="password" value="${userDTO.password}"/>
    </p>

    <p>

    <p>
        <label for="name">User name</label>
        <input type="text" id="name" name="name" value="${userDTO.name}"/>
    </p>

    <p>
        <label for="surname">User surname</label>
        <input type="text" id="surname" name="surname" value="${userDTO.surname}"/>
    </p>

    <%--<p>--%>
        <%--<label for="avatar"> Avatar</label>--%>
        <%--<input type="image" id="avatar" name="avatar" value="${userDTO.avatar}"/>--%>
    <%--</p>--%>

        <%
// Construct the captchas object (Default Values)
  Captchas captchas = new Captchas(
          request.getSession(true),     // Ensure session
          "demo",                       // client
          "secret"                      // secret
  );

%>
    <%--
     % encodeUrl produces jsessionid=xyz in case of disabled cookies
     % Please test your implementation also with disabled cookies <%=response.encodeUrl("captchaCheck.jsp")%>
     --%>
    <form method="get" action="<%=response.encodeUrl("captchaCheck.jsp")%>">
        <table>
            <tr>
                <td>
                    Your message:</td><td><input name="message" size="60" />
            </td>
            </tr>
            <tr>
                <td>
                    The CAPTCHA password:
                </td>
                <td>
                    <input name="passwordc" size="16" />
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <%--
                     % it's possible to set a  random in captchas.image("xyz"),
                     % captchas.imageUrl("xyz") and captchas.audioUrl("xyz").
                     % This is only needed at the first request
                     --%>
                    <%= captchas.image() %><br>
                    <a href="<%= captchas.audioUrl() %>">Phonetic spelling (mp3)</a>
                </td>
            </tr>

            <tr>
                <td>
                </td>
                <td>
                    <input type="submit" value="Submit" />
                </td>
            </tr>
        </table>
        </form>

    <%--<input type="submit" value="Add user">--%>
</form>
<form action="/login" method="get">
    <table>
        <tr>
            <td>
            </td>
            <td>
                <input type="submit" value="LogIn" />
            </td>
        </tr>
    </table>
</form>
<%--<jsp:forward page="/login"/>--%>
</body>
</html>