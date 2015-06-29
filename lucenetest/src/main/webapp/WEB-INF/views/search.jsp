<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
<h2>爆米花搜索测试</h2>


<form action="/lucenetest/search">
    <p><input name="keyword" type="text"/></p>
    <p><input type="submit" value="搜索"/></p>
</form>


<div>
    <p>这里是搜索的结果页面</p>
    <c:forEach var="vi" items="${list}">
        <p>${vi.videoName}</p>
    </c:forEach>
</div>


</body>
</html>
