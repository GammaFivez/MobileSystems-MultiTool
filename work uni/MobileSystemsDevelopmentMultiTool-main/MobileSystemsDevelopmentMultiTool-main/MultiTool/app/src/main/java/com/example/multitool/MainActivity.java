package com.example.multitool;

import androidx.appcompat.app.AppCompatActivity;
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


        //Creating a constructor for settings that stores the global database variables.
        //After that it will check if the boolean is enabled and if it is then dark mode and if it isn't then light mode.
        settingStorage settingDatabase = new settingStorage();
        if(settingStorage.dataStorage.isDarkModeEnabled == true){
            YourView.setBackgroundColor(Color.rgb(18, 18, 18));
        }
        else{
            YourView.setBackgroundColor(Color.rgb(255, 255, 255));
        }
        //---------------------------------------------------------------------------------

        Button buttonOpenSW = (Button) findViewById(R.id.buttonOpenSW);
        Button buttonOpenTimer = (Button) findViewById(R.id.buttonOpenTimer);
        Button buttonOpenSettings = (Button) findViewById(R.id.buttonOpenSetting);


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

        //Constructor to open the timer activity
        buttonOpenTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAlarm = new Intent(MainActivity.this, TimerApp.class);
                startActivity(intentAlarm);
            }
        });
    }
}