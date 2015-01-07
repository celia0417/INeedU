package com.examples.course.needone.Database.Beans;

import java.sql.Date;

import java.sql.Date;

public class DBRequest extends Users{
    private String RID;
    private String CONTENT;
    private Date Response_Time;
    private Date Request_Time;
    private Date Expire_Time;
    private Double Latitude;
    private Double Longitude;
    private String STATE;
    private String value;
    private double distance;


    public DBRequest(){

    }

    public void setRid(String RID){
        this.RID=RID;
    }

    public String getRid(){
        return RID;
    }

    public void setContent(String CONTENT){
        this.CONTENT=CONTENT;
    }

    public String getContent(){
        return CONTENT;
    }

    public Date getResponseTime(){
        return Response_Time;
    }
    public void setResponseTime(Date Response_Time){
        this.Response_Time=Response_Time;
    }

    public Date getRequestTime(){
        return Request_Time;
    }
    public void setRequestTime(Date Request_Time){
        this.Request_Time=Request_Time;
    }
    public Date getExpireTime(){
        return Expire_Time;
    }
    public void setExpireTime(Date Expire_Time){
        this.Expire_Time=Expire_Time;
    }

    public Double getLatitude() {
        return Latitude;
    }
    public void setLatitude(Double Latitude) {
        this.Latitude= Latitude;
    }


    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double Longitude) {
        this.Longitude = Longitude;
    }

    public String getState() {
        return STATE;
    }

    public void setState(String STATE) {
        this.STATE = STATE;
    }


    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

}
