package com.examples.course.needone.Database.Model;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.examples.course.needone.Database.DB;
import com.examples.course.needone.Database.Beans.Temp;

public class ViewTemp {
	public ArrayList<Temp> selecttempid(String username) {

		ArrayList<Temp> list = new ArrayList<Temp>();
		ResultSet rs=null;  
		DB db=new DB();  
		String sql="SELECT * FROM temp where username='"+username+"'";
		rs = db.doSelect(sql);

		try{
			while (rs.next()) {

				Temp r = new Temp();

				r.setUsername(rs.getString("username"));
				r.setTempid(rs.getString("tempid"));
				r.setTime(rs.getDate("time"));


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
		ViewTemp t=new ViewTemp();
		ArrayList<Temp> s=t.selecttempid("celia");
		
		for(int i = 0 ; i<s.size();i++) {
			Temp temp=s.get(i);
			System.out.println(temp.getTempid());
			System.out.println(temp.getUsername());
			System.out.println(temp.getTime());


		}
	}
}
