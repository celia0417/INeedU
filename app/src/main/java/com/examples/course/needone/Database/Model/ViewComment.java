package com.examples.course.needone.Database.Model;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.examples.course.needone.Database.DB;
import com.examples.course.needone.Database.Beans.Comment;

public class ViewComment {
	public ArrayList<Comment> selectcommentList(String requestID,DB db) {

		ArrayList<Comment> list = new ArrayList<Comment>();
		ResultSet rs=null;  
		String sql="select * from comments c where rid='"+requestID+"'";
		rs = db.doSelect(sql);
		try{

			while (rs.next()) {

				Comment c = new Comment();
				
				c.setRid(rs.getString("rid"));
				c.setComment(rs.getString("comments"));
				c.setCommentid(rs.getString("comment_id"));
				c.setCommentTime(rs.getDate("comment_time"));
				c.setUsername(rs.getString("username"));
				list.add(c);

			}
			db.close();
		}
		catch (Exception e) {  
			e.printStackTrace();  
		}  


		return list;

	}
	public static void main(String args[]){
		ViewComment t=new ViewComment();
		DB db=new DB();  
		ArrayList<Comment> s=t.selectcommentList("20001",db);
		for(int i = 0 ; i<s.size();i++) {
			Comment r=s.get(i);
			System.out.println(r.getRid());
			System.out.println(r.getComment());
			System.out.println(r.getCommentTime());


		}
	}
}
