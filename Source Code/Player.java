package com.example.minidnd;

import android.widget.Button;

import java.util.Random;

public class Player extends Game {
    public Button Att_1;
    public Button Att_2;
    public Button Att_3;
    public Button Att_4;
    public Button Att_5;
    Random dice = new Random();

    public Player(Button ATT_1,Button ATT_2,Button ATT_3,Button ATT_4,Button ATT_5){
        Att_1 = ATT_1;
        Att_2 = ATT_2;
        Att_3 = ATT_3;
        Att_4 = ATT_4;
        Att_5 = ATT_5;
    }

    public int Attacking_First_1() {
        //Race: Elf
        if (set.getString("Race", " ").equals("Elf")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //TODO
                    //Cure Wounds
                    //Target regain the number hit points equal to 1d8 + ability
                    return new Random().nextInt((8) + 1) + 3;

                case "Paladin":
                    //TODO
                    //Bless
                    //Add a d4 to an attack roll or saving roll

                    break;

                case "Wizard":
                    //TODO
                    //Mage Armor
                    //The target's base AC becomes 13+ Dex

                    break;

                default:
                    break;

            }
        }

        //Race: Human
        if (set.getString("Race", " ").equals("Human")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Charm Person
                    break;

                case "Paladin":
                    //Cure Wounds
                    break;

                case "Wizard":
                    //Mage Armor

                    break;

                default:
                    break;
            }

        }

        //Race: Orc
        if (set.getString("Race", " ").equals("Orc")) {
            switch (set.getString("Class", " ")) {
                case "Bard":
                    //Cure Wounds
                    break;

                case "Paladin":
                    //Compelled Duel
                    break;

                case "Wizard":
                    //Mage Armor
                    break;

                default:
                    break;
            }
        }

        //Race: Tiefling
        if (set.getString("Race", " ").equals("Tiefling")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Charm Person
                    break;

                case "Paladin":
                    //Cure Wounds
                    break;

                case "Wizard":
                    //Mage Armor

                    break;

                default:
                    break;
            }
        }

        return -10;

    }

    public int Attacking_First_2(){
        //Race: Elf
        if (set.getString("Race", " ").equals("Elf")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //TODO
                    //Charm Person
                    break;

                case "Paladin":
                    //TODO
                    //Searing Smite
                    break;

                case "Wizard":
                    //TODO
                    //Charm Person
                    break;

                default:
                    break;

            }
        }

        //Race: Human
        if (set.getString("Race", " ").equals("Human")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Detect Magic
                    break;

                case "Paladin":
                    //Wrathful Smite
                    break;

                case "Wizard":
                    //Charm Person
                    break;

                default:
                    break;
            }

        }

        //Race: Orc
        if (set.getString("Race", " ").equals("Orc")) {
            switch (set.getString("Class", " ")) {
                case "Bard":
                    //Charm Person
                    break;

                case "Paladin":
                    //Thunderous Smite
                    break;

                case "Wizard":
                    //Charm Person
                    break;

                default:
                    break;
            }
        }

        //Race: Tiefling
        if (set.getString("Race", " ").equals("Tiefling")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Detect Magic
                    break;

                case "Paladin":
                    //Wrathful Smite
                    break;

                case "Wizard":
                    //Charm Person
                    break;

                default:
                    break;
            }
        }

        return -10;

    }

    public int Attacking_First_3(){
        //TODO
        return -10;
    }

    public int Attacking_First_4(){
        //TODO
        return -10;
    }

    public int Attacking_Second_1(){
        //TODO
        return -10;
    }

    public int Attacking_Second_2(){
        //TODO
        return -10;
    }

    public int Attacking_Second_3(){
        //TODO
        return -10;
    }

    public int Attacking_Second_4(){
        //TODO
        return -10;
    }

    public int Attacking_Melee(){
        //TODO
        return -10;
    }


}
