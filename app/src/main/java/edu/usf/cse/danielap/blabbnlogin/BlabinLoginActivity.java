package edu.usf.cse.danielap.blabbnlogin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.database.DatabaseUtils;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import cse.usf.edu.android.db.BlabbinDBHelper;
import cse.usf.edu.android.db.BlabbinDBManager;

import static com.facebook.internal.FacebookRequestErrorClassification.KEY_NAME;
import static edu.usf.cse.danielap.blabbnlogin.R.id.contact;


/**
 * A login screen that offers login via email/password.
 */

public class BlabinLoginActivity extends AppCompatActivity {

    //Firebase Authentication Code.
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public static final String TAG = "Blabbin Login";



    // UI references.
    private AutoCompleteTextView mUserNameView;
    private EditText mPasswordView;


    BlabbinDBManager myBlabbinDBManager;
    long newUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blabin_login);

        // Get Username and Password field views
        mUserNameView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        // Font path
        String fontPath = "fonts/Hobo Std Medium.ttf";
        //String fontPath2 = "fonts/HelveticaNeue.ttf";

        // text view label
        Button txtCreateUser = (Button) findViewById(R.id.create_user_button);
        Button txtLogin = (Button) findViewById(R.id.login);
        TextView txtemail = (TextView) findViewById(R.id.email);
        TextView txtpass = (TextView) findViewById(R.id.password);

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        //Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPath2);


        // Applying font
        txtCreateUser.setTypeface(tf);
        txtLogin.setTypeface(tf);
        //txtpass.setTypeface(tf2);
        //txtemail.setTypeface(tf2);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


        // Get Username and Password field views
        mUserNameView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);


        // Create a User
        Button createUser = (Button) findViewById(R.id.create_user_button);
        createUser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

        // Login a User
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                signIn();
            }
        });



        // Font path
        String fontHobo = "fonts/Hobo Std Medium.ttf";
        String fontHelvetica = "fonts/HelveticaNeue.ttf";

        // Loading Font Face
        Typeface tfHobo = Typeface.createFromAsset(getAssets(), fontHobo);
        Typeface tfHelvetica = Typeface.createFromAsset(getAssets(), fontHelvetica);


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    private void createAccount(){

        String email = mUserNameView.getText().toString();
        String pw = mPasswordView.getText().toString();


        mAuth.createUserWithEmailAndPassword(email, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(BlabinLoginActivity.this, "Authentication failed. Try again.",
                                    Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(BlabinLoginActivity.this, BlabinLoginActivity.class);
                            startActivity(i);

                            // close this activity
                            finish();
                        }
                        else {

                            //newUser();
                            //updateContact();
                            Intent i = new Intent(BlabinLoginActivity.this, BlabbinChooseWhaleActivity.class);
                            startActivity(i);

                            // close this activity
                            finish();
                        }

                        // ...
                    }
                });

    }


    private void signIn(){


        String email = mUserNameView.getText().toString();
        String pw = mPasswordView.getText().toString();
        String whale = null;


        mAuth.signInWithEmailAndPassword(email, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(BlabinLoginActivity.this, "Authentication failed. Try again.",
                                    Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(BlabinLoginActivity.this, BlabinLoginActivity.class);
                            startActivity(i);
                            finish();

                        }
                        else{


                            Intent i = new Intent(BlabinLoginActivity.this, BlabbinChooseWhaleActivity.class);
                            startActivity(i);
                            finish();
                        }

                        // ...
                    }
                });




    }




}

