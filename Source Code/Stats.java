package com.example.minidnd;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class Stats extends AppCompatActivity {
    int Health;
    int Initiative;
    int AC;
    int Pro;
    //Ability Mod
    int Str;
    int Dex;
    int Con;
    int Int;
    int Wis;
    int Cha;
    int First_Level;
    int Second_Level;

    ArrayList<String> First_Spells = new ArrayList<>();
    ArrayList<String> Second_Spells = new ArrayList<>();

    static SharedPreferences set;
    static SharedPreferences.Editor edit;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Intent intent = new Intent(Stats.this, MainActivity.class);

        set = this.getPreferences(MODE_PRIVATE);
        edit = set.edit();
        edit.commit();

        //Get the Class, Race, and Button Press status from MainActivity.java
        edit.putString("Class", getIntent().getStringExtra("CharClass"));
        edit.putString("Race", getIntent().getStringExtra("CharRace"));
        edit.putBoolean("Press", getIntent().getBooleanExtra("Press",false));
        edit.commit();

        First_Spells.clear();
        Second_Spells.clear();

        final TextView Build_text = findViewById(R.id.build_create2);
        final TextView Stat_text = findViewById(R.id.stats_text);

        //Set the needed values based on the user choice
        //Race: Elf
        if (set.getString("Race", " ").equals("Elf")) {
            switch (set.getString("Class", " ")) {
                case "Rogue":
                    Health = 24;
                    Initiative = 3;
                    AC = 14;
                    Pro = 2;
                    Str = 0;
                    Dex = 3;
                    Con = 2;
                    Int = 2;
                    Wis = -1;
                    Cha = 1;
                    First_Level = 0;
                    Second_Level = 0;
                    break;

                case "Bard":
                    Health = 21;
                    Initiative = 4;
                    AC = 17;
                    Pro = 2;
                    Str = 1;
                    Dex = 3;
                    Con = 1;
                    Int = -1;
                    Wis = 0;
                    Cha = 2;
                    First_Level = 4;
                    Second_Level = 2;
                    First_Spells.add("Cure Wounds");
                    First_Spells.add("Charm Person");
                    First_Spells.add("Detect Magic");
                    Second_Spells.add("Invisibility");
                    break;

                case "Paladin":
                    Health = 25;
                    Initiative = 0;
                    AC = 19;
                    Pro = 2;
                    Str = 2;
                    Dex = 0;
                    Con = 1;
                    Int = 0;
                    Wis = 1;
                    Cha = 2;
                    First_Level = 3;
                    Second_Level = 0;
                    First_Spells.add("Bless");
                    First_Spells.add("Searing Smite");
                    First_Spells.add("Compelled Duel");
                    First_Spells.add("Thunderous Smite");
                    break;

                case "Wizard":
                    Health = 20;
                    Initiative = 2;
                    AC = 12;
                    Pro = 2;
                    Str = 0;
                    Dex = 2;
                    Con = 2;
                    Int = 3;
                    Wis = 1;
                    Cha = -1;
                    First_Level = 4;
                    Second_Level = 2;
                    First_Spells.add("Mage Armor");
                    First_Spells.add("Charm Person");
                    First_Spells.add("Sleep");
                    First_Spells.add("Detect Magic");
                    Second_Spells.add("Suggestion");
                    Second_Spells.add("Invisibility");
                    break;

                default:
                    break;

            }
        }

        //Race: Human
        if (set.getString("Race", " ").equals("Human")) {
            switch (set.getString("Class", " ")) {
                case "Rogue":
                    Health = 24;
                    Initiative = 3;
                    AC = 14;
                    Pro = 2;
                    Str = 0;
                    Dex = 3;
                    Con = 2;
                    Int = 2;
                    Wis = -1;
                    Cha = 1;
                    First_Level = 0;
                    Second_Level = 0;
                    break;

                case "Bard":
                    Health = 24;
                    Initiative = 3;
                    AC = 17;
                    Pro = 2;
                    Str = 1;
                    Dex = 2;
                    Con = 2;
                    Int = 0;
                    Wis = -1;
                    Cha = 3;
                    First_Level = 4;
                    Second_Level = 2;
                    First_Spells.add("Charm Person");
                    First_Spells.add("Detect Magic");
                    First_Spells.add("Cure Wounds");
                    First_Spells.add("Faerie Fire");
                    Second_Spells.add("Hold Person");
                    break;

                case "Paladin":
                    Health = 28;
                    Initiative = 0;
                    AC = 18;
                    Pro = 2;
                    Str = 3;
                    Dex = 0;
                    Con = 2;
                    Int = -1;
                    Wis = 1;
                    Cha = 2;
                    First_Level = 3;
                    Second_Level = 0;
                    First_Spells.add("Cure Wounds");
                    First_Spells.add("Wrathful Smite");
                    First_Spells.add("Bless");
                    First_Spells.add("Compelled Duel");
                    break;

                case "Wizard":
                    Health = 20;
                    Initiative = 2;
                    AC = 12;
                    Pro = 2;
                    Str = -1;
                    Dex = 2;
                    Con = 2;
                    Int = 3;
                    Wis = 1;
                    Cha = 0;
                    First_Level = 4;
                    Second_Level = 2;
                    First_Spells.add("Mage Armor");
                    First_Spells.add("Charm Person");
                    First_Spells.add("Sleep");
                    First_Spells.add("Detect Magic");
                    Second_Spells.add("Suggestion");
                    Second_Spells.add("Invisibility");
                    break;

                default:
                    break;
            }

        }

        //Race: Orc
        if (set.getString("Race", " ").equals("Orc")) {
            switch (set.getString("Class", " ")) {
                case "Rogue":
                    Health = 24;
                    Initiative = 2;
                    AC = 13;
                    Pro = 2;
                    Str = 1;
                    Dex = 2;
                    Con = 2;
                    Int = 1;
                    Wis = -1;
                    Cha = 1;
                    First_Level = 0;
                    Second_Level = 0;
                    break;

                case "Bard":
                    Health = 24;
                    Initiative = 3;
                    AC = 17;
                    Pro = 2;
                    Str = 2;
                    Dex = 2;
                    Con = 2;
                    Int = -1;
                    Wis = 0;
                    Cha = 2;
                    First_Level = 4;
                    Second_Level = 2;
                    First_Spells.add("Cure Wounds");
                    First_Spells.add("Charm Person");
                    Second_Spells.add("Invisibility");
                    Second_Spells.add("Enhance Ability");
                    Second_Spells.add("Hold Person");
                    break;

                case "Paladin":
                    Health = 28;
                    Initiative = 0;
                    AC = 18;
                    Pro = 2;
                    Str = 3;
                    Dex = 0;
                    Con = 2;
                    Int = -1;
                    Wis = 1;
                    Cha = 2;
                    First_Level = 3;
                    Second_Level = 0;
                    First_Spells.add("Compelled Duel");
                    First_Spells.add("Thunderous Smite");
                    First_Spells.add("Bless");
                    First_Spells.add("Cure Wounds");
                    break;

                case "Wizard":
                    Health = 20;
                    Initiative = 1;
                    AC = 11;
                    Pro = 2;
                    Str = 0;
                    Dex = 1;
                    Con = 2;
                    Int = 2;
                    Wis = 1;
                    Cha = 0;
                    First_Level = 4;
                    Second_Level = 2;
                    First_Spells.add("Mage Armor");
                    First_Spells.add("Charm Person");
                    First_Spells.add("Sleep");
                    First_Spells.add("Detect Magic");
                    Second_Spells.add("Suggestion");
                    break;

                default:
                    break;
            }
        }

        //Race: Tiefling
        if (set.getString("Race", " ").equals("Tiefling")) {
            switch (set.getString("Class", " ")) {
                case "Rogue":
                    Health = 24;
                    Initiative = 2;
                    AC = 13;
                    Pro = 2;
                    Str = -1;
                    Dex = 2;
                    Con = 2;
                    Int = 2;
                    Wis = 0;
                    Cha = 2;
                    First_Level = 0;
                    Second_Level = 0;
                    break;

                case "Bard":
                    Health = 21;
                    Initiative = 3;
                    AC = 17;
                    Pro = 2;
                    Str = 1;
                    Dex = 2;
                    Con = 1;
                    Int = -1;
                    Wis = 0;
                    Cha = 3;
                    First_Level = 4;
                    Second_Level = 2;
                    First_Spells.add("Charm Person");
                    First_Spells.add("Detect Magic");
                    First_Spells.add("Cure Wounds");
                    Second_Spells.add("Enhance Ability");
                    Second_Spells.add("Invisibility");
                    Second_Spells.add("Phantasmal Force");
                    break;

                case "Paladin":
                    Health = 25;
                    Initiative = -1;
                    AC = 18;
                    Pro = 2;
                    Str = 2;
                    Dex = -1;
                    Con = 1;
                    Int = 0;
                    Wis = 1;
                    Cha = 3;
                    First_Level = 3;
                    Second_Level = 0;
                    First_Spells.add("Cure Wounds");
                    First_Spells.add("Wrathful Smite");
                    First_Spells.add("Divine Favor");
                    First_Spells.add("Searing Smite");
                    First_Spells.add("Command");
                    break;

                case "Wizard":
                    Health = 20;
                    Initiative = 1;
                    AC = 11;
                    Pro = 2;
                    Str = 0;
                    Dex = 1;
                    Con = 2;
                    Int = 3;
                    Wis = 1;
                    Cha = 0;
                    First_Level = 4;
                    Second_Level = 2;
                    First_Spells.add("Mage Armor");
                    First_Spells.add("Charm Person");
                    First_Spells.add("Sleep");
                    First_Spells.add("Detect Magic");
                    Second_Spells.add("Suggestion");
                    Second_Spells.add("Invisibility");
                    break;

                default:
                    break;
            }
        }

        //Output Stats for the user
        Build_text.setText("Build Created: (Level 3) "+ set.getString("Race", "NA")
                + ", " + set.getString("Class","NA"));

        Stat_text.setText("Max Hp: " + Health + "\nInitiative: " + Initiative +
                "\nArmor Class: " + AC + "\nProficiency: " + Pro + "\nStrength: " + Str
                + "\nDexterity: " + Dex +"\nConstitution: " + Con + "\nIntelligence: " +
                Int + "\nWisdom: " + Wis + "\nCharisma: " + Cha + "\n1st Level(" + First_Level
                +" slots): " + First_Spells + "\n2nd Level(" + Second_Level+" slots): "
                + Second_Spells);

        if (!set.getBoolean("Press", false))
        {
            intent.putExtra("CharHealth", Health);
            intent.putExtra("CharInitiative", Initiative);
            intent.putExtra("CharAC", AC);
            intent.putExtra("CharPro", Pro);
            intent.putExtra("CharStr", Str);
            intent.putExtra("CharDex", Dex);
            intent.putExtra("CharCon", Con);
            intent.putExtra("CharInt", Int);
            intent.putExtra("CharWis", Wis);
            intent.putExtra("CharCha", Cha);
            intent.putExtra("CharFirst", First_Level);
            intent.putExtra("CharSecond", Second_Level);
            intent.putStringArrayListExtra("CharFirst_Spell", First_Spells);
            intent.putStringArrayListExtra("CharSecond_Spell", Second_Spells);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

        setResult(Activity.RESULT_OK, intent);

    }
}