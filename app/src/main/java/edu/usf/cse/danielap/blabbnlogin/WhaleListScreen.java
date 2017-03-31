package edu.usf.cse.danielap.blabbnlogin;

// Created by quichochod on 3/3/2017.

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.app.LoaderManager.LoaderCallbacks;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WhaleListScreen extends AppCompatActivity{

    TextView whaleName;
    ImageButton addWhale;
    EditText inputWhaleName;
    Button submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whale_list_screen);

        whaleName = (TextView) findViewById(R.id.text_add_whale);
        addWhale = (ImageButton) findViewById(R.id.buttonAddWhale);
        inputWhaleName = (EditText) findViewById(R.id.NameWhale);
        submit = (Button) findViewById(R.id.SubmitWhaleName);
        //initialWhale = (ImageButton) findViewById(R.id.buttonInitialWhale);

        addWhale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWhale.setVisibility(view.INVISIBLE);
                whaleName.setVisibility(view.INVISIBLE);

                inputWhaleName.setVisibility(view.VISIBLE);
                submit.setVisibility(view.VISIBLE);


            }
        });

//        initialWhale.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                Intent intent = new Intent(WhaleListScreen.this, MessageDB.class);
//                    startActivity(intent);
//
//                finish();
//            }
//
//
//        });

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                createNewButton();


                addWhale.setVisibility(view.VISIBLE);
                whaleName.setVisibility(view.VISIBLE);

                inputWhaleName.setVisibility(view.INVISIBLE);
                submit.setVisibility(view.INVISIBLE);
            }
        });


    }


    public void createNewButton()
    {
        Button myButton = new Button(this);
        myButton.setText(inputWhaleName.getText().toString());
        myButton.setTextSize(24);
        myButton.setBackgroundColor(Color.WHITE);
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(WhaleListScreen.this, MessageDB.class);
                    intent.putExtra("Whale Name", inputWhaleName.getText().toString());
                    startActivity(intent);
                //Retrieve whale name like this....
                //Intent intent = getIntent();
                //String whaleName = intent.getExtras().getString("Whale Name");

                finish();
            }
        });



        LinearLayout ll = (LinearLayout) findViewById(R.id.whaleLayout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 40, 0, 0);


        ll.addView(myButton, lp);


    }


}