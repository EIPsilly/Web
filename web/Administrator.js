window.onload = function () {
    //获取浏览器宽和高
    var html = document.querySelector('html');
    width = document.defaultView.getComputedStyle(html, null).width;
    // console.log(width);
    document.documentElement.style.setProperty('--width', width);
}

window.onresize = function () {
    var html = document.querySelector('html');
    width = document.defaultView.getComputedStyle(html, null).width;
    document.documentElement.style.setProperty('--width', width);
}

//身份判断 0学生 1教师
var identity = 0;

//学生表格输出
function print_student(table) {
    var out = $("#table_student");
    out.html("<tr>\n<th>学号</th>\n<th>姓名</th>\n<th>身份证</th>\n<th>学院</th>\n<th>专业</th>\n<th>班级</th>\n<th>健康状况</th>\n<th>连续打卡天数</th>\n<th>是否打卡</th>\n<th>操作</th></tr>");
    for (let index in table) {
        var it = table[index];
        var str = it.stoday == "1" ? "是" : "否";
        out.append("<tr>\n<td>"
            + it.sid + "</td>\n<td>"
            + it.sname + "</td>\n<td>"
            + it.sidcard + "</td>\n<td>"
            + it.scollege + "</td>\n<td>"
            + it.smajor + "</td>\n<td>"
            + it.sclass + "</td>\n<td>"
            + it.shealth + "</td>\n<td>"
            + it.sdate + "</td>\n<td>"
            + str + "</td>\n<td>\n<a href = \"#\">删除</a>\n</td>\n</tr>\n");
    }
}

//教师表格输出
function print_teacher(table) {
    var out = $("#table_teacher");
    out.html("<tr>\n<th>工号</th>\n<th>姓名</th>\n<th>身份证</th>\n<th>学院</th>\n<th>角色</th>\n<th>健康状况/th>\n<th>连续打卡天数</th>\n<th>是否打卡</th>\n<th>操作</th></tr>");
    console.log(table)
    for (let index in table) {
        var it = table[index];
        var str = it.ttoday == "1" ? "是" : "否";
        let str1 = "<tr>\n<td>"
            + it.tid + "</td>\n<td>"
            + it.tname + "</td>\n<td>"
            + it.tidcard + "</td>\n<td>"
            + it.tcollege + "</td>\n<td id = \"tea_record" + index + "\">"
            + it.trole + "</td>\n<td>"
            + it.thealth + "</td>\n<td>"
            + it.tdate + "</td>\n<td>"
            + str + "</td>\n<td>\n<a href = \"#\">删除</a>\n";
        if (it.trole == "系统管理员" || it.trole == "院级管理员" || it.trole == "校级管理员") str1 = str1 + "<a href = \"#\" onclick = \"tea_modify(" + index + ")\">修改</a>\n";
        str1 = str1 + "</td>\n</tr>\n";
        out.append(str1);
    }
}

$(document).ready(function () {
    $("#student").addClass("identity-select-check");
    $("#teacher").addClass("identity-select-nocheck");
    $("#table2").css("display", "none");

    

    $("#college").change(
        function () {

        }
    )

    //页面学生和教师切换
    $("#student").click(
        function () {
            identity = 0;
            $("#student").removeClass("identity-select-nocheck");
            $("#student").addClass("identity-select-check");
            $("#teacher").removeClass("identity-select-check");
            $("#teacher").addClass("identity-select-nocheck");
            $("#query_teacher").css("display", "block");
            $("#college").attr("name", "Scollege");
            $("#Sid").attr("name", "Sid");
            $("#Sname").attr("name", "Sname");
            $("#Sidcard").attr("name", "Sidcard");
            $("#statistc_show").html("今日打卡统计：学生");
            $("#table2").css("display", "none");
            $("#table1").css("display", "table");
            $("#form2_Sno").html("学号");
            $("#Sid").attr("placeholder", "请输入学号");
            $("#import").attr("value","学生导入");
            let obj = document.getElementsByName("identity");
            for (let i = 0; i < obj.length; i++)
                obj[i].value = "student";
        });
    $("#teacher").click(
        function () {
            identity = 1;
            $("#teacher").removeClass("identity-select-nocheck");
            $("#teacher").addClass("identity-select-check");
            $("#student").removeClass("identity-select-check");
            $("#student").addClass("identity-select-nocheck");
            $("#query_teacher").css("display", "none");
            $("#college").attr("name", "Tcollege");
            $("#Sid").attr("name", "Tid");
            $("#Sid").attr("placeholder", "请输入工号");
            $("#Sname").attr("name", "Tname");
            $("#Sidcard").attr("name", "Tidcard");
            $("#statistc_show").html("今日打卡统计：教师");
            $("#table1").css("display", "none");
            $("#table2").css("display", "table");
            $("#form2_Sno").html("工号");
            $("#import").attr("value","教师导入");
            let obj = document.getElementsByName("identity");
            for (let i = 0; i < obj.length; i++)
                obj[i].value = "teacher";
        });

    //表单1提交（学院、专业、班级查询）
    $("#query1").click(
        function () {
            x = $("#form1").serializeArray();
            var obj = JSON.stringify(x);
            console.log(x);
            $.ajax({
                type: "post",
                data: x,
                url: "batchquery.do",
                success: function (result) {
                    console.log(result);
                    var tmp = jQuery.parseJSON(result);
                    var statistic = tmp[0];
                    var table = tmp[1];
                    // console.log(statistic);
                    // console.log(statistic.sfinish);
                    console.log(table);
                    if (identity == 0) {
                        $("#totalnum").html(statistic.ssum);
                        $("#greennum").html(statistic.sgreen);
                        $("#yellownum").html(statistic.syellow);
                        $("#rednum").html(statistic.sred);
                        $("#finishnum").html(statistic.sfinish);
                        print_student(table);
                    }
                    else if (identity == 1) {
                        $("#totalnum").html(statistic.tsum);
                        $("#greennum").html(statistic.tgreen);
                        $("#yellownum").html(statistic.tyellow);
                        $("#rednum").html(statistic.tred);
                        $("#finishnum").html(statistic.tfinish);
                        print_teacher(table);
                    }
                },
                error: function (e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            })
        }
    );

    //表单2提交（学号、姓名、身份证）
    $("#query2").click(
        function () {
            x = $("#form2").serializeArray();
            var obj = JSON.stringify(x);
            console.log(x);
            $.ajax({
                type: "post",
                data: x,
                url: "queryPeople.do",
                success: function (result) {
                    $("#totalnum").html("");
                    $("#greennum").html("");
                    $("#yellownum").html("");
                    $("#rednum").html("");
                    $("#finishnum").html("");
                    console.log(result);
                    var table = jQuery.parseJSON(result);
                    console.log(table);
                    if (identity == 0) {
                        print_student(table);
                    }
                    else if (identity == 1) {
                        print_teacher(table);
                    }
                },
                error: function (e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            })
        }
    );

    //导入数据
    $("#import").click(
        function () {
            var formData = new FormData();
            formData.append("identity", document.getElementById("import_identity").value);
            formData.append("JsonFile", document.getElementById("import_jsonfile").files[0]);
            $.ajax({
                url: "add.do",
                type: "POST",
                data: formData,
                contentType: false,
                dataType: "text",
                processData: false,
                success: function (result) {
                    if (result != "ok")
                    {
                        var str = result.split('\n')[0].split(":")[1];
                    }
                    else if (result == "ok")
                    {
                        var str = "导入成功";
                    }
                    alert(str);
                    console.log(str);
                },
                error: function (e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            })
        }
    );
});

function tea_modify(e) {
    $("#modify_page").css("display", "block");
    document.getElementById("passive").value = document.getElementById("tea_record" + e).innerText;
}

//关闭修改页面
function close_modify() {
    $("#modify_page").css("display", "none");
}

