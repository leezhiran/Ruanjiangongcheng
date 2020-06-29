package com.example.ruanjiangongcheng.UI;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.example.ruanjiangongcheng.Misc.BasicData;
import com.example.ruanjiangongcheng.Misc.Card;
import com.example.ruanjiangongcheng.Misc.CardAdapter;
import com.example.ruanjiangongcheng.Misc.InternetActions;
import com.example.ruanjiangongcheng.R;
import com.example.ruanjiangongcheng.Misc.Tools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import Services.Deck;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Class.forName("android.os.AsyncTask");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BitmapDrawable[][] pokers= Tools.initDrawables(this);
        findViewById(R.id.imageView).setBackground(pokers[0][0]);
        Card.setFace(pokers);
        Button button=findViewById(R.id.button);
        button.setOnClickListener((v)->{
            BasicData b= (BasicData) getApplication();
            List<String> tmp= InternetActions.LogIn();
            b.setUser_id(tmp.get(1));
            findViewById(R.id.hand);
        });
        List<Card> lists=new ArrayList<>();
        RecyclerView recyclerView =  findViewById(R.id.hand);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        CardAdapter adapter = new CardAdapter(lists);
        recyclerView.setAdapter(adapter);
        lists.add(new Card(Deck.红桃7));
        lists.add(new Card(Deck.大王));
        lists.add(new Card(Deck.大王));
        lists.add(new Card(Deck.大王));
        lists.add(new Card(Deck.大王));
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
