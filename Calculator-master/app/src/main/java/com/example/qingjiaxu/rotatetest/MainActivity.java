package com.example.qingjiaxu.rotatetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    protected TextView inputText;
    protected TextView expression;
    protected TextView outputText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.text_input);
        expression = findViewById(R.id.expression);
        outputText = findViewById(R.id.text_output);
        expression.setVisibility(View.INVISIBLE);
        Button button_exp = findViewById(R.id.button_exponent);
        Button button_power = findViewById(R.id.button_power);
        Button button_percent = findViewById(R.id.button_percent);
        Button button_mod = findViewById(R.id.button_mod);
        Button button_backspace = findViewById(R.id.button_backspace);
        Button button_all_clear = findViewById(R.id.button_all_clear);
        Button button_radical = findViewById(R.id.button_radical);
        Button button_sin = findViewById(R.id.button_sin);
        Button button_cos = findViewById(R.id.button_cos);
        Button button_tan = findViewById(R.id.button_tan);
        Button button_divide = findViewById(R.id.button_divide);
        Button button_log = findViewById(R.id.button_log);
        Button button_seven = findViewById(R.id.button_seven);
        Button button_eight = findViewById(R.id.button_eight);
        Button button_nine = findViewById(R.id.button_nine);
        Button button_multiply = findViewById(R.id.button_multiply);
        Button button_ln = findViewById(R.id.button_ln);
        Button button_four = findViewById(R.id.button_four);
        Button button_five = findViewById(R.id.button_five);
        Button button_six = findViewById(R.id.button_six);
        Button button_minus = findViewById(R.id.button_minus);
        Button button_factorial = findViewById(R.id.button_factorial);
        Button button_one = findViewById(R.id.button_one);
        Button button_two = findViewById(R.id.button_two);
        Button button_three = findViewById(R.id.button_three);
        Button button_plus = findViewById(R.id.button_plus);
        Button button_left_bracket = findViewById(R.id.button_left_bracket);
        Button button_right_bracket = findViewById(R.id.button_right_bracket);
        Button button_zero = findViewById(R.id.button_zero);
        Button button_point = findViewById(R.id.button_point);
        Button button_equal = findViewById(R.id.button_equal);

        button_exp.setOnClickListener(this);
        button_power.setOnClickListener(this);
        button_percent.setOnClickListener(this);
        button_mod.setOnClickListener(this);
        button_backspace.setOnClickListener(this);
        button_all_clear.setOnClickListener(this);
        button_radical.setOnClickListener(this);
        button_sin.setOnClickListener(this);
        button_cos.setOnClickListener(this);
        button_tan.setOnClickListener(this);
        button_divide.setOnClickListener(this);
        button_log.setOnClickListener(this);
        button_seven.setOnClickListener(this);
        button_eight.setOnClickListener(this);
        button_nine.setOnClickListener(this);
        button_multiply.setOnClickListener(this);
        button_ln.setOnClickListener(this);
        button_four.setOnClickListener(this);
        button_five.setOnClickListener(this);
        button_six.setOnClickListener(this);
        button_minus.setOnClickListener(this);
        button_factorial.setOnClickListener(this);
        button_one.setOnClickListener(this);
        button_two.setOnClickListener(this);
        button_three.setOnClickListener(this);
        button_plus.setOnClickListener(this);
        button_left_bracket.setOnClickListener(this);
        button_right_bracket.setOnClickListener(this);
        button_zero.setOnClickListener(this);
        button_point.setOnClickListener(this);
        button_equal.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.unit_conversion:
                //Toast.makeText(this, "You click Add", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(MainActivity.this, ConversionActivity.class);
                startActivity(intent1);
                break;
            case R.id.radix_conversion:
                Intent intent2 = new Intent(MainActivity.this, RadixActivity.class);
                startActivity(intent2);
                break;
            case R.id.currency_conversion:
                Intent intent3 = new Intent(MainActivity.this, CurrencyActivity.class);
                startActivity(intent3);
                break;
            default:
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_exponent: {
                inputText.append("E");
                expression.append("E");
            }
            break;

            case R.id.button_power: {
                inputText.append("^");
                expression.append("^");
            }
            break;

            case R.id.button_percent: {
                inputText.append("%");
                expression.append("÷100");
            }
            break;

            case R.id.button_mod: {
                inputText.append("mod");
                expression.append("m");
            }
            break;

            case R.id.button_backspace: {
                inputText.setText(inputText.getText().subSequence(0, inputText.getText().length() - 1));
                expression.setText(expression.getText().subSequence(0, expression.getText().length() - 1));
            }
            break;

            case R.id.button_all_clear: {
                inputText.setText("");
                expression.setText("");
                outputText.setText("");
            }
            break;

            case R.id.button_radical: {
                inputText.append("√");
                expression.append("√");
            }
            break;

            case R.id.button_sin: {
                inputText.append("sin");
                expression.append("s");
            }
            break;

            case R.id.button_cos: {
                inputText.append("cos");
                expression.append("c");
            }
            break;

            case R.id.button_tan: {
                inputText.append("tan");
                expression.append("t");
            }
            break;

            case R.id.button_divide: {
                inputText.append("÷");
                expression.append("÷");
            }
            break;

            case R.id.button_log: {
                inputText.append("log");
                expression.append("g");
            }
            break;

            case R.id.button_seven: {
                inputText.append("7");
                expression.append("7");
            }
            break;

            case R.id.button_eight: {
                inputText.append("8");
                expression.append("8");
            }
            break;

            case R.id.button_nine: {
                inputText.append("9");
                expression.append("9");
            }
            break;

            case R.id.button_multiply: {
                inputText.append("×");
                expression.append("×");
            }
            break;

            case R.id.button_ln: {
                inputText.append("ln");
                expression.append("l");
            }
            break;

            case R.id.button_four: {
                inputText.append("4");
                expression.append("4");
            }
            break;

            case R.id.button_five: {
                inputText.append("5");
                expression.append("5");
            }
            break;

            case R.id.button_six: {
                inputText.append("6");
                expression.append("6");
            }
            break;

            case R.id.button_minus: {
                inputText.append("-");
                expression.append("-");
            }
            break;

            case R.id.button_factorial: {
                inputText.append("!");
                expression.append("!");
            }
            break;

            case R.id.button_one: {
                inputText.append("1");
                expression.append("1");
            }
            break;

            case R.id.button_two: {
                inputText.append("2");
                expression.append("2");
            }
            break;

            case R.id.button_three: {
                inputText.append("3");
                expression.append("3");
            }
            break;

            case R.id.button_plus: {
                inputText.append("+");
                expression.append("+");
            }
            break;

            case R.id.button_left_bracket: {
                inputText.append("(");
                expression.append("(0");
            }
            break;

            case R.id.button_right_bracket: {
                inputText.append(")");
                expression.append(")");
            }
            break;

            case R.id.button_zero: {
                inputText.append("0");
                expression.append("0");
            }
            break;

            case R.id.button_point: {
                inputText.append(".");
                expression.append(".");
            }
            break;

            case R.id.button_equal: {
                outputText.setText(Calc.process(expression.getText().toString(), true));
            }
            break;

            default:break;
        }

    }
}
