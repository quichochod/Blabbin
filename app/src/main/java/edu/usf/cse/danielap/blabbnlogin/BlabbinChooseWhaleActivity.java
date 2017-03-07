package edu.usf.cse.danielap.blabbnlogin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import static edu.usf.cse.danielap.blabbnlogin.R.id.container;


/**
 * Created by danielaperez on 3/3/17.
 */

public class BlabbinChooseWhaleActivity extends ActionBarActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blabbin_choosewhale);

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

    final Context context = this;
    Intent intent;

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.Whale1:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.Whale2:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.Whale3:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.Whale4:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;

                case R.id.Whale5:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.Whale6:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.Whale7:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.Whale8:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;

                case R.id.Whale9:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.Whale10:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.Whale11:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.Whale12:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;

                case R.id.Whale13:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.Whale14:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.Whale15:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.Whale16:
                    intent = new Intent(context, BlabbinWhaleActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };


  /*  public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.Whale1);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, BlabbinWhaleActivity.class);
                startActivity(intent);

            }

        });

    }*/
}
