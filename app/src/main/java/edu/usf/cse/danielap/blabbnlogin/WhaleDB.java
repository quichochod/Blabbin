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

        Intent i = getIntent();
        String whaleName = i.getExtras().getString("Whale Name");
        String whaleLoc = i.getExtras().getString("Whale Location");
        String whaleIcon = i.getExtras().getString("User Whale");

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(whaleName).push().setValue(new Whale());
        mDatabase.child(whaleName).setValue(whaleLoc);
        Log.d("ADebugTag", "whale name is" + whaleName);

        Intent messageIntent = new Intent(WhaleDB.this, MessageDB.class);
        messageIntent.putExtra("User Whale", whaleIcon);
        messageIntent.putExtra("Whale Name", whaleName);
        messageIntent.putExtra("Whale Location", whaleLoc);
        startActivity(messageIntent);
    }

//    private void displayChatMessages() {
//
//        ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);
//
//        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
//                R.layout.message, FirebaseDatabase.getInstance().getReference().child("messages_whale01")) {
//            @Override
//            protected void populateView(View v, ChatMessage model, int position) {
//                // Get references to the views of message.xml
//                TextView messageText = (TextView)v.findViewById(R.id.message_text);
//                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
//                TextView messageTime = (TextView)v.findViewById(R.id.message_time);
//
//                //ImageView image1 = (ImageView)findViewById(R.id.image1);
//                //image1.setImageResource(R.drawable.ic_arrow_forward_black);
//                //Set whale image
//                messageUser.setBackgroundResource(R.drawable.whale01);
//
//                // Set their text
//                messageText.setText(model.getMessageText());
//                // Format the date before showing it
//                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));
//            }
//        };
//
//
//        listOfMessages.setAdapter(adapter);
//
//    }


}