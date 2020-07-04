package com.model;

import com.alibaba.fastjson.annotation.JSONField;

public class Teacher {
    @JSONField(name = "Tid")
    private String Tid;
    @JSONField(name = "Tname")
    private String Tname;
    @JSONField(name = "Tidcard")
    private String Tidcard;
    @JSONField(name = "Tcollege")
    private String Tcollege;
    @JSONField(name = "Trole")
    private String Trole;
    @JSONField(name = "Thealth")
    private String Thealth;
    @JSONField(name = "Tdate")
    private int Tdate;
    @JSONField(name = "Ttoday")
    private int Ttoday;
    public Teacher(){}
    public String getTid() {
        return Tid;
    }

    public void setTid(String tid) {
        Tid = tid;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public String getTcollege() {
        return Tcollege;
    }

    public void setTcollege(String tcollege) {
        Tcollege = tcollege;
    }

    public String getTrole() {
        return Trole;
    }

    public void setTrole(String trole) {
        Trole = trole;
    }

    public String getTidcard() {
        return Tidcard;
    }

    public void setTidcard(String tidcard) {
        Tidcard = tidcard;
    }

    public String getThealth() {
        return Thealth;
    }

    public void setThealth(String thealth) {
        Thealth = thealth;
    }

    public int getTdate() {
        return Tdate;
    }

    public void setTdate(int tdate) {
        Tdate = tdate;
    }

    public int getTtoday() {
        return Ttoday;
    }

    public void setTtoday(int ttoday) {
        Ttoday = ttoday;
    }
}