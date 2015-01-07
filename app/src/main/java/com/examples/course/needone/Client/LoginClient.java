package com.examples.course.needone.Client;

/**
 * 
 * @author Fallenmoon
 *
 */

import com.examples.course.needone.tools.Global;
import com.examples.course.needone.tools.MyResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;





public class LoginClient {
	private String url;
	public LoginClient(String URL)
	{
		url = URL;
	}
	public int login(String userID, String password)
	{
		//status: -2~error, -1~no userID, 0~wrong password, 1~successful
		int status = -2;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost(url);
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("userID", userID));
			formparams.add(new BasicNameValuePair("password", password));
	        UrlEncodedFormEntity formEntity;
			formEntity = new UrlEncodedFormEntity(formparams);
			request.setEntity(formEntity);
	        HttpResponse response = client.execute(request);
	        if (response.getStatusLine().getStatusCode() == 200)
	        {
	        	BufferedReader in = null;
		        in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

	            StringBuffer sb = new StringBuffer("");
	            String line = "";
	            String NL = System.getProperty("line.separator");
	            while ((line = in.readLine()) != null) {
	                sb.append(line + NL);
	            }
	            in.close();

	            MyResponse myResponse = new MyResponse(sb.toString());
	            String LoginResult = myResponse.getLoginResult();
	            if (LoginResult.length() > 0)
	                status = Integer.parseInt(LoginResult);
	            System.out.println(status);
	            
	            /**
	             * login successfully
	             */
	            if (status == 1)
	            {
	            	String sessionID = myResponse.getSessionID();
                    Global.userID = userID;
                    Global.sessionID = sessionID;
	            	System.out.println(sessionID);
	            	//TODO: Save UserID and SessionID
	            }
		        
	        }
	        else
	        {
		        System.out.println(response.getStatusLine());
	        }
	        
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return status;
	}
    
	public static void main(String[] args) {
		String url = "http://servlet-xqqeybyk3j.elasticbeanstalk.com/Login";
		LoginClient client = new LoginClient(url);
		client.login("hello", "world");
	}
    
}
