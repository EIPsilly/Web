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

$(document).ready(function () {
    $("#student").addClass("identity-select-check");
    $("#teacher").addClass("identity-select-nocheck");
    $("#student").click(
        function(){
            $("#student").removeClass("identity-select-nocheck");
            $("#student").addClass("identity-select-check");
            $("#teacher").removeClass("identity-select-check");
            $("#teacher").addClass("identity-select-nocheck");
            $("#query_teacher").css("display","block");
            $("#college").attr("name","Scollege");
            $("#Sid").attr("name", "Sid");
            $("#Sname").attr("name", "Sname");
            $("#Sidcard").attr("name", "Sidcard");
            document.getElementById("statistc_show").innerText = "今日打卡统计：学生";
            $("#table_teacher").css("display","none");
            $("#table_student").css("display","table");
            let obj = document.getElementsByName("identity");
            for (let i = 0;i<obj.length;i++)
                obj[i].value = "student";
    });
    $("#teacher").click(
        function(){
            $("#teacher").removeClass("identity-select-nocheck");
            $("#teacher").addClass("identity-select-check");
            $("#student").removeClass("identity-select-check");
            $("#student").addClass("identity-select-nocheck");
            $("#query_teacher").css("display","none");
            $("#college").attr("name","Tcollege");
            $("#Sid").attr("name", "Tid");
            $("#Sname").attr("name", "Tname");
            $("#Sidcard").attr("name", "Tidcard");
            document.getElementById("statistc_show").innerText = "今日打卡统计：教师";
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
                    console.log(result);
                    console.log(typeof(result));
                    
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