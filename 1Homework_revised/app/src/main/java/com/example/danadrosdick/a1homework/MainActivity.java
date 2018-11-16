package com.example.danadrosdick.a1homework;

/* this main activity method handles the on click handler and performs the proper calculation */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private EditText Input1;
    private EditText Input2;
    private Spinner Operators;
    Button Calculate;
    private TextView Result;

    private int result_num;
    private int num1;
    private int num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Operators = findViewById(R.id.Operators);
        Input1 = findViewById(R.id.Input1);
        Input2 = findViewById(R.id.Input2);
        Result = findViewById(R.id.Result);

        Button button= findViewById(R.id.Calculate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(Input1.getText().toString());
                num2 = Integer.parseInt(Input2.getText().toString());
                switch (Operators.getSelectedItem().toString()) {
                    case "+":
                        result_num = num1 + num2;
                        break;
                    case "-":
                        result_num = num1 - num2;
                        break;
                    case "/":
                        result_num = num1 / num2;
                        break;
                    case "*":
                        result_num = num1 * num2;
                        break;
                }

                Result.setText(String.valueOf(result_num));
            }
        });
    }
}
