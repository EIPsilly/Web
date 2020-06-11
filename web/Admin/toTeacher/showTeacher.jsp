<%--
  Created by IntelliJ IDEA.
  User: 29615
  Date: 2020/5/30
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>教师展示</title>
</head>
<body>
<table border="1">
    <tr>
        <td>教师工号</td>
        <td>姓名</td>
        <td>学院</td>
        <td>角色</td>
        <td colspan="2">操作</td>
    </tr>
    <c:forEach var="teacher" items="${teacher}"  varStatus="status">
    <tr>
        <td>${teacher.getTid()}</td>
        <td>${teacher.getTname()}</td>
        <td>${teacher.getTcollege()}</td>
        <td>${teacher.getTrole()}</td>
        <td><a href="modifyTeacher.do?Tid=${teacher.getTid()}&Tname=${teacher.getTname()}&Tcollege=${teacher.getTcollege()}&Trole=${teacher.getTrole()}">修改</a></td>
        <td><a href="DeleteTeacher.do?Tid=${teacher.getTid()}">删除</a></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
