<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加学生信息</title>
</head>
<body>
<font color=red>${result}</font>
<p>请输入一条学生信息</p>
<form action = "AddStudent.do" method = "post">
    <table>
        <table>
            <tr><td>学生id：</td> <td><input type="text" name="Sid"></td></tr>
            <tr><td>姓名：</td> <td><input type="text" name="Sname"></td></tr>
            <tr><td>身份证号：</td><td><input type="text" name="Sidcard"></td></tr>
            <tr><td>学院：</td><td><input type="text" name="Scollege"></td></tr>
            <tr><td>专业：</td><td><input type="text" name="Smajor"></td></tr>
            <tr><td>班级：</td><td><input type="text" name="Sclass"></td></tr>
            <tr><td><input type="submit" value="确定" ></td><td><input type="reset" value="重置" ></td></tr>
        </table>
    </table>
</form>
</body>
</html>
