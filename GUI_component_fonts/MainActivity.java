package com.sg.practice;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    TextView tvResult;
    EditText editText1, editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tvResult);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
    }
    public void addNumber(View v) {
        double n1, n2, result;
        n1 = Double.parseDouble(editText1.getText().toString());
        n2 = Double.parseDouble(editText2.getText().toString());
        result = n1 + n2;
        tvResult.setText(String.valueOf(result));
    }
}