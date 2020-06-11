<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生修改</title>
</head>
<body>
<font color=red>${message}</font>
<p>修改学生信息</p>
<form action = "modifyStudentInfo.do" method = "post">
    <table>
        <tr><td></td> <td><input type="hidden" name="Sid" value="${Student.getSid()}"></td></tr>
        <tr><td>姓名：</td> <td><input type="text" name="Sname" value="${Student.getSname()}"></td></tr>
        <tr><td>身份证号：</td><td><input type="text" name="Sidcard" value="${Student.getSgid()}"></td></tr>
        <tr><td>学院：</td><td><input type="text" name="Scollege" value="${Student.getScollege()}"></td></tr>
        <tr><td>专业：</td><td><input type="text" name="Smajor" value="${Student.getSmajor()}"></td></tr>
        <tr><td>班级：</td><td><input type="text" name="Sclass" value="${Student.getSclass()}"></td></tr>
        <tr><td><input type="submit" value="确定" ></td><td><input type="reset" value="重置" ></td></tr>
    </table>
</form>
</body>
</html>
