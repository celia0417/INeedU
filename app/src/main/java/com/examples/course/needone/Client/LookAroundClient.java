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

public class LookAroundClient {
    private String url;
    public LookAroundClient(String URL)
    {
        url = URL;
    }
    public List<String> getAroundPosts(String userID, String sessionID, Double latitude, Double longitude)
    {
        List<String> result = new ArrayList<String>();
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(url);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("userID", userID));
            formparams.add(new BasicNameValuePair("sessionID", sessionID));
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
                    result = myResponse.getPosts();
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

}
