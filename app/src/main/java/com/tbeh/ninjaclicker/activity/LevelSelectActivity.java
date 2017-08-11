package com.tbeh.ninjaclicker.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tbeh.ninjaclicker.ninjaclicker.R;

public class LevelSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);

        Button level1Button = (Button) findViewById(R.id.level_1);
        level1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LevelModeActivity.class);
                intent.putExtra("SelectedLevel", "level1");
                startActivity(intent);
            }
        });

    }
}
