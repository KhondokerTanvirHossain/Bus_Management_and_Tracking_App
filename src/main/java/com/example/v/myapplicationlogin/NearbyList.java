package com.example.v.myapplicationlogin;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.v.myapplicationlogin.QueryUtils.makeHttpRequest;

public class NearbyList extends AppCompatActivity {
    String urlString ="http://192.168.43.50/user1/joinlist.php";
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);
        mContext = this;
        new RouteAsyncTask().execute(urlString,null,null,null,null,"ZERO",null,null);

    }
    private class RouteAsyncTask extends AsyncTask<String, Void, JSONObject> {
        //        ProgressBar progressBar = new ProgressBar(mContext);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//          int time = (int)System.currentTimeMillis() / 1000;
//          progressBar.setProgress((int)System.currentTimeMillis() / 1000 - time);
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONObject jsonObject = makeHttpRequest(strings[0],strings[1],strings[2],strings[3],strings[4],strings[5],strings[6],strings[7]);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            updateUi(jsonObject);
        }
    }
    private void updateUi(JSONObject jsonObject){
        final ArrayList<RouteOption> options = new ArrayList<RouteOption>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("array");
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);

                options.add(new RouteOption(object.getString("start_at"),object.getString("end_at"),
                        object.getString("time"),object.getString("gender")
                        ,object.getString("route"),object.getString("route_id")
                        ,object.getString("bus_id"),object.getString("seat")));
            }
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(mContext, DescriptionActivity.class);
                    intent.putExtra("start",options.get(position).getStart());
                    intent.putExtra("end",options.get(position).getStop());
                    intent.putExtra("time",options.get(position).getmTime());
                    intent.putExtra("gender",options.get(position).getGender());
                    intent.putExtra("route",options.get(position).getRoute());
                    intent.putExtra("route_id",options.get(position).getRouteID());
                    intent.putExtra("bus_id",options.get(position).getBusID());
                    intent.putExtra("seat",options.get(position).getSeat());
                    startActivity(intent);

                }
            });
            AdapterActivity adapterActivity = new AdapterActivity(this, R.id.list, options);
            listView.setAdapter(adapterActivity);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "Database Error", Toast.LENGTH_LONG).show();

        }
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
