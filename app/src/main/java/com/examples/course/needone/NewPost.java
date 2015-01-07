package com.examples.course.needone;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.course.needone.Client.PostsClient;
import com.examples.course.needone.tools.Global;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jialu on 12/16/14.
 */
public class NewPost extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner locationSpinner, timeSpanner, contentSpanner;
    Button postButton;
    TextView postmsg;
    String location, exptime, content;
    Double longitude, latitude;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_newpost, null);

        locationSpinner = (Spinner)view.findViewById(R.id.location_spinner);
        String[] locations = new String[]{"Downtown Manhattan", "Midtown Manhattan",
                "UpperEast Manhattan", "UpperWest Manhattan"};
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, locations);
        locationSpinner.setAdapter(locationAdapter);

        timeSpanner = (Spinner)view.findViewById(R.id.exptime_spinner);
        String[] times = new String[]{"0","1", "2", "3", "4", "5", "6", "7"};
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, times);
        timeSpanner.setAdapter(timeAdapter);

        contentSpanner = (Spinner)view.findViewById(R.id.content_spinner);
        String[] contents = new String[]{"1-Find a restaurant", "2-Get a lift",
                "3-Clean house together", "4-Moving help", "5-Search for a study group"};
        ArrayAdapter<String> contentAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, contents);
        contentSpanner.setAdapter(contentAdapter);

        postButton = (Button)view.findViewById(R.id.post_button);

        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        postButton.setOnClickListener(this);

        locationSpinner.setOnItemSelectedListener(this);
        timeSpanner.setOnItemSelectedListener(this);
        contentSpanner.setOnItemSelectedListener(this);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        location = locationSpinner.getSelectedItem().toString();

        switch (location) {
            case "Downtown Manhattan":
                latitude = 40.7230;
                longitude = -74.0006;
                break;
            case "Midtown Manhattan":
                latitude = 40.7549;
                longitude =-73.9840;
                break;
            case "UpperEast Manhattan":
                latitude = 40.7736;
                longitude = -73.9566;
                break;
            case "UpperWest Manhattan":
                latitude = 40.7870;
                longitude = -73.9754;
                break;
            default:
                latitude = 40.8075;
                longitude = -73.9626;
                break;
        }
        Calendar c = Calendar.getInstance(); // starts with today's date and time
        c.add(Calendar.DAY_OF_YEAR, Integer.parseInt(timeSpanner.getSelectedItem().toString()));  // advances day by 2
        Date date = c.getTime(); // gets modified time
        exptime = dateFormat.format(date);

        content = contentSpanner.getSelectedItem().toString();

//        System.out.println(locationSpinner.getSelectedItem().toString());
//        System.out.println(timeSpanner.getSelectedItem().toString());
//        System.out.println(contentSpanner.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    @Override
    public void onClick(View v) {

        new PostTask().execute();
    }

    private class PostTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            // TODO Auto-generated method stub
            PostsClient postsClient = new PostsClient("http://servlet-xqqeybyk3j.elasticbeanstalk.com/Posts");

            return postsClient.post(Global.userID,Global.sessionID,"None",content,exptime,latitude,longitude);
        }//close doInBackground

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                Toast.makeText(getActivity(),
                        "Post Successfully! Please wait for your helper...", Toast.LENGTH_LONG).show();
                // postmsg = (TextView)getActivity().findViewById(R.id.post_success);
                // postmsg.setText("Post Successfully! Please wait for your helper...");
            }
            else
            {
                Toast.makeText(getActivity(),
                "Post Unsuccessfully! Please retry...", Toast.LENGTH_LONG).show();
                // postmsg = (TextView)getActivity().findViewById(R.id.post_success);
                // postmsg.setText("Post unsuccessfully!");
            }
        }//close onPostExecute
    }// close validateUserTask
}
