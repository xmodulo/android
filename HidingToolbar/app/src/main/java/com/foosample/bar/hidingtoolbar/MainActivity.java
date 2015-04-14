package com.foosample.bar.hidingtoolbar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by obaro on 10/04/2015.
 */
public class MainActivity extends ActionBarActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(createItemList());
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.setOnScrollListener(new MyScrollListener(this) {
            @Override
            public void onMoved(int distance) {
                toolbar.setTranslationY(-distance);
            }
        });
    }

    private List<String> createItemList() {
        ArrayList<String> list = new ArrayList();
        for(int i = 0; i < 20; i++) {
            list.add(new String("List Item " + i));
        }
        return list;
    }

}
