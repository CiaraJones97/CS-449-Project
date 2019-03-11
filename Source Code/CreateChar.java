package com.example.dnd;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class CreateChar extends AppCompatActivity {

    static SharedPreferences temp;
    static SharedPreferences.Editor edit;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_char);

        final TextView Build_text = findViewById(R.id.build_create);
        temp = this.getPreferences(MODE_PRIVATE);
        edit = temp.edit();
        edit.putString("Race", " ");
        edit.putString("Class", " ");
        edit.commit();
        Build_text.setText("Build Created: "+temp.getString("Race", " ")+", "+temp.getString("Class"," "));

        //Choose Fantasy Race Buttons
        //Create Character Screen
        Button humanButton = findViewById(R.id.human_button);
        humanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("Race","Human");
                edit.commit();

                Build_text.setText("Build Created: "+temp.getString("Race", " ")+", "+temp.getString("Class"," "));
            }
        });

        Button elfButton = findViewById(R.id.elf_button);
        elfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("Race","Elf");
                edit.commit();

                Build_text.setText("Build Created: "+temp.getString("Race", " ")+", "+temp.getString("Class"," "));
            }
        });

        Button orcButton = findViewById(R.id.orc_button);
        orcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("Race","Orc");
                edit.commit();

                Build_text.setText("Build Created: "+temp.getString("Race", " ")+", "+temp.getString("Class"," "));
            }
        });

        Button tiefButton = findViewById(R.id.tiefling_button);
        tiefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("Race","Tiefling");
                edit.commit();

                Build_text.setText("Build Created: "+temp.getString("Race", " ")+", "+temp.getString("Class"," "));
            }
        });

        //Choose Fantasy Class Buttons
        Button rogueButton = findViewById(R.id.rogue_button);
        rogueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("Class","Rogue");
                edit.commit();

                Build_text.setText("Build Created: "+temp.getString("Race", " ")+", "+temp.getString("Class"," "));

            }
        });

        Button bardButton = findViewById(R.id.bard_button);
        bardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("Class","Bard");
                edit.commit();

                Build_text.setText("Build Created: "+temp.getString("Race", " ")+", "+temp.getString("Class"," "));
            }
        });

        Button palaButton = findViewById(R.id.paladin_button);
        palaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("Class","Paladin");
                edit.commit();

                Build_text.setText("Build Created: "+temp.getString("Race", " ")+", "+temp.getString("Class"," "));
            }
        });

        Button wizButton = findViewById(R.id.wizard_button);
        wizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("Class","Wizard");
                edit.commit();

                Build_text.setText("Build Created: "+temp.getString("Race", " ")+", "+temp.getString("Class"," "));
            }
        });

        //Confirm Character Selection
        Button confirmButton = findViewById(R.id.confirm_char);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp.getString("Race"," ").equals(" ") || temp.getString("Class", " ").equals(" "))
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CreateChar.this);
                    alert.setTitle("Error");
                    alert.setMessage("Character Not Created");
                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();
                }
                else {
                    Intent intent = new Intent(CreateChar.this, MainActivity.class);
                    intent.putExtra("CharClass",temp.getString("Class", "NA"));
                    intent.putExtra("CharRace", temp.getString("Race", "NA"));

                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
