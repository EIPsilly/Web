window.onload = function () {
    //获取浏览器宽和高
    var html = document.querySelector('html');
    width = document.defaultView.getComputedStyle(html, null).width;
    document.documentElement.style.setProperty('--width', width);
}

window.onresize = function () {
    var html = document.querySelector('html');
    width = document.defaultView.getComputedStyle(html, null).width;
    document.documentElement.style.setProperty('--width', width);
}

$(document).ready(
    function () {
        $.ajax({
            type: "get",
            url: "getCollege.do?identity=student",
            success: function (result) {
                var tmp = jQuery.parseJSON(result);
                var out = $("#college_list");
                out.html("");
                for (let index in tmp){
                    out.append("<option>" + tmp[index] +"</option>")
                }
            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        })
    }
)

$(document).ready(function () {
    $("#student").addClass("identity-select-check");
    $("#teacher").addClass("identity-select-nocheck");
    $("#table2").css("display", "none");

    //（学院、专业、班级）获取焦点时清空内容
    $("#college").focus(
        function () {
            $("#college").prop("value","");
            $("#Smajor").prop("value","");
            $("#Sclass").prop("value","");
            $("#major").html("");
            $("#class").html("");
        }
    );

    $("#Smajor").focus(
        function () {
            $("#Smajor").prop("value","");
            $("#Sclass").prop("value","");
            $("#class").html("");
        }
    );

    $("#Sclass").focus(
        function () {
            $("#Sclass").prop("value","");
        }
    );

    //学院更改时候，清空专业、班级已有选择，更新专业选项
    $("#college").change(
        function () {
            if (identity == 0)
            {
                $("#Smajor").prop("value","");
                $("#Sclass").prop("value","");
                console.log($("#college").val());
                $.ajax({
                    type: "get",
                    url: "getmajor.do?Scollege=" + $("#college").val(),
                    success: function (result) {
                        var tmp = jQuery.parseJSON(result);
                        var out = $("#major");
                        $("#class").html("请先选择专业");
                        out.html("");
                        for (let index in tmp){
                            out.append("<option>" + tmp[index] +"</option>")
                        }
                    },
                    error: function (e) {
                        console.log(e.status);
                        console.log(e.responseText);
                    }
                })
            }
        }
    );

    //专业更改时候，清空班级已有选择，更新班级选项
    $("#Smajor").change(
        function () {
            if (identity == 0)
            {
                $("#Sclass").prop("value","");
                console.log($("#Smajor").val());
                $.ajax({
                    type: "get",
                    url: "getClass.do?Smajor=" + $("#Smajor").val(),
                    success: function (result) {
                        var tmp = jQuery.parseJSON(result);
                        var out = $("#class");
                        out.html("");
                        for (let index in tmp){
                            out.append("<option>" + tmp[index] +"</option>")
                        }
                    },
                    error: function (e) {
                        console.log(e.status);
                        console.log(e.responseText);
                    }
                })
            }
        }
    );

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
            $("#table_teacher").css("display", "none");
            $("#table_student").css("display", "table");
            $("#form2_Sno").html("学号");
            $("#Sid").attr("placeholder", "请输入学号");
            $("#import").attr("value","学生导入");
            $("#college").attr("value","");
            $("#bantch_identity").val("student");
            $("#queryone_identity").val("student");
            $("#Sno").html("学号");
            $("#tea_statistc").css("display","none");
            $("#stu_statistc").css("display","table-row");
            //更新学院选项
            $.ajax({
                type: "get",
                url: "getCollege.do?identity=student",
                success: function (result) {
                    var tmp = jQuery.parseJSON(result);
                    var out = $("#college_list");
                    $("#major").html("请先选择学院");
                    $("#class").html("请先选择专业");
                    out.html("");
                    for (let index in tmp){
                        out.append("<option>" + tmp[index] +"</option>")
                    }
                },
                error: function (e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            })
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
            $("#table_student").css("display", "none");
            $("#table_teacher").css("display", "table");
            $("#form2_Sno").html("工号");
            $("#import").attr("value","教师导入");
            $("#college").attr("value","");
            $("#bantch_identity").val("teacher");
            $("#queryone_identity").val("teacher");
            $("#Sno").html("工号");
            $("#tea_statistc").css("display","table-row");
            $("#stu_statistc").css("display","none");
            //更新学院选项
            $.ajax({
                type: "get",
                url: "getCollege.do?identity=teacher",
                success: function (result) {
                    var tmp = jQuery.parseJSON(result);
                    var out = $("#college_list");
                    out.html("");
                    for (let index in tmp){
                        out.append("<option>" + tmp[index] +"</option>")
                    }
                },
                error: function (e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            })
        });

    //表单1提交（学院、专业、班级查询）
    $("#query1").click(
        function () {
            x = $("#form1").serializeArray();
            var obj = JSON.stringify(x);
            $.ajax({
                type: "post",
                data: x,
                url: "batchquery.do",
                success: function (result) {
                    var tmp = jQuery.parseJSON(result);
                    var statistic = tmp[0];
                    var table = tmp[1];
                    if (identity == 0) {
                        $("#sgreennum").html(statistic.sgreen);
                        $("#syellownum").html(statistic.syellow);
                        $("#srednum").html(statistic.sred);
                        $("#snonenum").html(statistic.ssum - statistic.sgreen - statistic.syellow - statistic.sred);
                        print_student(table);
                    }
                    else if (identity == 1) {
                        $("#tgreennum").html(statistic.tgreen);
                        $("#tyellownum").html(statistic.tyellow);
                        $("#trednum").html(statistic.tred);
                        $("#tnonenum").html(statistic.tsum - statistic.tgreen - statistic.tyellow - statistic.tred);
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
            $.ajax({
                type: "post",
                data: x,
                url: "queryPeople.do",
                success: function (result) {
                    var table = jQuery.parseJSON(result);
                    if (identity == 0) {
                        print_student(table);
                        $("#sgreennum").html("");
                        $("#syellownum").html("");
                        $("#srednum").html("");
                        $("#snonenum").html("");
                    }
                    else if (identity == 1) {
                        print_teacher(table);
                        $("#tsgreennum").html("");
                        $("#tsyellownum").html("");
                        $("#tsrednum").html("");
                        $("#tsnonenum").html("");
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
            var tmpform = new FormData();
            tmpform.append("identity", document.getElementById("import_identity").value);
            tmpform.append("JsonFile", document.getElementById("import_jsonfile").files[0]);
            $.ajax({
                url: "add.do",
                type: "POST",
                data: tmpform,
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

    //用于修改的ajax
    function modify_ajax(x){
        $.ajax({
            type: "post",
            data: x,
            url: "modify.do",
            success: function (result) {
                console.log(result);
                alert(result);
            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        })
    }

    //修改管理员密码
    $("#modify_password").click(
        function () {
            var x = $("#modify_password_from").serializeArray();
            modify_ajax(x);
        }
    )

    //修改教师信息
    $("#modify_teacher").click(
        function () {
            var x = $("#modify_tea_from").serializeArray();
            modify_ajax(x);
        }
    )

    //修改学生信息
    $("#modify_student").click(
        function () {
            var x = $("#modify_stu_from").serializeArray();
            modify_ajax(x);
        }
    )

    //修改具有教师和管理员双重身份的信息
    $("#modify_A_tea").click(
        function () {
            if ($("#check_pass").prop('checked') == true){
                var x = $("#modify_tea_pass_from").serializeArray();
                modify_ajax(x);
            }
            x = $("#modify_tea_from").serializeArray();
            modify_ajax(x);
        }
    )

    //是否修改密码
    $("#check_pass").change(
        function () {
            if ($("#check_pass").prop('checked') == false){
                document.getElementById("Apassword").disabled = true;
            }
            else{
                document.getElementById("Apassword").disabled = false;
            }
        }
    )
});
