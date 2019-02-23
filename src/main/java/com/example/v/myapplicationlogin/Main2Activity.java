package com.example.v.myapplicationlogin;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.v.myapplicationlogin.QueryUtils.makeHttpRequest;

public class Main2Activity extends AppCompatActivity {
    EditText editText;
    EditText editText0;
    EditText editText1;
    EditText editText2;
    Button button1;
    Button button2;
    Context mContext;
    String url = "http://192.168.43.50/user/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText = (EditText) findViewById(R.id.name);
        editText0 = (EditText) findViewById(R.id.id);
        editText1 = (EditText) findViewById(R.id.email);
        editText2 = (EditText) findViewById(R.id.password);
        button1 = (Button) findViewById(R.id.btnRegister);
        button2 = (Button) findViewById(R.id.btnLinkToLoginScreen);
        mContext = this;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = false;
                String idd = editText0.getText().toString().trim();
                String emaill = editText0.getText().toString().trim();
                if(idd.charAt(0)>= 'A' &&  idd.charAt(0) <= 'Z'){
                    if (idd.length() == 7) {
                        int count = 0;
                        for (int i = 1; i < idd.length(); i++) {
                            if (idd.charAt(i) >= '0' && idd.charAt(i) <= '9') {
                                count++;
                            } else
                                break;
                            ;
                        }
                        if (count == 6) {
//                            if (emaill.charAt(emaill.length() - 1) == 'm' && emaill.charAt(emaill.length() - 2) == 'o' &&
//                                                    emaill.charAt(emaill.length() - 3) == 'c' && emaill.charAt(emaill.length() - 4) == '.' ){
                            for(int j = emaill.length() -  1;  j  > 0 ; j--){
                                if(emaill.charAt(j) == '@')
                                    b = true;
                            }
                            if (b == false){
                                new RegAsync().execute(url, editText0.getText().toString(),
                                        editText.getText().toString(),
                                        editText2.getText().toString(),
                                        editText1.getText().toString(),
                                        "ZERO", null, null);

                            }
                            else
                                Toast.makeText(mContext,"Email is not correct", Toast.LENGTH_SHORT).show();

//                            }
//                            else
//                                Toast.makeText(mContext,"Email is not correct1", Toast.LENGTH_SHORT).show();


                        } else
                            Toast.makeText(mContext,"ID is not correct2", Toast.LENGTH_SHORT).show();
                    }
                }else
                    Toast.makeText(mContext,"ID is not correct3", Toast.LENGTH_SHORT).show();
            }

        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private class RegAsync extends AsyncTask<String,Void,JSONObject>{
//        ProgressBar progressBar = new ProgressBar(mContext);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            int time = (int)System.currentTimeMillis() / 1000;
//            progressBar.setProgress((int)System.currentTimeMillis() / 1000 - time);
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONObject jsonObject = makeHttpRequest(strings[0],strings[1],strings[2],strings[3],strings[4],strings[5], strings[6],strings[7]);
            return jsonObject;

        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            if (jsonObject == null) {
                Toast.makeText(mContext, "Registration Failed", Toast.LENGTH_LONG).show();
                return;
            }
            try {
                Toast.makeText(mContext,jsonObject.getString("message").toString(),Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
