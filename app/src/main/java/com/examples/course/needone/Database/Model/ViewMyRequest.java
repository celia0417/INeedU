package com.examples.course.needone.Database.Model;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.examples.course.needone.Database.DB;
import com.examples.course.needone.Database.Beans.DBRequest;

public class ViewMyRequest {
    public ArrayList<DBRequest> selectMyRequestList(String username,DB db) {

        ArrayList<DBRequest> list = new ArrayList<DBRequest>();
        ResultSet rs=null;
//		DB db=new DB();  
        String sql="SELECT * FROM request r, post p, users u where u.username='"+username+"' and p.username=u.username and p.rid=r.rid";
        rs = db.doSelect(sql);
        try{

            while (rs.next()) {
                DBRequest r = new DBRequest();
                r.setRid(rs.getString("rid"));
                r.setContent(rs.getString("content"));
                r.setRequestTime(rs.getDate("requesttime"));
                r.setExpireTime(rs.getDate("expire_time"));
                r.setLatitude(rs.getDouble("latitude"));
                r.setLongitude(rs.getDouble("longitude"));
                r.setState(rs.getString("State"));
                r.setUsername(rs.getString("username"));
                r.setCredit(rs.getInt("credit"));
                r.setUrl(rs.getString("picurl"));

                list.add(r);

            }
            db.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }



        return list;

    }
    public static void main(String args[]){
        ViewMyRequest t=new ViewMyRequest();
        DB db=new DB();
        ArrayList<DBRequest> s=t.selectMyRequestList("celia",db);

        for(int i = 0 ; i<s.size();i++) {
            DBRequest r=s.get(i);
            System.out.println(r.getRid());
            System.out.println(r.getContent());
            System.out.println(r.getRequestTime());
        }
    }
}
