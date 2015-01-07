package com.examples.course.needone.Client;

/**
 * Created by Chang on 12/22/14.
 */

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.UnsupportedEncodingException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
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

public class PostsClient {
    private String url;
    public PostsClient(String URL)
    {
        url = URL;
    }
    public boolean post(String userID, String sessionID, String subject, String content, String expireTime, Double latitude,Double longitude)
    {
        boolean success = false;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(url);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("action", "post"));
            formparams.add(new BasicNameValuePair("userID", userID));
            formparams.add(new BasicNameValuePair("sessionID", sessionID));
            formparams.add(new BasicNameValuePair("subject", subject));
            formparams.add(new BasicNameValuePair("content", content));
            formparams.add(new BasicNameValuePair("expireTime", expireTime));
            formparams.add(new BasicNameValuePair("latitude", latitude.toString()));
            formparams.add(new BasicNameValuePair("longitude", longitude.toString()));
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

    public ArrayList<String> getUserPosts(String userID, String sessionID)
    {
        ArrayList<String> posts = new ArrayList<String>();
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
                    posts = myResponse.getMyPosts();
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
        return posts;
    }

    public static void main(String[] args) {
        String url = "http://server-npcxbe6d2s.elasticbeanstalk.com/Posts";
        PostsClient client = new PostsClient(url);
        Date cutime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String expiretime = format.format(cutime);
        System.out.println(client.post("kevin920302@gmail.edu","ea6ab95d-dc4f-44fb-ac6e-e1adc86524e1","study","help me with english",expiretime,23.5,101.0));
        System.out.println(client.getUserPosts("kevin920302@gmail.edu","ea6ab95d-dc4f-44fb-ac6e-e1adc86524e1"));

    }
}