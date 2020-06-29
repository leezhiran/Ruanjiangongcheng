package com.example.ruanjiangongcheng.Misc;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ruanjiangongcheng.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{
    private List<Card> cards;

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView cardImage;
        private TextView cardName;
        public ViewHolder (View view)
        {
            super(view);
            cardImage =view.findViewById(R.id.cardImage);
            cardName =view.findViewById(R.id.cardText);
        }
    }
    public CardAdapter(List<Card> list){
        cards=list;
    }
    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        Card card = cards.get(position);
        holder.cardImage.setBackground(card.getFaceDrawable());
        holder.cardName.setText(card.getInfo().name());
    }
    @Override
    public int getItemCount(){
        return cards.size();
    }
}
