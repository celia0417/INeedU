package com.examples.course.needone.Database.Model;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.examples.course.needone.Database.DB;
import com.examples.course.needone.Database.Beans.DBRequest;

public class ViewClose {
    public ArrayList<DBRequest> selectcloseList(Double la,Double lo,DB db) {

        ArrayList<DBRequest> list = new ArrayList<DBRequest>();
        ResultSet rs=null;
        String sql="select * from (select sqrt(power((latitude-("+la+")),2)+power((longitude-("+lo+")),2)) as dis, rid from request)s, request r, users u,post p where s.rid=r.rid and p.rid=r.rid and u.username=p.username and dis<10 order by dis";
        rs = db.doSelect(sql);
        try{

            while (rs.next()) {

                DBRequest r = new DBRequest();

                r.setRid(rs.getString("rid"));
                r.setContent(rs.getString("content"));
                r.setRequestTime(rs.getDate("REQUESTTIME"));
                r.setExpireTime(rs.getDate("expire_time"));
                r.setLatitude(rs.getDouble("latitude"));
                r.setLongitude(rs.getDouble("longitude"));
                r.setDistance(rs.getDouble("dis"));
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
        ViewClose t=new ViewClose();
        DB db=new DB();
        ArrayList<DBRequest> s=t.selectcloseList(23.0,100.0,db);
        for(int i = 0 ; i<s.size();i++) {
            DBRequest r=s.get(i);
            System.out.println(r.getRid());
            System.out.println(r.getContent());
            System.out.println(r.getRequestTime());
            System.out.println(r.getLatitude());

            System.out.println(r.getUrl());
            System.out.println(r.getDistance());

        }
    }
}
