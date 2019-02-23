package com.example.v.myapplicationlogin;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.StringTokenizer;

import static com.example.v.myapplicationlogin.QueryUtils.makeHttpRequest;

public class DisplayActivity extends FragmentActivity implements OnMapReadyCallback {
    Context mContext;
    String url = "http://192.168.43.50/user1/locationget.php";
    String lat;
    String lan;
    String mBus;

    private static final String TAG = DisplayActivity.class.getSimpleName();
    private HashMap<String, Marker> mMarkers = new HashMap<>();
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mContext = this;
        Intent intent = getIntent();
        mBus = intent.getStringExtra("bus");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Authenticate with Firebase when the Google map is loaded
        mMap = googleMap;
        mMap.setMaxZoomPreference(16);
        mark(mMap,22.3590,91.8212);
        mark(mMap,22.3584,91.7818);
        mark(mMap,22.3610,91.8111);
//        loginToFirebase();
//        new LocationAsync().execute(url,null,null,null,null,null,null,mBus);
    }
    private class LocationAsync extends AsyncTask<String,Void,JSONObject> {
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
                Toast.makeText(mContext, "Network Failed", Toast.LENGTH_LONG).show();
//                mark(mMap,22.341900,91.815536);
                return;
            }
//            try {
//                Toast.makeText(mContext,"success to get location",Toast.LENGTH_LONG).show();
//                lat = jsonObject.getString("lat");
//                lan = jsonObject.getString("lan");
////                mark(mMap,Double.parseDouble(lat),Double.parseDouble(lan));
//                mark(mMap,22.3590,91.8212);
//                mark(mMap,22.3584,91.7818);
//                mark(mMap,22.3610,91.8111);
//
//            }
//            catch (JSONException e) {
//                e.printStackTrace();
//            }
            String s = jsonObject.toString();
            StringTokenizer st = new StringTokenizer(s);
            while (st.hasMoreElements()){
                String ss = st.nextToken();
                if(ss.equals("lat")){
                    st.nextToken();
                    lat = st.nextToken();
                }
                if(ss.equals("lan")){
                    st.nextToken();
                    lan = st.nextToken();
                }
            }
            mark(mMap,Double.parseDouble(lat),Double.parseDouble(lan));
        }
    }

    private void loginToFirebase() {
        String email = getString(R.string.firebase_email);
        String password = getString(R.string.firebase_password);
        // Authenticate with Firebase and subscribe to updates
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
                email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    subscribeToUpdates();
                    Log.d(TAG, "firebase auth success");
                } else {
                    Log.d(TAG, "firebase auth failed");
                }
            }
        });
    }

    private void subscribeToUpdates() {
        // Functionality coming next step
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(getString(R.string.firebase_path));
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                setMarker(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                setMarker(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void setMarker(DataSnapshot dataSnapshot) {
        // Functionality coming next step
        // When a location update is received, put or update
        // its value in mMarkers, which contains all the markers
        // for locations received, so that we can build the
        // boundaries required to show them all on the map at once
        String key = dataSnapshot.getKey();
        HashMap<String, Object> value = (HashMap<String, Object>) dataSnapshot.getValue();
        double lat = Double.parseDouble(value.get("latitude").toString());
        double lng = Double.parseDouble(value.get("longitude").toString());
        LatLng location = new LatLng(lat, lng);
        if (!mMarkers.containsKey(key)) {
            mMarkers.put(key, mMap.addMarker(new MarkerOptions().title(key).position(location)));
        } else {
            mMarkers.get(key).setPosition(location);
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : mMarkers.values()) {
            builder.include(marker.getPosition());
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 300));
    }
    static void mark(GoogleMap googleMap, double lat, double lan){
        LatLng sydney = new LatLng(lat, lan);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}