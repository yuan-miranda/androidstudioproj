package com.example.dvdscreensaverripoff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();
    ImageView imageView;
    int screenWidth, screenHeight;
    int xPosition, yPosition;
    int xDirection = 1;
    int yDirection = 1;

    int speed = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        moveImage();
    }

    void moveImage() {
        xPosition += speed * xDirection;
        yPosition += speed * yDirection;

//         speed is non-negative
        if (speed > 0) {
//        LEFT
            if (xPosition <= 0) {
                xPosition = 0;
                xDirection = 1;
//        RIGHT
            } else if (xPosition >= screenWidth - imageView.getWidth()) {
                xPosition = screenWidth - imageView.getWidth();
                xDirection = -1;
            }

//        TOP
            if (yPosition <= 0) {
                yPosition = 0;
                yDirection = 1;
//        BOTTOM
            } else if (yPosition >= screenHeight - imageView.getHeight()) {
                yPosition = screenHeight - imageView.getHeight();
                yDirection = -1;
            }
        }
        else {
//        LEFT
            if (xPosition <= 0) {
                xPosition = 0;
                xDirection = -1;
//        RIGHT
            } else if (xPosition >= screenWidth - imageView.getWidth()) {
                xPosition = screenWidth - imageView.getWidth();
                xDirection = 1;
            }

//        TOP
            if (yPosition <= 0) {
                yPosition = 0;
                yDirection = -1;
//        BOTTOM
            } else if (yPosition >= screenHeight - imageView.getHeight()) {
                yPosition = screenHeight - imageView.getHeight();
                yDirection = 1;
            }

        }

        imageView.setX(xPosition);
        imageView.setY(yPosition);

        handler.postDelayed(this::moveImage, 0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        TextView displaySpeed = findViewById(R.id.displaySpeed);
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            speed += 1;
            displaySpeed.setText("speed = " + speed);
            return true;
        }
        else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            speed -= 1;
            displaySpeed.setText("speed = " + speed);
            return true;
        }
        return super.onKeyDown(keyCode, keyEvent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}