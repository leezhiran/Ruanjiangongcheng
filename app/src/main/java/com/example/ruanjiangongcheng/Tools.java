package com.example.ruanjiangongcheng;

import android.app.AppComponentFactory;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import androidx.appcompat.app.AppCompatActivity;

public class Tools {
    public static BitmapDrawable[][] initDrawables(AppCompatActivity context){
        Bitmap pokerOriginal= BitmapFactory.decodeResource(
                context.getResources(),R.drawable.poker
        );
        int curx=0,cury=1;
        BitmapDrawable[][] pokers=new BitmapDrawable[3][14];
        for(int i=0;i<3;i++){
            for(int j=0;j<14;j++){
                pokers[i][j]=new BitmapDrawable(Bitmap.createBitmap(pokerOriginal,curx,cury,130,180));
                curx=Math.min(curx+139,pokerOriginal.getWidth()-139);
            }
            curx=0;
            cury=Math.min(cury+195,pokerOriginal.getHeight()-190);
        }
        return pokers;
    }
}
