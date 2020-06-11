window.onload = function () {
    document.getElementById("check_box1").style.backgroundColor = "rgb(255,255,255,0.4)";
}


function selected(e)
{
    if (e == 1)
    {
        document.getElementById("check_box1").style.backgroundColor = "rgb(255,255,255,0.4)";
        document.getElementById("check_box2").style.backgroundColor = "transparent";
        document.getElementById("check_box3").style.backgroundColor = "transparent";
    }
    else if (e == 2)
    {
        document.getElementById("check_box1").style.backgroundColor = "transparent";
        document.getElementById("check_box2").style.backgroundColor = "rgb(255,255,255,0.4)";
        document.getElementById("check_box3").style.backgroundColor = "transparent";
    }
    else if (e == 3)
    {
        document.getElementById("check_box1").style.backgroundColor = "transparent";
        document.getElementById("check_box2").style.backgroundColor = "transparent";
        document.getElementById("check_box3").style.backgroundColor = "rgb(255,255,255,0.4)";
    }
}