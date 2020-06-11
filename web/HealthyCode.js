function check(str)
{
    var check = document.getElementsByName(str);
    for (var i = 0;i<check.length;i++)   
    {
        if (check[i].checked == true) return false;
    }
    return true;
}

function isEmpty(obj){
    if(typeof obj == undefined || obj == null || obj == "") return true;
    return false;
}

function jump()
{
    if (isEmpty(document.getElementsByName("name")[0].value) ||
    isEmpty(document.getElementsByName("IDcard")[0].value) ||
    isEmpty(document.getElementsByName("Sno")[0].value) ||
    isEmpty(document.getElementsByName("phone")[0].value))
    {
        alert("请完善个人信息");
        return false;
    }
    if (check("Leave"))
    {
        alert("请选择第二题");
        return false;
    }
    if (check("abroad"))
    {
        alert("请选择第三题");
        return false;
    }
    if (check("contact"))
    {
        alert("请选择第四题");
        return false;
    }
    if (check("diagnosis"))
    {
        alert("请选择第五题");
        return false;
    }
    if (check("HealthStatus") && check("Healthy"))
    {
        alert("请选择第六题")
        return false;
    }
    if (check("Promise"))
    {
        alert("请做出承诺");
        return false;
    }
    return true;
}