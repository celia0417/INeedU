package com.examples.course.needone;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.examples.course.needone.Client.RegisterClient;
import com.examples.course.needone.tools.Global;

/**
 * Created by Jialu on 12/21/14.
 */
public class RegisterSuccess extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_success);

        final Button confirmButton = (Button)findViewById(R.id.confirm_submit);
        final EditText confirmCode = (EditText)findViewById(R.id.confirm_code);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ValidateConfirmCodeTask().execute(Global.userID, confirmCode.getText().toString());
            }
        });
    }

    private class ValidateConfirmCodeTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            // TODO Auto-generated method stub
            String userID = params[0], code = params[1];
            Global.userID = userID;
            String url = "http://servlet-xqqeybyk3j.elasticbeanstalk.com/Register";
            RegisterClient client = new RegisterClient(url);
            return client.registerConfirm(userID, code);
        }//close doInBackground

        @Override
        protected void onPostExecute(Integer result) {
            if (result == 1)
            {
                Intent confirmIntent = new Intent(RegisterSuccess.this, LoginScreen.class);
                startActivity(confirmIntent);
            }
            else if (result == 0)
            {
                Toast.makeText(RegisterSuccess.this,
                        "Please check your confirmation code!", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(RegisterSuccess.this,
                        "Error!", Toast.LENGTH_LONG).show();
            }

        }//close onPostExecute
    }// close validateUserTask
}
