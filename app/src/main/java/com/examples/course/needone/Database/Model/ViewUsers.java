package com.examples.course.needone.Database.Model;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.examples.course.needone.Database.Beans.Users;

import com.examples.course.needone.Database.DB;

public class ViewUsers {

    public ArrayList<Users> selectuserList(String username, DB db) {

        ArrayList<Users> list = new ArrayList<Users>();
        try{

            ResultSet rs=null;
            String sql="SELECT * FROM users";
            rs = db.doSelect(sql);

            while (rs.next()) {

                Users user = new Users();

                user.setUsername(rs.getString("username"));
                user.setDob(rs.getDate("dob"));
                user.setPassword(rs.getString("password"));
                user.setUrl(rs.getString("picurl"));
                user.setCredit(rs.getInt("credit"));
                list.add(user);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }



        return list;

    }
    public static void main(String args[]){
        ViewUsers t=new ViewUsers();
        DB db=new DB();
        ArrayList<Users> s=t.selectuserList("celia",db);

        for(int i = 0 ; i<s.size();i++) {
            Users u=s.get(i);
            System.out.println(u.getUsername());
            System.out.println(u.getDob());

        }
    }
}