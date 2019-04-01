package com.example.dnd;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

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

        //Get the data from MainActivity.java
        edit.putString("Class", getIntent().getStringExtra("CharClass"));
        edit.putString("Race", getIntent().getStringExtra("CharRace"));
        edit.putInt("Health", getIntent().getIntExtra("CharHealth",-10));
        edit.putInt("Initiative", getIntent().getIntExtra("CharInitiative", -10));
        edit.putInt("AC", getIntent().getIntExtra("CharAC",-10));
        edit.putInt("Pro", getIntent().getIntExtra("CharPro", -10));
        edit.putInt("Str", getIntent().getIntExtra("CharStr",-10));
        edit.putInt("Dex", getIntent().getIntExtra("CharDex", -10));
        edit.putInt("Con", getIntent().getIntExtra("CharCon",-10));
        edit.putInt("Int", getIntent().getIntExtra("CharInt", -10));
        edit.putInt("Wis", getIntent().getIntExtra("CharWis",-10));
        edit.putInt("Cha", getIntent().getIntExtra("CharCha", -10));
        edit.putInt("Page",x);
        edit.commit();


        final Enemies enemy = new Enemies(new Random().nextInt(5));
        final TextView Instruct_text = findViewById(R.id.instruct_text);
        final TextView Health_num = findViewById(R.id.health_num);
        ProgressBar HealthBar = (ProgressBar) findViewById(R.id.progressBar);
        Next = findViewById(R.id.next_button);

        Instruct_text.setText("WELCOME TO THE GAME!!\n You are a " +
                set.getString("Race", "NA") + ", " +
                set.getString("Class", "NA") +
                ".\nAccess your Stats anytime from the upper menu" +
                "\nWatch your health bar below" +
                "\nHit the NEXT Button to continue");

        HealthBar.setMax(set.getInt("Health",-10));
        HealthBar.setProgress(set.getInt("Health",-10));
        HealthBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        Health_num.setText("Health: " + set.getInt("Health",-10));


        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x++;
                edit.putInt("Page",x);
                edit.commit();

                switch(set.getInt("Page",0))
                {
                    case 2:
                        Instruct_text.setText("Your story starts in the small village." +
                                "\nYou are standing in the center of town when you hear a noise" +
                                "\nYou look in the direction and see a " + enemy.getEnemyName()+
                                "\nattacking a nearby store" +
                                "\nWhat do you want to do?");
                        break;
                    case 3:
                        Instruct_text.setText("Next Page");
                        break;

                }
            }
        });


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
            statIntent.putExtra("Press", true);
            startActivity(statIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
