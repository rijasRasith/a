package com.sg.practice;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate(View view) {
        EditText cycle_name = findViewById(R.id.cycletext);
        EditText cycle = findViewById(R.id.cycle);
        TextView cycle_show = findViewById(R.id.cycleview);

        EditText walk_name = findViewById(R.id.walktext);
        EditText walk = findViewById(R.id.walk);
        TextView walk_show = findViewById(R.id.walkview);

        EditText gym_name = findViewById(R.id.gymtext);
        EditText gym = findViewById(R.id.gym);
        TextView gym_show = findViewById(R.id.gymview);

        // Check if any EditText field is empty
        if (cycle_name.getText().toString().isEmpty() || cycle.getText().toString().isEmpty()
                || walk_name.getText().toString().isEmpty() || walk.getText().toString().isEmpty()
                || gym_name.getText().toString().isEmpty() || gym.getText().toString().isEmpty()) {
            // Show a message or handle this scenario according to your app's logic
            return;
        }

        try {
            // Parse the input values
            int cycle_name_int = Integer.parseInt(cycle_name.getText().toString());
            int cycle_int = Integer.parseInt(cycle.getText().toString()) * cycle_name_int;

            int walk_name_int = Integer.parseInt(walk_name.getText().toString());
            int walk_int = Integer.parseInt(walk.getText().toString()) * walk_name_int;

            int gym_name_int = Integer.parseInt(gym_name.getText().toString());
            int gym_int = Integer.parseInt(gym.getText().toString()) * gym_name_int;

            // Set the calculated values
            cycle_show.setText(String.valueOf(cycle_int) + " rps");
            walk_show.setText(String.valueOf(walk_int) + " rps");
            gym_show.setText(String.valueOf(gym_int) + " rps");

            // Calculate and display total expense
            TextView total_show = findViewById(R.id.totalcal);
            int total = gym_int + walk_int + cycle_int;
            total_show.setText("Total Expense: " + String.valueOf(total));
        } catch (NumberFormatException e) {
            // Handle NumberFormatException, usually caused by invalid input
            e.printStackTrace();
            // Show a message or handle this scenario according to your app's logic
        }
    }
}
