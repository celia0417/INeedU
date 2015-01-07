package com.examples.course.needone;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.examples.course.needone.Client.DealClient;
import com.examples.course.needone.tools.Global;

import java.util.ArrayList;

/**
 * Created by Jialu on 12/22/14.
 */
public class HelperList extends Activity {
    ArrayList<String> responses;
    String requestID;
    String myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myhelper_list);

        responses = getIntent().getStringArrayListExtra("list");
        requestID = getIntent().getStringExtra("requestID");
        System.out.println("responses?" + responses);

        ListView helperLv = (ListView)findViewById(R.id.help_lv);

        ArrayAdapter<String> helperAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, responses);
        helperLv.setAdapter(helperAdapter);

        helperLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myHelper = (String)parent.getItemAtPosition(position);
                Toast.makeText(HelperList.this,
                        "You have chosen your helper: " + myHelper, Toast.LENGTH_LONG).show();

                new DealTask().execute(myHelper);

            }
        });
    }

    private class DealTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            // TODO Auto-generated method stub
            String responseUserID = params[0];
            DealClient dealClient = new DealClient("http://servlet-xqqeybyk3j.elasticbeanstalk.com/Deal");

            return dealClient.make(Global.userID,Global.sessionID,requestID,responseUserID);
        }//close doInBackground

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                //TODO: jump to previous page
                System.out.println("Deal!");
            }
            else
            {
                //TODO: fail
                System.out.println("Fail");
            }
        }//close onPostExecute
    }// close validateUserTask

}
