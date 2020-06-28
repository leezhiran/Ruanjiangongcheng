package com.example.ruanjiangongcheng;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bitmap pokerOriginal= BitmapFactory.decodeResource(
                this.getResources(),R.drawable.poker
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
        findViewById(R.id.imageView).setBackground(pokers[0][0]);
        FloatingActionButton fab = findViewById(R.id.fab);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
