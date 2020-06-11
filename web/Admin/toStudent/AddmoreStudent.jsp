<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>批量添加学生</title>
</head>
<body>
<font color=red>${result}</font>
<form action="AddmoreStudent.do" method="post" enctype="multipart/form-data">
    <table>
        <tr><td>学生信息文件（请按照正确格式排布）</td></tr>
        <tr><td><input type="file" name="file"></td></tr>
        <tr><td><input type="submit" value="确定" ></td></tr>
        <tr><td><input type="reset" value="重置" ></td></tr>
    </table>
</form>
</body>
</html>
