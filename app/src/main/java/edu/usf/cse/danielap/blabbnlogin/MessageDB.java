package edu.usf.cse.danielap.blabbnlogin;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.text.format.DateFormat;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cse.usf.edu.android.db.BlabbinDBManager;

import static android.R.attr.value;


/**
 * Created by danielaperez on 3/26/17.
 */

public class MessageDB extends AppCompatActivity {

    private FirebaseListAdapter<ChatMessage> adapter;
    //BlabbinDBManager myBlabbinDBManager;
    //Cursor WhaleChosen = myBlabbinDBManager.getUser();
    //String result = null;
    //String test2;
    String whaleIcon;
    String whaleName;
    String whaleLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Cursor userWhale = myBlabbinDBManager.getUser();
        //Log.d("!!Work for daddy!!", DatabaseUtils.dumpCursorToString(userWhale));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages_main_act);

        //displayChatMessages();
        Intent intent = getIntent();
        whaleIcon = intent.getExtras().getString("User Whale");
        whaleName = intent.getExtras().getString("Whale Name");
        whaleLoc = intent.getExtras().getString("Whale Location");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                EditText input = (EditText)findViewById(R.id.input);
                String test = input.getText().toString();
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child(whaleName).child("Messages").push().setValue(new ChatMessage(test, whaleIcon));
                Log.d("ADebugTag", "Value:1 " + input.getText().toString());
                // Clear the input
                input.setText("");
            }
        });

    displayChatMessages(whaleName);
    }

    private void displayChatMessages(String whaleName) {

        ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);

        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message, FirebaseDatabase.getInstance().getReference().child(whaleName).child("Messages")) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                String tesVal = model.getMessageUser();
                if (tesVal.equals("whale01")){
                    messageUser.setBackgroundResource(R.drawable.whale01);
                }
                else if (tesVal.equals("whale02")){
                    messageUser.setBackgroundResource(R.drawable.whale02);
                }
                else if (tesVal.equals("whale03")){
                    messageUser.setBackgroundResource(R.drawable.whale03);
                }
                else if (tesVal.equals("whale04")){
                    messageUser.setBackgroundResource(R.drawable.whale04);
                }
                else if (tesVal.equals("whale05")){
                    messageUser.setBackgroundResource(R.drawable.whale05);
                }
                else if (tesVal.equals("whale06")){
                    messageUser.setBackgroundResource(R.drawable.whale06);
                }
                else if (tesVal.equals("whale07")){
                    messageUser.setBackgroundResource(R.drawable.whale07);
                }
                else if (tesVal.equals("whale08")){
                    messageUser.setBackgroundResource(R.drawable.whale08);
                }
                else if (tesVal.equals("whale09")){
                    messageUser.setBackgroundResource(R.drawable.whale09);
                }
                else if (tesVal.equals("whale10")){
                    messageUser.setBackgroundResource(R.drawable.whale10);
                }
                else if (tesVal.equals("whale11")){
                    messageUser.setBackgroundResource(R.drawable.whale11);
                }
                else if (tesVal.equals("whale12")){
                    messageUser.setBackgroundResource(R.drawable.whale12);
                }
                else if (tesVal.equals("whale13")){
                    messageUser.setBackgroundResource(R.drawable.whale13);
                }
                else if (tesVal.equals("whale14")){
                    messageUser.setBackgroundResource(R.drawable.whale14);
                }
                else if (tesVal.equals("whale15")){
                    messageUser.setBackgroundResource(R.drawable.whale15);
                }
                else if (tesVal.equals("whale16")){
                    messageUser.setBackgroundResource(R.drawable.whale16);
                }
                else {
                    messageUser.setBackgroundResource(R.drawable.bluewhale);
                }

                // Set their text
                messageText.setText(model.getMessageText());
                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }

}


