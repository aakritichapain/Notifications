package com.aakriti.notifications;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aakriti.notifications.createchannel.CreateChannel;

@RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop;

    BroadcastReceiverExample broadcastReceiverExample = new BroadcastReceiverExample(this);

    private NotificationManagerCompat notificationManagerCompat;
    private Button btnDisplayNotification;
    private Button btnDisplayNotification2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart= findViewById(R.id.btnStartS);
        btnStop= findViewById(R.id.btnStopS);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMyservice();

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMyservice();

            }
        });
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel =  new CreateChannel(this);
        channel.createChannel();

        btnDisplayNotification= findViewById(R.id.btnDisplayNotification);
        btnDisplayNotification2= findViewById(R.id.btnDisplayNotification2);


        btnDisplayNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayNotification();
            }
        });

        btnDisplayNotification2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayNotification2();
            }
        });




    }

    private void startMyservice() {
        startService(new Intent(this,ServicesActivity.class));
    }

    private void stopMyservice() {
        stopService(new Intent(this,ServicesActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter =  new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiverExample,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(broadcastReceiverExample);
    }

    int count=1;

    private void DisplayNotification() {
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_vibration_black_24dp)
                .setContentTitle("First message")
                .setContentText("FIRST MESSAGE")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)

                .build();


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(count,notification);
        count++;

    }



    private void DisplayNotification2() {


        Notification notification2 = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_2)
                .setSmallIcon(R.drawable.ic_sms_black_24dp)
                .setContentTitle("second message")
                .setContentText("SECOND MESSAGE")
                .setCategory(NotificationCompat.CATEGORY_CALL)
                .build();

        notificationManagerCompat.notify(count,notification2);
        count++;
    }




}
