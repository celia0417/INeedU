package com.examples.course.needone.Client;

/**
 * Created by Chang on 12/21/14.
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

public class RegisterClient {
    private String url;
    public RegisterClient(String URL)
    {
        url = URL;
    }
    public int registerQuery(String userID, String password)
    {
        int status = -1;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(url);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("action", "query"));
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
                if (myResponse.getStatus())
                {
                    status = 1;
                }
                else
                {
                    status = 0;
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

    public int registerConfirm(String userID, String code)
    {
        int status = -1;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(url);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("action", "confirm"));
            formparams.add(new BasicNameValuePair("userID", userID));
            formparams.add(new BasicNameValuePair("code", code));
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
                if (myResponse.getStatus())
                {
                    status = 1;
                }
                else
                {
                    status = 0;
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

    public static void main(String[] args)
    {
        String url = "http://servlet-xqqeybyk3j.elasticbeanstalk.com/Register";
        RegisterClient client = new RegisterClient(url);
        String FROM = "kevin920302@gmail.edu";
        System.out.println(client.registerQuery(FROM, "wwx"));
        System.out.println(client.registerConfirm(FROM, "abcd"));
    }

}
