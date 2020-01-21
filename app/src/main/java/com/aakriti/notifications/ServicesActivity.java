package com.aakriti.notifications;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ServicesActivity extends Service {
    public Context context =this;
    public Handler handler =null;
    public Runnable runnable =null;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(context, "Service created", Toast.LENGTH_SHORT).show();

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                double randomNo = getRandomDoubleBetween(1,100);
                Toast.makeText(context, "Random no" + randomNo, Toast.LENGTH_SHORT).show();
                handler.postDelayed(runnable, 2000);
            }
        };
        handler.postDelayed(runnable,2000);
    }

    private static double getRandomDoubleBetween(double min, double max) {
        return (Math.random()*((max-min))-min);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        Toast.makeText(context, "Service stopped", Toast.LENGTH_SHORT).show();
    }
}
