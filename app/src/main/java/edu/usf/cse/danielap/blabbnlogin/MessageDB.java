package edu.usf.cse.danielap.blabbnlogin;

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

import static android.R.attr.value;


/**
 * Created by danielaperez on 3/26/17.
 */

import edu.usf.cse.danielap.blabbnlogin.WhaleListScreen;

public class MessageDB extends AppCompatActivity {

    private FirebaseListAdapter<ChatMessage> adapter;
    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference messageRef = database.getReference("message");
    //String value="TEST";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages_main_act);


        //displayChatMessages();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                EditText input = (EditText)findViewById(R.id.input);

                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("messages_whale01").push().setValue(new ChatMessage(input.getText().toString()));
                Log.d("ADebugTag", "Value:1 " + input.getText().toString());

                // Clear the input
                input.setText("");


            }


        });

    displayChatMessages();
    }

    private void displayChatMessages() {

        ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);

        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message, FirebaseDatabase.getInstance().getReference().child("messages_whale01")) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                //ImageView image1 = (ImageView)findViewById(R.id.image1);
                //image1.setImageResource(R.drawable.ic_arrow_forward_black);
                //Set whale image
                messageUser.setBackgroundResource(R.drawable.whale01);

                // Set their text
                messageText.setText(model.getMessageText());
                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));
            }
        };


        listOfMessages.setAdapter(adapter);

    }


}


