package com.example.ruanjiangongcheng.UI;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.ruanjiangongcheng.Exception.ConnectionFailed;
import com.example.ruanjiangongcheng.Misc.Base64Tools;
import com.example.ruanjiangongcheng.Misc.BasicData;
import com.example.ruanjiangongcheng.Misc.InternetActions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ruanjiangongcheng.R;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.ruanjiangongcheng.Misc.InternetActions.ListMatch;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            List<String> matches= null;
            matches = InternetActions.ListMatch();
            List<String> s=(List<String>)Base64Tools.decode(matches.get(1));
            String p="ID 人数\n";
            for(String i:s){
                p+=i;
                p+="\n";
            }
            TextView listMatch=findViewById(R.id.listMatch);
            listMatch.setText(p);
        } catch (ConnectionFailed connectionFailed) {
            connectionFailed.printStackTrace();
        }
        findViewById(R.id.join).setOnClickListener((v)->{
            EditText editText=findViewById(R.id.joinWhich);
            Map<String,String> args=new HashMap<>();
            args.put("User_id",((BasicData)getApplication()).getUser_id());
            args.put("Match_id",editText.getText().toString());
            try {
                InternetActions.joinMatch(args);
                BasicData basicData=(BasicData)getApplication();
                basicData.setMatch_id(args.get("Match_id"));
            } catch (ConnectionFailed connectionFailed) {
                connectionFailed.printStackTrace();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

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
