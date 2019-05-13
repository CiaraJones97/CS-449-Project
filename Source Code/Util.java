package com.example.minidnd;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Util extends Game {
    public Button N;
    public Button Fi;
    public Button Fl;
    public Button F_L;
    public Button S_L;
    public Button Inv;
    public Button Att_1;
    public Button Att_2;
    public Button Att_3;
    public Button Att_4;
    public Button Att_5;
    public Button Health;
    public Button ConfirmB;

    TextView Invent_text;
    ArrayList<String> Invent_Lst;

    public Util(Button NEXT,Button FIGHT,Button FLEE,
                Button FIR,Button SEC,Button INVENTORY,Button ATT_1,
                Button ATT_2,Button ATT_3,Button ATT_4,Button ATT_5,Button HEALTH,
                Button Confirm,TextView INVENT,ArrayList INVENT_LIST){
        N = NEXT;
        Fi = FIGHT;
        Fl = FLEE;
        F_L = FIR;
        S_L = SEC;
        Inv = INVENTORY;
        Att_1 = ATT_1;
        Att_2 = ATT_2;
        Att_3 = ATT_3;
        Att_4 = ATT_4;
        Att_5 = ATT_5;
        Invent_text = INVENT;
        Invent_Lst = INVENT_LIST;
        Health = HEALTH;
        ConfirmB = Confirm;
    }

    public void Combat(){
        if (!set.getBoolean("Continue",true)) {
            N.setVisibility(View.GONE);
            ConfirmB.setVisibility(View.GONE);
            Fi.setVisibility(View.VISIBLE);
            Fl.setVisibility(View.VISIBLE);
            Inv.setVisibility(View.VISIBLE);
        }
        else {
            N.setVisibility(View.VISIBLE);
            ConfirmB.setVisibility(View.GONE);
            Fi.setVisibility(View.GONE);
            Fl.setVisibility(View.GONE);
            F_L.setVisibility(View.GONE);
            S_L.setVisibility(View.GONE);
            Inv.setVisibility(View.GONE);
            Invent_text.setVisibility(View.GONE);
            Att_1.setVisibility(View.GONE);
            Att_2.setVisibility(View.GONE);
            Att_3.setVisibility(View.GONE);
            Att_4.setVisibility(View.GONE);
            Att_5.setVisibility(View.GONE);
            Health.setVisibility(View.GONE);
        }

    }

    public void Invent(){
        Invent_text.setVisibility(View.VISIBLE);
        Health.setVisibility(View.VISIBLE);
        Att_1.setVisibility(View.GONE);
        Att_2.setVisibility(View.GONE);
        Att_3.setVisibility(View.GONE);
        Att_4.setVisibility(View.GONE);
        Att_5.setVisibility(View.GONE);
        F_L.setVisibility(View.GONE);
        S_L.setVisibility(View.GONE);
    }

    public void Fight(int firstmax, int secondmax){
        Invent_text.setVisibility(View.GONE);
        Health.setVisibility(View.GONE);
        if (firstmax!=0){
            F_L.setVisibility(View.VISIBLE);
        }
        if (secondmax!=0){
            S_L.setVisibility(View.VISIBLE);
        }
        Att_5.setVisibility(View.VISIBLE);
    }

    public void FirstSecondL(){
        Att_1.setVisibility(View.VISIBLE);
        Att_2.setVisibility(View.VISIBLE);
        Att_3.setVisibility(View.VISIBLE);
        Att_4.setVisibility(View.VISIBLE);
        Invent_text.setVisibility(View.GONE);
        Health.setVisibility(View.GONE);

    }

    public void EndTurn(){
        ConfirmB.setVisibility(View.VISIBLE);
        Fi.setVisibility(View.GONE);
        Fl.setVisibility(View.GONE);
        F_L.setVisibility(View.GONE);
        S_L.setVisibility(View.GONE);
        Invent_text.setVisibility(View.GONE);
        Att_1.setVisibility(View.GONE);
        Att_2.setVisibility(View.GONE);
        Att_3.setVisibility(View.GONE);
        Att_4.setVisibility(View.GONE);
        Att_5.setVisibility(View.GONE);
        Health.setVisibility(View.GONE);
    }




}
