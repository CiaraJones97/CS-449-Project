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
import java.util.Random;

public class Game extends AppCompatActivity {

    static SharedPreferences set;
    static SharedPreferences.Editor edit;
    Random dice = new Random();
    int x = 1;
    int numPot = 3;
    int wealth = 30;
    int player_roll;
    int enemy_roll;
    String currentline;
    ArrayList<String> First_Spell;
    ArrayList<String> Second_Spell;
    ArrayList<String> Inventory_List = new ArrayList<>();

    public Button Next;
    public Button Fight;
    public Button Flee;
    public Button FirstLevel;
    public Button SecondLevel;
    public Button Inventory;
    public Button Attack_1;
    public Button Attack_2;
    public Button Attack_3;
    public Button Attack_4;
    public Button Attack_5;
    public Button HealthPot;

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
        edit.putString("Turn","X");
        edit.commit();

        //Add initial items to the inventory
        Inventory_List.add("Health Potions: " + numPot);
        Inventory_List.add("Gold Pieces: " + wealth);

        //Define all the needed requirements
        final ProgressBar HealthBar = findViewById(R.id.progressBar);
        final TextView Instruct_text = findViewById(R.id.instruct_text);
        final TextView Invent_text = findViewById(R.id.invent_text);
        final TextView Health_num = findViewById(R.id.health_num);
        Next = findViewById(R.id.next_button);
        Fight = findViewById(R.id.fight_button);
        Flee = findViewById(R.id.flee_button);
        FirstLevel = findViewById(R.id.first_spell_button);
        SecondLevel = findViewById(R.id.second_spell_button);
        Inventory = findViewById(R.id.inventory_button);
        Attack_1 = findViewById(R.id.attack_button_1);
        Attack_2 = findViewById(R.id.attack_button_2);
        Attack_3 = findViewById(R.id.attack_button_3);
        Attack_4 = findViewById(R.id.attack_button_4);
        Attack_5 = findViewById(R.id.attack_button_5);
        HealthPot = findViewById(R.id.health_potion_button);

        //Define the needed classes
        final Enemies enemy = new Enemies();
        final Util util = new Util(Next,Fight,Flee,FirstLevel,
                SecondLevel,Inventory,Attack_1,Attack_2,Attack_3,Attack_4,Attack_5,HealthPot,Invent_text,Inventory_List);

        //Remove the FIGHT, FLEE, INVENTORY, FIRST LEVEL, SECOND LEVEL, and ATTACK buttons
        util.Combat();

        //Initial Message at the start of the game
        Instruct_text.setText("WELCOME TO THE GAME!!\n You are a LEVEL 3 " +
                set.getString("Race", "NA") + ", " +
                set.getString("Class", "NA") +
                ".\nFor this game you have been given a pre-made character sheet" +
                ".\nAccess your STATS PAGE anytime from the upper menu" +
                "\nWatch your HEALTH BAR below" +
                "\nHit the NEXT Button to continue");

        //Set the initial progress bar
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
                        player_roll = (dice.nextInt(20)+1) + set.getInt("Initiative",-10);
                        enemy_roll = enemy.getInitiative();
                        currentline = "Your story starts in the small village." +
                                "\nYou are standing in the center of town when you hear a noise." +
                                "\nYou look in the direction and see a " + enemy.getEnemyName()+
                                " attacking a nearby store." + "\nWhat do you want to do? " +
                                "\nYOUR INITIATIVE: " + player_roll + " ENEMY INITIATIVE: " + enemy_roll;
                        Instruct_text.setText(currentline);

                        edit.putBoolean("Continue", false);

                        //Set the turn
                        if (player_roll >= enemy_roll) {
                            edit.putString("Turn","P");
                        }
                        else{
                            edit.putString("Turn","E");
                        }
                        edit.commit();
                        util.Combat();
                        break;

                    case 3:
                        Instruct_text.setText("After the combat has finished");
                        break;

                }

            }
        });

        //Combat during the enemies turn
        if (set.getString("Turn", "X").equals("E")){
            int temp = enemy.getEnemyAttacks().size();
            int att_roll = enemy.rollATT();
            int dmg_roll;
            String weapon = enemy.getEnemyAttacks().get(dice.nextInt(temp));

            Instruct_text.append("\nThe enemy attacks with: " + weapon +
                    "\nATTACK ROLL: " + att_roll);

            if (att_roll > set.getInt("AC",-10)) {
                dmg_roll = enemy.rollDMG(weapon);
                Instruct_text.append("\nHIT! You take " + dmg_roll +" points of damage");
                HealthBar.setProgress(set.getInt("Health",-10)-dmg_roll);
                Health_num.setText("Health: " + (set.getInt("Health",-10)-dmg_roll));

                edit.putInt("Health",(set.getInt("Health",-10)-dmg_roll));
                edit.putString("Turn","P");
                edit.commit();
            }
            else{
                Instruct_text.append("\nThe enemy misses");
            }

        }

        //Player chooses to fight
        Fight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Attack_5.setText("Melee");
                util.Fight();
            }
        });

        //Show all available first level spells
        FirstLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.FirstSecondL();
                Attack_1.setText(First_Spell.get(0));
                Attack_2.setText(First_Spell.get(1));
                Attack_3.setText(First_Spell.get(2));
                Attack_4.setText(First_Spell.get(3));
            }
        });

        //Show all available second level spells
        SecondLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.FirstSecondL();
                Attack_1.setText(Second_Spell.get(0));
                Attack_2.setText(Second_Spell.get(1));
                Attack_3.setText(Second_Spell.get(2));
                Attack_4.setText(Second_Spell.get(3));

            }
        });

        //If the user decides to flee from combat
        Flee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Instruct_text.setText(currentline);
                Instruct_text.append("\nYou decide to flee from combat. The enemy gets an attack with " +
                        "advantage");
                int temp = enemy.getEnemyAttacks().size();
                int att_roll=0;
                int dmg_roll;
                int max = 0;
                String weapon = enemy.getEnemyAttacks().get(dice.nextInt(temp));

                for (int i =1; i<=2; i++){
                    att_roll = enemy.rollATT();
                    if (att_roll> max)
                    {
                        max = att_roll;
                    }
                }

                if (att_roll > set.getInt("AC",-10)) {
                    dmg_roll = enemy.rollDMG(weapon);
                    Instruct_text.append("\nHIT! You take " + dmg_roll +" points of damage");
                    HealthBar.setProgress(set.getInt("Health",-10)-dmg_roll);
                    Health_num.setText("Health: " + (set.getInt("Health",-10)-dmg_roll));

                    edit.putInt("Health",(set.getInt("Health",-10)-dmg_roll));
                    edit.putString("Turn","P");
                }
                else{
                    Instruct_text.append("\nThe enemy misses");
                }

                edit.putBoolean("Continue", true);
                edit.commit();
                util.Combat();
            }
        });

        //Show the inventory to the user
        Inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.Invent();
                Invent_text.setText(Inventory_List.get(0) + "\n" + Inventory_List.get(1));
            }
        });

        //If the user decides to use a HEALTH POTION
        HealthPot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HealthBar.getMax()==set.getInt("Health",-10) || numPot ==0)
                {
                    Invent_text.setText(Inventory_List.get(0) + "\n" + Inventory_List.get(1)+
                            "\nYour HEALTH is at the maximum or you are out of HEALTH POTIONS");
                }
                else
                {
                    HealthBar.setProgress(set.getInt("Health", -10) + 5);
                    Health_num.setText("Health: " + (set.getInt("Health", -10) + 5));
                    numPot--;
                    Inventory_List.set(0, "Health Potions: " + numPot);

                    edit.putInt("Health", (set.getInt("Health", -10) + 5));
                    edit.commit();
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