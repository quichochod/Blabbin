package edu.usf.cse.danielap.blabbnlogin;

// Created by quichochod on 3/3/2017.

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.app.LoaderManager.LoaderCallbacks;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import edu.usf.cse.danielap.blabbnlogin.Whale;

public class WhaleListScreen extends AppCompatActivity  implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener{

    TextView whaleName;
    ImageButton addWhale;
    EditText inputWhaleName;
    Button submit;
    ImageButton initialWhale;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whale_list_screen);

        whaleName = (TextView) findViewById(R.id.text_add_whale);
        addWhale = (ImageButton) findViewById(R.id.buttonAddWhale);
        inputWhaleName = (EditText) findViewById(R.id.NameWhale);
        submit = (Button) findViewById(R.id.SubmitWhaleName);
        initialWhale = (ImageButton) findViewById(R.id.buttonInitialWhale);

        addWhale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWhale.setVisibility(view.INVISIBLE);
                whaleName.setVisibility(view.INVISIBLE);

                inputWhaleName.setVisibility(view.VISIBLE);
                submit.setVisibility(view.VISIBLE);




            }
        });

        initialWhale.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(WhaleListScreen.this, MessageDB.class);
                    startActivity(intent);

                finish();
            }


        });





    }



//    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//                if (view.getId() == R.id.buttonAddWhale) {
//                    View createWhale = WhaleListScreen.this.findViewById(R.id.text_add_whale);
//                    createWhale.setVisibility(View.INVISIBLE);
//
//                    View whaleName = WhaleListScreen.this.findViewById(R.id.NameWhale);
//                    whaleName.setVisibility(View.VISIBLE);
//
//
//                }
//
//
//            switch (view.getId()) {
//                case R.id.buttonInitialWhale:
////                    intent = new Intent(context, BlabbinWhaleActivity.class);
////                    startActivity(intent);
//                    break;
//            }
//        }
//    };

    /**************************************************************
     * Google Location Services implementation and methods
     * ************************************************************/
    private static int REQUEST_CODE_RECOVER_PLAY_SERVICES = 200;

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    Location myCurrentLocation;
    LocationRequest mLocationRequest;
    static float maxWhaleRange = 1610;  // Approx meters in a mile

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
                        android.Manifest.permission.ACCESS_COARSE_LOCATION ) !=
                        PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_LOCATION);
                }
                return true;

            }
        }
        return false;
    }

    protected synchronized void buildGoogleApiClient() {
        Log.d("PRE BUILD CLIENT", "Building GoogleApiClient");
        this.mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        Log.d("POST CLIENT BUILD", "myGoogleApiClient is :" + mGoogleApiClient.isConnected());
        this.mGoogleApiClient.connect();
        Log.d("POST CONNECT", "myGoogleApiClient is :" + mGoogleApiClient.isConnected());
        createLocationRequest();
    }

    @Override
    public void onConnected(Bundle bundle) {
//
        if ( ContextCompat.checkSelfPermission( this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION ) ==
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
                android.Manifest.permission.ACCESS_COARSE_LOCATION ) ==
                PackageManager.PERMISSION_GRANTED )
            Log.d("PRE_LOCATION", "Pre Location: " + myCurrentLocation);
        myCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        Log.d("POST-LOCATION", "Post Location: " + myCurrentLocation);
    }

    public void createLocationRequest(){
        mLocationRequest = new LocationRequest();
        Log.d("CREATE_LOC_REQ", "Loc Req = " + mLocationRequest);
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        Log.d("POST-LOCATION", "Post Location: " + myCurrentLocation);
    }


    /******************************
     * Firebase Functions
     *******************************/
    DatabaseReference mDatabase;

    public boolean searchWhaleList(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final List<String> currWhaleList = null;
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final Whale whale_data = dataSnapshot.getValue(Whale.class);
                Log.d("FB: Whale Name", "Whale name is " + whale_data.getName());
                currWhaleList.add(0, whale_data.getName());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // loop through whale list3
        // assign x and y as a location in whaleLocation
        // calculate distance from myCurrentLocation to whaleLocation
        // If within range return true?? Or add to an array
        // if not keep searching
//        mDatabase.child("messages_whale01").push().setValue(new ChatMessage(input.getText().toString()));
//
//        float distance = whaleLocation.distanceTo(myCurrentLocation);
//        if(distance <= maxWhaleRange){
//            Log.d("IN RANGE: ", "Distance from whale: " + distance);
//        }
//        else{
//            Log.d("OUT OF RANGE: ", "Distance from whale: " + distance);
//        }

        //Return list of whales
        return true;
    }
}