<%--
  管理员登录
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登陆</title>
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
