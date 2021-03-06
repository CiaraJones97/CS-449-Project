package com.example.minidnd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static SharedPreferences settings;
    static SharedPreferences.Editor editor;
    ArrayList<String> First_Spell;
    ArrayList<String> Second_Spell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        settings = this.getPreferences(MODE_PRIVATE);
        editor = settings.edit();
        editor.commit();

        Button createChar = findViewById(R.id.create_char_button);
        createChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent charIntent = new Intent(MainActivity.this, CreateChar.class);
                startActivityForResult(charIntent, 101);

            }
        });

        //User chooses to start the game
        Button startGame = findViewById(R.id.start_game_button);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (settings.getString("Race", "NA").equals("NA") ||
                        settings.getString("Class", "NA").equals("NA") ||
                        First_Spell == null){
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Error");
                    alert.setMessage("Create A Character Before Starting The Game");
                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();
                }
                else{
                    //The needed data is passed to the game file
                    Intent gameIntent = new Intent(MainActivity.this, Game.class);
                    gameIntent.putExtra("CharRace", settings.getString("Race", "NA"));
                    gameIntent.putExtra("CharClass", settings.getString("Class", "NA"));
                    gameIntent.putExtra("CharHealth", settings.getInt("Health", -10));
                    gameIntent.putExtra("CharInitiative", settings.getInt("Initiative", -10));
                    gameIntent.putExtra("CharAC", settings.getInt("AC", -10));
                    gameIntent.putExtra("CharPro", settings.getInt("Pro", -10));
                    gameIntent.putExtra("CharStr", settings.getInt("Str", -10));
                    gameIntent.putExtra("CharDex", settings.getInt("Dex", -10));
                    gameIntent.putExtra("CharCon", settings.getInt("Con", -10));
                    gameIntent.putExtra("CharInt", settings.getInt("Int", -10));
                    gameIntent.putExtra("CharWis", settings.getInt("Wis", -10));
                    gameIntent.putExtra("CharCha", settings.getInt("Cha", -10));
                    gameIntent.putExtra("CharFirst", settings.getInt("First",-10));
                    gameIntent.putExtra("CharSecond", settings.getInt("Second",-10));
                    gameIntent.putStringArrayListExtra("CharFirst_Spell", First_Spell);
                    gameIntent.putStringArrayListExtra("CharSecond_Spell", Second_Spell);
                    startActivityForResult(gameIntent, 103);
                }

            }
        });

    }

    //Return results from the called activities
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case 101:
                if (resultCode == Activity.RESULT_OK) {
                    editor.putString("Race",data.getStringExtra("CharRace"));
                    editor.putString("Class", data.getStringExtra("CharClass"));
                    editor.putBoolean("Press", false);
                    editor.commit();
                    Intent statIntent = new Intent(MainActivity.this, Stats.class);
                    statIntent.putExtra("CharClass",settings.getString("Class", " "));
                    statIntent.putExtra("CharRace", settings.getString("Race", " "));
                    statIntent.putExtra("Press", settings.getBoolean("Press", false));
                    startActivityForResult(statIntent, 102);
                    break;

                }
                else {
                    editor.putString("Race", "NA");
                    editor.putString("Class", "NA");
                    editor.commit();

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Error");
                    alert.setMessage("Character Not Confirmed");
                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();
                    break;
                }

            case 102:
                if (resultCode == Activity.RESULT_OK) {
                    if (!settings.getBoolean("Press",false))
                    {
                        editor.putInt("Health", data.getIntExtra("CharHealth",-10));
                        editor.putInt("Initiative", data.getIntExtra("CharInitiative", -10));
                        editor.putInt("AC", data.getIntExtra("CharAC", -10));
                        editor.putInt("Pro", data.getIntExtra("CharPro",-10));
                        editor.putInt("Str", data.getIntExtra("CharStr", -10));
                        editor.putInt("Dex", data.getIntExtra("CharDex", -10));
                        editor.putInt("Con", data.getIntExtra("CharCon",-10));
                        editor.putInt("Int", data.getIntExtra("CharInt", -10));
                        editor.putInt("Wis", data.getIntExtra("CharWis", -10));
                        editor.putInt("Cha", data.getIntExtra("CharCha", -10));
                        editor.putInt("First", data.getIntExtra("CharFirst", -10));
                        editor.putInt("Second", data.getIntExtra("CharSecond", -10));
                        First_Spell = data.getStringArrayListExtra("CharFirst_Spell");
                        Second_Spell = data.getStringArrayListExtra("CharSecond_Spell");
                        editor.commit();
                    }
                    break;
                }
                else {
                    break;
                }

            case 103:
                editor.clear();
                editor.commit();
                break;

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

        //Access the STATS page
        if (id == R.id.menu_stats) {
            if (settings.getString("Class", "NA").equals("NA"))
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Error");
                alert.setMessage("Confirm a Character to Access the Stats Page");
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
            else{
                editor.putBoolean("Press",true);
                editor.commit();
                Intent statIntent = new Intent(MainActivity.this, Stats.class);
                statIntent.putExtra("CharClass",settings.getString("Class", " "));
                statIntent.putExtra("CharRace", settings.getString("Race", " "));
                statIntent.putExtra("Press", settings.getBoolean("Press",false));
                startActivityForResult(statIntent, 102);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}