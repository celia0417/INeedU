package com.examples.course.needone.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jialu on 12/19/14.
 */
public class Request {

    private String id, user, location, time, exptime, content, credit, myResponse;
    private ArrayList<String> responses;
    private ArrayList<Comment> comments;

    public Request() {
    }

    public Request (String id, String user, String location, String time, String exptime,
                  String credit, String content, String myResponse, ArrayList<String> responses,
                  ArrayList<Comment> comments) {
        this.id = id;
        this.user = user;
        this.location = location;
        this.time = time;
        this.exptime = exptime;
        this.credit = credit;
        this.content = content;
        this.myResponse = myResponse;
        this.responses = responses;
        this.comments = comments;
    }

    public String getId() { return id; }

    public void setId(String id) {this.id = id; }

    public String getUser() { return user; }

    public void setUser(String user) { this.user = user; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public String getExptime() { return exptime; }

    public void setExptime(String exptime) { this.exptime = exptime; }

    public String getCredit() { return credit; }

    public void setCredit(String credit) { this.credit = credit; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getMyResponse() { return myResponse; }

    public void setMyResponse(String myResponse) { this.myResponse = myResponse; }

    public ArrayList<String> getResponses() { return responses; }

    public void setResponses(ArrayList<String> responses) { this.responses = responses; }

    public ArrayList<Comment> getComments() { return comments; }

    public void setComments(ArrayList<Comment> comments) {this.comments = comments; }
}
