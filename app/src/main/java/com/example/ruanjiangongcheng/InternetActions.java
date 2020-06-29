package com.example.ruanjiangongcheng;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InternetActions {
    private static final String mainServer="AsyncServer";
    private static final String chatRoomServer="ChatRoomServer";
    public static List<String> Spooling(String target){
        Map<String,String> args=new HashMap<>();
        args.put("Action","Spooling");
        return new InternetInterface(args,target).getRet();
    }
    public static List<String> LogIn(){
        Map<String,String> args=new HashMap<>();
        args.put("Action","log_in");
        InternetInterface i=new InternetInterface(args,mainServer);
        return  i.getRet();
    }
    public static List<String> ListMatch(){
        Map<String,String> args=new HashMap<>();
        args.put("Action","list_match");
        return new InternetInterface(args,mainServer).getRet();
    }
    public static List<String> joinMatch(Map<String,String> input){
        Map<String,String> args=new HashMap<>();
        args.put("Action","join_match");
        args.put("user_id",input.get("user_id"));
        args.put("match_id",input.get("match_id"));
        return new InternetInterface(args,mainServer).getRet();
    }
    public static List<String> createMatch(Map<String,String> input){
        Map<String,String> args=new HashMap<>();
        args.put("Action","create_match");
        args.put("Player_count",input.get("Player_count"));
        args.put("Include_jokers",input.get("Include_jokers"));
        args.put("Deck_count",input.get("Deck_count"));
        args.put("user_id",input.get("User_id"));
        return new InternetInterface(args,mainServer).getRet();
    }
    public static List<String> broadcast(Map<String,String> input){
        Map<String,String> args=new HashMap<>();
        args.put("Action","create_match");
        args.put("user_id",input.get("user_id"));
        args.put("match_id",input.get("match_id"));
        args.put("Content",input.get("Content"));
        return new InternetInterface(args,chatRoomServer).getRet();
    }
}
