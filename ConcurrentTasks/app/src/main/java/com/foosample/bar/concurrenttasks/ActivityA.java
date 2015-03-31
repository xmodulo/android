package com.foosample.bar.concurrenttasks;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ActivityA extends Activity {

    public static final String KEY_EXTRA_NEW_DOCUMENT_COUNTER = "KEY_EXTRA_NEW_DOCUMENT_COUNTER";
    public static final String KEY_EXTRA_ACTIVITY_NAME = "KEY_EXTRA_ACTIVITY_NAME";
    public static final String STRING_ACTIVITY_B = "ACTIVITY_B";
    public static int NEW_DOCUMENT_COUNTER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            // Do something for froyo and above versions
        } else{
            // do something for phones running an SDK before froyo
        }

        Button buttonSwitch = (Button) findViewById(R.id.buttonSwitch);
        buttonSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityA.this, ActivityB.class);
                startActivity(intent);
            }
        });

        Button buttonSingle = (Button) findViewById(R.id.buttonSingle);
        buttonSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSingleConcurrent();
            }
        });

        Button buttonConcurrent = (Button) findViewById(R.id.buttonConcurrent);
        buttonConcurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMultipleConcurrent();
            }
        });

        Button buttonCancel = (Button) findViewById(R.id.buttonCloseConcurrent);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeRandomActivityB();
            }
        });
    }

    private void startSingleConcurrent() {
        Intent intent = new Intent(this, ActivityB.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        intent.putExtra(KEY_EXTRA_NEW_DOCUMENT_COUNTER, NEW_DOCUMENT_COUNTER++);
        intent.putExtra(KEY_EXTRA_ACTIVITY_NAME, STRING_ACTIVITY_B);
        startActivity(intent);
    }

    private void startMultipleConcurrent() {
        Intent intent = new Intent(this, ActivityB.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        intent.putExtra(KEY_EXTRA_NEW_DOCUMENT_COUNTER, NEW_DOCUMENT_COUNTER++);
        intent.putExtra(KEY_EXTRA_ACTIVITY_NAME, STRING_ACTIVITY_B);
        startActivity(intent);
    }

    private void getRecent() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> myTasks = manager.getAppTasks();

        int counter = 1;
        for(ActivityManager.AppTask task : myTasks) {
            Intent baseIntent = task.getTaskInfo().baseIntent;
            Log.e("TASK " + counter++, baseIntent.getIntExtra(KEY_EXTRA_NEW_DOCUMENT_COUNTER, -1) + "");
        }
    }

    private void removeRandomActivityB() {
        ArrayList<ActivityManager.AppTask> tasks = new ArrayList();

        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> myTasks = manager.getAppTasks();
        for(ActivityManager.AppTask task : myTasks) {
            Intent baseIntent = task.getTaskInfo().baseIntent;
            if(STRING_ACTIVITY_B.equalsIgnoreCase(baseIntent.getStringExtra(KEY_EXTRA_ACTIVITY_NAME))) {
                tasks.add(task);
            }
        }

        Random random = new Random();
        int toRemove = random.nextInt(tasks.size());
        tasks.get(toRemove).finishAndRemoveTask();
    }

}
