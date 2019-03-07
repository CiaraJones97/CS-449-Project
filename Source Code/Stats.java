package com.example.minidnd;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class Stats extends AppCompatActivity {

    //static SharedPreferences set;
    //static SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        //set = this.getPreferences(MODE_PRIVATE);
        //edit = set.edit();

        String Class = getIntent().getExtras().getString("Class");
        String Race = getIntent().getExtras().getString("Race");

        final TextView Build_text = findViewById(R.id.build_create2);
        Build_text.setText("Build Created: "+ Race + ", " + Class);


    }
}
