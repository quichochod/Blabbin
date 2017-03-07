package edu.usf.cse.danielap.blabbnlogin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.database.DatabaseUtils;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cse.usf.edu.android.db.BlabbinDBManager;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */

public class BlabinLoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blabin_login);


        // Font path
        String fontHobo = "fonts/Hobo Std Medium.ttf";
        String fontHelvetica = "fonts/HelveticaNeue.ttf";

        // text view label
        TextView txtLoginbut = (TextView) findViewById(R.id.email_sign_in_button);
        TextView txtCreateUser = (TextView) findViewById(R.id.create_user_button);
        TextView txtemail = (TextView) findViewById(R.id.email);
        TextView txtpassword = (TextView) findViewById(R.id.password);

        // Loading Font Face
        Typeface tfHobo = Typeface.createFromAsset(getAssets(), fontHobo);
        Typeface tfHelvetica = Typeface.createFromAsset(getAssets(), fontHelvetica);

        // Applying font
        txtLoginbut.setTypeface(tfHobo);
        txtemail.setTypeface(tfHelvetica);
        txtpassword.setTypeface(tfHelvetica);
        txtCreateUser.setTypeface(tfHobo);
    }
}

