<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/7/4
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>二维码展示页面</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        html,
        body {
            width: 100%;
            height: 100%;
            color: white;
            font-size: 40px;
            font-weight: 800;
        }

        body {
            background-image: url(images/2.jpg);
            background-repeat: no-repeat;
            background-size: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        
        #block{
            width:550px;
            height:550px;
            background-color: rgb(0,0,0,0.3);
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        #showtimediv{
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
    </style>
    <script>
        window.onload = function () {
            var oDt = new Date();
            var sWd = "";
            var iWeekDay = oDt.getDay();
            switch (iWeekDay) {
                case 0:
                    sWd = "星期日";
                    break;
                case 1:
                    sWd = "星期一";
                    break;
                case 2:
                    sWd = "星期二";
                    break;
                case 3:
                    sWd = "星期三";
                    break;
                case 4:
                    sWd = "星期四";
                    break;
                case 5:
                    sWd = "星期五";
                    break;
                case 6:
                    sWd = "星期六";
                    break;
            }
            var iMonth = parseInt(oDt.getMonth()) + 1;
            document.getElementById("displaydate").innerHTML = "<span>" + oDt.getFullYear() + "年" + iMonth + "月" + oDt.getDate() + "日 " + sWd + "</span>";
            var iTimerid = window.setInterval("showtime()", 1000);
        }

        function showtime() {
            var oDt = new Date();
            var iTimerid;
            var sTime = "";
            if (oDt.getHours() < 10) {
                sTime += "0" + oDt.getHours() + ":";
            }
            else {
                sTime += oDt.getHours() + ":";
            }
            if (oDt.getMinutes() < 10) {
                sTime += "0" + oDt.getMinutes() + ":";
            }
            else {
                sTime += oDt.getMinutes() + ":";
            }
            if (oDt.getSeconds() < 10) {
                sTime += "0" + oDt.getSeconds();
            }
            else {
                sTime += oDt.getSeconds();
            }
            document.getElementById("displaytime").innerHTML = "<span>" + sTime + "</span>";
        }
    </script>
</head>

<body>
    <div id = "block">
        <p>${sessionScope.name}</p>
        <p>${sessionScope.id}</p>
        <img src="images/QRcode/${sessionScope.codename}" />
        <div id = "showtimediv">
            <div id="displaydate">
            </div>
            <div id="displaytime">
            </div>
        </div>
        <a href = "control.jsp" style="color:white">返回</a>
    </div>
</body>

</html>