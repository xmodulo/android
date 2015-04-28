package com.foosample.bar.lollipopnotifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RemoteViews;

/**
 * Created by obaro on 22/04/2015.
 */
public class MainActivity extends ActionBarActivity {

    public static final int HEADS_UP_NOTIFICATION_ID = 1;
    public static final int SECRET_NOTIFICATION_ID = 2;
    public static final int PRIVATE_NOTIFICATION_ID = 3;
    public static final int PUBLIC_NOTIFICATION_ID = 4;
    public static final int NORMAL_NOTIFICATION_ID = 5;
    public static final int CUSTOM_NOTIFICATION_ID = 6;

    NotificationManager notificationManager;

    Button headsUpButton;
    CheckBox headsUpCheckBox;
    Button visibilityButton;
    RadioGroup visibilityRadioGroup;
    Button normalButton;
    Button customButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationManager = (NotificationManager) getSystemService(Context
                .NOTIFICATION_SERVICE);
        setContentView(R.layout.activity_main);
        setTitle("LollipopNotifications");

        headsUpButton = (Button) findViewById(R.id.buttonHeadsUp);
        headsUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createHeadsUpNotification();
            }
        });

        visibilityRadioGroup = (RadioGroup) findViewById(R.id.visibilityRadioGroup);
        visibilityButton = (Button) findViewById(R.id.visibilityButton);
        visibilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createOtherNotification();
            }
        });

        normalButton = (Button) findViewById(R.id.normalButton);
        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.ic_launcher_notification)
                        .setContentTitle("Simple Notification")
                        .setContentText("This is a normal notification.");

                notificationManager.notify(NORMAL_NOTIFICATION_ID, notificationBuilder.build());
            }
        });

        customButton = (Button) findViewById(R.id.customButton);
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCustomNotification();
            }
        });
    }

    private void createHeadsUpNotification() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_notification)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVibrate(new long[] {1, 1, 1})
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentTitle("Simple Heads-Up Notification")
                .setContentText("This is a normal notification.");
//        if (makeHeadsUpNotification) {

//            Intent push = new Intent();
//            push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            push.setClass(this, MainActivity.class);
//
//            PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(this, 0,
//                    push, PendingIntent.FLAG_CANCEL_CURRENT);
//            notificationBuilder
//                    .setContentText("A Heads-Up notification for Lollipop and above")
//                    .setFullScreenIntent(fullScreenPendingIntent, true);

//        }

//        notificationBuilder.set

//        NotificationCompat.InboxStyle inboxStyle =
//                new NotificationCompat.InboxStyle();
//        String[] events = {"Additional text one","Additional Text two"};
//// Sets a title for the Inbox in expanded layout
//        inboxStyle.setBigContentTitle("Extended contents");
//
//// Moves events into the expanded layout
//        for (int i=0; i < events.length; i++) {
//            inboxStyle.addLine(events[i]);
//        }
//// Moves the expanded layout object into the notification object.
//        notificationBuilder.setStyle(inboxStyle);
//
        notificationManager.notify(HEADS_UP_NOTIFICATION_ID, notificationBuilder.build());
    }

    private void createOtherNotification() {
        int visibility;
        String description;
        int notificationId;
        int notificationIconId;

        switch (visibilityRadioGroup.getCheckedRadioButtonId()) {
            case R.id.privateRadioButton:
                visibility = NotificationCompat.VISIBILITY_PRIVATE;
                description = "This notification is partially visible on lock screen";
                notificationId = PRIVATE_NOTIFICATION_ID;
                notificationIconId = R.drawable.ic_private_notification;
                break;
            case R.id.secretRadioButton:
                visibility = NotificationCompat.VISIBILITY_SECRET;
                description = "This notification is never visible on lock screen";
                notificationId = SECRET_NOTIFICATION_ID;
                notificationIconId = R.drawable.ic_secret_notification;
                break;
            default:
                //If not selected, returns PUBLIC as default.
                visibility = NotificationCompat.VISIBILITY_PUBLIC;
                description = "This notification is always visible on lock screen";
                notificationId = PUBLIC_NOTIFICATION_ID;
                notificationIconId = R.drawable.ic_public_notification;
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Notification Visibility for Lollipop");

        notificationBuilder.setVisibility(visibility);
        notificationBuilder.setContentText(description);
        notificationBuilder.setSmallIcon(notificationIconId);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    private void createCustomNotification() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_notification)
                .setContent(remoteViews);

        notificationManager.notify(CUSTOM_NOTIFICATION_ID, notificationBuilder.build());
    }
}
