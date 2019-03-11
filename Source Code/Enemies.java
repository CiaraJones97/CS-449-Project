package com.example.dnd;

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

    public int die;
    public Enemies(){
        die = new Random().nextInt(5);
        switch (die) {
            //Goblin
            case 0:
                this.EnemyName = "Goblin";
                this.Health = 7;
                this.Initiative = new Random().nextInt(20);
                this.AC = 15;
                this.Str = -1;
                this.Dex = 2;
                this.Con = 0;
                this.Int = 0;
                this.Wis = -1;
                this.Cha = -1;
                break;

            //Bandit
            case 1:
                this.EnemyName = "Bandit";
                this.Health = 11;
                this.Initiative = new Random().nextInt(20);
                this.AC = 12;
                this.Str = 0;
                this.Dex = 1;
                this.Con = 1;
                this.Int = 0;
                this.Wis = 0;
                this.Cha = 0;
                break;

            //Bugbear
            case 2:
                this.EnemyName = "Bugbear";
                this.Health = 27;
                this.Initiative = new Random().nextInt(20);
                this.AC = 16;
                this.Str = 2;
                this.Dex = 2;
                this.Con = 1;
                this.Int = -1;
                this.Wis = 0;
                this.Cha = -1;
                break;

            //Dire Wolf
            case 3:
                this.EnemyName = "Dire Wolf";
                this.Health = 37;
                this.Initiative = new Random().nextInt(20);
                this.AC = 14;
                this.Str = 3;
                this.Dex = 2;
                this.Con = 2;
                this.Int = -4;
                this.Wis = 1;
                this.Cha = -2;
                break;

            //Wight
            case 4:
                this.EnemyName = "Wight";
                this.Health = 45;
                this.Initiative = new Random().nextInt(20);
                this.AC = 14;
                this.Str = 2;
                this.Dex = 2;
                this.Con = 3;
                this.Int = 0;
                this.Wis = 1;
                this.Cha = 2;
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
}
