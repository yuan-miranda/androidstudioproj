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
        // if xDirection is negative, it would make the image move left.
        // if yDirection is negative, it would make the image move top.
        // and vise versa when its positive (right/bottom).
        xPosition += speed * xDirection;
        yPosition += speed * yDirection;

        // Check if the image touches the screen edges.
        // x's 0 is located at the top left corner of the screen, while y's 0 is at the left top.
        // (0, 0) +----
        //        |

        // If speed is positive
        if (speed >= 1) {
            // Handle collision with left edge.
            if (xPosition <= 0) {
                xPosition = 0;
                xDirection = 1;
            }
            // Handle collision with right edge.
            else if (xPosition >= screenWidth - imageView.getWidth()) {
                xPosition = screenWidth - imageView.getWidth();
                xDirection = -1;
            }
            // Handle collision with top edge.
            if (yPosition <= 0) {
                yPosition = 0;
                yDirection = 1;
            }
            // Handle collision with bottom edge
            else if (yPosition >= screenHeight - imageView.getHeight()) {
                yPosition = screenHeight - imageView.getHeight();
                yDirection = -1;
            }
        }
        // If speed is negative, reverse the logic.
        else if (speed <= -1) {
            if (xPosition <= 0) {
                xPosition = 0;
                xDirection = -1;
            } else if (xPosition >= screenWidth - imageView.getWidth()) {
                xPosition = screenWidth - imageView.getWidth();
                xDirection = 1;
            }
            if (yPosition <= 0) {
                yPosition = 0;
                yDirection = -1;
            } else if (yPosition >= screenHeight - imageView.getHeight()) {
                yPosition = screenHeight - imageView.getHeight();
                yDirection = 1;
            }
        } else {
            // do nothing lol
        }

        imageView.setX(xPosition);
        imageView.setY(yPosition);

        // delay the movement of the image if needed.
        handler.postDelayed(this::moveImage, 0);
    }

    // handle the volume key.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        TextView displaySpeed = findViewById(R.id.displaySpeed);
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            speed += 1;
            displaySpeed.setText("speed = " + speed);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            speed -= 1;
            displaySpeed.setText("speed = " + speed);
            return true;
        }
        return super.onKeyDown(keyCode, keyEvent);
    }
}