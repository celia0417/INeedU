package com.examples.course.needone.Client;

/**
 * Created by Chang on 12/22/14.
 */

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.UnsupportedEncodingException;
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

public class ResponseClient {
    private String url;
    public ResponseClient(String URL)
    {
        url = URL;
    }
    public ArrayList<String> getResponse(String userID,String sessionID,String requestID)
    {
        ArrayList<String> result = new ArrayList<String>();
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(url);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            String state="get";
            formparams.add(new BasicNameValuePair("userID", userID));
            formparams.add(new BasicNameValuePair("sessionID", sessionID));
            formparams.add(new BasicNameValuePair("requestID", requestID));
            formparams.add(new BasicNameValuePair("status", state));

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
                System.out.println(sb.toString());
                MyResponse myResponse = new MyResponse(sb.toString());
                int status = myResponse.getLoginStatus();
                System.out.println(status);

                if (status == 1)
                {
                    result = myResponse.getResponse();
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

        return result;
    }

    public ArrayList<String> getUserResponse(String userID, String sessionID)
    {
        ArrayList<String> res = new ArrayList<String>();
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(url);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            String state="getMy";
            formparams.add(new BasicNameValuePair("status", state));
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
                    res = myResponse.getMyResponse();
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
        return res;
    }

    public boolean postResponse(String userID,String sessionID,String requestID)
    {

        boolean result = false;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(url);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            String state="post";
            formparams.add(new BasicNameValuePair("userID", userID));
            formparams.add(new BasicNameValuePair("sessionID", sessionID));
            formparams.add(new BasicNameValuePair("requestID", requestID));
            formparams.add(new BasicNameValuePair("status", state));

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
                    result = myResponse.getStatus();
                    System.out.println("Login succeed");
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

        return result;
    }

    public static void main(String[] args) {
        String url = "http://server-npcxbe6d2s.elasticbeanstalk.com/Response";
        ResponseClient client = new ResponseClient(url);
        System.out.println(client.postResponse("celia","30001","20001"));
        System.out.println(client.getResponse("celia","30001","20001"));
        System.out.println(client.getUserResponse("celia","30001"));

    }

}