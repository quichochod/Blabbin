package edu.usf.cse.danielap.blabbnlogin;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by danielaperez on 3/3/17.
 */

public class BlabbinWhaleActivity extends ActionBarActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blabbin_whale);

        // Font path
        String fontPath = "fonts/Hobo Std Medium.ttf";

        // text view label
        TextView txtWhale = (TextView) findViewById(R.id.textView2);

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        // Applying font
        txtWhale.setTypeface(tf);
    }


}
