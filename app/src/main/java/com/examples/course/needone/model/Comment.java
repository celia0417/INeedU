package com.examples.course.needone.model;

/**
 * Created by Jialu on 12/21/14.
 */
public class Comment {
    private String user, time, content;

    public Comment() {
    }

    public Comment(String user, String time, String content){
        this.user = user;
        this.time = time;
        this.content = content;
    }

    public String getUser() { return user; }

    public void setUser(String user) { this.user = user; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content;}
}
