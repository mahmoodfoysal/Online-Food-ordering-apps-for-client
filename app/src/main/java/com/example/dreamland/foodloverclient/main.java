package com.example.dreamland.foodloverclient;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class main extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        long Delay = 2500;
        Timer runsTimer = new Timer();
        TimerTask shoTimerTask=new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent a= new Intent(main.this,login.class);
                startActivity(a);
            }
        };
        runsTimer.schedule(shoTimerTask,Delay);
    }
}
