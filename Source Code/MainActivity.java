package com.example.dnd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button CreateChar;
    private Button StartGame;

    static SharedPreferences settings;
    static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        settings = this.getPreferences(MODE_PRIVATE);
        editor = settings.edit();

        CreateChar = findViewById(R.id.create_char_button);
        CreateChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent charIntent = new Intent(MainActivity.this, CreateChar.class);
                startActivityForResult(charIntent, 101);

            }
        });

        //User chooses to start the game
        StartGame = findViewById(R.id.start_game_button);
        StartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (settings.getString("Race", "NA").equals("NA") ||
                        settings.getString("Class", "NA").equals("NA")){
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Error");
                    alert.setMessage("Create A Character Before Starting The Game");
                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();
                }
                else{
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
                    startActivity(gameIntent);
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
                        editor.commit();
                    }
                    break;
                }
                else {
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
