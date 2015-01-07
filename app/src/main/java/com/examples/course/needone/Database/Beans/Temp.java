package com.examples.course.needone.Database.Beans;

import java.sql.Date;

public class Temp {
    private String tempid;
    private String username;
    private Date TIME;

    public Temp(){

    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getUsername(){
        return username;
    }

    public void setTempid(String tempid){
        this.tempid=tempid;
    }

    public String getTempid(){
        return tempid;
    }

    public Date getTime(){
        return TIME;
    }
    public void setTime(Date TIME){
        this.TIME=TIME;
    }
}