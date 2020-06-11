<%--
  Created by IntelliJ IDEA.
  User: 29615
  Date: 2020/5/30
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师修改</title>
</head>
<body>
<font color=red>${message}</font>
<p>修改教师信息</p>
<form action = "modifyTeacherInfo.do" method = "post">
    <table>
        <tr><td></td> <td><input type="hidden" name="Tid" value="${Teacher.getTid()}"></td></tr>
        <tr><td>姓名：</td> <td><input type="text" name="Tname" value="${Teacher.getTname()}"></td></tr>
        <tr><td>学院：</td><td><input type="text" name="Tcollege" value="${Teacher.getTcollege()}"></td></tr>
        <tr><td>角色：</td><td><input type="text" name="Trole" value="${Teacher.getTrole()}"></td></tr>
        <tr><td><input type="submit" value="确定" ></td><td><input type="reset" value="重置" ></td></tr>
    </table>
</form>
</body>
</html>
