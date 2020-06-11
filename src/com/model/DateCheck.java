package com.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class DateCheck {
    @JSONField(name = "date", format = "yyyy-MM-dd")
    private Date date;
    @JSONField(name = "Tsum")
    private int Tsum;
    @JSONField(name = "Tfinish")
    private int Tfinish;
    @JSONField(name = "Ssum")
    private int Ssum;
    @JSONField(name = "Sfinish")
    private int Sfinish;
    @JSONField(name = "Tred")
    private int Tred;
    @JSONField(name = "yellow")
    private int Tyellow;
    @JSONField(name = "Tgreen")
    private int Tgreen;
    @JSONField(name = "Sred")
    private int Sred;
    @JSONField(name = "Syellow")
    private int Syellow;
    @JSONField(name = "Sgreen")
    private int Sgreen;

    public int getTsum() {
        return Tsum;
    }

    public void setTsum(int tsum) {
        Tsum = tsum;
    }

    public int getTfinish() {
        return Tfinish;
    }

    public void setTfinish(int tfinish) {
        Tfinish = tfinish;
    }

    public int getSsum() {
        return Ssum;
    }

    public void setSsum(int ssum) {
        Ssum = ssum;
    }

    public int getSfinish() {
        return Sfinish;
    }

    public void setSfinish(int sfinish) {
        Sfinish = sfinish;
    }

    public int getTred() {
        return Tred;
    }

    public void setTred(int tred) {
        Tred = tred;
    }

    public int getTyellow() {
        return Tyellow;
    }

    public void setTyellow(int tyellow) {
        Tyellow = tyellow;
    }

    public int getTgreen() {
        return Tgreen;
    }

    public void setTgreen(int tgreen) {
        Tgreen = tgreen;
    }

    public int getSred() {
        return Sred;
    }

    public void setSred(int sred) {
        Sred = sred;
    }

    public int getSyellow() {
        return Syellow;
    }

    public void setSyellow(int syellow) {
        Syellow = syellow;
    }

    public int getSgreen() {
        return Sgreen;
    }

    public void setSgreen(int sgreen) {
        Sgreen = sgreen;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}