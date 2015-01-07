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

public class LogoutClient {
    private String url;
    public LogoutClient(String URL)
    {
        url = URL;
    }
    public boolean logout(String userID, String sessionID)
    {
        //status: -2~error, -1~no userID, 0~wrong password, 1~successful
        boolean status = false;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(url);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
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
                if (myResponse.getStatus())
                    return true;

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
        String url = "http://servlet-xqqeybyk3j.elasticbeanstalk.com/Logout";
        LogoutClient client = new LogoutClient(url);
        System.out.println(client.logout("weilan@gmail.com","bf9b9373-a563-48ce-a8e4-21b9d5579e57"));
    }

}