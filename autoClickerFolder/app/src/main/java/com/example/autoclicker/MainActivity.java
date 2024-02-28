package com.example.autoclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.button2);

        btn1.setOnClickListener(v -> {
            simulateTouch(100, 200);
        });
    }

    void simulateTouch(float x, float y) {
        int action = MotionEvent.ACTION_DOWN;
        long downTime = System.currentTimeMillis();
        long eventTime = System.currentTimeMillis();

        MotionEvent motionEvent = MotionEvent.obtain(
                downTime,
                eventTime,
                action,
                x,
                y,
                0
        );

        getWindow().getDecorView().dispatchTouchEvent(motionEvent);
        motionEvent.recycle();
    }

    // miscellaneous function, im just testing the KeyEvent
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            simulateTouch(100, 200);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}