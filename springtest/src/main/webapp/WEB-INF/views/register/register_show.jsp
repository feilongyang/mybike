<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>用户注册页面</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>

<%--
    1，如果这个form没有写action，那么它就会被提交到当前的URL上，比如，如果当前的url
    是A，则这个表单的数据就会被提交到URL为A的post请求

    2，modelAttribute属性表示：把这个表单的输入数据绑定到名称为user的对象上
--%>
<sf:form method="post" modelAttribute="user" enctype="multipart/form-data">

    <p>
        用户名：
        <sf:input path="username"/><br/>
        <sf:errors path="username" cssClass="error"/>
    </p>

    <p>密码：<sf:password path="password"/><br/>
        <sf:errors path="password" cssClass="error"/>
    </p>

    <p>
        <input name="image" type="file"/>
    </p>

    <p><input type="submit" value="注册"/></p>

</sf:form>

</body>
</html>
