package com.example.qingjiaxu.rotatetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ConversionActivity extends AppCompatActivity implements View.OnClickListener{

    protected TextView result;
    protected TextView input;
    float t1 = 1, t2 = 1;
    double inputNum = 0;
    float resultNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        Button button_backspace = findViewById(R.id.button_backspace);
        Button button_all_clear = findViewById(R.id.button_all_clear);
        Button button_seven = findViewById(R.id.button_seven);
        Button button_eight = findViewById(R.id.button_eight);
        Button button_nine = findViewById(R.id.button_nine);
        Button button_four = findViewById(R.id.button_four);
        Button button_five = findViewById(R.id.button_five);
        Button button_six = findViewById(R.id.button_six);
        Button button_one = findViewById(R.id.button_one);
        Button button_two = findViewById(R.id.button_two);
        Button button_three = findViewById(R.id.button_three);
        Button button_zero = findViewById(R.id.button_zero);
        Button button_point = findViewById(R.id.button_point);
        Button button_equal = findViewById(R.id.button_equal);

        button_backspace.setOnClickListener(this);
        button_all_clear.setOnClickListener(this);
        button_seven.setOnClickListener(this);
        button_eight.setOnClickListener(this);
        button_nine.setOnClickListener(this);
        button_four.setOnClickListener(this);
        button_five.setOnClickListener(this);
        button_six.setOnClickListener(this);
        button_one.setOnClickListener(this);
        button_two.setOnClickListener(this);
        button_three.setOnClickListener(this);
        button_zero.setOnClickListener(this);
        button_point.setOnClickListener(this);
        button_equal.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.unit, android.R.layout.simple_dropdown_item_1line);

        result = findViewById(R.id.result);
        input = findViewById(R.id.input);


        Spinner s1 = findViewById(R.id.spinner1);
        s1.setAdapter(adapter1);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Spinner", "being chose");
                String itemNumber = ConversionActivity.this.getResources().getStringArray(R.array.unit)[position];
                if (itemNumber.equals("千米km"))
                    t1 = 100000;
                else if (itemNumber.equals("米m"))
                    t1 = 100;
                else if (itemNumber.equals("分米dm"))
                    t1 = 10;
                else if (itemNumber.equals("厘米cm"))
                    t1 = 1;
                else if (itemNumber.equals("毫米mm"))
                    t1 = 0.1f;
                else if (itemNumber.equals("微米μm"))
                    t1 = 0.0001f;
                else if (itemNumber.equals("纳米nm"))
                    t1 = 0.0000001f;
                resultNum = (float)inputNum * t1 / t2;
                result.setText("" + resultNum);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("Spinner", "not being chose");
            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.unit, android.R.layout.simple_dropdown_item_1line);

        Spinner s2 = findViewById(R.id.spinner2);
        s2.setAdapter(adapter2);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Spinner", "be chose");
                String itemNumber = ConversionActivity.this.getResources().getStringArray(R.array.unit)[position];
                if (itemNumber.equals("千米km"))
                    t2 = 100000;
                else if (itemNumber.equals("米m"))
                    t2 = 100;
                else if (itemNumber.equals("分米dm"))
                    t2 = 10;
                else if (itemNumber.equals("厘米cm"))
                    t2 = 1;
                else if (itemNumber.equals("毫米mm"))
                    t2 = 0.1f;
                else if (itemNumber.equals("微米μm"))
                    t2 = 0.0001f;
                else if (itemNumber.equals("纳米nm"))
                    t2 = 0.0000001f;
                resultNum = (float)inputNum * t1 / t2;
                result.setText("" + resultNum);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("Spinner", "not being chose");
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_backspace: {
                input.setText(input.getText().subSequence(0, input.getText().length() - 1));
            }
            break;

            case R.id.button_all_clear: {
                input.setText("");
                result.setText("");
            }
            break;

            case R.id.button_seven: {
                input.append("7");
            }
            break;

            case R.id.button_eight: {
                input.append("8");
            }
            break;

            case R.id.button_nine: {
                input.append("9");
            }
            break;

            case R.id.button_four: {
                input.append("4");
            }
            break;

            case R.id.button_five: {
                input.append("5");
            }
            break;

            case R.id.button_six: {
                input.append("6");
            }
            break;

            case R.id.button_one: {
                input.append("1");
            }
            break;

            case R.id.button_two: {
                input.append("2");
            }
            break;

            case R.id.button_three: {
                input.append("3");
            }
            break;

            case R.id.button_zero: {
                input.append("0");
            }
            break;

            case R.id.button_point: {
                input.append(".");
            }
            break;

            case R.id.button_equal: {
                if (!input.getText().toString().equals(""))
                    inputNum = Double.parseDouble(input.getText().toString());
                else inputNum = 0;
                resultNum = (float)inputNum * t1 / t2;
                result.setText("" + resultNum);
            }
            break;

            default:break;
        }

    }

}
