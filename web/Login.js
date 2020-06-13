window.onload = function () {
    document.getElementById("check_box1").style.backgroundColor = "rgb(255,255,255,0.4)";
}


function selected(e) {
    if (e == 1) {
        document.getElementById("check_box1").style.backgroundColor = "rgb(255,255,255,0.4)";
        document.getElementById("check_box2").style.backgroundColor = "transparent";
        document.getElementById("check_box3").style.backgroundColor = "transparent";
    }
    else if (e == 2) {
        document.getElementById("check_box1").style.backgroundColor = "transparent";
        document.getElementById("check_box2").style.backgroundColor = "rgb(255,255,255,0.4)";
        document.getElementById("check_box3").style.backgroundColor = "transparent";
    }
    else if (e == 3) {
        document.getElementById("check_box1").style.backgroundColor = "transparent";
        document.getElementById("check_box2").style.backgroundColor = "transparent";
        document.getElementById("check_box3").style.backgroundColor = "rgb(255,255,255,0.4)";
    }
}

$(document).ready(function () {
    $("#submitbutton").click(
        function () {
            x = $("#form1").serializeArray();
            var obj = JSON.stringify(x);
            console.log(x);
            $.ajax({
                type: "post",
                data: x,
                url: "Login.do",
                success: function (result) {
                    console.log(result);
                    if (result == "用户名或密码错误") alert(result);
                    else window.location.href = result;
                },
                error: function (e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            })
        });
})