package edu.usf.cse.danielap.blabbnlogin;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Thread.sleep;

/**
 * Deals with creation of new Whale
 */

public class WhaleDB extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.messages_main_act);
        Log.d("tag", "start whale db");
        Intent i = getIntent();
        String whaleName = i.getExtras().getString("Whale Name");
        String whaleLoc = i.getExtras().getString("Whale Location");
        String whaleIcon = i.getExtras().getString("User Whale");

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(whaleName).child("Location").push().setValue(new Whale());
        mDatabase.child(whaleName).child("Location").setValue(whaleLoc);
        Log.d("ADebugTag", "whale name is" + whaleName);

        Intent messageIntent = new Intent(WhaleDB.this, MessageDB.class);
        messageIntent.putExtra("User Whale", whaleIcon);
        messageIntent.putExtra("Whale Name", whaleName);
        messageIntent.putExtra("Whale Location", whaleLoc);
        startActivity(messageIntent);
    }

}