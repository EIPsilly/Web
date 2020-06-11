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
                <datalist id = "college_list">
                    <option>计算机科学与技术学院</option>
                    <option>信息学院</option>
                </datalist>
            </li>
            <div id = "query_teacher">
                <li>
                    <p>专业</p>
                    <input list = "major" name = "Smajor" id = "Smajor" class = "list" placeholder = "请选择专业">
                    <datalist id = "major">
                        <option>计算机科学与技术</option>
                        <option>软件工程</option>
                        <option>数字媒体</option>
                    </datalist>
                </li>
                <li>
                    <p>班级</p>
                    <input list = "class" name = "Sclass" id = "Sclass" class = "list" placeholder = "请选择班级">
                    <datalist id = "class">
                        <option>软工1801</option>
                        <option>软工1802</option>
                        <option>软工1803</option>
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
            <input type="submit" value="导入">
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
                <th id = "greennum">90</th>
                <th id = "yellownum">90</th>
                <th id = "rednum">90</th>
                <th id = "nonenum">90</th>
            </tr>
        </table>
    </div>
    <div id = "showwindow">
        <table id = "table_student" class = "showtable">
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>身份证</th>
                <th>学院</th>
                <th>专业</th>
                <th>班级</th>
                <th>健康状况</th>
                <th>连续打卡天数</th>
                <th>是否打卡</th>
                <th>操作</th>
            </tr>
            <tr>
                <td>201806062405</td>
                <td>张三</td>
                <td>330122199612161716</td>
                <td>计算机学院</td>
                <td>软件工程</td>
                <td>软工1801</td>
                <td>绿码</td>
                <td>5</td>
                <td>是</td>
                <td>
                    <a href = "#">删除</a>
                </td>
            </tr>
        </table>
        <table id = "table_teacher" class = "showtable" style="display: none;">
            <tr>
                <th>工号</th>
                <th>姓名</th>
                <th>身份证</th>
                <th>学院</th>
                <th>角色</th>
                <th>健康状况</th>
                <th>连续打卡天数</th>
                <th>是否打卡</th>
                <th>操作</th>
            </tr>
            <tr id = "tea_record1">
                <td>201806060606</td>
                <td>李四</td>
                <td>330122199612161716</td>
                <td>计算机学院</td>
                <td>院级管理员</td>
                <td>绿码</td>
                <td>5</td>
                <th>是</th>
                <td>
                    <a href = "#" onclick="tea_modify(1)">修改</a>
                    <a href = "#">删除</a>
                </td>
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
            <input type="submit" value="提交修改" class = "button">
        </div>
    </form>
</div>
</body>
</html>