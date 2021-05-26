package com.example.multitool;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.os.CountDownTimer;
import android.os.Bundle;
import java.util.Locale;

public class TimerApp extends AppCompatActivity {
    private EditText enterTimerTextBox;
    //--------------------------------------------------------------------------
    //Grab what the user has written in the textbox and store onto a string.
    //Convert the string into an integer.
    //The timer will be equal to what you have wrote in the textbox  now.
    private EditText et = (EditText)findViewById(R.id.enterTimerTextBox);
    private String valueSecond = et.getText().toString();
    private int finalValue = Integer.parseInt(valueSecond);
    private final long TimerMiliSecond = finalValue;
    //----------------------------------------------------------------------------
    private TextView TextViewCountDown;
    private Button ButtonStartPause;
    private Button ButtonReset;
    private CountDownTimer CountDownTimer;
    private boolean TimerRunning;
    private long TimeLeftInMillis = TimerMiliSecond;
    private View YourView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_app);

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

        TextViewCountDown = findViewById(R.id.text_view_countdown);
        ButtonStartPause = findViewById(R.id.button_start_pause);
        ButtonReset = findViewById(R.id.button_reset);

        ButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
        ButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        updateCountDown();
    }
    private void startTimer() {
        CountDownTimer = new CountDownTimer(TimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeLeftInMillis = millisUntilFinished;
                updateCountDown();
            }
            @Override
            public void onFinish() {
                TimerRunning = false;
                ButtonStartPause.setText("Start");
                ButtonStartPause.setVisibility(View.INVISIBLE);
                ButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();
        TimerRunning = true;
        ButtonStartPause.setText("pause");
        ButtonReset.setVisibility(View.INVISIBLE);
    }
    private void pauseTimer() {
        CountDownTimer.cancel();
        TimerRunning = false;
        ButtonStartPause.setText("Start");
        ButtonReset.setVisibility(View.VISIBLE);
    }
    private void resetTimer() {
        TimeLeftInMillis = TimerMiliSecond;
        updateCountDown();
        ButtonReset.setVisibility(View.INVISIBLE);
        ButtonStartPause.setVisibility(View.VISIBLE);
    }
    private void updateCountDown() {
        int minutes = (int) (TimeLeftInMillis / 1000) / 60;
        int seconds = (int) (TimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        TextViewCountDown.setText(timeLeftFormatted);
    }
}