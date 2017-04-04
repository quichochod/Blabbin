package edu.usf.cse.danielap.blabbnlogin;

/**
 * Created by adria on 3/5/2017.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1500;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {

        /*
         * Showing splash screen with a timer. This will be useful when you
         * want to show case your app logo / company
         */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if (user != null) {
                    // User is signed in
                    Intent i = new Intent(SplashScreen.this, BlabbinChooseWhaleActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                } else {
                    // User is signed out
                    Intent i = new Intent(SplashScreen.this, BlabinLoginActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);
    }

}




