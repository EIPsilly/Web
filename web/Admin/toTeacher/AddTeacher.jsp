<%--
  Created by IntelliJ IDEA.
  User: 29615
  Date: 2020/5/30
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加教师信息</title>
</head>
<body>
<font color=red>${result}</font>
<p>请输入一条教师信息</p>
<form action = "AddTeacher.do" method = "post">
    <table>
            <tr><td>教师工号：</td> <td><input type="text" name="Tid"></td></tr>
            <tr><td>姓名：</td> <td><input type="text" name="Tname"></td></tr>
            <tr><td>学院：</td><td><input type="text" name="Tcollege"></td></tr>
            <tr><td>角色：</td><td><input type="text" name="Trole"></td></tr>
            <tr><td><input type="submit" value="确定" ></td><td><input type="reset" value="重置" ></td></tr>
    </table>
</form>
</body>
</html>
