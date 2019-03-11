package com.example.dnd;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    static SharedPreferences set;
    static SharedPreferences.Editor edit;
    private Button Next;
    int x = 1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        set = this.getPreferences(MODE_PRIVATE);
        edit = set.edit();
        edit.commit();

        //Get the data from MainActivity.java
        edit.putString("Class", getIntent().getStringExtra("CharClass"));
        edit.putString("Race", getIntent().getStringExtra("CharRace"));
        edit.putInt("Health", getIntent().getIntExtra("CharHealth",-10));
        edit.putInt("Initiative", getIntent().getIntExtra("CharInitiative", -10));
        edit.putInt("AC", getIntent().getIntExtra("CharAc",-10));
        edit.putInt("Pro", getIntent().getIntExtra("CharPro", -10));
        edit.putInt("Str", getIntent().getIntExtra("CharStr",-10));
        edit.putInt("Dex", getIntent().getIntExtra("CharDex", -10));
        edit.putInt("Con", getIntent().getIntExtra("CharCon",-10));
        edit.putInt("Int", getIntent().getIntExtra("CharInt", -10));
        edit.putInt("Wis", getIntent().getIntExtra("CharWis",-10));
        edit.putInt("Cha", getIntent().getIntExtra("CharCha", -10));
        edit.putInt("Page",x);
        edit.commit();

        final TextView Instruct_text = findViewById(R.id.instruct_text);
        Next = findViewById(R.id.next_button);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x++;
                edit.putInt("Page",x);
                edit.commit();
            }
        });

        switch(set.getInt("Page",0))
        {
            case 1:
                Instruct_text.setText("WELCOME TO THE GAME!!\n You are a " +
                        set.getString("Race", "NA") + ", " +
                        set.getString("Class", "NA") +
                        ".\nAccess your Stats anytime from the upper menu" +
                        "\nHit the NEXT Button to continue");
                break;
            case 2:
                Instruct_text.setText("TEST PAGE");
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case 102:
                if (resultCode == Activity.RESULT_OK) {
                    break;
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_stats) {
            Intent statIntent = new Intent(Game.this, Stats.class);
            statIntent.putExtra("CharClass",set.getString("Class", " "));
            statIntent.putExtra("CharRace", set.getString("Race", " "));
            startActivityForResult(statIntent, 102);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
