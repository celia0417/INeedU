package com.examples.course.needone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.examples.course.needone.Client.LoginClient;
import com.examples.course.needone.tools.Global;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Jialu on 12/17/14.
 */
public class LoginScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        final EditText userID = (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);
        final Button loginButton = (Button)findViewById(R.id.signin);
        final Button regButton = (Button)findViewById(R.id.register);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ValidateUserTask task = new ValidateUserTask();
                task.execute(userID.getText().toString(), password.getText().toString());
                //new Connection().execute();
                userID.setText("");
                password.setText("");
            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(LoginScreen.this, Register.class);
                startActivity(regIntent);
            }
        });

        userID.requestFocus();
        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.showSoftInput(userID, InputMethodManager.SHOW_IMPLICIT);

//        userID.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus) {
//                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//                }
//            }
//        });
    }

    private class ValidateUserTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            // TODO Auto-generated method stub
            String userID = params[0], password = params[1];
            LoginClient loginClient = new LoginClient("http://servlet-xqqeybyk3j.elasticbeanstalk.com/Login");
            return loginClient.login(userID,password);
        }//close doInBackground

        @Override
        protected void onPostExecute(Integer result) {
            if(result == 1){
                //navigate to Main Menu
                Intent hwIntent = new Intent(LoginScreen.this, MainPage.class);
                startActivity(hwIntent);
            }
            else if(result == 0){
                //incorrect password
            }
            else if(result == -1) {
                //incorrect userID
            }
            else {
                //error
            }
        }//close onPostExecute
    }// close validateUserTask

}
