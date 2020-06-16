package com.model;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.mysql.cj.conf.ConnectionUrlParser;

import java.util.ArrayList;

public class admin {
    @JSONField(name = "Aid")
    private String Aid;
    @JSONField(name = "Apassword")
    private String Apassword;
    @JSONField(name = "Arole")
    private String Arole;
    public admin() { }

    public String getAid() {
        return Aid;
    }

    public void setAid(String aid) {
        Aid = aid;
    }

    public String getApassword() {
        return Apassword;
    }

    public void setApassword(String apassword) {
        Apassword = apassword;
    }

    private ArrayList<admin> listOfPersons = new ArrayList<admin>();
    public void whenJavaList_thanConvertToJsonCorrect() {
        String jsonOutput= JSON.toJSONString(listOfPersons);
    }

    public String getArole() {
        return Arole;
    }

    public void setArole(String arole) {
        Arole = arole;
    }
}