package com.example.multitool;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Switch;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingApp extends AppCompatActivity {

    private Switch switchLoudAlrm;
    private Switch switchBackgroundClr;
    private Button saveButton;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SWITCHLdAlrm = "switchLoudAlrm";
    private boolean switchOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_app);

        switchLoudAlrm = (Switch) findViewById(R.id.switchAlrm2);
        switchBackgroundClr = (Switch) findViewById(R.id.switchDK);
        saveButton = (Button) findViewById(R.id.buttonSaveSettings);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        if(switchBackgroundClr.isChecked()){
            settingStorage.dataStorage.isDarkModeEnabled = true;
        }
        else{
            settingStorage.dataStorage.isDarkModeEnabled = false;
        }

        loadData();
        updateViews();
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SWITCHLdAlrm, switchLoudAlrm.isChecked());
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        switchOnOff = sharedPreferences.getBoolean(SWITCHLdAlrm, false);
    }

    public void updateViews(){
        switchLoudAlrm.setChecked(switchOnOff);
    }
}