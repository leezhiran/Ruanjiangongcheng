package com.example.ruanjiangongcheng.Misc;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class BasicData extends Application {
    private String User_id;
    private String Match_id;
    private List<String> Current_history=new ArrayList<>();
    public void setDealt(boolean dealt) {
        this.dealt = dealt;
    }

    public List<String> getCurrent_history() {
        return Current_history;
    }

    public void setCurrent_history(List<String> current_history) {
        Current_history = current_history;
    }

    public boolean isDealt() {
        return dealt;
    }

    private boolean dealt=false;
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
