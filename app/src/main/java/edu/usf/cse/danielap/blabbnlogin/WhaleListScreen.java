package edu.usf.cse.danielap.blabbnlogin;

// Created by quichochod on 3/3/2017.

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.app.LoaderManager.LoaderCallbacks;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

        addWhale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWhale.setVisibility(view.INVISIBLE);
                whaleName.setVisibility(view.INVISIBLE);

                inputWhaleName.setVisibility(view.VISIBLE);
                submit.setVisibility(view.VISIBLE);



            }
        });





    }



//    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//                if (view.getId() == R.id.buttonAddWhale) {
//                    View createWhale = WhaleListScreen.this.findViewById(R.id.text_add_whale);
//                    createWhale.setVisibility(View.INVISIBLE);
//
//                    View whaleName = WhaleListScreen.this.findViewById(R.id.NameWhale);
//                    whaleName.setVisibility(View.VISIBLE);
//
//
//                }
//
//
//            switch (view.getId()) {
//                case R.id.buttonInitialWhale:
////                    intent = new Intent(context, BlabbinWhaleActivity.class);
////                    startActivity(intent);
//                    break;
//            }
//        }
//    };

}