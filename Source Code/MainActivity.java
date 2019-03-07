package com.example.minidnd;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button CreateChar;
    String Race;
    String Class;

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

        CreateChar = (Button) findViewById(R.id.create_char_button);
        CreateChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent charIntent = new Intent(MainActivity.this, CreateChar.class);
                startActivityForResult(charIntent, 101);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case 101:
                if (resultCode == Activity.RESULT_OK) {
                    editor.putString("Race",data.getStringExtra("CharRace"));
                    editor.putString("Class", data.getStringExtra("CharClass"));
                    editor.commit();

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
            Intent statIntent = new Intent(MainActivity.this, Stats.class);
            startActivity(statIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
