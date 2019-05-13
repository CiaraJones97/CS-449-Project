package com.example.minidnd;

import java.util.Random;

public class Player extends Game {
    Random dice;

    public Player(){
        dice = new Random();
    }

    public int Attacking_First_1() {
        //Race: Elf
        if (set.getString("Race", " ").equals("Elf")) {
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
                    //Mage Armor
                    //The target's base AC becomes 13+ Dex
                    return 13;

                default:
                    break;

            }
        }

        //Race: Human
        if (set.getString("Race", " ").equals("Human")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Charm Person
                    //Enemy needs to make a wisdom saving throw
                    return -10;

                case "Paladin":
                    //Cure Wounds
                    //Target regain the number hit points equal to 1d8 + ability
                    return dice.nextInt((8) + 1) + 3;

                case "Wizard":
                    //Mage Armor
                    //The target's base AC becomes 13+ Dex
                    return 13;

                default:
                    break;
            }

        }

        //Race: Orc
        if (set.getString("Race", " ").equals("Orc")) {
            switch (set.getString("Class", " ")) {
                case "Bard":
                    //Cure Wounds
                    //Target regain the number hit points equal to 1d8 + ability
                    return dice.nextInt((8) + 1) + 3;

                case "Paladin":
                    //Compelled Duel
                    //Creature makes a wisdom save else gets disadvantage
                    return -10;

                case "Wizard":
                    //Mage Armor
                    //The target's base AC becomes 13+ Dex
                    return 13;

                default:
                    break;
            }
        }

        //Race: Tiefling
        if (set.getString("Race", " ").equals("Tiefling")) {
            switch (set.getString("Class", " ")) {

                case "Bard":
                    //Charm Person
                    //Enemy needs to make a wisdom saving throw
                    return -10;


                case "Paladin":
                    //Cure Wounds
                    //Target regain the number hit points equal to 1d8 + ability
                    return dice.nextInt((8) + 1) + 3;

                case "Wizard":
                    //Mage Armor
                    //The target's base AC becomes 13+ Dex
                    return 13;

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
