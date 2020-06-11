<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生查询</title>
</head>
<body>
<form action="SearchStudent.do" method="post">
    请输入学生姓名或学号（支持模糊查询）：
    <input type="text" name="Sname" size="20"> <input type="submit" value="确定">
</form>
<p><a href="AllStudent.do">查询所有学生</a></p>
</body>
</html>
