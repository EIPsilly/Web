<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理页面</title>
</head>
<body>
<%
    if(session.getAttribute("infor")== null || !((String)session.getAttribute("infor")).equals("success")) {
        System.out.println(session.getAttribute("infor"));
        request.getRequestDispatcher("AdminLogin.jsp").forward(request,response);
    }
%>
<table>
    <tr><td>功能列表</td></tr>
    <tr><td><a href="toStudent/Manage.jsp">学生信息管理</a></td></tr>
    <tr><td><a href="toTeacher/Manage.jsp">教师信息管理</a></td></tr>
</table>
</body>
</html>
