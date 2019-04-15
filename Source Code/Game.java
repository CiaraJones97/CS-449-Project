package com.example.minidnd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    static SharedPreferences set;
    static SharedPreferences.Editor edit;
    int x = 1;
    ArrayList<String> First_Spell;
    ArrayList<String> Second_Spell;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        set = this.getPreferences(MODE_PRIVATE);
        edit = set.edit();
        edit.putInt("Page",x);
        edit.apply();

        //Get the data from MainActivity.java at the start of the game
        edit.putString("Class", getIntent().getStringExtra("CharClass"));
        edit.putString("Race", getIntent().getStringExtra("CharRace"));
        edit.putInt("Health", getIntent().getIntExtra("CharHealth", -10));
        edit.putInt("Initiative", getIntent().getIntExtra("CharInitiative", -10));
        edit.putInt("AC", getIntent().getIntExtra("CharAC", -10));
        edit.putInt("Pro", getIntent().getIntExtra("CharPro", -10));
        edit.putInt("Str", getIntent().getIntExtra("CharStr", -10));
        edit.putInt("Dex", getIntent().getIntExtra("CharDex", -10));
        edit.putInt("Con", getIntent().getIntExtra("CharCon", -10));
        edit.putInt("Int", getIntent().getIntExtra("CharInt", -10));
        edit.putInt("Wis", getIntent().getIntExtra("CharWis", -10));
        edit.putInt("Cha", getIntent().getIntExtra("CharCha", -10));
        edit.putInt("First", getIntent().getIntExtra("CharFirst", -10));
        edit.putInt("Second", getIntent().getIntExtra("CharSecond", -10));
        First_Spell = getIntent().getStringArrayListExtra("CharFirst_Spell");
        Second_Spell = getIntent().getStringArrayListExtra("CharSecond_Spell");
        edit.putBoolean("Continue", true);
        edit.commit();

        final Enemies enemy = new Enemies();
        final TextView Instruct_text = findViewById(R.id.instruct_text);
        final TextView Health_num = findViewById(R.id.health_num);
        final Button Next = findViewById(R.id.next_button);
        final Button Fight = findViewById(R.id.fight_button);
        final Button Flee = findViewById(R.id.flee_button);
        final Button Inventory = findViewById(R.id.inventory_button);
        final Button Attack_1 = findViewById(R.id.attack_button_1);
        final Button Attack_2 = findViewById(R.id.attack_button_2);
        final Button Attack_3 = findViewById(R.id.attack_button_3);
        final Button Attack_4 = findViewById(R.id.attack_button_4);
        final Button Attack_5 = findViewById(R.id.attack_button_5);
        ProgressBar HealthBar = findViewById(R.id.progressBar);


        //Initial Message at the start of the game
        Instruct_text.setText("WELCOME TO THE GAME!!\n You are a LEVEL 3 " +
                set.getString("Race", "NA") + ", " +
                set.getString("Class", "NA") +
                ".\nFor this game you have been given a pre-made character sheet" +
                ".\nAccess your STATS PAGE anytime from the upper menu" +
                "\nWatch your HEALTH BAR below" +
                "\nHit the NEXT Button to continue");

        //Remove the FIGHT, FLEE, INVENTORY, and ATTACK buttons
        Fight.setVisibility(View.GONE);
        Flee.setVisibility(View.GONE);
        Inventory.setVisibility(View.GONE);
        Attack_1.setVisibility(View.GONE);
        Attack_2.setVisibility(View.GONE);
        Attack_3.setVisibility(View.GONE);
        Attack_4.setVisibility(View.GONE);

        HealthBar.setMax(set.getInt("Health",-10));
        HealthBar.setProgress(set.getInt("Health",-10));
        HealthBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        Health_num.setText("Health: " + set.getInt("Health",-10));


        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putInt("Page",x++);
                edit.commit();

                switch(set.getInt("Page",0))
                {
                    case 2:
                        enemy.newEnemy();
                        Instruct_text.setText("Your story starts in the small village." +
                                "\nYou are standing in the center of town when you hear a noise." +
                                "\nYou look in the direction and see a " + enemy.getEnemyName()+
                                " attacking a nearby store." + "\nWhat do you want to do?");
                        edit.putBoolean("Continue", false);
                        edit.commit();
                        break;
                    case 3:
                        Instruct_text.setText("Next Page");
                        break;

                }

                //Remove the visibility of the NEXT button if combat is about to start
                if (!set.getBoolean("Continue",true)) {
                    Next.setVisibility(View.GONE);
                    Fight.setVisibility(View.VISIBLE);
                    Flee.setVisibility(View.VISIBLE);
                    Inventory.setVisibility(View.VISIBLE);
                }
                else {
                    Next.setVisibility(View.VISIBLE);
                    Fight.setVisibility(View.GONE);
                    Flee.setVisibility(View.GONE);
                    Inventory.setVisibility(View.GONE);
                    Attack_1.setVisibility(View.GONE);
                    Attack_2.setVisibility(View.GONE);
                    Attack_3.setVisibility(View.GONE);
                    Attack_4.setVisibility(View.GONE);
                    Attack_5.setVisibility(View.GONE);
                }
            }
        });

        Fight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (set.getInt("First",0)==0){
                    Attack_5.setVisibility(View.VISIBLE);
                    Attack_5.setText("Melee");
                }
                else {
                    Attack_1.setVisibility(View.VISIBLE);
                    Attack_2.setVisibility(View.VISIBLE);
                    Attack_3.setVisibility(View.VISIBLE);
                    Attack_4.setVisibility(View.VISIBLE);
                    Attack_1.setText(First_Spell.get(0));
                    Attack_2.setText(First_Spell.get(1));
                    Attack_3.setText(First_Spell.get(2));
                    Attack_4.setText(First_Spell.get(3));
                    Attack_5.setVisibility(View.VISIBLE);
                    Attack_5.setText("Melee");
                }
            }
        });

        Flee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        Inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
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