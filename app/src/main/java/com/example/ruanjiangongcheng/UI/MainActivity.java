package com.example.ruanjiangongcheng.UI;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.example.ruanjiangongcheng.Exception.ConnectionFailed;
import com.example.ruanjiangongcheng.Misc.Base64Tools;
import com.example.ruanjiangongcheng.Misc.BasicData;
import com.example.ruanjiangongcheng.Misc.Card;
import com.example.ruanjiangongcheng.Misc.CardAdapter;
import com.example.ruanjiangongcheng.Misc.InternetActions;
import com.example.ruanjiangongcheng.Misc.RecyclerviewController;
import com.example.ruanjiangongcheng.R;
import com.example.ruanjiangongcheng.Misc.Tools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Services.Deck;

public class MainActivity extends AppCompatActivity {
    private RecyclerviewController r;
    private MainActivity activityReference=this;
    public final Handler handler=new Handler(){
      @Override
      public void handleMessage(Message msg){
          String s= (String) msg.obj;
          if(s==null){
              s="不可用\n";
          }
          if(msg.what==1) {
              TextView tv = findViewById(R.id.room);
              tv.setText(s);
          }
          if(msg.what==2){
              t.interrupt();
              r=new RecyclerviewController(R.id.hand,activityReference);
              BasicData b=(BasicData)getApplication();
              b.setDealt(true);
              Deck[] ls= (Deck[])Base64Tools.decode((String)msg.obj);
              for(Deck i:ls){
                  r.addItem(new Card(i));
              }
          }
      }
    };
    Thread t;
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
            try {
                BasicData b= (BasicData) getApplication();
                List<String> tmp= null;
                tmp = InternetActions.LogIn();
                b.setUser_id(tmp.get(1));
                findViewById(R.id.hand);
            } catch (ConnectionFailed connectionFailed) {
                connectionFailed.printStackTrace();
            }

        });
        RecyclerviewController r=new RecyclerviewController(R.id.hand,this);
        Button join=findViewById(R.id.join);
        join.setOnClickListener((v)->{
            Intent intent=new Intent(this,JoinActivity.class);
            startActivity(intent);
        });
        Button create=findViewById(R.id.create);
        create.setOnClickListener((v)->{
            Intent intent=new Intent(this,StartmatchActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.chatroom).setOnClickListener((view)->{
            Intent intent=new Intent(this,ChatRoom.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public void onStop(){
        super.onStop();
        if(t!=null) {
          //  t.interrupt();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        BasicData basicData=(BasicData)getApplication();
        if(t!=null){
            t.interrupt();
        }
        if(basicData.getMatch_id()!=null&&basicData.isDealt()==false){
            t=new Thread(()->{
                while(!Thread.interrupted()) {
                    System.out.println(toString());
                    Message msg = new Message();
                    msg.what = 1;
                    try {
                        BasicData bd=(BasicData)getApplication();
                        Map<String,String> args=new HashMap<String,String>();
                        args.put("User_id",bd.getUser_id());
                        args.put("Match_id",bd.getMatch_id());
                        List<String> ret=InternetActions.Spooling("AsyncServer",args);
                        msg.obj = ret.get(1);

                        if(ret.get(0).equals("Dealt")){
                            msg.what=2;
                        }
                        if(ret.get(0).equals("Interrupted")){
                            return;
                        };
                        handler.sendMessage(msg);
                    } catch (ConnectionFailed connectionFailed) {
                        connectionFailed.printStackTrace();
                        t.interrupt();
                        t=null;
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            t.start();
        }
    }
}
