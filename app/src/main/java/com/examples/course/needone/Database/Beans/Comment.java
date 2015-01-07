package com.examples.course.needone.Database.Beans;

import java.sql.Date;

public class Comment extends DBRequest {
    private String commentID;
    private Date comment_time;
    private String comments;


    public Comment(){

    }

    public void setCommentid(String commentID){
        this.commentID=commentID;
    }

    public String getCommentid(){
        return commentID;
    }

    public void setComment(String comments){
        this.comments=comments;
    }

    public String getComment(){
        return comments;
    }

    public Date getCommentTime(){
        return comment_time;
    }
    public void setCommentTime(Date comment_time){
        this.comment_time=comment_time;
    }
}
