package com.examples.course.needone;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.course.needone.Client.RegisterClient;
import com.examples.course.needone.tools.Global;

/**
 * Created by Jialu on 12/21/14.
 */
public class Register extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        // TODO: insert into database (newUser, newDob, pwd)
        // TODO:Also need to add credit attribute!!!!!

        final EditText newUser = (EditText)findViewById(R.id.user_new);
        final EditText newDob = (EditText)findViewById(R.id.dob_new);
        final EditText pwd = (EditText)findViewById(R.id.pwd_new);
        final EditText pwdConfirm = (EditText)findViewById(R.id.pwd_confirm_new);
        final Button regButton = (Button)findViewById(R.id.reg_button);

        pwdConfirm.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                Log.i("mark","Text changed mark!!!!!");
                // check to see if user confirm password
                if(!pwd.getText().toString().equals(s.toString())) {
                    TextView msg = (TextView)findViewById(R.id.pwd_confirm_msg);
                    msg.setText("Please confirm your password! ");
                }

                if(pwd.getText().toString().equals(s.toString())) {
                    TextView msg = (TextView)findViewById(R.id.pwd_confirm_msg);
                    msg.setText("");
                }
            }
        });


        regButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            if (pwd.getText().toString().equals(pwdConfirm.getText().toString()))
            {
                new ValidateNewUserIDTask().execute(newUser.getText().toString(), pwd.getText().toString());
            }

            }

        });

    }

    private class ValidateNewUserIDTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            // TODO Auto-generated method stub
            String userID = params[0], password = params[1];
            Global.userID = userID;
            String url = "http://servlet-xqqeybyk3j.elasticbeanstalk.com/Register";
            RegisterClient client = new RegisterClient(url);
            return client.registerQuery(userID, password);
        }//close doInBackground

        @Override
        protected void onPostExecute(Integer result) {
            if (result == 1)
            {

                Intent regIntent = new Intent(Register.this, RegisterSuccess.class);
                startActivity(regIntent);
            }
            else if(result == 0)
            {
                Toast.makeText(Register.this,
                        "Username already exists!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(Register.this,
                        "Error Occurs!", Toast.LENGTH_LONG).show();
            }

        }//close onPostExecute
    }// close validateUserTask
}
