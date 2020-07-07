package com.example.ruanjiangongcheng.UI;

import android.os.Bundle;

import com.example.ruanjiangongcheng.Exception.ConnectionFailed;
import com.example.ruanjiangongcheng.Misc.BasicData;
import com.example.ruanjiangongcheng.Misc.InternetActions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Message;
import android.view.View;

import com.example.ruanjiangongcheng.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChatRoom extends AppCompatActivity {
    Thread t;
    private final Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what==0) {
                TextView tv = findViewById(R.id.chatroom_messages);
                BasicData bd = (BasicData) getApplication();
                List<String> rets = (List<String>) msg.obj;
                tv.setText("");
                for (int i = 1; i < rets.size(); i++) {
                    bd.getCurrent_history().add(rets.get(i));
                    tv.setText(tv.getText().toString() + "\n" + rets.get(i));
                }
            }else{
                TextView tv = findViewById(R.id.chatroom_messages);
                BasicData bd = (BasicData) getApplication();
                for (int i = 1; i < bd.getCurrent_history().size(); i++) {
                    tv.setText(tv.getText().toString() + "\n" + bd.getCurrent_history().get(i));
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        Button b=findViewById(R.id.submit);
        final EditText et=findViewById(R.id.chatroom_sendmessage);
        BasicData bd=(BasicData)getApplication();
        Message msg1=new Message();
        msg1.what=1;
        handler.sendMessage(msg1);
        if(bd.getUser_id()!=null) {
            try {
                InternetActions.SignInChatRoom(bd.getUser_id());
            } catch (ConnectionFailed connectionFailed) {
                connectionFailed.printStackTrace();
            }
            t = new Thread(() -> {
                while(true){
                    if(Thread.interrupted()==true){
                        return;
                    }
                Map<String,String> args=new HashMap<>();
                args.put("User_id",bd.getUser_id());
                if(bd.getMatch_id()==null){
                    args.put("Match_id","0");
                }else{
                    args.put("Match_id",bd.getMatch_id());
                }
                try {
                    Message msg=new Message();
                    msg.obj = InternetActions.Spooling("ChatRoomServer",args);
                    msg.what=0;
                    handler.sendMessage(msg);
                } catch (ConnectionFailed connectionFailed) {
                    connectionFailed.printStackTrace();
                }try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                }
            });
            t.start();
        }
        b.setOnClickListener((v)->{
            Map<String,String> args=new HashMap<String,String>();
            args.put("User_id",bd.getUser_id());
            if(bd.getMatch_id()!=null)
                args.put("Match_id",bd.getMatch_id());
            else
                args.put("Match_id","0");
            args.put("Content",et.getText().toString());
            try {
                List<String> s=InternetActions.broadcast(args);
                System.out.println();
            } catch (ConnectionFailed connectionFailed) {
                connectionFailed.printStackTrace();
            }
        });
    }
    @Override
    protected void onStop(){
        super.onStop();
        if(t!=null){
            t.interrupt();
        }
    }
    @Override
    protected void onResume(){
        super.onResume();

    }
}