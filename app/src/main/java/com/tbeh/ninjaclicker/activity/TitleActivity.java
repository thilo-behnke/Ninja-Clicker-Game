package com.tbeh.ninjaclicker.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tbeh.ninjaclicker.R;
import com.tbeh.ninjaclicker.model.Message;

public class TitleActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        View view = findViewById(R.id.activity_title);
        // TODO: Deprecated, search for alternative
        view.setBackground(new BitmapDrawable(getResources(), getResourceManager().getMapFile("Title")));
        Button button = (Button) findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyObservers(Message.Event.START);
                Intent intent = new Intent(TitleActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        notifyObservers(Message.Event.MENU);
    }
}
