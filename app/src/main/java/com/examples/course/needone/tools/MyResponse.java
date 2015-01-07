package com.examples.course.needone.tools;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.examples.course.needone.Database.Beans.*;




public class MyResponse {
    private JSONObject response;
    public MyResponse(String Response)
    {
    	try {
			response = new JSONObject(Response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public MyResponse()
    {
    	response = new JSONObject();
    }
    
    public String toString()
    {
    	return response.toString();
    }
    
    public String getLoginResult()
    {
    	String result = "";
    	try {
			result = response.getString("LoginResult");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
    
    public void setLoginResult(String LoginResult) throws JSONException
    {
    	response.put("LoginResult", LoginResult);
    }
    
    public String getSessionID()
    {
    	String result = "";
    	try {
			result = response.getString("SessionID");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
    
    public void setSessionID(String SessionID) throws JSONException
    {
    	response.put("SessionID", SessionID);
    }
    
    public int getLoginStatus()
    {
    	int status = -1; //-1 for error
    	try {
    		String result = "";
			result = response.getString("LoginStatus");
			status = Integer.parseInt(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return status;
    }
    
    public void setLoginStatus(boolean Status) throws JSONException
    {
    	if (Status)
    		response.put("LoginStatus", "1");
    	else
    		response.put("LoginStatus", "0");
    }
    
    public List<String> getPosts()
    {
    	List<String> posts = new ArrayList<String>();
    	try {
			JSONArray array = response.getJSONArray("Posts");
			int n = array.length();
			for (int i = 0; i < n; i++)
			{
				posts.add(array.get(i).toString());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return posts;
    }
    
    public void setPosts(List<DBRequest> dbreq) throws JSONException
    {
    	int n = dbreq.size();
    	JSONArray array = new JSONArray();
    	for (int i = 0; i < n; i++)
    	{
    	    JSONObject obj = new JSONObject();
    		obj.put("latitude", dbreq.get(i).getLatitude());
    		obj.put("longitude", dbreq.get(i).getLongitude());
    		obj.put("distance", dbreq.get(i).getDistance());
    		obj.put("requestid", dbreq.get(i).getRid());
    		obj.put("content", dbreq.get(i).getContent());
    		obj.put("request_time", dbreq.get(i).getRequestTime());
    		obj.put("expire_time", dbreq.get(i).getExpireTime());
    		obj.put("requeststatus", dbreq.get(i).getState());
    		obj.put("username", dbreq.get(i).getUsername());
    		obj.put("credit", dbreq.get(i).getCredit());
    		obj.put("url", dbreq.get(i).getUrl());


    		//TODO:longitude, 
    		
    		array.put(obj);
    	}
    	response.put("Posts", array);
    }
    
    public ArrayList<String> getMyPosts()
    {
    	ArrayList<String> posts = new ArrayList<String>();
    	try {
			JSONArray array = response.getJSONArray("MyPosts");
			int n = array.length();
			for (int i = 0; i < n; i++)
			{
				posts.add(array.get(i).toString());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return posts;
    }
    public void setMyPosts(List<DBRequest> dbreq) throws JSONException
    {
    	int n = dbreq.size();
    	JSONArray array = new JSONArray();
    	for (int i = 0; i < n; i++)
    	{
    	    JSONObject obj = new JSONObject();
    		obj.put("latitude", dbreq.get(i).getLatitude());
    		obj.put("longitude", dbreq.get(i).getLongitude());
    		obj.put("requestid", dbreq.get(i).getRid());
    		obj.put("content", dbreq.get(i).getContent());
    		obj.put("request_time", dbreq.get(i).getRequestTime());
    		obj.put("expire_time", dbreq.get(i).getExpireTime());
    		obj.put("requeststatus", dbreq.get(i).getState());
    		obj.put("username", dbreq.get(i).getUsername());
    		obj.put("credit", dbreq.get(i).getCredit());
    		obj.put("url", dbreq.get(i).getUrl());


    		//TODO:longitude, 
    		
    		array.put(obj);
    	}
    	response.put("MyPosts", array);
    }
    
    public ArrayList<String> getComments()
    {
    	ArrayList<String> comments = new ArrayList<String>();
    	try {
			JSONArray array = response.getJSONArray("Comments");
			int n = array.length();
			for (int i = 0; i < n; i++)
			{
				comments.add(array.get(i).toString());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return comments;
    }
    
    
    public void setComments(ArrayList<Comment> comments) throws JSONException
    {
    	int n = comments.size();
    	JSONArray array = new JSONArray();
    	for (int i = 0; i < n; i++)
    	{
    	    JSONObject obj = new JSONObject();
    		obj.put("comment_id", comments.get(i).getCommentid());
    		obj.put("comment", comments.get(i).getComment());
    		obj.put("comment_time", comments.get(i).getCommentTime());
            obj.put("userID", comments.get(i).getUsername());
    		//TODO:longitude, 
    		
    		array.put(obj);
    	}
    	response.put("Comments", array);
    }
    
    public boolean getStatus()
    {
    	boolean result = false;
    	try {
			result = response.getString("Status").equals("1");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
    
    public void setStatus(Boolean Status) throws JSONException
    {
    	if (Status)
    	    response.put("Status", "1");
    	else
    		response.put("Status", "0");
    }

    public ArrayList<String> getResponse()
    {
        ArrayList<String> posts = new ArrayList<String>();
        try {
            JSONArray array = response.getJSONArray("Response");
            int n = array.length();
            for (int i = 0; i < n; i++)
            {
                posts.add(array.get(i).toString());
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return posts;
    }
    public void setResponse(List<Response> res) throws JSONException
    {
        int n = res.size();
        JSONArray array = new JSONArray();
        for (int i = 0; i < n; i++)
        {
            JSONObject obj = new JSONObject();

            obj.put("responsetime", res.get(i).getResponseTime());
            obj.put("username", res.get(i).getUsername());
            obj.put("value", res.get(i).getValue());

            //TODO:longitude,

            array.put(obj);
        }
        response.put("Response", array);
    }

    public void setMyResponse(List<Response> res) throws JSONException
    {
        int n = res.size();
        JSONArray array = new JSONArray();
        for (int i = 0; i < n; i++)
        {
            JSONObject obj = new JSONObject();


            obj.put("requestID", res.get(i).getRid());
            obj.put("content", res.get(i).getContent());   //request content
            obj.put("request_time", res.get(i).getRequestTime());
            obj.put("response_time", res.get(i).getResponseTime());
            obj.put("latitude", res.get(i).getLatitude());
            obj.put("longitude", res.get(i).getLongitude());
            obj.put("state", res.get(i).getState());
            obj.put("value", res.get(i).getValue());

            array.put(obj);
        }
        response.put("MyResponse", array);
    }
    public ArrayList<String> getMyResponse()
    {
        ArrayList<String> posts = new ArrayList<String>();
        try {
            JSONArray array = response.getJSONArray("MyResponse");
            int n = array.length();
            for (int i = 0; i < n; i++)
            {
                posts.add(array.get(i).toString());
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return posts;
    }

    public void setUsers(List<Users> res) throws JSONException
    {
        int n = res.size();
        JSONArray array = new JSONArray();
        for (int i = 0; i < n; i++)
        {
            JSONObject obj = new JSONObject();

            obj.put("username", res.get(i).getUsername());
            obj.put("dob", res.get(i).getDob());   //request content
            obj.put("picrurl", res.get(i).getUrl());
            obj.put("credit", res.get(i).getCredit());


            array.put(obj);
        }
        response.put("Users", array);
    }

    public ArrayList<String> getUsers()
    {
        ArrayList<String> users = new ArrayList<String>();
        try {
            JSONArray array = response.getJSONArray("Users");
            int n = array.length();
            for (int i = 0; i < n; i++)
            {
                users.add(array.get(i).toString());
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return users;
    }

    public static void main(String[] args) throws JSONException {
    	MyResponse myResponse = new MyResponse("{\"Posts\":[],\"LoginStatus\":\"1\"}");
        System.out.println(myResponse.getLoginStatus());
    }
}