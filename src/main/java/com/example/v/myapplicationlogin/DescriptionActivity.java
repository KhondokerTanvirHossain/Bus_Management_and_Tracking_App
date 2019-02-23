package com.example.v.myapplicationlogin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.v.myapplicationlogin.QueryUtils.makeHttpRequest;

public class DescriptionActivity extends AppCompatActivity {
    TextView textView1;
    TextView textView11;
    TextView textView2;
    TextView textView22;
    TextView textView3;
    TextView textView33;
    TextView textView4;
    TextView textView44;
    TextView textView5;
    TextView textView55;
    TextView textView6;
    TextView textView66;
    TextView textViewBus;
    TextView textViewBusNumber;
    Button btLocation;
    Button btSeat;
    Context mContext;
    String mSeat;
    String mRoute;
    String mBus;
    boolean inBus;
    String urlB = "http://192.168.43.50/user1/buslist.php";
    String urlS = "http://192.168.43.50/user1/seat.php";
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        mContext = this;
        inBus = false;
        context = this;
        final Intent intent = getIntent();
        textView1 = (TextView) findViewById(R.id.tvd1);
        textView1.setText("From               : ");
        textView11 = (TextView) findViewById(R.id.tvd11);
        textView11.setText(intent.getStringExtra("start"));
        textView2 = (TextView) findViewById(R.id.tvd2);
        textView2.setText("To                    : ");
        textView22 = (TextView) findViewById(R.id.tvd22);
        textView22.setText(intent.getStringExtra("end"));
        textView3 = (TextView) findViewById(R.id.tvd3);
        textView3.setText("Time               : ");
        textView33 = (TextView) findViewById(R.id.tvd33);
        textView33.setText(intent.getStringExtra("time"));
        textView4 = (TextView) findViewById(R.id.tvd4);
        textView4.setText("Gender           : ");
        textView44 = (TextView) findViewById(R.id.tvd44);
        textView44.setText(intent.getStringExtra("gender"));
        textView5 = (TextView) findViewById(R.id.tvd5);
        textView5.setText("Route             : ");
        textView55 = (TextView) findViewById(R.id.tvd55);
        textView55.setText(intent.getStringExtra("route"));
        textView6 = (TextView) findViewById(R.id.tvd6);
        textView6.setText("Seat              :");
        textView66 = (TextView) findViewById(R.id.tvd66);
        textView66.setText((intent.getStringExtra("seat").equals("50"))? "Available" : "Booked");
        textViewBus = (TextView) findViewById(R.id.tvdBus);
        textViewBus.setText("Bus Number:");
        textViewBusNumber = (TextView) findViewById(R.id.tvdBusNumber);
        textViewBusNumber.setText(intent.getStringExtra("bus_id"));
        mSeat = "50";
        mRoute = intent.getStringExtra("route_id");
//        new BusAsyncTask().execute(urlB, null, null, null, null, intent.getStringExtra("route_id"), null, null);

        btLocation = (Button) findViewById(R.id.buttonLocation);
        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(mContext, DisplayActivity.class);
                intent1.putExtra("bus", mBus);
                startActivity(intent1);

            }
        });
//        btSeat = (Button) findViewById(R.id.buttonSeat);
//        final int seatFirst = Integer.parseInt(mSeat);
//        btSeat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (seatFirst == Integer.parseInt(mSeat)) {
//                    String nSeat = "" + (Integer.parseInt(mSeat) - 1);
//                    mSeat = nSeat;
//                    new SeatAsyncTask().execute(urlS, null, null, null, null, null, nSeat , intent.getStringExtra("bus_id"));
//                } else
//                    Toast.makeText(mContext, "Already Have a Seat", Toast.LENGTH_LONG).show();
//            }
//        });
    }
    private class BusAsyncTask extends AsyncTask<String,Void,JSONObject> {
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
                Toast.makeText(mContext, "Fails TO Fetch Data, May be Connection Error", Toast.LENGTH_LONG).show();
                return;
            }
            try {
                mSeat = jsonObject.getString("seat");
                mBus = jsonObject.getString("bus_id");
                textView66.setText(mSeat);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(mContext,"JSONException !!!" , Toast.LENGTH_LONG).show();
            }
        }
    }
    private class SeatAsyncTask extends AsyncTask<String,Void,JSONObject> {
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
                Toast.makeText(mContext, "Fails TO Fetch Data, May be Connection Error", Toast.LENGTH_LONG).show();
                return;
            }
            try {
                if (jsonObject.getString("message").equals("success")) {
//                    Toast.makeText(mContext,"success" , Toast.LENGTH_LONG).show();
                    textView66.setText(mSeat);
                    inBus = true;
                }
                else if (jsonObject.getString("message").equals("fail"))
                    Toast.makeText(mContext,"Database Error" , Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(mContext,"JSONException !!!" , Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Leave Bus?");
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                String nSeat = ""+(Integer.parseInt(mSeat) + 1);
                mSeat = nSeat;
                new SeatAsyncTask().execute(urlS, null,null,null,null,mRoute,nSeat);
                inBus = false;
                finish();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        if (inBus == true) {
            AlertDialog alert = builder.create();
            alert.show();
        }
        else
            finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
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
