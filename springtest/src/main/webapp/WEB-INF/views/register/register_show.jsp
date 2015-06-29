<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>用户注册页面</title>
</head>
<body>

<sf:form method="post" modelAttribute="user">

    <p>用户名：
        <sf:input path="username" id="username" size="15"/>
        <%--<sf:errors path="username" cssClass="error"/>--%>
    </p>

    <p>密码：<sf:password path="password" id="password" size="15"/></p>

    <p><input type="submit" value="注册"/></p>

</sf:form>

</body>
</html>
