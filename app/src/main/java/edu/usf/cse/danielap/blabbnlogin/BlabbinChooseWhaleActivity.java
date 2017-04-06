package edu.usf.cse.danielap.blabbnlogin;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cse.usf.edu.android.db.BlabbinDBHelper;
import cse.usf.edu.android.db.BlabbinDBManager;

import static edu.usf.cse.danielap.blabbnlogin.R.id.container;


/**
 * Created by danielaperez on 3/3/17.
 */

public class BlabbinChooseWhaleActivity extends ActionBarActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private FirebaseAuth mAuth;
    long newWhale;
    Button signOut;
    //BlabbinDBManager myBlabbinDBManager;
    //BlabbinDBHelper exDB = BlabbinDBHelper.getInstance(this);
   // final Context context = this;
    private static String whaleName;

    public static String getWhaleName(){
        return whaleName;
    }
    public static void setWhaleName(String wn)
    {
        BlabbinChooseWhaleActivity.whaleName = wn;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blabbin_choosewhale);


        signOut = (Button) findViewById(R.id.signoutbut);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent out = new Intent(BlabbinChooseWhaleActivity.this, BlabinLoginActivity.class);
                startActivity(out);
                finish();

            }
        });

        // Font path
        String fontPath = "fonts/Hobo Std Medium.ttf";
        //String fontPath2 = "fonts/HelveticaNeue.ttf";

        // text view label
        Button signoutButton = (Button) findViewById(R.id.signoutbut);


        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        //Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPath2);

        // Applying font
        //signoutButton.setTypeface(tf2);


        defineButtons();
    }

    public void defineButtons() {
        findViewById(R.id.Whale1).setOnClickListener(buttonClickListener);
        findViewById(R.id.Whale2).setOnClickListener(buttonClickListener);
        findViewById(R.id.Whale3).setOnClickListener(buttonClickListener);
        findViewById(R.id.Whale4).setOnClickListener(buttonClickListener);

        findViewById(R.id.Whale5).setOnClickListener(buttonClickListener);
        findViewById(R.id.Whale6).setOnClickListener(buttonClickListener);
        findViewById(R.id.Whale7).setOnClickListener(buttonClickListener);
        findViewById(R.id.Whale8).setOnClickListener(buttonClickListener);

        findViewById(R.id.Whale9).setOnClickListener(buttonClickListener);
        findViewById(R.id.Whale10).setOnClickListener(buttonClickListener);
        findViewById(R.id.Whale11).setOnClickListener(buttonClickListener);
        findViewById(R.id.Whale12).setOnClickListener(buttonClickListener);

        findViewById(R.id.Whale13).setOnClickListener(buttonClickListener);
        findViewById(R.id.Whale14).setOnClickListener(buttonClickListener);
        findViewById(R.id.Whale15).setOnClickListener(buttonClickListener);
        findViewById(R.id.Whale16).setOnClickListener(buttonClickListener);
    }

    //final Context context = this;
    Intent intent;
    //add this variable declaration:
    public static String whaleId;

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.Whale1:
                    newUser("whale01");
                    //myBlabbinDBManager.createUser("whale01");
                    //intent = new Intent(context, WhaleListScreen.class);
                    //startActivity(intent);
                    break;
                case R.id.Whale2:
                    newUser("whale02");
                    //myBlabbinDBManager.createUser("whale02");
                    //intent = new Intent(context, WhaleListScreen.class);
                   // startActivity(intent);
                    break;
                case R.id.Whale3:
                    newUser("whale03");
                    //myBlabbinDBManager.createUser("whale03");
                    //intent = new Intent(context, WhaleListScreen.class);
                    //startActivity(intent);
                    break;
                case R.id.Whale4:
                    newUser("whale04");
                    //intent = new Intent(context, WhaleListScreen.class);
                    //startActivity(intent);
                    break;

                case R.id.Whale5:
                    newUser("whale05");
                    //intent = new Intent(context, WhaleListScreen.class);
                    //startActivity(intent);
                    break;
                case R.id.Whale6:
                    newUser("whale06");
                    //intent = new Intent(context, WhaleListScreen.class);
                    //startActivity(intent);
                    break;
                case R.id.Whale7:
                    newUser("whale07");
                    //intent = new Intent(context, WhaleListScreen.class);
                    //startActivity(intent);
                    break;
                case R.id.Whale8:
                    newUser("whale08");
                    //intent = new Intent(context, WhaleListScreen.class);
                    //startActivity(intent);
                    break;

                case R.id.Whale9:
                    newUser("whale09");
                    //intent = new Intent(context, WhaleListScreen.class);
                    //startActivity(intent);
                    break;
                case R.id.Whale10:
                    newUser("whale10");
                    //intent = new Intent(context, WhaleListScreen.class);
                    //startActivity(intent);
                    break;
                case R.id.Whale11:
                    newUser("whale11");
                    //intent = new Intent(context, WhaleListScreen.class);
                    //startActivity(intent);
                    break;
                case R.id.Whale12:
                    newUser("whale12");
                    //intent = new Intent(context, WhaleListScreen.class);
                   // startActivity(intent);
                    break;
                case R.id.Whale13:
                    newUser("whale13");
                   // intent = new Intent(context, WhaleListScreen.class);
                   // startActivity(intent);
                    break;
                case R.id.Whale14:
                    newUser("whale14");
                    //intent = new Intent(context, WhaleListScreen.class);
                    //startActivity(intent);
                    break;
                case R.id.Whale15:
                    newUser("whale15");
                    //intent = new Intent(context, WhaleListScreen.class);
                    //startActivity(intent);
                    break;
                case R.id.Whale16:
                    newUser("whale16");
                    //intent = new Intent(context, WhaleListScreen.class);
                    //startActivity(intent);
                    break;
            }
        }
    };

    public void newUser(String whaleName){

        setWhaleName(whaleName);
        Intent i = new Intent(BlabbinChooseWhaleActivity.this, WhaleListScreen.class);
        i.putExtra("User Whale", whaleName);
        i.putExtra("Status", "False");
        startActivity(i);


    }

}
