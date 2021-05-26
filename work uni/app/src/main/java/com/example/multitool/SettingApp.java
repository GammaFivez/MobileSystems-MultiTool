package com.example.multitool;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Switch;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class SettingApp extends AppCompatActivity {
    private Switch switchBackgroundClr;
    private Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_app);
        switchBackgroundClr = (Switch) findViewById(R.id.switchDK);
        saveButton = (Button) findViewById(R.id.save_button);

        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        switchBackgroundClr.setChecked(sharedPreferences.getBoolean("value", false));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (switchBackgroundClr.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value", true);
                    editor.apply();
                    switchBackgroundClr.setChecked(true);
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    switchBackgroundClr.setChecked(false);
                }


                if(switchBackgroundClr.isChecked()){
                    settingStorage.dataStorage.isDarkModeEnabled = true;
                }
                else{
                    settingStorage.dataStorage.isDarkModeEnabled = false;
                }
            }
        });
    }
}