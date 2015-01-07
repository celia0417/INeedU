package com.examples.course.needone.Database.Model;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.examples.course.needone.Database.DB;
import com.examples.course.needone.Database.Beans.DBRequest;
import com.examples.course.needone.Database.Beans.Response;
public class ViewMyResponse {
    public ArrayList<Response> selectMyResponseList(String username,DB db) {

        ArrayList<Response> list = new ArrayList<Response>();
        ResultSet rs=null;
        String sql="SELECT r.requesttime as r_time, p.reponsetime as p_time,r.rid,r.content,r.latitude,r.longitude,r.state, p.value FROM request r, response p where p.rid=r.rid and p.username='"+username+"'";

        rs = db.doSelect(sql);
        try{

            while (rs.next()) {

                Response r = new Response();
                r.setRid(rs.getString("rid"));
                r.setContent(rs.getString("content"));
                r.setRequestTime(rs.getDate("r_time"));
                r.setResponseTime(rs.getDate("p_time"));
                r.setLatitude(rs.getDouble("latitude"));
                r.setLongitude(rs.getDouble("longitude"));
                r.setState(rs.getString("state"));
                r.setValue(rs.getDouble("value"));
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
        ViewMyResponse t=new ViewMyResponse();
        DB db=new DB();

        ArrayList<Response> s=t.selectMyResponseList("celia",db);
        for(int i = 0 ; i<s.size();i++) {
            Response r=s.get(i);
            System.out.println(r.getRid());
            System.out.println(r.getContent());
            System.out.println(r.getRequestTime());
            System.out.println(r.getResponseTime());
            System.out.println(r.getValue());
            System.out.println(r.getLatitude());

        }
    }
}
