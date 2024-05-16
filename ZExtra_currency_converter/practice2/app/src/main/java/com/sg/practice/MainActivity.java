package com.sg.practice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAmount;
    private Button buttonConvert;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAmount = findViewById(R.id.editTextAmount);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        // Get the amount entered by the user
        String amountStr = editTextAmount.getText().toString();
        if (amountStr.isEmpty()) {
            textViewResult.setText("Please enter an amount.");
            return;
        }

        double amount = Double.parseDouble(amountStr);

        // Conversion rates
        double euroToDollar = 1.18;
        double euroToRupee = 88.61;

        // Convert the amount to Euros
        double euroAmount = amount;
        double dollarAmount = amount * euroToDollar;
        double rupeeAmount = amount * euroToRupee;

        // Display the result
        String result = String.format("%.2f Euros = %.2f Dollars = %.2f Rupees", euroAmount, dollarAmount, rupeeAmount);
        textViewResult.setText(result);
    }
}
