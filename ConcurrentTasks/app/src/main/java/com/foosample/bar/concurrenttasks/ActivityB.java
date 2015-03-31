package com.foosample.bar.concurrenttasks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by obaro on 29/03/2015.
 */
public class ActivityB extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int counter = intent.getIntExtra(ActivityA.KEY_EXTRA_NEW_DOCUMENT_COUNTER, -1);
        setTitle(getResources().getString(R.string.app_name) + " " + counter);
        setContentView(R.layout.activity_b);

        Button buttonExit = (Button) findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitActivity();
            }
        });

        Button buttonMain = (Button) findViewById(R.id.buttonMain);
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity();
            }
        });
    }

    private void exitActivity() {
        finishAndRemoveTask();
    }

    private void mainActivity() {
        Intent intent = new Intent(this, ActivityA.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
//        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//        intent.putExtra(KEY_EXTRA_NEW_DOCUMENT_COUNTER, ++NEW_DOCUMENT_COUNTER);
        startActivity(intent);
    }

}
