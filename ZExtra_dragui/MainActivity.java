package com.sg.practice;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private int offsetX, offsetY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        // Set touch listener for the button
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Get the initial touch position relative to the button's top-left corner
                        offsetX = (int) event.getRawX() - button.getLeft();
                        offsetY = (int) event.getRawY() - button.getTop();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // Update the button's position based on the current touch position
                        int newX = (int) event.getRawX() - offsetX;
                        int newY = (int) event.getRawY() - offsetY;
                        updateButtonPosition(newX, newY);
                        break;
                }
                return true;
            }
        });
    }

    private void updateButtonPosition(int x, int y) {
        // Get the layout parameters of the button
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) button.getLayoutParams();

        // Update the position
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;
        button.setLayoutParams(layoutParams);
    }
}
