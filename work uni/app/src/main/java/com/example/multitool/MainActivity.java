package com.example.multitool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;



public class MainActivity extends AppCompatActivity {

    private Button buttonOpenSW;
    private Button buttonOpenTimer;
    private Button buttonOpenSettings;
    private View YourView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonOpenSW = (Button) findViewById(R.id.buttonOpenSW);
        Button buttonOpenSettings = (Button) findViewById(R.id.buttonOpenSetting);

        updateTime();

        //Constructor to open the stopwatch activity
        buttonOpenSW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentStopWatch = new Intent(MainActivity.this, StopwatchApp.class);
                startActivity(intentStopWatch);
            }
        });

        //Constructor to open the settings activity
        buttonOpenSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentStopWatch = new Intent(MainActivity.this, SettingApp.class);
                startActivity(intentStopWatch);
            }
        });

    }

    Runnable updater;
    public void updateTime() {
        final Handler timerHandler = new Handler();

        updater = new Runnable() {
            @Override
            public void run() {
                if(settingStorage.dataStorage.isDarkModeEnabled == true){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else if (settingStorage.dataStorage.isDarkModeEnabled == false){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                timerHandler.postDelayed(updater,1000);
            }
        };
        timerHandler.post(updater);
    }
}

