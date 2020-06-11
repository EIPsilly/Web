<%--
  Created by IntelliJ IDEA.
  User: 29615
  Date: 2020/5/30
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师查询</title>
</head>
<body>
<form action="SearchTeacher.do" method="post">
    请输入教师姓名或工号（支持模糊查询）：
    <input type="text" name="Tname" size="20"> <input type="submit" value="确定">
</form>
<p>
    <a href="AllTeacher.do">查询所有教师</a>
</p>
</body>
</html>
