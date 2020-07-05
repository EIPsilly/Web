function check(str)
{
    var check = document.getElementsByName(str);
    for (var i = 0;i<check.length;i++)   
    {
        if (check[i].checked == true) return false;
    }
    return true;
}

function change(e) {
    if (document.getElementById("checkbox1").checked == true)
    {
        let tmp = document.getElementsByName("HealthStatus");
        for (let index = 0;index<tmp.length;index++)
        {
            tmp[index].checked = false;
        }
    }
}

function change1(e) {
    console.log(e.checked);
    if (e.checked == true){
        document.getElementById("checkbox1").checked = false;
    }
}


function isEmpty(obj){
    if(typeof obj == undefined || obj == null || obj == "") return true;
    return false;
}

function jump()
{
    if (isEmpty(document.getElementsByName("name")[0].value) ||
    isEmpty(document.getElementsByName("idcard")[0].value) ||
    isEmpty(document.getElementsByName("id")[0].value) ||
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
    let tmp = document.getElementsByName("Promise");
    for (var i = 0;i<tmp.length;i++)   
    {
        if (tmp[i].checked == false)
        {
            alert("请做出承诺");
            return false;
        }
    }
    return true;
}