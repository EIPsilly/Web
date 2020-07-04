<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/6/11
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="text/css" rel="stylesheet" href = "Administrator.css">
    <script src = "jquery-3.4.1/jquery-3.4.1.min.js"></script>
    <script>

        // 教师删除
        function delete_tea(e1,e2){
            console.log("delete.do?identity=teacher&Tid=" + e1 + "&Trole=" + e2);
            $.ajax({
                type :"get",
                url: "delete.do?identity=teacher&Tid=" + e1 + "&Trole=" + e2,
                success: function(result){
                    alert(result);
                },
                error: function(e){
                    console.log(e.status);
                    console.log(e.responseText);
                }
            })
        }

        // 学生删除
        function delete_stu(e){
            console.log("delete.do?identity=student&Sid=" + e);
            $.ajax({
                type :"get",
                url: "delete.do?identity=student&Sid=" + e,
                success: function(result){
                    alert(result);
                },
                error: function(e){
                    console.log(e.status);
                    console.log(e.responseText);
                }
            })
        }
        //身份判断 0学生 1教师
        var identity = 0;

        //学生表格输出
        function print_student(table) {
            var out = $("#table_student");
            out.html("<tr>\n<th>学号</th>\n<th>姓名</th>\n<th>学院</th>\n<th>专业</th>\n<th>班级</th>\n<th>健康状况</th>\n<th>连续打卡天数</th>\n<th>是否打卡</th>\n<th>操作</th></tr>");
            for (let index in table) {
                var it = table[index];
                var str = it.stoday == "1" ? "是" : "否";
                var str2;
                if (it.shealth == "green") str2 = "绿码";
                else if (it.shealth == "yellow") str2 = "黄码";
                else if (it.shealth == "red") str2 = "红码";
                str3 = 1;
                out.append("<tr>\n<td>"
                    + it.sid + "</td>\n<td>"
                    + it.sname + "</td>\n<td>"
                    + it.scollege + "</td>\n<td>"
                    + it.smajor + "</td>\n<td>"
                    + it.sclass + "</td>\n<td>"
                    + str2 + "</td>\n<td>"
                    + it.sdate + "</td>\n<td>"
                    + str + "</td>\n<td>"
                    + "\n<a onclick=\"delete_stu(" + it.sid + ")\">删除</a>\n"
                    + "</td>\n</tr>");
            }
        }

        //教师表格输出
        function print_teacher(table) {
            var out = $("#table_teacher");
            out.html("<tr>\n<th>工号</th>\n<th>姓名</th>\n<th>学院</th>\n<th>角色</th>\n<th>健康状况</th>\n<th>连续打卡天数</th>\n<th>是否打卡</th>\n<th>操作</th></tr>");
            console.log(table)
            for (let index in table) {
                var it = table[index];
                var str = it.ttoday == "1" ? "是" : "否";
                var str2;
                if (it.thealth == "green") str2 = "绿码";
                else if (it.thealth == "yellow") str2 = "黄码";
                else if (it.thealth == "red") str2 = "红码";
                var str3 = "identity=teacher&Tid=" + it.tid + "&Trole=" + it.trole;
                console.log(str3);
                var str1 = "<tr>\n<td>"
                    + it.tid + "</td>\n<td>"
                    + it.tname + "</td>\n<td>"
                    + it.tcollege + "</td>\n<td id = \"tea_record" + index + "\">"
                    + it.trole + "</td>\n<td>"
                    + str2 + "</td>\n<td>"
                    + it.tdate + "</td>\n<td>"
                    + str + "</td>\n<td>"
                    + "\n<a onclick=\"delete_tea(" + it.tid + "," + it.trole + ")\">删除</a>\n";
                if (it.trole == "系统管理员" || it.trole == "院级管理员" || it.trole == "校级管理员") str1 = str1 + "<a onclick = \"tea_modify(" + index + ")\">修改</a>\n";
                str1 = str1 + "</td>\n</tr>\n";
                out.append(str1);
            }
        }
    </script>
    <script type="text/javascript" src = "Administrator.js"></script>
    <title>管理员界面</title>
</head>
<body>
<div id = "selection">
    <form id = "form1">
        <input type="hidden" name = "identity" value = "student">
        <input type="hidden" name = "pattern" value = "batch">
        <p style="margin-top: 30px;">批量管理</p>
        <ul>
            <li>
                <p>学院</p>
                <input list = "college_list" id = "college" name = "Scollege" class = "list" placeholder = "请选择学院">
                <datalist id = "college_list" placeholder="请选择学院">
                </datalist>
            </li>
            <div id = "query_teacher">
                <li>
                    <p>专业</p>
                    <input list = "major" name = "Smajor" id = "Smajor" class = "list" placeholder = "请选择专业">
                    <datalist id = "major" placeholder="请选择专业">
                    </datalist>
                </li>
                <li>
                    <p>班级</p>
                    <input list = "class" name = "Sclass" id = "Sclass" class = "list" placeholder = "请选择班级">
                    <datalist id = "class" placeholder="请选择班级">
                    </datalist>
                </li>
            </div>
            <li>
                <input type="button" value="查询" class="button" id = "query1">
            </li>
        </ul>
    </form>
    <form id = "form2">
        <input type="hidden" name = "identity" value = "student">
        <input type="hidden" name = "pattern" value = "onebyone">
        <p>单个查询</p>
        <ul>
            <li>
                <p>学号</p>
                <input type="text" name = "Sid" id = "Sid" placeholder = "请输入学号">
            </li>
            <li>
                <p>姓名</p>
                <input type="text" name = "Sname" id = "Sname" placeholder = "请输入姓名">
            </li>
            <li>
                <p>身份证</p>
                <input type="text" name = "Sidcard" id = "Sidcard" placeholder = "请输入身份证">
            </li>
            <li>
                <input type="button" value="查询" class="button" id = "query2">
            </li>
        </ul>
    </form>
</div>
<div id ="showdata">
    <div id = "identity">
        <label>
            <div class = "identity-select" id = "student">
                <input type="radio" name = "identity1" value = "student" style="display: none;">
                <p>学生</p>
            </div>
        </label>
        <label>
            <div class = "identity-select" id = "teacher">
                <input type="radio" name = "identity1" value = "teacher" style="display: none;">
                <p>教师</p>
            </div>
        </label>
    </div>
    <div id = "postjson">
        <form action = "" enctype="multipart/form-data" method="post">
            <input type="hidden" name = "identity" value = "student">
            <input type="file" name = "JsonFile">
            <input type="submit" value="导入学生">
        </form>
    </div>
    <div id = "showstatistc">
        <table id = "statistctable">
            <tr>
                <th rowspan="2" id = "statistc_show">今日打卡统计：学生&nbsp;&nbsp;</th>
                <th>绿码</th>
                <th>黄码</th>
                <th>红码</th>
                <th>未打卡</th>
            </tr>
            <tr>
                <th id = "greennum"></th>
                <th id = "yellownum"></th>
                <th id = "rednum"></th>
                <th id = "nonenum"></th>
            </tr>
        </table>
    </div>
    <div id = "showwindow">
        <table id = "table_student" class = "showtable">
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>学院</th>
                <th>专业</th>
                <th>班级</th>
                <th>健康状况</th>
                <th>连续打卡天数</th>
                <th>是否打卡</th>
                <th>操作</th>
            </tr>
        </table>
        <table id = "table_teacher" class = "showtable" style="display: none;">
            <tr>
                <th>工号</th>
                <th>姓名</th>
                <th>学院</th>
                <th>角色</th>
                <th>健康状况</th>
                <th>连续打卡天数</th>
                <th>是否打卡</th>
                <th>操作</th>
            </tr>
        </table>
    </div>
</div>
<div id = "modify_page" style="display: none;">
    <form id = "modify_from">
        <img src = "images/3.png" style="width: 20px; position: absolute; top:10px; right:10px;" onclick="close_modify()">
        <input type="hidden" name = "initiative" id = "initiative" value = "系统管理员">
        <input type="hidden" name = "passive" id = "passive">
        <div id = "modify_password">
            修改后的密码：<input type="password" name = "Apassword">
            <input type="button" value="提交修改" class = "button" id = "modify_button">
        </div>
    </form>
</div>
</body>
</html>