package com.example.v.myapplicationlogin;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.v.myapplicationlogin.QueryUtils.makeHttpRequest;

public class MainActivity extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    Button button1;
    Button button2;
    Context mContext;
    String url = "http://192.168.43.50/user1/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = (EditText) findViewById(R.id.id);
        editText2 = (EditText) findViewById(R.id.password);
        button1 = (Button) findViewById(R.id.btnLogin);
        button2 = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        mContext = this;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginAsync().execute(url, editText1.getText().toString(),"",
                        editText2.getText().toString(), "","ZERO",null,null);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Main2Activity.class);
                startActivity(intent);
            }
        });
    }
    private class LoginAsync extends AsyncTask<String,Void,JSONObject> {
//        ProgressBar progressBar = new ProgressBar(mContext);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            int time = (int)System.currentTimeMillis() / 1000;
//            progressBar.setProgress((int)System.currentTimeMillis() / 1000 - time);
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONObject jsonObject = makeHttpRequest(strings[0],strings[1],strings[2],strings[3],strings[4],strings[5],strings[6],strings[7]);
            return jsonObject;

        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            if (jsonObject == null) {
                Toast.makeText(mContext, "Loging Failed, May be Connection Error", Toast.LENGTH_LONG).show();
                return;
            }
            try {
                if (jsonObject.getInt("success") == 1) {
                    Intent intent = new Intent(mContext, Main3Activity.class);
                    startActivity(intent);
                }
                if (jsonObject.getInt("success") == 0)
                    Toast.makeText(mContext, "Loging Failed, Wrong Information", Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(mContext, "Loging Failed, Exception in database", Toast.LENGTH_LONG).show();
            }

        }
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.blue:
                // Set the text color to blue
                Intent intent = new Intent(this,About.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
