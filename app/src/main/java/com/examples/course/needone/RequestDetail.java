package com.examples.course.needone;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.examples.course.needone.Client.CommentClient;
import com.examples.course.needone.Client.ResponseClient;
import com.examples.course.needone.Database.Beans.Response;
import com.examples.course.needone.adapter.CommentAdapter;
import com.examples.course.needone.adapter.RequestAdapter;
import com.examples.course.needone.model.Comment;
import com.examples.course.needone.model.Request;
import com.examples.course.needone.tools.Global;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jialu on 12/20/14.
 */
public class RequestDetail extends Activity {

    Request request;
    String reps = "";
    String id, user, location, time, expiretime, content, credit, indicator;
    ArrayList<String> responses;
    ArrayList<Comment> comments = new ArrayList<Comment>();
    ListView listView;
    CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_detail_view);

        // TODO: get comment by getIntent() with json string
        //fakeComment();
        new GetCommentTask().execute(this);


        // indicating where the page was going from
        indicator = getIntent().getStringExtra("indicator");

        // TODO: if json string passed, replace
        id = getIntent().getStringExtra("id");
        user = getIntent().getStringExtra("user");
        location = getIntent().getStringExtra("location");
        time = getIntent().getStringExtra("time");
        expiretime = getIntent().getStringExtra("exptime");
        content = getIntent().getStringExtra("content");
        credit = getIntent().getStringExtra("credit");
        responses = getIntent().getStringArrayListExtra("responses");

        final TextView userView = (TextView)findViewById(R.id.user_detail);
        final TextView locationView = (TextView)findViewById(R.id.location_detail);
        final TextView timeView = (TextView)findViewById(R.id.time_detail);
        final TextView exptimeView = (TextView)findViewById(R.id.exptime_detail);
        final TextView creditView = (TextView)findViewById(R.id.credit_detail);
        final TextView contentView = (TextView)findViewById(R.id.content_detail);

        userView.setText("User: " + user);
        locationView.setText("Location: " + location);
        timeView.setText("Time: " + time);
        exptimeView.setText("Expire time: " + expiretime);
        creditView.setText("Credit: " + credit);
        contentView.setText("Content: " + content);

        final Button help = (Button)findViewById(R.id.want_to_help);
        final Button selectHelper = (Button)findViewById(R.id.helperButton);
        final TextView helperlist = (TextView)findViewById(R.id.helperlist);
        final TextView helper = (TextView)findViewById(R.id.helper);
        final Button comment = (Button)findViewById(R.id.submit_comment);
        final EditText commentBox = (EditText)findViewById(R.id.comment_box);

        for(int i = 0; i < responses.size(); i++) {
            if(i != 0) {
                reps += ", ";
                reps += responses.get(i);
            }
            else {
                reps += responses.get(i);
            }
        }

        helper.setText("HELPERS:  " + reps);

        help.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO: change to session
                if (!Global.userID.equals(user))
                {
                    reps += "  ";
                    reps += Global.userID;
                    helper.setText("HELPERS:  " + reps);
                    new ResponseTask().execute(id);
                }
                else
                {
                    //TODO: display "can not response yourself"
                }
            }
        });

        // TODO: insert new comment into database
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();

                // TODO: replace "GlobalValue" with username
                Comment newComment = new Comment(Global.userID, dateFormat.format(date), commentBox.getText().toString());
                comments.add(newComment);
                adapter.notifyDataSetChanged();

                new PublishCommentTask().execute(commentBox.getText().toString());

            }
        });


        // if is not from personal profile, hide button
        if(!indicator.equals("3")) {
            help.setVisibility(View.INVISIBLE);
            selectHelper.setVisibility(View.INVISIBLE);
            helperlist.setVisibility(View.VISIBLE);
        }

        // if is my post (my activity)
        if(indicator.equals("0")) {
            helper.setVisibility(View.INVISIBLE);
            helperlist.setVisibility(View.INVISIBLE);
            selectHelper.setVisibility(View.VISIBLE);

            selectHelper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent select = new Intent(RequestDetail.this, HelperList.class);

                    select.putExtra("list", responses);
                    select.putExtra("requestID", id);
                    startActivity(select);
                }
            });
        }
    }

    // TODO: delete if get actual comment
    // fake comment data;
    public void fakeComment() {
        Comment c1 = new Comment("cc", "2012-01-01", "wawawawawa");
        Comment c2 = new Comment("haa", "2013-01-01", "hahahahaha");
        Comment c3 = new Comment("jj", "2013-10-12", "yeyeyeyeye");

        comments.add(c1);
        comments.add(c2);
        comments.add(c3);

    }

    private class ResponseTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            // TODO Auto-generated method stub
            String requestID = params[0];
            ResponseClient responseClient = new ResponseClient("http://servlet-xqqeybyk3j.elasticbeanstalk.com/Response");

            return responseClient.postResponse(Global.userID, Global.sessionID, requestID);
        }//close doInBackground

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                //TODO: Successfully posted
                System.out.println("Successful");
            } else {
                System.out.println("Failed");
            }
        }//close onPostExecute
    }

    private class PublishCommentTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            // TODO Auto-generated method stub
            String content = params[0];
            CommentClient commentClient = new CommentClient("http://servlet-xqqeybyk3j.elasticbeanstalk.com/Comment");

            return commentClient.postComments(Global.userID,Global.sessionID,id,content);
        }//close doInBackground

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                //TODO: Successfully posted
                System.out.println("Successful");
            } else {
                System.out.println("Failed");
            }
        }//close onPostExecute
    }

    private class GetCommentTask extends AsyncTask<Activity, Void, ArrayList<String>> {
        private Activity activity;
        @Override
        protected ArrayList<String> doInBackground(Activity... params) {
            // TODO Auto-generated method stub
            activity = params[0];
            CommentClient commentClient = new CommentClient("http://servlet-xqqeybyk3j.elasticbeanstalk.com/Comment");

            return commentClient.getComments(Global.userID,Global.sessionID,id);
        }//close doInBackground

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            int n = result.size();
            for (int i=0; i<n; i++)
            {
                Comment c = new Comment();
                try {
                    JSONObject obj = new JSONObject(result.get(i));
                    System.out.println(result.get(i));
                    c.setContent(obj.getString("comment"));
                    c.setUser(obj.getString("userID"));
                    System.out.println(obj.getString("comment_time"));
                    c.setTime(obj.getString("comment_time"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                comments.add(c);
            }
            listView = (ListView)findViewById(R.id.commentlist);
            adapter = new CommentAdapter(activity, comments);
            listView.setAdapter(adapter);
        }//close onPostExecute
    }
}
