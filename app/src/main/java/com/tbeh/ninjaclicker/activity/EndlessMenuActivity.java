package com.tbeh.ninjaclicker.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.tbeh.ninjaclicker.ninjaclicker.R;
import com.tbeh.ninjaclicker.fragment.PreviewFragment;
import com.tbeh.ninjaclicker.model.Message;
import com.tbeh.ninjaclicker.view.BaseGameView;

public class EndlessMenuActivity extends BaseActivity {

    PreviewFragment previewFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endless_menu);

        previewFragment = new PreviewFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, previewFragment)
                .commit();

        final Spinner difficultySpinner = (Spinner) findViewById(R.id.difficulty_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        final EditText spriteNumberField = (EditText) findViewById(R.id.numberSprites_edit);
        spriteNumberField.setText("50");

        Button confirmButton = (Button) findViewById(R.id.endless_confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyObservers(Message.Event.STARTGAME);
                Intent intent = new Intent(getApplicationContext(), BaseGameActivity.class);
                intent.putExtra("Difficulty", String.valueOf(difficultySpinner.getSelectedItemId()));
                intent.putExtra("SpriteNumber", String.valueOf(String.valueOf(spriteNumberField.getText())));
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onPause() {
        super.onPause();
        notifyObservers(Message.Event.PAUSED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        notifyObservers(Message.Event.MENU);
        ((BaseGameView) previewFragment.getView()).getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                SurfaceHolder surfaceHolder = ((BaseGameView) previewFragment.getView()).getHolder();
                Canvas canvas = surfaceHolder.lockCanvas();
                ((BaseGameView) previewFragment.getView()).init();
                ((BaseGameView) previewFragment.getView()).renderBackground(canvas);
                surfaceHolder.getSurface().unlockCanvasAndPost(canvas);

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }
}
