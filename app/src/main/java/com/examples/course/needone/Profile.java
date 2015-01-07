package com.examples.course.needone;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.examples.course.needone.Client.LogoutClient;
import com.examples.course.needone.Client.PostsClient;
import com.examples.course.needone.Client.ProfileClient;
import com.examples.course.needone.Client.ResponseClient;
import com.examples.course.needone.adapter.ExpandableListAdapter;
import com.examples.course.needone.model.ProfileBasic;
import com.examples.course.needone.model.Request;
import com.examples.course.needone.tools.Global;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jialu on 12/20/14.
 */
public class Profile extends Fragment implements View.OnClickListener, ExpandableListView.OnChildClickListener {

    View view;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    Button logout;
    List<Integer> listDataHeader;
    HashMap<Integer, List<Request>> listDataChild;
    ProfileBasic profileBasic;
    String indicator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        displayListView();

        logout.setOnClickListener(this);

        expListView.setOnChildClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }


        // TODO: replace
        // get data from database in the following helper function
        //profileBasicData("cc@columbia.edu");

        prepareListData();

        view = inflater.inflate(R.layout.tab_profile, container, false);
        logout = (Button)view.findViewById(R.id.logout_button);

        new GetProfileTask().execute();

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onClick(View v) {

        // Log out to main page
        new LogoutTask().execute();

    }

    @Override
    public boolean onChildClick (ExpandableListView parent, View v,
                                 int groupPosition, int childPosition, long id) {

        Request selectedValue = (Request)listDataChild.get(groupPosition).get(childPosition);

        indicator = listDataHeader.get(groupPosition).toString();
        new RequestDetailTask().execute(selectedValue);
        return true;
    }

    private void displayListView() {

        expListView = (ExpandableListView) getActivity().findViewById(R.id.profile_activitylist);
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

        //enables filtering for the contents of the given ListView
        expListView.setTextFilterEnabled(true);
    }


    // prepare basic profile data from server
    private void profileBasicData(String email) {
        profileBasic = new ProfileBasic();

        profileBasic.setEmail(email);
        profileBasic.setDob("1990-01-01");
        profileBasic.setCredit("100");
    }

    // prepare expandable list view data
    private void prepareListData() {
        listDataHeader = new ArrayList<Integer>();
        listDataChild = new HashMap<Integer, List<Request>>();

        // Adding group data; DO NOT CHANGE!!!
        listDataHeader.add(0);
        listDataHeader.add(1);
        listDataHeader.add(2);

        // TODO: Adding requests from db
        // use constructor
        Request r1 = new Request();
        r1.setId("request1");
        r1.setLocation("loc1");
        r1.setTime("t1");
        r1.setExptime("10d");
        r1.setUser("user1");
        r1.setCredit("10");
        r1.setContent("helloworld");
        ArrayList<String> rl1 = new ArrayList<String>();
        rl1.add("response1-1");
        rl1.add("response1-2");
        r1.setResponses(rl1);

        Request r2 = new Request();
        r2.setId("request2");
        r2.setLocation("loc222");
        r2.setTime("t22");
        r2.setExptime("15d");
        r2.setUser("user22");
        r2.setCredit("100");
        r2.setContent("helloworld2222");
        ArrayList<String> rl2 = new ArrayList<String>();
        rl2.add("response2-1");
        rl2.add("response2-2");
        r2.setResponses(rl2);

        Request r3 = new Request();
        r3.setId("request3");
        r3.setLocation("loc3");
        r3.setTime("t1");
        r3.setExptime("20d");
        r3.setUser("user1");
        r3.setCredit("10");
        r3.setContent("helloworld");
        ArrayList<String> rl3 = new ArrayList<String>();
        rl3.add("response3-1");
        rl3.add("response3-2");
        r3.setResponses(rl3);

        Request r4 = new Request();
        r4.setId("request4");
        r4.setLocation("loc2224");
        r4.setTime("t22");
        r4.setExptime("25d");
        r4.setUser("user22");
        r4.setCredit("100");
        r4.setContent("helloworld2222");
        ArrayList<String> rl4 = new ArrayList<String>();
        rl4.add("response4-1");
        rl4.add("response4-2");
        r4.setResponses(rl4);

        // Adding child data according to which state they belong to
        // belong to post
        new GetMyPostsTask().execute();

        // belong to participation
        List<Request> participate = new ArrayList<Request>();
        participate.add(r1);
        participate.add(r2);
        participate.add(r3);

        // belong to response
        List<Request> response = new ArrayList<Request>();
        response.add(r4);

        listDataChild.put(listDataHeader.get(1), participate);
        listDataChild.put(listDataHeader.get(2), response);
    }
    private class LogoutTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            // TODO Auto-generated method stub
            LogoutClient logoutClient = new LogoutClient("http://servlet-xqqeybyk3j.elasticbeanstalk.com/Logout");
            return logoutClient.logout(Global.userID,Global.sessionID);
        }//close doInBackground

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                Intent logoutIntent = new Intent(getActivity(), LoginScreen.class);
                startActivity(logoutIntent);
            }
            else
            {
                System.out.println("Failed to logout");
            }
        }//close onPostExecute
    }// close

    private class GetProfileTask extends AsyncTask<String, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(String... params) {
            // TODO Auto-generated method stub
            ProfileClient profileClient = new ProfileClient("http://servlet-xqqeybyk3j.elasticbeanstalk.com/Profile");
            return profileClient.getUserProfile(Global.userID,Global.sessionID);
        }//close doInBackground

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            try {
                JSONObject obj = new JSONObject(result.get(0));
                profileBasic = new ProfileBasic();
                profileBasic.setEmail(obj.getString("username"));
                profileBasic.setCredit(obj.getString("credit"));
                profileBasic.setDob(obj.getString("dob"));

            } catch (JSONException e) {
                //e.printStackTrace();
                profileBasic.setDob("-");
            }
            TextView profileEmail = (TextView)view.findViewById(R.id.profile_email);
            TextView profileDob = (TextView)view.findViewById(R.id.profile_dob);
            TextView profileCredit = (TextView)view.findViewById(R.id.profile_credit);

            profileEmail.setText("Email: " + profileBasic.getEmail());
            profileDob.setText("Date of Birth: " + profileBasic.getDob());
            profileCredit.setText("Credit: " + profileBasic.getCredit());
        }//close onPostExecute
    }// close

    private class GetMyPostsTask extends AsyncTask<String, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(String... params) {
            // TODO Auto-generated method stub
            PostsClient postsClient = new PostsClient("http://servlet-xqqeybyk3j.elasticbeanstalk.com/Posts");
            return postsClient.getUserPosts(Global.userID,Global.sessionID);
        }//close doInBackground

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            int n = result.size();
            List<Request> post = new ArrayList<Request>();
            for (int i = 0; i < n; i++)
            {
                try {
                    JSONObject obj = new JSONObject(result.get(i));
                    Request req = new Request();
                    req.setId(obj.getString("requestid"));
                    req.setUser(obj.getString("username"));
                    req.setContent(obj.getString("content"));
                    req.setCredit(obj.getString("credit"));
                    String lati = obj.getString("latitude"), longi = obj.getString("longitude");

                    int index = lati.indexOf(".");
                    if (index < lati.length() - 5)
                        lati = lati.substring(0,index+5);

                    index = longi.indexOf(".");
                    if (index < longi.length() - 5)
                        longi = lati.substring(0,index+5);

                    req.setLocation("("+lati+" , "+longi+")");
                    req.setTime(obj.getString("request_time"));
                    req.setExptime(obj.getString("expire_time"));
                    System.out.println(req.getContent());
                    post.add(req);

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
            listDataChild.put(listDataHeader.get(0), post); // Header, Child data
        }//close onPostExecute
    }// close

    private class RequestDetailTask extends AsyncTask<Request, Void, Request> {
        @Override
        protected Request doInBackground(Request... params) {
            Request req = params[0];
            System.out.println("Get responses");
            System.out.println(req.getId());
            ResponseClient responseClient = new ResponseClient("http://servlet-xqqeybyk3j.elasticbeanstalk.com/Response");
            ArrayList<String> resp = responseClient.getResponse(Global.userID, Global.sessionID, req.getId());
            ArrayList<String> responseUsers = new ArrayList<String>();
            int n = resp.size();
            for (int i=0; i<n; i++)
            {
                try {
                    JSONObject obj = new JSONObject(resp.get(i));
                    responseUsers.add(obj.getString("username"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            req.setResponses(responseUsers);
            return req;
        }

        protected void onPostExecute(Request result) {
            Request selectedValue = result;

            Intent intent = new Intent(getActivity(), RequestDetail.class);

            intent.putExtra("indicator", indicator);

            //TODO: change to pass json, same with profile
            intent.putExtra("id", selectedValue.getId());
            intent.putExtra("user", selectedValue.getUser());
            intent.putExtra("location", selectedValue.getLocation());
            intent.putExtra("time", selectedValue.getTime());
            intent.putExtra("exptime", selectedValue.getExptime());
            intent.putExtra("content", selectedValue.getContent());
            intent.putExtra("credit", selectedValue.getCredit());
            intent.putExtra("responses", selectedValue.getResponses());

            startActivity(intent);
        }
    }

}
