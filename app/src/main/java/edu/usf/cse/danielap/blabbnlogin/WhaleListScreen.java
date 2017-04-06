package edu.usf.cse.danielap.blabbnlogin;

// Created by quichochod on 3/3/2017.

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.app.LoaderManager.LoaderCallbacks;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.usf.cse.danielap.blabbnlogin.Whale;

public class WhaleListScreen extends AppCompatActivity  implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener{

    TextView whaleName;
    ImageButton addWhale;
    EditText inputWhaleName;
    Button submit;
    Button refreshLocation;
    String whaleIcon;
    String test;
    private FirebaseAuth mAuth;
    Button signOut2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whale_list_screen);

         //test = BlabbinChooseWhaleActivity.getWhaleName();
        //Log.d("HEY IT WORKED",test);
        //Retrieve whale name like this....

        signOut2 = (Button) findViewById(R.id.signoutbut2);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        signOut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent out = new Intent(WhaleListScreen.this, BlabinLoginActivity.class);
                startActivity(out);
                finish();

            }
        });
        Intent intent = getIntent();
        whaleIcon = intent.getExtras().getString("User Whale");


        // Ask for permissions, build google api, and connect to play services
        if (checkGooglePlayServices() && checkLocationPermission()) {
            buildGoogleApiClient();
            searchWhaleList();
        }

        whaleName = (TextView) findViewById(R.id.text_add_whale);
        addWhale = (ImageButton) findViewById(R.id.buttonAddWhale);
        inputWhaleName = (EditText) findViewById(R.id.NameWhale);
        submit = (Button) findViewById(R.id.SubmitWhaleName);

        //initialWhale = (ImageButton) findViewById(R.id.buttonInitialWhale);


        refreshLocation = (Button) findViewById(R.id.refreshLocation);

        addWhale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWhale.setVisibility(view.INVISIBLE);
                whaleName.setVisibility(view.INVISIBLE);

                inputWhaleName.setVisibility(view.VISIBLE);
                submit.setVisibility(view.VISIBLE);

            }
        });

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                createNewButton(null);


                addWhale.setVisibility(view.VISIBLE);
                whaleName.setVisibility(view.VISIBLE);

//                inputWhaleName.setText("");
                inputWhaleName.setVisibility(view.INVISIBLE);
                submit.setVisibility(view.INVISIBLE);
            }
        });

        refreshLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("AAA", myCurrentLocation.toString());
                createLocationRequest();
                Log.d("BBB", myCurrentLocation.toString());
            }
        });
    }

    public void createNewButton(String whale_name)
    {
        if(whale_name == null) {

            Button myButton = new Button(this);
            myButton.setText(inputWhaleName.getText().toString());
            myButton.setTextSize(24);
            myButton.setBackgroundColor(Color.WHITE);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (checkLocationPermission()) {
                        myCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                    }
                    Double lat = myCurrentLocation.getLatitude();
                    Double lon = myCurrentLocation.getLongitude();
                    String currLoc = lat.toString() + "," + lon.toString();

                    Intent intent = new Intent(WhaleListScreen.this, WhaleDB.class);
                    intent.putExtra("Whale Name", inputWhaleName.getText().toString());
                    String testing = inputWhaleName.getText().toString();
                    Log.d("afnlaefn", inputWhaleName.getText().toString());
                    intent.putExtra("Whale Location", currLoc);
                    intent.putExtra("User Whale", whaleIcon);
                    //intent.putExtra("Whale Name", inputWhaleName.getText().toString());
                    inputWhaleName.setText("");
                    startActivity(intent);
                    //Retrieve whale name like this....
                    //Intent intent = getIntent();
                    //String whaleName = intent.getExtras().getString("Whale Name");
                    finish();
                }
            });
            LinearLayout ll = (LinearLayout) findViewById(R.id.whaleLayout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 40, 0, 0);
            ll.addView(myButton, lp);
        }
        else{
            final String var = whale_name;
            Button myButton = new Button(this);
            myButton.setText(var);
            myButton.setTextSize(24);
            myButton.setBackgroundColor(Color.WHITE);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (checkLocationPermission()) {
                        myCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                    }
                    Double lat = myCurrentLocation.getLatitude();
                    Double lon = myCurrentLocation.getLongitude();
                    String currLoc = lat.toString() + "," + lon.toString();

                    Intent intent = new Intent(WhaleListScreen.this, WhaleDB.class);
                    intent.putExtra("Whale Name", var);
                    String testing = var;
                    Log.d("afnlaefn", var);

                    final List<String> existingWhaleName = new ArrayList<String>();
                    final List<String> existingWhaleLoc = new ArrayList<String>();

                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot snapshot) {
                         for (DataSnapshot child : snapshot.getChildren()) {
                             existingWhaleName.add(child.getKey());
                             existingWhaleLoc.add(child.child("Location").getValue().toString());
                         }
                     }

                    public void onCancelled(DatabaseError databaseError) {

                    }

                    });

                    if(existingWhaleName.contains(var)){
                        int index = existingWhaleName.indexOf(var);
                        intent.putExtra("Whale Location", existingWhaleLoc.indexOf(index));
                    }
                    else{
                        intent.putExtra("Whale Location", currLoc);
                    }

                    intent.putExtra("User Whale", whaleIcon);
                    //intent.putExtra("Whale Name", inputWhaleName.getText().toString());
                    inputWhaleName.setText("");
                    startActivity(intent);
                    //Retrieve whale name like this....
                    //Intent intent = getIntent();
                    //String whaleName = intent.getExtras().getString("Whale Name");
                    finish();
                }
            });
            LinearLayout ll = (LinearLayout) findViewById(R.id.whaleLayout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 40, 0, 0);
            ll.addView(myButton, lp);
        }

    }


    /**************************************************************
     * Google Location Services implementation and methods
     * ************************************************************/
    private static int REQUEST_CODE_RECOVER_PLAY_SERVICES = 200;

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    Location myCurrentLocation;
    LocationRequest mLocationRequest;
    static float maxWhaleRange = 1609;  // Approx meters in a mile

    private boolean checkGooglePlayServices() {
        int checkGooglePlayServices = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (checkGooglePlayServices != ConnectionResult.SUCCESS) {
			/*
			* google play services is missing or update is required
			*  return code could be
			* SUCCESS,
			* SERVICE_MISSING, SERVICE_VERSION_UPDATE_REQUIRED,
			* SERVICE_DISABLED, SERVICE_INVALID.
			*/
            GooglePlayServicesUtil.getErrorDialog(checkGooglePlayServices,
                    this, REQUEST_CODE_RECOVER_PLAY_SERVICES).show();

            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_RECOVER_PLAY_SERVICES) {

            if (resultCode == RESULT_OK) {
                // Make sure the app is not already connected or attempting to connect
                if (!mGoogleApiClient.isConnecting() &&
                        !mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.connect();
                }
            } else if (resultCode == RESULT_CANCELED) {
//                Toast.makeText(this, "Google Play Services must be installed.",
//                        Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                // No explanation needed, we can request the permission.
                if ( ContextCompat.checkSelfPermission( this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION ) !=
                        PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_LOCATION);
                }
                return true;

            }
        }
        return true;
    }

    protected synchronized void buildGoogleApiClient() {
        Log.d("PRE BUILD CLIENT", "Building GoogleApiClient");
        this.mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        Log.d("POST CLIENT BUILD", "myGoogleApiClient is :" + mGoogleApiClient.isConnected());
        while(!mGoogleApiClient.isConnected() && !mGoogleApiClient.isConnecting()){
            this.mGoogleApiClient.connect();
        }
        Log.d("POST CONNECT", "myGoogleApiClient is :" + mGoogleApiClient.isConnected());
        if(mGoogleApiClient.isConnected()){
            createLocationRequest();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        if ( ContextCompat.checkSelfPermission( this,
                android.Manifest.permission.ACCESS_FINE_LOCATION ) ==
                PackageManager.PERMISSION_GRANTED ) {
            Log.d("LOC-DEBUG-1", "Connected to GoogleApiClient " + mGoogleApiClient.isConnected());
            myCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            Log.d("PERMISSIONS GRANTED", "Connected to GoogleApiClient " + mGoogleApiClient.isConnected());
            // Debug - At this point myCurrentLocation  is NULL
            if (myCurrentLocation == null) {
                Log.d("LOC-DEBUG-2", "myCurrentLocation is : " + myCurrentLocation);
                // Debug - myCurrentLocation  is STILL NULL
                LocationServices.FusedLocationApi.requestLocationUpdates(
                        mGoogleApiClient, mLocationRequest, this);
                // Debug - app crashes with stack trace below at this point
                Log.d("LOC-DEBUG-3", "Doesnt't reach here : " + myCurrentLocation);
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if ( ContextCompat.checkSelfPermission( this,
                android.Manifest.permission.ACCESS_FINE_LOCATION ) ==
                PackageManager.PERMISSION_GRANTED )
            Log.d("PRE_LOCATION", "Pre Location: " + myCurrentLocation);
        myCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        Log.d("POST-LOCATION", "Post Location: " + myCurrentLocation);
        searchWhaleList();
    }

    public void createLocationRequest(){
        mLocationRequest = new LocationRequest();
        Log.d("CREATE_LOC_REQ", "Loc Req = " + mLocationRequest);
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        Log.d("PRE-LOCATION", "Post Location: " + myCurrentLocation);
    }


    /******************************
     * Firebase Functions
     *******************************/
    DatabaseReference mDatabase;

    public void searchWhaleList(){
        final List<String> currWhaleList = new ArrayList<String>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String whale_name = (String) dataSnapshot.getValue();
                String whale_key = dataSnapshot.getKey();
                Log.d("FIRST KEY", whale_key);
                Log.d("FIRST KEY", dataSnapshot.child("Location").getValue().toString());
                String temp = dataSnapshot.child("Location").getValue().toString();
                Log.d("FIRST KEY", temp);
                String whale_loc[] = temp.split(",");
                String valid_length[] = temp.split("=");

                if( valid_length.length > 2) {
                    Log.d("SKIP", "Not a whale name");
                }
                else {
                    Log.d("VVAALLUUEE", dataSnapshot.getValue().toString());
                    Double lat = Double.parseDouble(whale_loc[0]);
                    Double lon = Double.parseDouble(whale_loc[1]);

                    currWhaleList.add(0, whale_key);
                    Log.d("Key", "Key is " + whale_key);
                    if (withinDistance(lat, lon)) {
                        // Display button here
                        Log.d("Available Whale", whale_key);
                        Log.d("Lat", lat.toString());
                        Log.d("Long", lon.toString());
                        createNewButton(whale_key);
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                currWhaleList.remove(dataSnapshot)
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public boolean withinDistance(Double wLat, Double wLon){
        Double myLat = myCurrentLocation.getLatitude();
        Double myLon = myCurrentLocation.getLongitude();
        float [] dist = new float[1];
        Location.distanceBetween(wLat, wLon, myLat, myLon, dist);
        if(dist[0] <= maxWhaleRange){
            return true;
        }
        else{
            Log.d("RANGE", "Not withing distance of this whale");
            return false;
        }
    }

}