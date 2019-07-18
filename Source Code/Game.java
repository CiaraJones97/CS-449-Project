package com.example.minidnd;

import android.annotation.SuppressLint;
import android.app.Activity;
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
    int enemy_save;
    int firstmax;
    int secondmax;
    int acmax;
    boolean enemy_charm = false;
    boolean enemy_dis = false;
    boolean player_adv = false;
    boolean player_ac_chg = false;
    String currentline;
    String spell_option;
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
    public Button ConfirmTurn;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Set the intent to go back to the main file
        Intent intent = new Intent(Game.this, MainActivity.class);

        set = this.getPreferences(MODE_PRIVATE);
        edit = set.edit();
        edit.putInt("Page",x);
        edit.apply();

        //Get the data from MainActivity.java at the start of the game
        edit.putString("Class", getIntent().getStringExtra("CharClass"));
        edit.putString("Race", getIntent().getStringExtra("CharRace"));
        edit.putInt("Health", getIntent().getIntExtra("CharHealth", -10));
        edit.putInt("Initiative", getIntent().getIntExtra("CharInitiative", -10));
        edit.putInt("Pro", getIntent().getIntExtra("CharPro", -10));
        edit.putInt("Str", getIntent().getIntExtra("CharStr", -10));
        edit.putInt("Dex", getIntent().getIntExtra("CharDex", -10));
        edit.putInt("Con", getIntent().getIntExtra("CharCon", -10));
        edit.putInt("Int", getIntent().getIntExtra("CharInt", -10));
        edit.putInt("Wis", getIntent().getIntExtra("CharWis", -10));
        edit.putInt("Cha", getIntent().getIntExtra("CharCha", -10));
        firstmax = getIntent().getIntExtra("CharFirst", -10);
        secondmax = getIntent().getIntExtra("CharSecond", -10);
        acmax = getIntent().getIntExtra("CharAC", -10);
        First_Spell = getIntent().getStringArrayListExtra("CharFirst_Spell");
        Second_Spell = getIntent().getStringArrayListExtra("CharSecond_Spell");

        edit.putInt("First", firstmax);
        edit.putInt("Second", secondmax);
        edit.putInt("AC", acmax);
        edit.putBoolean("Continue", true);
        edit.putBoolean("AC_Chg", false);
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
        ConfirmTurn = findViewById(R.id.confirm_end_button);

        //Define the needed classes
        final Enemies enemy = new Enemies();
        final Util util = new Util(Next,Fight,Flee,FirstLevel,
                SecondLevel,Inventory,Attack_1,Attack_2,Attack_3,Attack_4,Attack_5,
                HealthPot,ConfirmTurn,Invent_text,Inventory_List);
        final Player player = new Player();

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

        //The NEXT button that will take the user through the plot
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

                        //Set the initial turn
                        if (player_roll < enemy_roll) {
                            enemy.enemyTurn(currentline,enemy_charm,enemy_dis,
                                    Instruct_text,Health_num,HealthBar);
                        }

                        edit.commit();
                        util.Combat();
                        break;

                    case 3:
                        Instruct_text.setText("After the combat has finished you go to the " +
                                "local inn to breathe and get some answers");
                        break;

                }

            }
        });

        //If the player dies
        if(set.getInt("Health",-10)<=0){
            Instruct_text.setText("You have died. Your journey ends here");
            Instruct_text.append("GAME OVER");

            setResult(Activity.RESULT_OK, intent);
            finish();

        }

        //So the player can confirm they are finished with their turn
        ConfirmTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.EndTurn();
                enemy.enemyTurn(currentline,enemy_charm,enemy_dis,
                        Instruct_text,Health_num,HealthBar);
                enemy_charm = false;
                util.Combat();
            }

        });

        //Player chooses to fight
        Fight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Attack_5.setText("Melee");
                util.Fight(firstmax,secondmax);
            }
        });

        //Show all available first level spells
        FirstLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spell_option = "first";
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
                spell_option = "second";
                util.FirstSecondL();
                Attack_1.setText(Second_Spell.get(0));
                Attack_2.setText(Second_Spell.get(1));
                Attack_3.setText(Second_Spell.get(2));
                Attack_4.setText(Second_Spell.get(3));

            }
        });

        //First ATTACK Button
        //TODO: BETTER FORMAT ADDED TO PLAYER, FIX THIS SECTION OF CODE
        Attack_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Instruct_text.setText(currentline);

                //If player had a temporary higher AC
                if(player_ac_chg){
                    Instruct_text.append("\nYour AC has returned to: " + acmax);
                    edit.putInt("AC", acmax);
                    edit.commit();
                }

                //If the button is "NA"
                if(First_Spell.get(0).equals("NA") && spell_option.equals("first")){
                    Instruct_text.append("\nPlease pick an available option");
                }

                if(Second_Spell.get(0).equals("NA") && spell_option.equals("second")){
                    Instruct_text.append("\nPlease pick an available option");
                }

                //If the attack is a FIRST LEVEL spell
                else if(spell_option.equals("first")){

                    //If the user is out of FIRST LEVEL spell slots
                    if(set.getInt("First",-10)==0){
                        Instruct_text.append("\nYou are out of FIRST LEVEL spell slots");
                    }

                    else{
                        int value = player.Attacking_First_1();
                        edit.putInt("First", (set.getInt("First",-10)-1));

                        switch (First_Spell.get(0)) {
                            case "Cure Wounds":
                                //If the player's health is at the maximum
                                if (HealthBar.getMax() == set.getInt("Health", -10)) {
                                    Instruct_text.append("\nYour HEALTH is at its maximum");
                                }
                                else {
                                    Instruct_text.append("You cast CURE WOUNDS\n You Roll: " + value);

                                    //If the added health is more than the players max
                                    if (set.getInt("Health", -10) + value > HealthBar.getMax()) {
                                        HealthBar.setProgress(HealthBar.getMax());
                                        Health_num.setText("Health: " + HealthBar.getMax());
                                        edit.putInt("Health", HealthBar.getMax());
                                        edit.commit();
                                    }

                                    //Add the rolled value to the player's health
                                    else {
                                        HealthBar.setProgress(set.getInt("Health", -10) + value);
                                        Health_num.setText("Health: " + (set.getInt("Health", -10) + value));
                                        edit.putInt("Health", (set.getInt("Health", -10) + value));
                                        edit.commit();
                                    }

                                }
                                break;

                            case "Bless":
                                Instruct_text.append("\nYou cast BLESS\nYou Roll: " + value);
                                edit.putInt("Bless", value);
                                edit.commit();
                                break;

                            case "Mage Armor":
                                Instruct_text.append("\nYou cast MAGE ARMOR");
                                Instruct_text.append("\nYour AC is now: " + (value + set.getInt("Dex", -10)));
                                edit.putInt("AC", value + set.getInt("Dex", -10));
                                edit.commit();
                                player_ac_chg = true;
                                break;

                            case "Charm Person": {
                                int enemy_save = (dice.nextInt(20) + 1) + enemy.getWis();
                                Instruct_text.append("\nYou cast CHARM PERSON. DC 13");
                                Instruct_text.append("\nEnemy Rolled: " + enemy_save);
                                if (enemy_save > 13) {
                                    Instruct_text.append("\nYou failed to charm");
                                } else {
                                    Instruct_text.append("\nSuccess");
                                    enemy_charm = true;
                                }
                                break;
                            }

                            case "Compelled Duel": {
                                int enemy_save = (dice.nextInt(20) + 1) + enemy.getWis();
                                Instruct_text.append("\nYou cast COMPELLED DUEL. DC 13");
                                Instruct_text.append("\nEnemy Rolled: " + enemy_save);
                                if (enemy_save > 13) {
                                    Instruct_text.append("\nYou failed compel");
                                } else {
                                    Instruct_text.append("\nSuccess");
                                    enemy_dis = true;
                                }
                                break;
                            }
                        }

                        edit.commit();
                        util.EndTurn();
                    }
                }

                //If the attack is a SECOND LEVEL spell
                else if(spell_option.equals("second")){

                    //If the user is out of SECOND LEVEL spell slots
                    if(set.getInt("Second",-10)==0){
                        Instruct_text.append("\nYou are out of SECOND LEVEL spell slots");
                    }

                    else{
                        edit.putInt("Second", (set.getInt("Second",-10)-1));

                        switch (Second_Spell.get(0)){
                            case "Invisibility":
                                Instruct_text.append("\nYou cast INVISIBILITY");
                                enemy_dis = true;
                                break;

                            case "Suggestion":
                                enemy_save = (dice.nextInt(20) + 1) + enemy.getWis();
                                Instruct_text.append("\nYou cast SUGGESTION. DC 13");
                                Instruct_text.append("\nEnemy Rolled: " + enemy_save);
                                if (enemy_save > 13) {
                                    Instruct_text.append("\nYou failed to suggest");
                                }
                                else {
                                    Instruct_text.append("\nSuccess");
                                    enemy_dis = true;
                                }
                                break;

                            case "Enhance Ability":
                                Instruct_text.append("\nYou cast ENHANCE ABILITY");
                                player_adv = true;
                                break;

                            case "Hold Person":
                                enemy_save = (dice.nextInt(20) + 1) + enemy.getWis();
                                Instruct_text.append("\nYou cast HOLD PERSON. DC 13");
                                Instruct_text.append("\nEnemy Rolled: " + enemy_save);
                                if (enemy_save > 13) {
                                    Instruct_text.append("\nYou failed to hold person");
                                }
                                else {
                                    Instruct_text.append("\nSuccess");
                                    enemy_charm = true;
                                }
                                break;
                        }

                        edit.commit();
                        util.EndTurn();
                    }

                }


            }
        });

        //Second ATTACK Button
        Attack_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Instruct_text.setText(currentline);

                //If player had a temporary higher AC
                if(player_ac_chg){
                    Instruct_text.append("\nYour AC has returned to: " + acmax);
                    edit.putInt("AC", acmax);
                    edit.commit();
                }

                //If the button is "NA"
                if(First_Spell.get(1).equals("NA") && spell_option.equals("first")){
                    Instruct_text.append("\nPlease pick an available option");
                }

                if(Second_Spell.get(1).equals("NA") && spell_option.equals("second")){
                    Instruct_text.append("\nPlease pick an available option");
                }


                //If the attack is a FIRST LEVEL spell
                else if(spell_option.equals("first")){

                    //If the user is out of FIRST LEVEL spell slots
                    if(set.getInt("First",-10)==0){
                        Instruct_text.append("\nYou are out of FIRST LEVEL spell slots");
                    }

                    else{
                        int value = player.Attacking_First_2();
                        int roll;
                        int enemy_save;
                        edit.putInt("First", (set.getInt("First",-10)-1));

                        switch (First_Spell.get(1)) {
                            case "Charm Person":
                                enemy_save = (dice.nextInt(20) + 1) + enemy.getWis();
                                Instruct_text.append("\nYou cast CHARM PERSON. DC 13");
                                Instruct_text.append("\nEnemy Rolled: " + enemy_save);
                                if (enemy_save > 13) {
                                    Instruct_text.append("\nYou failed to charm");
                                } else {
                                    Instruct_text.append("\nSuccess");
                                    enemy_charm = true;
                                }
                                break;

                            case "Searing Smite":
                                roll = (dice.nextInt(20) + 1);
                                Instruct_text.append("\nYou cast SEARING SMITE");

                                //Use BLESS
                                if(set.getInt("Bless",0)!=0){
                                    Instruct_text.append("\nYour BLESS of "+set.getInt("Bless",0)+" is added");
                                    Instruct_text.append("\nYou Roll: "+roll+" plus BLESS: " +(roll+set.getInt("Bless",0)));

                                    if((roll+set.getInt("Bless",0) > enemy.getAC())){
                                        Instruct_text.append("\nYou Hit. Enemy takes " + value+ " points of damage");
                                        enemy.setHealth(enemy.getHealth()-value);

                                        enemy_save = (dice.nextInt(20) + 1) + enemy.getCon();
                                        if(enemy_save < 13){
                                            Instruct_text.append("\nEnemy failed the save");
                                            Instruct_text.append("\nEnemy takes another " + value+ " points of damage");
                                        }
                                    }

                                    edit.putInt("Bless",0);
                                    edit.commit();
                                }

                                else{
                                    Instruct_text.append("\nYou Roll: "+roll);
                                    if((roll > enemy.getAC())){
                                        Instruct_text.append("\nYou Hit. Enemy takes " + value+ " points of damage");
                                        enemy.setHealth(enemy.getHealth()-value);

                                        enemy_save = (dice.nextInt(20) + 1) + enemy.getCon();
                                        if(enemy_save < 13){
                                            Instruct_text.append("\nEnemy failed the save");
                                            Instruct_text.append("\nEnemy takes another " + value+ " points of damage");
                                        }
                                    }
                                }
                                break;

                            case "Detect Magic":
                                Instruct_text.append("\nYou cast DETECT MAGIC");
                                Instruct_text.append("\nEnemy attacks: " + enemy.getEnemyAttacks());
                                break;

                            case "Wrathful Smite":
                                roll = (dice.nextInt(20) + 1);
                                Instruct_text.append("\nYou cast WRATHFUL SMITE");

                                //Use BLESS
                                if(set.getInt("Bless",0)!=0){
                                    Instruct_text.append("\nYour BLESS of "+set.getInt("Bless",0)+" is added");
                                    Instruct_text.append("\nYou Roll: "+roll+" plus BLESS: " +(roll+set.getInt("Bless",0)));

                                    if((roll+set.getInt("Bless",0) > enemy.getAC())){
                                        Instruct_text.append("\nYou Hit. Enemy takes " + value+ " points of damage");
                                        enemy.setHealth(enemy.getHealth()-value);

                                        enemy_save = (dice.nextInt(20) + 1) + enemy.getWis();
                                        if(enemy_save < 13){
                                            Instruct_text.append("\nEnemy failed the save and is frightened");
                                            enemy_charm=true;
                                        }
                                    }

                                    edit.putInt("Bless",0);
                                    edit.commit();
                                }
                                else{
                                    Instruct_text.append("\nYou Roll: "+roll);
                                    if((roll > enemy.getAC())){
                                        Instruct_text.append("\nYou Hit. Enemy takes " + value+ " points of damage");
                                        enemy.setHealth(enemy.getHealth()-value);

                                        enemy_save = (dice.nextInt(20) + 1) + enemy.getWis();
                                        if(enemy_save < 13){
                                            Instruct_text.append("\nEnemy failed the save and is frightened");
                                            enemy_charm=true;
                                        }
                                    }
                                }
                                break;

                            case "Thunderous Smite":
                                roll = (dice.nextInt(20) + 1);
                                Instruct_text.append("\nYou cast THUNDEROUS SMITE");

                                //Use BLESS
                                if(set.getInt("Bless",0)!=0){
                                    Instruct_text.append("\nYour BLESS of "+set.getInt("Bless",0)+" is added");
                                    Instruct_text.append("\nYou Roll: "+roll+" plus BLESS: " +(roll+set.getInt("Bless",0)));

                                    if((roll+set.getInt("Bless",0) > enemy.getAC())){
                                        Instruct_text.append("\nYou Hit. Enemy takes " + value+ " points of damage");
                                        enemy.setHealth(enemy.getHealth()-value);

                                        enemy_save = (dice.nextInt(20) + 1) + enemy.getStr();
                                        if(enemy_save < 13){
                                            Instruct_text.append("\nEnemy failed the save and is knocked prone");
                                            enemy_dis=true;
                                        }
                                    }

                                    edit.putInt("Bless",0);
                                    edit.commit();
                                }
                                else{
                                    Instruct_text.append("\nYou Roll: "+roll);
                                    if((roll > enemy.getAC())){
                                        Instruct_text.append("\nYou Hit. Enemy takes " + value+ " points of damage");
                                        enemy.setHealth(enemy.getHealth()-value);

                                        enemy_save = (dice.nextInt(20) + 1) + enemy.getStr();
                                        if(enemy_save < 13){
                                            Instruct_text.append("\nEnemy failed the save and is knocked prone");
                                            enemy_dis=true;
                                        }
                                    }
                                }
                                break;
                        }

                        edit.commit();
                        util.EndTurn();
                    }
                }

                //If the attack is a SECOND LEVEL spell
                else if(spell_option.equals("second")){

                    //If the user is out of SECOND LEVEL spell slots
                    if(set.getInt("Second",-10)==0){
                        Instruct_text.append("\nYou are out of SECOND LEVEL spell slots");
                    }

                    else{
                        edit.putInt("Second", (set.getInt("Second",-10)-1));

                        switch (Second_Spell.get(1)){
                            case "Invisibility":
                                Instruct_text.append("\nYou cast INVISIBILITY");
                                enemy_dis = true;
                                break;


                            case "Enhance Ability":
                                Instruct_text.append("\nYou cast ENHANCE ABILITY");
                                player_adv = true;
                                break;
                        }

                        edit.commit();
                        util.EndTurn();
                    }

                }

                if(enemy.getHealth()<=0){
                    Instruct_text.append("The Enemy is dead. You are victorious");
                    edit.putBoolean("Continue", true);
                    util.Combat();
                }


            }

        });

        //Third ATTACK Button
        Attack_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Instruct_text.setText(currentline);

                //If player had a temporary higher AC
                if(player_ac_chg){
                    Instruct_text.append("\nYour AC has returned to: " + acmax);
                    edit.putInt("AC", acmax);
                    edit.commit();
                }

                //If the button is "NA"
                if(First_Spell.get(2).equals("NA") && spell_option.equals("first")){
                    Instruct_text.append("\nPlease pick an available option");
                }

                if(Second_Spell.get(2).equals("NA") && spell_option.equals("second")){
                    Instruct_text.append("\nPlease pick an available option");
                }


                //If the attack is a FIRST LEVEL spell
                else if(spell_option.equals("first")){

                    //If the user is out of FIRST LEVEL spell slots
                    if(set.getInt("First",-10)==0){
                        Instruct_text.append("\nYou are out of FIRST LEVEL spell slots");
                    }

                    else{
                        int value = player.Attacking_First_3();
                        int roll;
                        int enemy_save;
                        edit.putInt("First", (set.getInt("First",-10)-1));

                        switch (First_Spell.get(2)) {
                            case "Detect Magic":
                                Instruct_text.append("\nYou cast DETECT MAGIC");
                                Instruct_text.append("\nEnemy attacks: " + enemy.getEnemyAttacks());
                                break;

                            case "Compelled Duel":
                                enemy_save = (dice.nextInt(20) + 1) + enemy.getWis();
                                Instruct_text.append("\nYou cast COMPELLED DUEL. DC 13");
                                Instruct_text.append("\nEnemy Rolled: " + enemy_save);
                                if (enemy_save > 13) {
                                    Instruct_text.append("\nYou failed compel");
                                } else {
                                    Instruct_text.append("\nSuccess");
                                    enemy_dis = true;
                                }
                                break;

                            case "Sleep":
                                roll = (dice.nextInt(20) + 1);
                                Instruct_text.append("\nYou cast SLEEP\nYou Roll: " + roll);

                                //Use BLESS
                                if(set.getInt("Bless",0)!=0){
                                    Instruct_text.append("\nYour BLESS of "+set.getInt("Bless",0)+" is added");
                                    Instruct_text.append("\nYou Roll: "+roll+" plus BLESS: " +(roll+set.getInt("Bless",0)));

                                    if((roll+set.getInt("Bless",0) > enemy.getAC())){
                                        Instruct_text.append("\nYou Hit. Enemy takes " + value+ " points of damage");
                                        enemy.setHealth(enemy.getHealth()-value);
                                    }
                                    else{
                                        Instruct_text.append("\nYou failed to put the enemy to sleep");
                                    }

                                    edit.putInt("Bless",0);
                                    edit.commit();
                                }
                                else{
                                    Instruct_text.append("\nYou Roll: "+roll);
                                    if((roll > enemy.getAC())){
                                        Instruct_text.append("\nYou Hit. Enemy takes " + value+ " points of damage");
                                        enemy.setHealth(enemy.getHealth()-value);

                                    }
                                    else{
                                        Instruct_text.append("\nYou failed to put the enemy to sleep");
                                    }
                                }
                                break;

                            case "Cure Wounds":
                                //If the player's health is at the maximum
                                if (HealthBar.getMax() == set.getInt("Health", -10)) {
                                    Instruct_text.append("\nYour HEALTH is at its maximum");
                                }
                                else {
                                    Instruct_text.append("You cast CURE WOUNDS\n You Roll: " + value);

                                    //If the added health is more than the players max
                                    if (set.getInt("Health", -10) + value > HealthBar.getMax()) {
                                        HealthBar.setProgress(HealthBar.getMax());
                                        Health_num.setText("Health: " + HealthBar.getMax());
                                        edit.putInt("Health", HealthBar.getMax());
                                        edit.commit();
                                    }

                                    //Add the rolled value to the player's health
                                    else {
                                        HealthBar.setProgress(set.getInt("Health", -10) + value);
                                        Health_num.setText("Health: " + (set.getInt("Health", -10) + value));
                                        edit.putInt("Health", (set.getInt("Health", -10) + value));
                                        edit.commit();
                                    }

                                }
                                break;

                            case "Bless":
                                Instruct_text.append("\nYou cast BLESS\nYou Roll: " + value);
                                edit.putInt("Bless", value);
                                edit.commit();
                                break;

                            case "Divine Favor":
                                Instruct_text.append("\nYou cast DIVINE FAVOR\nYou Roll: " + value);
                                edit.putInt("Divine", value);
                                edit.commit();
                                break;
                        }

                        edit.commit();
                        util.EndTurn();
                    }
                }

                //If the attack is a SECOND LEVEL spell
                else if(spell_option.equals("second")){

                    //If the user is out of SECOND LEVEL spell slots
                    if(set.getInt("Second",-10)==0){
                        Instruct_text.append("\nYou are out of SECOND LEVEL spell slots");
                    }

                    else{
                        int value = player.Attacking_Second_3();
                        int roll;
                        int enemy_save;
                        edit.putInt("Second", (set.getInt("Second",-10)-1));

                        switch (Second_Spell.get(2)){
                            case "Hold Person":
                                enemy_save = (dice.nextInt(20) + 1) + enemy.getWis();
                                Instruct_text.append("\nYou cast HOLD PERSON. DC 13");
                                Instruct_text.append("\nEnemy Rolled: " + enemy_save);
                                if (enemy_save > 13) {
                                    Instruct_text.append("\nYou failed to hold person");
                                }
                                else {
                                    Instruct_text.append("\nSuccess");
                                    enemy_charm = true;
                                }
                                break;

                            case "Bless":
                                Instruct_text.append("\nYou cast BLESS\nYou Roll: " + value);
                                edit.putInt("Bless", value);
                                edit.commit();
                                break;

                            case "Sleep":
                                roll = (dice.nextInt(20) + 1);
                                Instruct_text.append("\nYou cast SLEEP\nYou Roll: " + roll);

                                //Use BLESS
                                if(set.getInt("Bless",0)!=0){
                                    Instruct_text.append("\nYour BLESS of "+set.getInt("Bless",0)+" is added");
                                    Instruct_text.append("\nYou Roll: "+roll+" plus BLESS: " +(roll+set.getInt("Bless",0)));

                                    if((roll+set.getInt("Bless",0) > enemy.getAC())){
                                        Instruct_text.append("\nYou Hit. Enemy takes " + value+ " points of damage");
                                        enemy.setHealth(enemy.getHealth()-value);
                                    }
                                    else{
                                        Instruct_text.append("\nYou failed to put the enemy to sleep");
                                    }

                                    edit.putInt("Bless",0);
                                    edit.commit();
                                }
                                else{
                                    Instruct_text.append("\nYou Roll: "+roll);
                                    if((roll > enemy.getAC())){
                                        Instruct_text.append("\nYou Hit. Enemy takes " + value+ " points of damage");
                                        enemy.setHealth(enemy.getHealth()-value);

                                    }
                                    else{
                                        Instruct_text.append("\nYou failed to put the enemy to sleep");
                                    }
                                }

                            case "Phantasmal Force":
                                roll = (dice.nextInt(20) + 1);
                                enemy_save = (dice.nextInt(20) + 1) + enemy.getInt();
                                Instruct_text.append("\nYou cast PHANTASMAL FORCE DC 13");
                                Instruct_text.append("\nEnemy Rolled: " + enemy_save);
                                if (enemy_save > 13) {
                                    Instruct_text.append("\nEnemy sees through the illusion");
                                }
                                else {
                                    if((roll > enemy.getAC())){
                                        Instruct_text.append("\nYou Hit. Enemy takes " + value+ " points of damage");
                                        enemy.setHealth(enemy.getHealth()-value);

                                    }
                                    else{
                                        Instruct_text.append("\nYou failed to scare the enemy");
                                    }
                                }

                                break;

                            case "Divine Favor":
                                Instruct_text.append("\nYou cast DIVINE FAVOR\nYou Roll: " + value);
                                edit.putInt("Divine", value);
                                edit.commit();
                                break;
                        }

                        edit.commit();
                        util.EndTurn();
                    }

                }

                if(enemy.getHealth()<=0){
                    Instruct_text.append("The Enemy is dead. You are victorious");
                    edit.putBoolean("Continue", false);
                    util.Combat();
                }


            }
        });

        //The Melee ATTACK Button
        Attack_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Instruct_text.setText(currentline);
                Instruct_text.append("\nYou Attack with Melee");

                //If player had a temporary higher AC
                if(player_ac_chg){
                    Instruct_text.append("\nYour AC has returned to: " + acmax);
                    edit.putInt("AC", acmax);
                    edit.commit();
                }

                int value = player.Attacking_Melee();
                int roll = (dice.nextInt(20) + 1);
                int enemy_save;

                //Use BLESS
                if(set.getInt("Bless",0)!=0){
                    Instruct_text.append("\nYour BLESS of "+set.getInt("Bless",0)+" is added");
                    Instruct_text.append("\nYou Roll: "+roll+" plus BLESS: " +(roll+set.getInt("Bless",0)));

                    if((roll+set.getInt("Bless",0) > enemy.getAC())){
                        Instruct_text.append("\nYou Hit. Enemy takes " + value+ " points of damage");
                        enemy.setHealth(enemy.getHealth()-value);

                    }
                    edit.putInt("Bless",0);
                    edit.commit();
                }

                else{
                    Instruct_text.append("\nYou Roll: "+roll);
                    if((roll > enemy.getAC())){
                        Instruct_text.append("\nYou Hit. Enemy takes " + value+ " points of damage");
                        enemy.setHealth(enemy.getHealth()-value);

                    }
                    else{
                        Instruct_text.append("\nYou miss");
                    }
                }

                if(enemy.getHealth()<=0){
                    Instruct_text.append("The Enemy is dead. You are victorious");
                    edit.putBoolean("Continue", false);
                    util.Combat();
                }

                edit.commit();
                util.EndTurn();
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

                //Roll twice and take the better roll
                for (int i =1; i<=2; i++){
                    att_roll = enemy.rollATT();
                    if (att_roll> max)
                    {
                        max = att_roll;
                    }
                }
                att_roll = max;

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
                //If the player's HEALTH is at the maximum
                if (HealthBar.getMax()==set.getInt("Health",-10) || numPot ==0)
                {
                    Invent_text.setText(Inventory_List.get(0) + "\n" + Inventory_List.get(1)+
                            "\nYour HEALTH is at the maximum or you are out of HEALTH POTIONS");
                }
                else
                {
                    //If the added health is more than the players max
                    if(set.getInt("Health",-10)+5 >HealthBar.getMax()){
                        HealthBar.setProgress(HealthBar.getMax());
                        Health_num.setText("Health: " + HealthBar.getMax());
                        edit.putInt("Health", HealthBar.getMax());
                        edit.commit();
                    }

                    //Add the 5 HP to the player's HEALTH
                    else{
                        HealthBar.setProgress(set.getInt("Health", -10) + 5);
                        Health_num.setText("Health: " + (set.getInt("Health", -10) + 5));
                        numPot--;
                        Inventory_List.set(0, "Health Potions: " + numPot);

                        edit.putInt("Health", (set.getInt("Health", -10) + 5));
                        edit.commit();
                    }

                    Invent_text.setText(Inventory_List.get(0) + "\n" + Inventory_List.get(1));

                }
            }
        });

        setResult(Activity.RESULT_OK, intent);

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