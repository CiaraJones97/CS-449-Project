package com.example.minidnd;

import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class Player extends Game {
    Random dice;
    int value;

    public Player(){
        dice = new Random();
    }

    //TODO: ADD MORE
    public void Attacking_First_1(TextView instruct, ProgressBar healthbar, TextView healthnum, String line,
                                 int acmax, int enemy_wis) {
        TextView instruct_txt = instruct;
        instruct_txt.setText(line);

        //If the user is out of FIRST LEVEL spell slots
        if(set.getInt("First",-10)==0){
            instruct_txt.append("\nYou are out of FIRST LEVEL spell slots");
        }

        //If player had a temporary higher AC
        if(set.getBoolean("AC_Chg", false)){
            instruct_txt.append("\nYour AC has returned to: " + acmax);
            edit.putInt("AC", acmax);
            edit.commit();
        }


        //Race: Elf
        else if (set.getString("Race", " ").equals("Elf")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Cure Wounds
                    //Target regain the number hit points equal to 1d8 + ability

                    //If the player's health is at the maximum
                    if (healthbar.getMax() == set.getInt("Health", -10)) {
                        instruct_txt.append("\nYour HEALTH is at its maximum");
                    }

                    else {
                        value = dice.nextInt((8) + 1) + 3;
                        instruct_txt.append("You cast CURE WOUNDS\n You Roll: " + value);

                        //If the added health is more than the players max
                        if (set.getInt("Health", -10) + value > healthbar.getMax()) {
                            healthbar.setProgress(healthbar.getMax());
                            healthnum.setText("Health: " + healthbar.getMax());
                            edit.putInt("Health", healthbar.getMax());
                            edit.commit();
                        }

                        //Add the rolled value to the player's health
                        else {
                            healthbar.setProgress(set.getInt("Health", -10) + value);
                            healthnum.setText("Health: " + (set.getInt("Health", -10) + value));
                            edit.putInt("Health", (set.getInt("Health", -10) + value));
                            edit.commit();
                        }

                    }

                    break;

                case "Paladin":
                    //Bless
                    //Add a d4 to an attack roll

                    value = dice.nextInt((4) + 1);
                    instruct_txt.append("\nYou cast BLESS\nYou Roll: " + value);
                    edit.putInt("Bless", value);
                    edit.commit();
                    break;

                case "Wizard":
                    //Mage Armor
                    //The target's base AC becomes 13+ Dex

                    instruct_txt.append("\nYou cast MAGE ARMOR");
                    instruct_txt.append("\nYour AC is now: " + (13 + set.getInt("Dex", -10)));
                    edit.putInt("AC", 13 + set.getInt("Dex", -10));
                    edit.putBoolean("AC_Chg", true);
                    edit.commit();

                    break;

                default:
                    break;

            }
        }

        //Race: Human
        else if (set.getString("Race", " ").equals("Human")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Charm Person
                    //Enemy needs to make a wisdom saving throw

                    int enemy_save = (dice.nextInt(20) + 1) + enemy_wis;
                    instruct_txt.append("\nYou cast CHARM PERSON. DC 13");
                    instruct_txt.append("\nEnemy Rolled: " + enemy_save);
                    if (enemy_save > 13) {
                        instruct_txt.append("\nYou failed to charm");
                    } else {
                        instruct_txt.append("\nSuccess");
                        edit.putBoolean("Enemy_Charm", true);
                        edit.commit();
                    }
                    break;

                case "Paladin":
                    //Cure Wounds
                    //Target regain the number hit points equal to 1d8 +

                    //If the player's health is at the maximum
                    if (healthbar.getMax() == set.getInt("Health", -10)) {
                        instruct_txt.append("\nYour HEALTH is at its maximum");
                    }

                    else {
                        value = dice.nextInt((8) + 1) + 3;
                        instruct_txt.append("You cast CURE WOUNDS\n You Roll: " + value);

                        //If the added health is more than the players max
                        if (set.getInt("Health", -10) + value > healthbar.getMax()) {
                            healthbar.setProgress(healthbar.getMax());
                            healthnum.setText("Health: " + healthbar.getMax());
                            edit.putInt("Health", healthbar.getMax());
                            edit.commit();
                        }

                        //Add the rolled value to the player's health
                        else {
                            healthbar.setProgress(set.getInt("Health", -10) + value);
                            healthnum.setText("Health: " + (set.getInt("Health", -10) + value));
                            edit.putInt("Health", (set.getInt("Health", -10) + value));
                            edit.commit();
                        }

                    }

                    break;

                case "Wizard":
                    //Mage Armor
                    //The target's base AC becomes 13+ Dex

                    instruct_txt.append("\nYou cast MAGE ARMOR");
                    instruct_txt.append("\nYour AC is now: " + (13 + set.getInt("Dex", -10)));
                    edit.putInt("AC", 13 + set.getInt("Dex", -10));
                    edit.putBoolean("AC_Chg", true);
                    edit.commit();

                    break;

                default:
                    break;
            }

        }

        //Race: Orc
        else if (set.getString("Race", " ").equals("Orc")) {
            switch (set.getString("Class", " ")) {
                case "Bard":
                    //Cure Wounds
                    //Target regain the number hit points equal to 1d8 + ability

                    //If the player's health is at the maximum
                    if (healthbar.getMax() == set.getInt("Health", -10)) {
                        instruct_txt.append("\nYour HEALTH is at its maximum");
                    }

                    else {
                        value = dice.nextInt((8) + 1) + 3;
                        instruct_txt.append("You cast CURE WOUNDS\n You Roll: " + value);

                        //If the added health is more than the players max
                        if (set.getInt("Health", -10) + value > healthbar.getMax()) {
                            healthbar.setProgress(healthbar.getMax());
                            healthnum.setText("Health: " + healthbar.getMax());
                            edit.putInt("Health", healthbar.getMax());
                            edit.commit();
                        }

                        //Add the rolled value to the player's health
                        else {
                            healthbar.setProgress(set.getInt("Health", -10) + value);
                            healthnum.setText("Health: " + (set.getInt("Health", -10) + value));
                            edit.putInt("Health", (set.getInt("Health", -10) + value));
                            edit.commit();
                        }

                    }

                    break;

                case "Paladin":
                    //Compelled Duel
                    //Creature makes a wisdom save else gets disadvantage

                    int enemy_save = (dice.nextInt(20) + 1) + enemy_wis;
                    instruct_txt.append("\nYou cast COMPELLED DUEL. DC 13");
                    instruct_txt.append("\nEnemy Rolled: " + enemy_save);
                    if (enemy_save > 13) {
                        instruct_txt.append("\nYou failed compel");
                    }
                    else {
                        instruct_txt.append("\nSuccess");
                        edit.putBoolean("Enemy_Dis", true);
                        edit.commit();
                    }
                    break;

                case "Wizard":
                    //Mage Armor
                    //The target's base AC becomes 13+ Dex

                    instruct_txt.append("\nYou cast MAGE ARMOR");
                    instruct_txt.append("\nYour AC is now: " + (13 + set.getInt("Dex", -10)));
                    edit.putInt("AC", 13 + set.getInt("Dex", -10));
                    edit.putBoolean("AC_Chg", true);
                    edit.commit();

                    break;

                default:
                    break;
            }
        }

        //Race: Tiefling
        else if (set.getString("Race", " ").equals("Tiefling")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Charm Person
                    //Enemy needs to make a wisdom saving throw

                    int enemy_save = (dice.nextInt(20) + 1) + enemy_wis;
                    instruct_txt.append("\nYou cast CHARM PERSON. DC 13");
                    instruct_txt.append("\nEnemy Rolled: " + enemy_save);
                    if (enemy_save > 13) {
                        instruct_txt.append("\nYou failed to charm");
                    } else {
                        instruct_txt.append("\nSuccess");
                        edit.putBoolean("Enemy_Charm", true);
                        edit.commit();
                    }
                    break;


                case "Paladin":
                    //Cure Wounds
                    //Target regain the number hit points equal to 1d8 + ability

                    //If the player's health is at the maximum
                    if (healthbar.getMax() == set.getInt("Health", -10)) {
                        instruct_txt.append("\nYour HEALTH is at its maximum");
                    }

                    else {
                        value = dice.nextInt((8) + 1) + 3;
                        instruct_txt.append("You cast CURE WOUNDS\n You Roll: " + value);

                        //If the added health is more than the players max
                        if (set.getInt("Health", -10) + value > healthbar.getMax()) {
                            healthbar.setProgress(healthbar.getMax());
                            healthnum.setText("Health: " + healthbar.getMax());
                            edit.putInt("Health", healthbar.getMax());
                            edit.commit();
                        }

                        //Add the rolled value to the player's health
                        else {
                            healthbar.setProgress(set.getInt("Health", -10) + value);
                            healthnum.setText("Health: " + (set.getInt("Health", -10) + value));
                            edit.putInt("Health", (set.getInt("Health", -10) + value));
                            edit.commit();
                        }

                    }

                    break;

                case "Wizard":
                    //Mage Armor
                    //The target's base AC becomes 13+ Dex

                    instruct_txt.append("\nYou cast MAGE ARMOR");
                    instruct_txt.append("\nYour AC is now: " + (13 + set.getInt("Dex", -10)));
                    edit.putInt("AC", 13 + set.getInt("Dex", -10));
                    edit.putBoolean("AC_Chg", true);
                    edit.commit();

                    break;

                default:
                    break;
            }
        }

        edit.putInt("First", (set.getInt("First",-10)-1));
        edit.commit();

    }

    public int Attacking_First_2(){
        //Race: Elf
        if (set.getString("Race", " ").equals("Elf")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Charm Person
                    //Enemy needs to make a wisdom saving throw
                    return -10;

                case "Paladin":
                    //Searing Smite
                    //Enemy must make a constitution saving throw or take additional damage
                    return dice.nextInt((6) + 1);

                case "Wizard":
                    //Charm Person
                    //Enemy needs to make a wisdom saving throw
                    return -10;

                default:
                    break;

            }
        }

        //Race: Human
        if (set.getString("Race", " ").equals("Human")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Detect Magic
                    //Show the player the enemies attacks
                    return -10;

                case "Paladin":
                    //Wrathful Smite
                    //Enemy must make a wisdom saving throw or be frightened
                    return dice.nextInt((6) + 1);

                case "Wizard":
                    //Charm Person
                    //Enemy needs to make a wisdom saving throw
                    return -10;

                default:
                    break;
            }

        }

        //Race: Orc
        if (set.getString("Race", " ").equals("Orc")) {
            switch (set.getString("Class", " ")) {
                case "Bard":
                    //Charm Person
                    //Enemy needs to make a wisdom saving throw
                    return -10;

                case "Paladin":
                    //Thunderous Smite
                    //Enemy must make a Strength saving throw or me knocked prone
                    return 2*dice.nextInt((6) + 1);

                case "Wizard":
                    //Charm Person
                    //Enemy needs to make a wisdom saving throw
                    return -10;

                default:
                    break;
            }
        }

        //Race: Tiefling
        if (set.getString("Race", " ").equals("Tiefling")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Detect Magic
                    //Show the player the enemies attacks
                    return -10;

                case "Paladin":
                    //Wrathful Smite
                    //Enemy must make a wisdom saving throw or be frightened
                    return dice.nextInt((6) + 1);

                case "Wizard":
                    //Charm Person
                    //Enemy needs to make a wisdom saving throw
                    return -10;

                default:
                    break;
            }
        }

        return -10;

    }

    public int Attacking_First_3(){
        //Race: Elf
        if (set.getString("Race", " ").equals("Elf")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Detect Magic
                    //Show the player the enemies attacks
                    return -10;

                case "Paladin":
                    //Compelled Duel
                    //Creature makes a wisdom save else gets disadvantage
                    return -10;

                case "Wizard":
                    //Sleep
                    //Creature takes 1d8
                    return dice.nextInt((8) + 1);

                default:
                    break;

            }
        }

        //Race: Human
        if (set.getString("Race", " ").equals("Human")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Cure Wounds
                    //Target regain the number hit points equal to 1d8 + ability
                    return dice.nextInt((8) + 1) + 3;

                case "Paladin":
                    //Bless
                    //Add a d4 to an attack roll
                    return dice.nextInt((4) + 1);

                case "Wizard":
                    //Sleep
                    //Creature takes 1d8
                    return dice.nextInt((8) + 1);

                default:
                    break;
            }

        }

        //Race: Orc
        if (set.getString("Race", " ").equals("Orc")) {
            switch (set.getString("Class", " ")) {

                case "Paladin":
                    //Bless
                    //Add a d4 to an attack roll
                    return dice.nextInt((4) + 1);

                case "Wizard":
                    //Sleep
                    //Creature takes 1d8
                    return dice.nextInt((8) + 1);

                default:
                    break;
            }
        }

        //Race: Tiefling
        if (set.getString("Race", " ").equals("Tiefling")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Cure Wounds
                    //Target regain the number hit points equal to 1d8 + ability
                    return dice.nextInt((8) + 1) + 3;


                case "Paladin":
                    //Divine Favor
                    //Deal an extra 1d4 radiant damage
                    return dice.nextInt((4) + 1);

                case "Wizard":
                    //Sleep
                    //Creature takes 1d8
                    return dice.nextInt((8) + 1);

                default:
                    break;
            }
        }

        return -10;
    }

    public int Attacking_First_4(){
        //TODO
        return -10;
    }

    public void Attacking_Second_1(){

    }

    public void Attacking_Second_2(){

    }

    public int Attacking_Second_3(){

        //Race: Orc
        if (set.getString("Race", " ").equals("Orc")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Hold Person
                    return -10;

                case "Paladin":
                    //Bless
                    //Add a d4 to an attack roll
                    return dice.nextInt((4) + 1);

                case "Wizard":
                    //Sleep
                    //Creature takes 1d8
                    return dice.nextInt((8) + 1);

                default:
                    break;
            }
        }

        //Race: Tiefling
        if (set.getString("Race", " ").equals("Tiefling")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Phantasmal Force
                    //Deal 1d6 psychic damage
                    return dice.nextInt((6) + 1);


                case "Paladin":
                    //Divine Favor
                    //Deal an extra 1d4 radiant damage
                    return dice.nextInt((4) + 1);

                case "Wizard":
                    //Sleep
                    //Creature takes 1d8
                    return dice.nextInt((8) + 1);

                default:
                    break;
            }
        }

        return -10;
    }

    public int Attacking_Melee(){
        //Race: Elf
        if (set.getString("Race", " ").equals("Elf")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Longsword
                    //1d8+1 slashing damage
                    return dice.nextInt((8) + 1) + 1;

                case "Paladin":
                    //Battleaxe
                    //1d8 + 2 slashing damage
                    return dice.nextInt((8) + 1) + 2;

                case "Rogue":
                    //Shortbow
                    //1d6+3 piercing damage
                    return dice.nextInt((6) + 1) + 3;

                case "Wizard":
                    //Quarterstaff
                    //1d6+0 bludgeoning damage
                    return dice.nextInt((6) + 1);

                default:
                    break;

            }
        }

        //Race: Human
        if (set.getString("Race", " ").equals("Human")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Longsword
                    //1d8+1 slashing damage
                    return dice.nextInt((8) + 1) + 1;

                case "Paladin":
                    //Longsword
                    //1d8+3 slashing damage
                    return dice.nextInt((8) + 1) + 3;

                case "Rogue":
                    //Shortbow
                    //1d6+3 piercing damage
                    return dice.nextInt((6) + 1) + 3;

                case "Wizard":
                    //Quarterstaff
                    //1d6+1 bludgeoning damage
                    return dice.nextInt((6) + 1) + 1;

                default:
                    break;
            }

        }

        //Race: Orc
        if (set.getString("Race", " ").equals("Orc")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Longsword
                    //1d8+1 slashing damage
                    return dice.nextInt((8) + 1) + 2;

                case "Paladin":
                    //Battleaxe
                    //1d8 + 3 slashing damage
                    return dice.nextInt((8) + 1) + 3;

                case "Rogue":
                    //Shortbow
                    //1d6+2 piercing damage
                    return dice.nextInt((6) + 1) + 2;

                case "Wizard":
                    //Quarterstaff
                    //1d6+0 bludgeoning damage
                    return dice.nextInt((6) + 1);

                default:
                    break;
            }
        }

        //Race: Tiefling
        if (set.getString("Race", " ").equals("Tiefling")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Longsword
                    //1d8+1 slashing damage
                    return dice.nextInt((8) + 1) + 1;

                case "Rogue":
                    //Shortbow
                    //1d6+2 piercing damage
                    return dice.nextInt((6) + 1) + 2;

                case "Paladin":
                    //Longsword
                    //1d8 + 2 slashing damage
                    return dice.nextInt((8) + 1) + 2;

                case "Wizard":
                    //Quarterstaff
                    //1d6+0 bludgeoning damage
                    return dice.nextInt((6) + 1);

                default:
                    break;
            }
        }

        return -10;
    }


}
