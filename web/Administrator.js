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

// var stu_table,tea_table;

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
        }
    );

    $("#Smajor").focus(
        function () {
            $("#Smajor").prop("value","");
            $("#Sclass").prop("value","");
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
            let obj = document.getElementsByName("identity");
            for (let i = 0; i < obj.length; i++)
                obj[i].value = "student";
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
            let obj = document.getElementsByName("identity");
            for (let i = 0; i < obj.length; i++)
                obj[i].value = "teacher";
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
                    var table = jQuery.parseJSON(result);
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

    $("#modify_button").click(
        function () {
            var x = $("#modify_from").serializeArray();
            $.ajax({
                type: "post",
                data: x,
                url: "modify.do",
                success: function (result) {
                    console.log(result);
                },
                error: function (e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            })
        }
    )
});

function tea_modify(e) {
    $("#modify_page").css("display", "block");
    document.getElementById("passive").value = document.getElementById("tea_record" + e).innerText;
}

//关闭修改页面
function close_modify() {
    $("#modify_page").css("display", "none");
}

function deleterecord(e){
    $.ajax({
        type :"get",
        url:e,
        success: function(result){
            alert(result);
        },
        eoor: function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    })
}
