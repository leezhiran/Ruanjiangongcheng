package com.example.ruanjiangongcheng.Misc;

import android.app.Activity;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ruanjiangongcheng.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewController {
    private List<Card> items;
    public RecyclerviewController(int widgetId, Activity activity){
        View view=activity.getWindow().getDecorView();
        items=new ArrayList<>();
        RecyclerView recyclerView =  view.findViewById(widgetId);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        CardAdapter adapter = new CardAdapter(items);
        recyclerView.setAdapter(adapter);
    }
    public void addItem(Card card){
        items.add(card);
    }
}
