package com.example.minidnd;

import java.util.ArrayList;
import java.util.Random;

public class Enemies extends Game {

    public int Health;
    public int Initiative;
    public int AC;

    //Ability Mod
    public int Str;
    public int Dex;
    public int Con;
    public int Int;
    public int Wis;
    public int Cha;
    public String EnemyName;

    ArrayList<String> EnemyAttacks = new ArrayList<>();

    public Enemies(){
        EnemyName = " ";
        Health = -10;
        Initiative = -10;
        AC = -10;
        Str = -10;
        Dex = -10;
        Con = -10;
        Int = -10;
        Wis = -10;
        Cha = -10;
    }

    public void newEnemy(){
        int die = new Random().nextInt(5);
        this.EnemyAttacks.clear();
        switch (die) {
            //Goblin
            case 0:
                this.EnemyName = "Goblin";
                this.Health = 7;
                this.Initiative = new Random().nextInt(20)+1;
                this.AC = 15;
                this.Str = -1;
                this.Dex = 2;
                this.Con = 0;
                this.Int = 0;
                this.Wis = -1;
                this.Cha = -1;
                this.EnemyAttacks.add("Scimitar");
                this.EnemyAttacks.add("Shortbow");
                break;

            //Bandit
            case 1:
                this.EnemyName = "Bandit";
                this.Health = 11;
                this.Initiative = new Random().nextInt(20)+1;
                this.AC = 12;
                this.Str = 0;
                this.Dex = 1;
                this.Con = 1;
                this.Int = 0;
                this.Wis = 0;
                this.Cha = 0;
                this.EnemyAttacks.add("Scimitar");
                this.EnemyAttacks.add("Light Crossbow");
                break;

            //Bugbear
            case 2:
                this.EnemyName = "Bugbear";
                this.Health = 27;
                this.Initiative = new Random().nextInt(20)+1;
                this.AC = 16;
                this.Str = 2;
                this.Dex = 2;
                this.Con = 1;
                this.Int = -1;
                this.Wis = 0;
                this.Cha = -1;
                this.EnemyAttacks.add("Morning Star");
                this.EnemyAttacks.add("Javelin");
                break;

            //Dire Wolf
            case 3:
                this.EnemyName = "Dire Wolf";
                this.Health = 37;
                this.Initiative = new Random().nextInt(20)+1;
                this.AC = 14;
                this.Str = 3;
                this.Dex = 2;
                this.Con = 2;
                this.Int = -4;
                this.Wis = 1;
                this.Cha = -2;
                this.EnemyAttacks.add("Bite");
                break;

            //Wight
            case 4:
                this.EnemyName = "Wight";
                this.Health = 45;
                this.Initiative = new Random().nextInt(20)+1;
                this.AC = 14;
                this.Str = 2;
                this.Dex = 2;
                this.Con = 3;
                this.Int = 0;
                this.Wis = 1;
                this.Cha = 2;
                this.EnemyAttacks.add("Multiattack");
                this.EnemyAttacks.add("Life Drain");
                this.EnemyAttacks.add("Longsword");
                this.EnemyAttacks.add("Longbow");
                break;

        }
    }

    public String getEnemyName() {
        return EnemyName;
    }

    public int getHealth() {
        return Health;
    }

    public int getInitiative() {
        return Initiative;
    }

    public int getAC() {
        return AC;
    }

    public int getStr() {
        return Str;
    }

    public int getDex() {
        return Dex;
    }

    public int getCon() {
        return Con;
    }

    public int getInt() {
        return Int;
    }

    public int getWis() {
        return Wis;
    }

    public int getCha() {
        return Cha;
    }

    public int rollDMG(String Weapon){
        switch (EnemyName) {
        //Goblin
        case "Goblin":
            return new Random().nextInt((6)+1) + 2;

        //Bandit
        case "Bandit":
            if (Weapon.equals("Scimitar")){
                return new Random().nextInt((6)+1) + 1;
            }
            else{
                return new Random().nextInt((8)+1) + 1;
            }

            //Bugbear
        case "Bugbear":
            if (Weapon.equals("Morningstar")){
                return 3*(new Random().nextInt((8)+1) + 2);
            }
            else{
                return 3*(new Random().nextInt((6)+1) + 2);
            }

            //Dire Wolf
        case "Dire Wolf":
            return 2*(new Random().nextInt((6)+1) + 3);

        //Wight
        case "Wight":
            if(Weapon.equals("Multiattack")){
                return 2*(new Random().nextInt((8)+1) + 2);
            }
            else if (Weapon.equals("Life Drain"))
            {
                //TODO: ADD Constitution saving throw for the player
                return new Random().nextInt((6)+1) + 2;
            }
            else if (Weapon.equals("Longsword")){
                return new Random().nextInt((8)+1) + 2;
            }
            else{
                return new Random().nextInt((8)+1) + 2;
            }
    }
        return -10;
}

    public int rollATT(){
        switch (EnemyName) {
            //Goblin
            case "Goblin":
                return new Random().nextInt((20)+1) + 4;

            //Bandit
            case "Bandit":
                return new Random().nextInt((20)+1) + 3;

                //Bugbear
            case "Bugbear":
                return new Random().nextInt((20)+1) + 4;

                //Dire Wolf
            case "Dire Wolf":
                return new Random().nextInt((20)+1) + 5;

            //Wight
            case "Wight":
                return new Random().nextInt((20)+1) + 4;
        }
        return -10;
    }

    public ArrayList<String> getEnemyAttacks(){
        return EnemyAttacks;
    }

}