package com.foosample.bar.myapplication;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerList);
        LinearLayoutManager linearLM = new LinearLayoutManager(this);
        linearLM.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLM);

        recyclerView.setAdapter(new MyRecyclerAdapter(generateBooks()));
    }

    private ArrayList<Palette> generateBooks() {
        ArrayList<Palette> palettes = new ArrayList<>();
        palettes.add(new Palette("RED", "#D32F2F", Color.parseColor("#d32f2f")));
        palettes.add(new Palette("PINK", "#FF4081", Color.parseColor("#ff4081")));
        palettes.add(new Palette("INDIGO", "#7B1FA2", Color.parseColor("#7b1fa2")));
        palettes.add(new Palette("BLUE", "#536DFE", Color.parseColor("#536dfe")));
        palettes.add(new Palette("GREEN", "#388E3C", Color.parseColor("#388e3c")));
        palettes.add(new Palette("ORANGE", "#FF5722", Color.parseColor("#ff5722")));
        palettes.add(new Palette("AMBER", "#FFA000", Color.parseColor("#ffa000")));
        return palettes;
    }
}
