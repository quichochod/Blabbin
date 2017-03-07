package edu.usf.cse.danielap.blabbnlogin;

// Created by quichochod on 3/3/2017.

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.app.LoaderManager.LoaderCallbacks;
import android.view.View;

public class WhaleListScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whale_list_screen);
        defineButtonsChooseWhale();
    }

    public void defineButtonsChooseWhale() {
        findViewById(R.id.buttonInitialWhale).setOnClickListener(buttonClickListener);
    }

    final Context context = this;
    Intent intent;

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.buttonInitialWhale:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

}