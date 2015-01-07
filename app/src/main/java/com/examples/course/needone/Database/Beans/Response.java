package com.examples.course.needone.Database.Beans;
import java.sql.Date;
/**
 * Created by Chang on 12/21/14.
 */
public class Response extends DBRequest {
    private String responseID;
    private Date response_time;
    private Double value;


    public Response(){

    }


    public void setValue(Double value){
        this.value=value;
    }

    public Double getValue(){
        return value;
    }

    public Date getResponseTime(){
        return response_time;
    }
    public void setResponseTime(Date response_time){
        this.response_time=response_time;
    }

}
