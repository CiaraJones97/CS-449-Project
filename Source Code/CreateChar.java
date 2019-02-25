package com.example.minidnd;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CreateChar extends AppCompatActivity {
    //Create Character Screen
    private Button HumanButton;
    private Button ElfButton;
    private Button OrcButton;
    private Button TiefButton;

    private Button RogueButton;
    private Button BardButton;
    private Button PalaButton;
    private Button WizButton;

    static SharedPreferences temp;
    static SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_char);

        final TextView Build_text =(TextView)findViewById(R.id.build_create);
        temp = this.getPreferences(MODE_PRIVATE);
        edit = temp.edit();

        //Choose Fantasy Race Buttons
        HumanButton = (Button) findViewById(R.id.human_button);
        HumanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("Race","Human");
                edit.commit();
            }
        });

        ElfButton = (Button) findViewById(R.id.elf_button);
        ElfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("Race","Elf");
                edit.commit();
            }
        });

        OrcButton = (Button) findViewById(R.id.orc_button);
        OrcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("Race","Orc");
                edit.commit();
            }
        });

        TiefButton = (Button) findViewById(R.id.tiefling_button);
        TiefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("Race","Tiefling");
                edit.commit();
            }
        });

        //Choose Fantasy Class Buttons
        RogueButton = (Button) findViewById(R.id.rogue_button);
        RogueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("Class","Rogue");
                edit.commit();
            }
        });

        BardButton = (Button) findViewById(R.id.bard_button);
        BardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("Class","Bard");
                edit.commit();
            }
        });

        PalaButton = (Button) findViewById(R.id.paladin_button);
        PalaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("Class","Paladin");
                edit.commit();
            }
        });

        WizButton = (Button) findViewById(R.id.wizard_button);
        WizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("Class","Wizard");
                edit.commit();
            }
        });

        //Output Build
        Build_text.setText("Build Created: "+temp.getString("Race"," ")+", "+temp.getString("Class"," "));

    }
}
