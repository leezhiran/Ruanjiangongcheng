package com.example.ruanjiangongcheng;

import android.app.Application;

public class BasicData extends Application {
    private String User_id;
    private String Match_id;

    public String getUser_id() {
        return User_id;
    }

    public String getMatch_id() {
        return Match_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public void setMatch_id(String match_id) {
        Match_id = match_id;
    }
}
