<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title></title>
</head>
<body>
<h2>登陆</h2>

<form action="${pageContext.request.contextPath}/login" method="post">
    <p><input type="text" name="username"></p>
    <p><input type="text" name="password"></p>
    <p><input type="submit" value="登陆"/></p>
</form>

</body>
</html>
