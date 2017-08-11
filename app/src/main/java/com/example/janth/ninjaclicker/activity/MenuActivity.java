package com.example.janth.ninjaclicker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.janth.ninjaclicker.R;

public class MenuActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button endlessButton = (Button) findViewById(R.id.mode_endless_button);
        endlessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EndlessMenuActivity.class);
                startActivity(intent);
            }
        });
        Button levelButton = (Button) findViewById(R.id.mode_level_button);
        levelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LevelSelectActivity.class);
                startActivity(intent);
            }
        });
    }
}