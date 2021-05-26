package com.example.multitool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import java.util.ArrayList;

public class StopwatchApp extends AppCompatActivity {
    private Chronometer timer;
    private TextView lapList;
    private long pauseTimer;
    private boolean runningTimer;
    private Button lapBtn;
    private ArrayList<String> timerLap = new ArrayList <String>();
    private View YourView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch_app);

        //----------------------------------------------------------------------------------
        //If lap button is clicked it will grab the time from the stopwatch and convert into an integer
        //It will turn the integer into a string and put it into an array list.
        //It will then output the arraylist onto the textview called Laplist.
        Button lapBtn = (Button) findViewById(R.id.buttonLap);
        lapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long saveTime = SystemClock.elapsedRealtime() - timer.getBase();
                int seconds = (int)(saveTime/1000 % 60);
                String secondsString = Integer.toString(seconds);
                timerLap.add(secondsString);
                TextView lapList = (TextView) findViewById(R.id.textViewLap);
                lapList.setText(timerLap.toString());
            }
        });
        //---------------------------------------------------------------------------------

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

        timer = findViewById(R.id.timer);
        timer.setFormat("Time: %s");
        timer.setBase(SystemClock.elapsedRealtime());
        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer timer) {
                if ((SystemClock.elapsedRealtime() - timer.getBase()) >= 10000) {
                    timer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(StopwatchApp.this, "Finished!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void startChronometer(View v) {
        if (!runningTimer) {
            timer.setBase(SystemClock.elapsedRealtime() - pauseTimer);
            timer.start();
            runningTimer = true;
        }
    }
    public void pauseChronometer(View v) {
        if (runningTimer) {
            timer.stop();
            pauseTimer = SystemClock.elapsedRealtime() - timer.getBase();
            runningTimer = false;
        }
    }
    public void resetChronometer(View v) {
        timer.setBase(SystemClock.elapsedRealtime());
        pauseTimer = 0;
    }
}