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

$(document).ready(function () {
    $("#student").addClass("identity-select-check");
    $("#teacher").addClass("identity-select-nocheck");
    $("#student").click(
        function(){
            identity = 0;
            $("#student").removeClass("identity-select-nocheck");
            $("#student").addClass("identity-select-check");
            $("#teacher").removeClass("identity-select-check");
            $("#teacher").addClass("identity-select-nocheck");
            $("#query_teacher").css("display","block");
            $("#college").attr("name","Scollege");
            $("#Sid").attr("name", "Sid");
            $("#Sname").attr("name", "Sname");
            $("#Sidcard").attr("name", "Sidcard");
            $("#statistc_show").html("今日打卡统计：学生");
            $("#table_teacher").css("display","none");
            $("#table_student").css("display","table");
            let obj = document.getElementsByName("identity");
            for (let i = 0;i<obj.length;i++)
                obj[i].value = "student";
    });
    $("#teacher").click(
        function(){
            identity = 1;
            $("#teacher").removeClass("identity-select-nocheck");
            $("#teacher").addClass("identity-select-check");
            $("#student").removeClass("identity-select-check");
            $("#student").addClass("identity-select-nocheck");
            $("#query_teacher").css("display","none");
            $("#college").attr("name","Tcollege");
            $("#Sid").attr("name", "Tid");
            $("#Sname").attr("name", "Tname");
            $("#Sidcard").attr("name", "Tidcard");
            $("#statistc_show").html("今日打卡统计：教师");
            $("#table_student").css("display","none");
            $("#table_teacher").css("display","table");
            let obj = document.getElementsByName("identity");
            for (let i = 0;i<obj.length;i++)
                obj[i].value = "teacher";
    });
    $("#query1").click(
        function(){
            x=$("#form1").serializeArray();
            var obj = JSON.stringify(x);
            console.log(x);
            $.ajax({
                type:"post",
                data:x,
                url:"batchquery.do",
                success:function(result){
                    var tmp = jQuery.parseJSON(result);
                    var statistic = tmp[0];
                    var table = tmp[1];
                    console.log(statistic);
                    console.log(statistic.sfinish);
                    console.log(table);
                    if (identity == 0)
                    {
                        $("#totalnum").html(statistic.ssum);
                        $("#greennum").html(statistic.sgreen);
                        $("#yellownum").html(statistic.syellow);
                        $("#rednum").html(statistic.sred);
                        $("#finishnum").html(statistic.sfinish);
                        var out = $("#table_student");
                        for (let index in table)
                        {
                            var it = table[index];
                            var str = it.stoday == "1"?"是":"否";
                            console.log(str);
                            out.append("<tr>\n<td>" + it.sid +"</td>\n<td>" + it.sname +"</td>\n<td>" + it.sidcard + "</td>\n<td>" + it.scollege + "</td>\n<td>" + it.smajor + "</td>\n<td>" + it.sclass + "</td>\n<td>" + it.shealth +"</td>\n<td>" + it.sdate + "</td>\n<td>" + str + "</td>\n<td>\n<a href = \"#\">删除</a>\n</td>\n</tr>\n");
                        }
                    }
                },
                error:function (e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            })
    });
});


function tea_modify(e){
    $("#modify_page").css("display","block");
    var tr = document.getElementById("tea_record" + e);
    var list = tr.getElementsByTagName("td");
    document.getElementById("passive").value = list[4].innerText;
}

function close_modify(){
    $("#modify_page").css("display","none");
}