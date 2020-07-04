<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/7/4
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>二维码展示页面</title>
</head>
<body>
    <c:if test="${sessionScope.codename == null}" var = "check">
        <script>
            alert("请先申报健康码");
            window.location.href = "control.jsp";
        </script>
    </c:if>
    ${sessionScope.codename}
</body>
</html>
