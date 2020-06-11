<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生展示界面</title>
</head>
<body>
<table border="1">
    <tr>
        <td>学生id</td>
        <td>姓名</td>
        <td>身份证</td>
        <td>学院</td>
        <td>专业</td>
        <td>班级</td>
        <td colspan="2">操作</td>
    </tr>
    <c:forEach var="student" items="${student}"  varStatus="status">
        <tr>
        <td>${student.getSid()}</td>
        <td>${student.getSname()}</td>
        <td>${student.getSgid()}</td>
        <td>${student.getScollege()}</td>
        <td>${student.getSmajor()}</td>
        <td>${student.getSclass()}</td>
        <td><a href="modifyStudent.do?Sid=${student.getSid()}&Sname=${student.getSname()}&Sgid=${student.getSgid()}
                    &Scollege=${student.getScollege()}&Smajor=${student.getSmajor()}&Sclass=${student.getSclass()}">修改</a></td>
        <td><a href="DeleteStudent.do?Sid=${student.getSid()}">删除</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
