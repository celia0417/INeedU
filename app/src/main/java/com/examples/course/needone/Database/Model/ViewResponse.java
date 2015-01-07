package com.examples.course.needone.Database.Model;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.examples.course.needone.Database.DB;
import com.examples.course.needone.Database.Beans.DBRequest;
import com.examples.course.needone.Database.Beans.Response;
/**
 * Created by Chang on 12/21/14.
 */
public class ViewResponse {
    public ArrayList<Response> selectResponseList(String requestID,DB db) {

        ArrayList<Response> list = new ArrayList<Response>();
        ResultSet rs=null;
        String sql="select * from response p, request r where p.rid='"+requestID+"' and p.rid=r.rid";

        rs = db.doSelect(sql);
        try{


            while (rs.next()) {

                Response r = new Response();
                r.setResponseTime(rs.getDate("reponsetime"));
                r.setUsername(rs.getString("username"));
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
        ViewResponse t=new ViewResponse();
        DB db=new DB();
        ArrayList<Response> s=t.selectResponseList("20001",db);
        for(int i = 0 ; i<s.size();i++) {
            Response r=s.get(i);
//			System.out.println(r.getResponseid());
            System.out.println(r.getUsername());

        }
    }
}
