<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:set var="Login_identity" value="${sessionScope.identity}"/>
    <c:if test="${!(Login_identity.equals(\"系统管理员\") || Login_identity.equals(\"院级管理员\") || Login_identity.equals(\"校级管理员\"))}" var = "check">
        <script>
            alert("请先登录");
            window.location.href = "Login.html";
        </script>
    </c:if>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="text/css" rel="stylesheet" href = "Administrator.css">
    <script src = "jquery-3.4.1/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src = "Administrator.js"></script>
    <script>

        function tea_modify(e) {
            $("#modify_pass_page").css("display","block");
            let array = document.getElementById(e).getElementsByTagName("td");
            document.getElementById("passive").value = array[4].innerText;
            document.getElementById("Aid").value = array[0].innerText;
        }

        function modify_stu(e){
            $("#modify_stu_page").css("display","block");
            e = e.toString();
            let array = document.getElementById(e).getElementsByTagName("td");
            let array2 = document.getElementById("modify_stu_from").getElementsByTagName("input");
            // console.log(array); console.log(array2);
            for (let i = 0;i<=5;i++)  array2[i+1].value = array[i].innerText;
        }


        function modify_tea(e){
            $("#modify_tea_page").css("display","block");
            e = e.toString();
            let array = document.getElementById(e).getElementsByTagName("td");
            let array2 = document.getElementById("modify_tea_from").getElementsByTagName("input");
            // console.log(array); console.log(array2);
            for (let i = 0;i<=3;i++)  array2[i+1].value = array[i].innerText;
            if (array[4].innerText == "普通教师"){
                $("#onlyteacher").css("display","table-row");
                $("#modify_tea_pass_from").css("display","none");
            }
            else{
                document.getElementById("passive").value = array[4].innerText;
                document.getElementById("Aid").value = array[0].innerText;
                $("#onlyteacher").css("display","none");
                $("#modify_tea_pass_from").css("display","flex");
            }
        }

        //关闭修改页面
        function close_modify(e) {
            e.style.display = "none";
        }

        // 教师删除
        function delete_tea(e1,e2){
            console.log(typeof(e1));
            console.log(typeof(e2));
            console.log(e1);
            console.log(e2);
            console.log("delete.do?identity=teacher&Tid=" + e1 + "&Trole=" + e2);
            $.ajax({
                type :"get",
                url: "delete.do?identity=teacher&Tid=" + e1 + "&Trole=" + e2,
                success: function(result){
                    console.log(result)
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
            var out = $("#table_student")
            <c:choose>
                <c:when test="${Login_identity.equals(\"系统管理员\")}">
                    out.html("<tr>\n<th>学号</th>\n<th>姓名</th>\n<th>身份证</th>\n<th>学院</th>\n<th>专业</th>\n<th>班级</th>\n<th>健康状况</th>\n<th>连续打卡天数</th>\n<th>是否打卡</th>\n<th>操作</th>\n</tr>");
                </c:when>
                <c:otherwise>
                    out.html("<tr>\n<th>学号</th>\n<th>姓名</th>\n<th>身份证</th>\n<th>学院</th>\n<th>专业</th>\n<th>班级</th>\n<th>健康状况</th>\n<th>连续打卡天数</th>\n<th>是否打卡</th>\n</tr>");
                </c:otherwise>
            </c:choose>

            for (let index in table) {
                var it = table[index];
                // console.log(typeof (it.sidcard));
                var str = it.stoday == "1" ? "是" : "否";
                var str2;
                if (it.shealth == "green") str2 = "绿码";
                else if (it.shealth == "yellow") str2 = "黄码";
                else if (it.shealth == "red") str2 = "红码";
                else if (it.shealth == "null") str2 = "未申报";
                var str3 = "<tr id = " + it.sid + ">\n<td>"
                    + it.sid + "</td>\n<td>"
                    + it.sname + "</td>\n<td>"
                    + it.sidcard + "</td>\n<td>"
                    + it.scollege + "</td>\n<td>"
                    + it.smajor + "</td>\n<td>"
                    + it.sclass + "</td>\n<td>"
                    + str2 + "</td>\n<td>"
                    + it.sdate + "</td>\n<td>"
                    + str + "</td>\n";
                <c:if test="${Login_identity.equals(\"系统管理员\")}">
                    str3 += "<td>\n<a href = \"#\" onclick=\"delete_stu(\'" + it.sid + "\')\">删除</a>\n" +
                                  "<a href = \"#\" onclick=\"modify_stu(\'" + it.sid + "\')\">修改</a>\n" +
                            "</td>\n";
                </c:if>
                out.append(str3 + "</tr>");
            }
        }

        //教师表格输出
        function print_teacher(table) {
            var out = $("#table_teacher");
            <c:choose>
                <c:when test="${Login_identity.equals(\"系统管理员\") || Login_identity.equals(\"校级管理员\")}">
                    out.html("<tr>\n<th>工号</th>\n<th>姓名</th>\n<th>身份证</th>\n<th>学院</th>\n<th>角色</th>\n<th>健康状况</th>\n<th>连续打卡天数</th>\n<th>是否打卡</th>\n<th>操作</th>\n</tr>");
                </c:when>
                <c:otherwise>
                    out.html("<tr>\n<th>工号</th>\n<th>姓名</th>\n<th>身份证</th>\n<th>学院</th>\n<th>角色</th>\n<th>健康状况</th>\n<th>连续打卡天数</th>\n<th>是否打卡</th>\n</tr>");
                </c:otherwise>
            </c:choose>
            console.log(table)
            for (let index in table) {
                var it = table[index];
                var str = it.ttoday == "1" ? "是" : "否";
                var str2;
                if (it.thealth == "green") str2 = "绿码";
                else if (it.thealth == "yellow") str2 = "黄码";
                else if (it.thealth == "red") str2 = "红码";
                else if (it.thealth == "null") str2 = "未申报";
                var str1 = "<tr id = " + it.tid + ">\n<td>"
                    + it.tid + "</td>\n<td>"
                    + it.tname + "</td>\n<td>"
                    + it.tidcard + "</td>\n<td>"
                    + it.tcollege + "</td>\n<td>"
                    + it.trole + "</td>\n<td>"
                    + str2 + "</td>\n<td>"
                    + it.tdate + "</td>\n<td>"
                    + str + "</td>\n";
                <c:choose>
                    <c:when test="${Login_identity.equals(\"系统管理员\")}">
                        if (it.trole != "系统管理员") {
                            str1 = str1 + "<td>"
                            str1 = str1 + "\n<a href = \"#\" onclick=\"delete_tea(\'" + it.tid + "\',\'" + it.trole + "\')\">删除</a>";
                            str1 = str1 + "\n<a href = \"#\" onclick = \"modify_tea(\'" + it.tid + "\')\">修改</a>";
                            str1 = str1 + "\n</td>\n";
                        }
                        // else str1 = str1 + " ";
                    </c:when>
                    <c:when test="${Login_identity.equals(\"校级管理员\")}">
                        if (it.trole == "院级管理员"){
                            str1 = str1 + "<td>"
                            str1 = str1 + "\n<a href = \"#\" onclick = \"tea_modify(\'" + it.tid + "\')\">修改密码</a>";
                            str1 = str1 + "\n</td>\n";
                        }
                        // else str1 = str1 + " ";
                    </c:when>
                </c:choose>
                str1 += "</tr>";
                out.append(str1);
            }
        }
    </script>
    <title>管理员界面</title>
</head>
<body>
<div id = "selection">
    <form id = "form1">
        <input type="hidden" name = "identity" value = "student" id = "bantch_identity">
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
        <input type="hidden" name = "identity" value = "student" id = "queryone_identity">
        <input type="hidden" name = "pattern" value = "onebyone">
        <p>单个查询</p>
        <ul>
            <li>
                <p id = "Sno">学号</p>
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

<%--    json导入--%>
    <c:if test="${Login_identity.equals(\"系统管理员\")}">
        <script>
            $("#student").click(
                function () {
                    $("#import_identity").val("student");
                }
            )

            $("#teacher").click(
                function () {
                    $("#import_identity").val("teacher");
                }
            )
        </script>
        <div id = "postjson">
            <form >
                <input type="hidden" name = "identity" value = "student" id = "import_identity">
                <input type="file" name = "JsonFile" id = "import_jsonfile">
                <input type="button" value="导入学生" id = "import">
            </form>
        </div>
    </c:if>


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
        </table>
        <table id = "table_teacher" class = "showtable" style="display: none;">
        </table>
    </div>
</div>

<c:if test="${Login_identity.equals(\"校级管理员\")}">
    <%--密码修改（校级管理员）--%>
    <div class = "modify_page" style="display: none;" id = "modify_pass_page">
        <form class = "modify_from" id = "modify_password_from">
            <img src = "images/3.png" style="width: 20px; position: absolute; top:10px; right:10px;" onclick="close_modify(modify_pass_page)">
            <input type="hidden" name = "initiative" id = "initiative" value = "校级管理员">
            <input type="hidden" name = "identity" value = "admin">
            <input type="hidden" name = "passive" id = "passive">
            <input type="hidden" name = "Aid" value = "" id = "Aid">
            <div class = "modify_password">
                修改后的密码：<input type="password" name = "Apassword">
                <input type="submit" value="提交修改" class = "modify_button" id = "modify_password">
            </div>
        </form>
    </div>
</c:if>

<c:if test="${Login_identity.equals(\"系统管理员\")}">
    <%--学生信息修改（系统管理员）--%>
    <div class = "modify_page" style="display: none;" id = "modify_stu_page">
        <form class = "modify_from modify_form_stu" id = "modify_stu_from">
            <img src = "images/3.png" style="width: 20px; position: absolute; top:10px; right:10px;" onclick="close_modify(modify_stu_page)">
            <input type="hidden" name = "identity" value = "student">
            <input type="hidden" name = "Sid" value = "" id = "modify_Sid">
            <table cellspacing = "8">
                <tr>
                    <td>姓名</td>
                    <td><input name = "Sname" type="text"></td>
                </tr>
                <tr>
                    <td>身份证</td>
                    <td><input name = "Sidcard" type="text"></td>
                </tr>
                <tr>
                    <td>学院</td>
                    <td><input name = "Scollege" type="text"></td>
                </tr>
                <tr>
                    <td>专业</td>
                    <td><input name = "Smajor" type="text"></td>
                </tr>
                <tr>
                    <td>班级</td>
                    <td><input name = "Sclass" type="text"></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center; margin-top: 10px;">
                        <input type="submit" value="提交修改" class = "modify_button" style="left: 0; top: 0;" id = "modify_student">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <%--教师信息修改（系统管理员）--%>
    <div class = "modify_page" id = "modify_tea_page" style="display: none">
        <div class = "modify_from modify_form_stu" style = "height:200px;" >
            <img src = "images/3.png" style="width: 20px; position: absolute; top:10px; right:10px;" onclick="close_modify(modify_tea_page)">
            <form id = "modify_tea_from">
                <input type="hidden" name = "identity" value = "teacher">
                <input type="hidden" name = "Tid" value = "" id = "modify_Tid">
                <table cellspacing = "8">
                    <tr>
                        <td>姓名</td>
                        <td><input name = "Tname" type="text"></td>
                    </tr>
                    <tr>
                        <td>身份证</td>
                        <td><input name = "Tidcard" type="text"></td>
                    </tr>
                    <tr>
                        <td>学院</td>
                        <td><input name = "Tcollege" type="text"></td>
                    </tr>
                    <tr id = "onlyteacher">
                        <td colspan="2" style="text-align: center; margin-top: 10px;">
                            <input type="button" value="提交修改" class = "modify_button" style="left: 0; top: 5px;" id = "modify_teacher">
                        </td>
                    </tr>
                </table>
            </form>
            <form id="modify_tea_pass_from" style="display: none">
                <input type="checkbox" name = "modify_password" style="position: relative; top:7px; left:-4px;" id = "check_pass">
                <input type="hidden" name = "identity" value = "admin">
                <input type="hidden" name = "initiative" id = "initiative" value = "系统管理员">
                <input type="hidden" name = "passive" id = "passive">
                <input type="hidden" name = "Aid" value = "" id = "Aid">
                <table>
                    <tr>
                        <td>修改密码&nbsp;&nbsp;</td>
                        <td><input type="password" name = "Apassword" id = "Apassword" disabled="true"></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center; margin-top: 10px;">
                            <input type="button" value="提交修改" class = "modify_button" style="left: 0; top: 5px;" id = "modify_A_tea">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</c:if>
</body>
</html>