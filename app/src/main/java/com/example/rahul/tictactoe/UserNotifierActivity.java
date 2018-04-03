package com.example.rahul.tictactoe;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;


public class UserNotifierActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_notifier);

        ImageButton setX = findViewById(R.id.selected_default_x);
        ImageButton setO = findViewById(R.id.selected_default_o);

        setX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoardManager.setP1X();
                finish();
            }
        });

        setO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoardManager.setP1O();
                finish();
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;


        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            //code for portrait mode
            getWindow().setLayout((width * 9) / 10, (height * 5) / 10);
        } else {
            //code for landscape mode
            getWindow().setLayout((width * 6) / 10, (height) / 2);
        }

    }
}
