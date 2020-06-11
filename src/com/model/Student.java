package com.model;

import com.alibaba.fastjson.annotation.JSONField;

public class Student {
    @JSONField(name = "Sid")
    private String Sid;
    @JSONField(name = "Sname")
    private String Sname;
    @JSONField(name = "Sidcard")
    private String Sidcard;
    @JSONField(name = "Scollege")
    private String Scollege;
    @JSONField(name = "Smajor")
    private String Smajor;
    @JSONField(name = "Sclass")
    private String Sclass;
    @JSONField(name = "Shealth")
    private String Shealth;
    @JSONField(name = "Sdate")
    private int Sdate;
    @JSONField(name = "Stoday")
    private int Stoday;
    public Student(){}
    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSidcard() {
        return Sidcard;
    }

    public void setSidcard(String sidcard) {
        Sidcard = sidcard;
    }

    public String getScollege() {
        return Scollege;
    }

    public void setScollege(String scollege) {
        Scollege = scollege;
    }

    public String getSmajor() {
        return Smajor;
    }

    public void setSmajor(String smajor) {
        Smajor = smajor;
    }

    public String getSclass() {
        return Sclass;
    }

    public void setSclass(String sclass) {
        Sclass = sclass;
    }

    public String getShealth() {
        return Shealth;
    }

    public void setShealth(String shealth) {
        Shealth = shealth;
    }

    public int getSdate() {
        return Sdate;
    }

    public void setSdate(int sdate) {
        Sdate = sdate;
    }

    public int getStoday() {
        return Stoday;
    }

    public void setStoday(int stoday) {
        Stoday = stoday;
    }
}