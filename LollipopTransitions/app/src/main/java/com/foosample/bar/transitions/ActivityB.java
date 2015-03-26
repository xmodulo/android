package com.foosample.bar.transitions;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Pair;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ActivityB extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Transition trans = new Explode();
        getWindow().setEnterTransition(trans);

        Transition returnTrans = new Slide();
        returnTrans.setDuration(2000);
        ((Slide)returnTrans).setSlideEdge(Gravity.LEFT);
        getWindow().setReturnTransition(returnTrans);
        getWindow().setAllowReturnTransitionOverlap(true);

        setContentView(R.layout.activity_b);

        final TextView fuchsiaTextView = (TextView) findViewById(R.id.fuchsiaView);
        fuchsiaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        ActivityB.this, fuchsiaTextView, "fuchsiaView");
                Intent intent = new Intent(ActivityB.this, ActivityC.class);
                startActivity(intent, options.toBundle());
            }
        });

        final TextView redTextView = (TextView) findViewById(R.id.redTextView);
        redTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        ActivityB.this, new Pair(fuchsiaTextView, "fuchsiaView"),
                        new Pair(redTextView, "redView"));
                Intent intent = new Intent(ActivityB.this, ActivityC.class);
                startActivity(intent, options.toBundle());
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
