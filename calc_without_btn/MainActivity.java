package com.sg.practice;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextNum1;
    EditText editTextNum2;
    EditText editTextOperator;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNum1 = findViewById(R.id.editTextNum1);
        editTextNum2 = findViewById(R.id.editTextNum2);
        editTextOperator = findViewById(R.id.editTextOperator);
        textViewResult = findViewById(R.id.textViewResult);

        // Add a text watcher to listen for changes in EditText fields
        editTextNum1.addTextChangedListener(textWatcher);
        editTextNum2.addTextChangedListener(textWatcher);
        editTextOperator.addTextChangedListener(textWatcher);
    }

    final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void afterTextChanged(android.text.Editable editable) {
            calculateResult();
        }
    };

    private void calculateResult() {
        String num1Str = editTextNum1.getText().toString().trim();
        String num2Str = editTextNum2.getText().toString().trim();
        String operator = editTextOperator.getText().toString().trim();

        if (!TextUtils.isEmpty(num1Str) && !TextUtils.isEmpty(num2Str) && !TextUtils.isEmpty(operator)) {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textViewResult.setText("Cannot divide by zero");
                        return;
                    }
                    break;
                default:
                    textViewResult.setText("Invalid operator");
                    return;
            }

            textViewResult.setText(String.valueOf(result));
        } else {
            textViewResult.setText("");
        }
    }
}
