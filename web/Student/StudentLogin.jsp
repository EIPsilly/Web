<%--
  Created by IntelliJ IDEA.
  User: 29615
  Date: 2020/6/4
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生登录</title>
</head>
<body>
<font color=red>${infor}</font>
<p>请输入管理员id即密码</p>
<form action = "loginAdmin.do" method = "post">
    <table>
        <tr><td>id ：</td> <td><input type="text" name="Aid" ></td></tr>
        <tr><td>密码 ：</td> <td><input type="text" name="Apassword" ></td></tr>
        <tr><td><input type="submit" value="确定" ></td>
            <td><input type="reset" value="重置" ></td>
        </tr>
    </table>
</form>
</body>
</html>
