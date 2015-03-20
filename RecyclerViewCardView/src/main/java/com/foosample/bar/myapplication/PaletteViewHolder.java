package com.foosample.bar.myapplication;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by obaro on 15/03/2015.
 */
public class PaletteViewHolder extends RecyclerView.ViewHolder {

    protected TextView titleText;
    protected TextView contentText;
    protected CardView card;

    public PaletteViewHolder(View itemView) {
        super(itemView);
        titleText = (TextView) itemView.findViewById(R.id.name);
        contentText = (TextView) itemView.findViewById(R.id.hexValue);
        card = (CardView) itemView;
    }

}
