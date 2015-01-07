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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.course.needone.Client.LookAroundClient;
import com.examples.course.needone.Client.ResponseClient;
import com.examples.course.needone.adapter.RequestAdapter;
import com.examples.course.needone.model.Request;
import com.examples.course.needone.tools.Global;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jialu on 12/18/14.
 */
public class RequestList extends Fragment {

    //OnURLSelectedListener mListener;

    private List<Request> reqList = new ArrayList<Request>();
    private RequestAdapter adapter;
    private Double latitude, longitude;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v("RequestList", "===!!!=============onCreate()");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (Global.latitude != null && Global.longitude!=null) {
            latitude = Global.latitude;
            longitude = Global.longitude;
        }
        latitude = 40.8080; longitude = -73.9606;
        LookAroundTask task = new LookAroundTask();
        task.execute(latitude, longitude);
        //Generate list View from ArrayList


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.v("ListFragment", "onCreateView()");
        Log.v("ListContainer", container == null ? "true" : "false");
        Log.v("ListsavedInstanceState", savedInstanceState == null ? "true" : "false");
        if (container == null) {
            return null;
        }

        // if current list is not null, clear it;
        if(reqList != null) {
            reqList.clear();
        }

        View view = inflater.inflate(R.layout.tab_requestlist, container, false);
        return view;
    }


    // Container Activity must implement this interface
//    public interface OnURLSelectedListener {
//        public void onURLSelected(String URL);
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnURLSelectedListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString() + " must implement OnURLSelectedListener");
//        }
    }

    private class LookAroundTask extends AsyncTask<Double, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Double... params) {
            // TODO Auto-generated method stub
            Double latitude = params[0], longitude = params[1];
            LookAroundClient lookAroundClient = new LookAroundClient("http://servlet-xqqeybyk3j.elasticbeanstalk.com/LookAround");
            return lookAroundClient.getAroundPosts(Global.userID,Global.sessionID,latitude,longitude);
        }//close doInBackground

        @Override
        protected void onPostExecute(List<String> result) {
            int n = result.size();
            for (int i = 0; i < n; i++)
            {
                try {
                    JSONObject obj = new JSONObject(result.get(i));
                    Request req = new Request();
                    req.setId(obj.getString("requestid"));
                    req.setUser(obj.getString("username"));
                    req.setContent(obj.getString("content"));
                    req.setCredit(obj.getString("credit"));
                    Double distance = Double.parseDouble(obj.getString("distance")) * 69.1;

                    String d = distance.toString();
                    int index = d.indexOf(".");
                    if (index < d.length() - 2)
                        d = d.substring(0,index+2);
                    req.setLocation("in "+ d +" "+"miles");
                    req.setTime(obj.getString("request_time"));
                    req.setExptime(obj.getString("expire_time"));
                    System.out.println(req.getContent());
                    reqList.add(req);

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
                displayListView();
            }

        }//close onPostExecute
    }// close validateUserTask

    private void displayListView() {

        ListView listView = (ListView) getView().findViewById(R.id.list);
        // Initialize the customized adapter
        adapter = new RequestAdapter(getActivity(), reqList);
        // Assign adapter to ListView
        listView.setAdapter(adapter);

        //enables filtering for the contents of the given ListView
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Request selectedValue = (Request)parent.getItemAtPosition(position);

                new RequestDetailTask().execute(selectedValue);
            }
        });

    }

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

            intent.putExtra("indicator", "3");

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

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        android.util.Log.d("mark", "onPause()--------->news Fragment");
    }
}


