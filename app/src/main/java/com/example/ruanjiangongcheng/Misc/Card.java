package com.example.ruanjiangongcheng.Misc;

import android.graphics.drawable.BitmapDrawable;

import Services.Deck;

public class Card {
    Deck info;
    static BitmapDrawable[][] face;
    public Card(Deck info) {
        this.info = info;
    }

    public Deck getInfo() {
        return info;
    }
    public static void setFace(BitmapDrawable Face[][]){
        face=Face;
    }
    public BitmapDrawable getFaceDrawable(){
        if(info.name().equals("大王")){
            return face[0][13];
        }else if(info.name().equals("小王")) {
            return face[1][13];
        }else{
            int i=info.ordinal();
            return face[info.ordinal()/13][info.ordinal()%13];
        }
    }
}
