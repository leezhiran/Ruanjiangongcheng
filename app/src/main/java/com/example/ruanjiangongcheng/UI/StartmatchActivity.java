package com.example.ruanjiangongcheng.UI;

import android.os.Bundle;

import com.example.ruanjiangongcheng.Exception.ConnectionFailed;
import com.example.ruanjiangongcheng.Misc.BasicData;
import com.example.ruanjiangongcheng.Misc.InternetActions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.example.ruanjiangongcheng.R;

import java.util.HashMap;
import java.util.Map;

public class StartmatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startmatch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        findViewById(R.id.create).setOnClickListener((v)->{
            BasicData data=(BasicData)getApplication();
            EditText players=findViewById(R.id.player);
            EditText decks=findViewById(R.id.deckcount);
            Switch jokers=findViewById(R.id.switch1);
            Map<String,String> args=new HashMap<>();
            args.put("User_id",data.getUser_id());
            args.put("Player_count",players.getText().toString());
            args.put("Deck_count",decks.getText().toString());
            args.put("Include_jokers",String.valueOf(jokers.isChecked()));
            try {
                data.setMatch_id(InternetActions.createMatch(args).get(1));
                finish();
            } catch (ConnectionFailed connectionFailed) {
                connectionFailed.printStackTrace();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
