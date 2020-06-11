<%--
  Created by IntelliJ IDEA.
  User: 29615
  Date: 2020/5/30
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>批量添加教师</title>
</head>
<body>
<font color=red>${result}</font>
<form action="AddmoreTeacher.do" method="post" enctype="multipart/form-data">
    <table>
        <tr><td>老师信息文件（请按照正确格式排布）</td></tr>
        <tr><td><input type="file" name="file"></td></tr>
        <tr><td><input type="submit" value="确定" ></td></tr>
        <tr><td><input type="reset" value="重置" ></td></tr>
    </table>
</form>
</body>
</html>
