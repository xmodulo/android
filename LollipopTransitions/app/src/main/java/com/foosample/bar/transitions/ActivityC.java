package com.foosample.bar.transitions;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.view.View;
import android.view.ViewGroup;


public class ActivityC extends ActionBarActivity {

    ViewGroup viewGroup;
    View blueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Transition exitTrans = new Explode();
        exitTrans.setDuration(3000);
        exitTrans.setStartDelay(1000);
        getWindow().setExitTransition(exitTrans);

        Transition enterTrans = new Slide();
        enterTrans.setDuration(3000);
        getWindow().setReenterTransition(enterTrans);


//        Transition shared = new Slide();
//        shared.setDuration(3000);
//        getWindow().setSharedElementEnterTransition(shared);

        setContentView(R.layout.activity_c);

        viewGroup = (ViewGroup) findViewById(R.id.main_layout);
        viewGroup.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        ActivityC.this);
                Intent intent = new Intent(ActivityC.this, ActivityB.class);
                startActivity(intent, options.toBundle());
            }
        });

    }

}
