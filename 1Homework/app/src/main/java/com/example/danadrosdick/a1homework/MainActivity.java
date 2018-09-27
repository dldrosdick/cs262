package com.example.danadrosdick.a1homework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    EditText Input1, Input2;
    Spinner Operators;
    Button Calculate;
    TextView Result;

    int result_num;
    int num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Operators = (Spinner) findViewById(R.id.Operators);
        Input1 =    (EditText)findViewById(R.id.Input1);
        Input2 =    (EditText)findViewById(R.id.Input2);
        Result =    (TextView)findViewById(R.id.Result);

        Button button= (Button) findViewById(R.id.Calculate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(Input1.getText().toString());
                num2 = Integer.parseInt(Input2.getText().toString());
                if (Operators.getSelectedItem().toString().equals("+")) {
                    result_num = num1 + num2;
                }
                else if (Operators.getSelectedItem().toString().equals("-")) {
                    result_num = num1 - num2;
                }
                else if (Operators.getSelectedItem().toString().equals("/")) {
                    result_num = num1 / num2;
                }
                else if (Operators.getSelectedItem().toString().equals("*")) {
                    result_num = num1 * num2;
                }

                Result.setText(String.valueOf(result_num));
            }
        });
    }
}
