package com.examples.course.needone.Client;

/**
 * Created by Chang on 12/22/14.
 */


        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.UnsupportedEncodingException;
        import java.lang.reflect.Array;
        import java.util.ArrayList;
        import java.util.List;

        import org.apache.http.HttpResponse;
        import org.apache.http.NameValuePair;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.message.BasicNameValuePair;

        import com.examples.course.needone.tools.MyResponse;

public class ProfileClient {
    private String url;
    public ProfileClient(String URL)
    {
        url = URL;
    }
    public boolean update(String userID, String sessionID, String dob,String picurl)
    {
        boolean success = false;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(url);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("action", "update"));
            formparams.add(new BasicNameValuePair("userID", userID));
            formparams.add(new BasicNameValuePair("sessionID", sessionID));
            if(dob!=null){
                formparams.add(new BasicNameValuePair("dob", dob));
            }else if(picurl!=null){
                formparams.add(new BasicNameValuePair("PicUrl", picurl));
            }

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
                int status = myResponse.getLoginStatus();
                if (status == 1)
                {
                    success = myResponse.getStatus();
                }
                else if(status == 0)
                {
                    System.out.println("Not Login");
                }
                else
                {
                    System.out.println("Error");
                }
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
        return success;
    }

    public ArrayList<String> getUserProfile(String userID, String sessionID)
    {
        ArrayList<String> users = new ArrayList<String>();
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(url);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("action", "get"));
            formparams.add(new BasicNameValuePair("userID", userID));
            formparams.add(new BasicNameValuePair("sessionID", sessionID));
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
                int status = myResponse.getLoginStatus();
                if (status == 1)
                {
                    users = myResponse.getUsers();
                }
                else if(status == 0)
                {
                    System.out.println("Not Login");
                }
                else
                {
                    System.out.println("Error");
                }
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
        return users;
    }
    public static void main(String[] args) {
        String url = "http://server-npcxbe6d2s.elasticbeanstalk.com/Profile";
        ProfileClient client = new ProfileClient(url);
        String FROM = "kevin920302@gmail.edu";
        System.out.println(client.getUserProfile("celia", "30001"));
        System.out.println(client.update(FROM, "8c51804c-9d94-4d4d-8244-d0b194926f07",null,null));
        System.out.println(client.update("celia", "30001","17-April-1990",null));


    }
}