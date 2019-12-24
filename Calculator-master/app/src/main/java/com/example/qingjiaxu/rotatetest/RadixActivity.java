package com.example.qingjiaxu.rotatetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RadixActivity extends AppCompatActivity {

    private EditText binaryText;

    private EditText octalText;

    private EditText decimalText;

    private EditText hexadecimalText;

    private Button submitBtn;

    private Button clearBtn;

    private String binary = "", octal = "", decimal = "", hexadecimal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radix);

        findViews();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = validateCheck();

                if (result == -1)
                    Toast.makeText(RadixActivity.this, "请检查一下您的输入", Toast.LENGTH_SHORT).show();
                else {
                    compute(result);
                    display();
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
    }

    private void findViews() {
        binaryText = findViewById(R.id.binary);
        octalText = findViewById(R.id.octal);
        decimalText = findViewById(R.id.decimal);
        hexadecimalText = findViewById(R.id.hexadecimal);

        submitBtn = findViewById(R.id.submit);
        clearBtn = findViewById(R.id.clear);
    }

    private int validateCheck() {
        if (!binaryText.getText().toString().isEmpty() && octalText.getText().toString().isEmpty()
                && decimalText.getText().toString().isEmpty() && hexadecimalText.getText().toString().isEmpty()) {
            binary = binaryText.getText().toString();
            if (binary.matches("[01]*"))
                return 2;
        }
        else if (binaryText.getText().toString().isEmpty() && !octalText.getText().toString().isEmpty()
                && decimalText.getText().toString().isEmpty() && hexadecimalText.getText().toString().isEmpty()) {
            octal = octalText.getText().toString();
            if (octal.matches("[01234567]*"))
                return 8;
        }
        else if (binaryText.getText().toString().isEmpty() && octalText.getText().toString().isEmpty()
                && !decimalText.getText().toString().isEmpty() && hexadecimalText.getText().toString().isEmpty()) {
            decimal = decimalText.getText().toString();
            if (decimal.matches("[0123456789]*"))
                return 10;
        }

        else if (binaryText.getText().toString().isEmpty() && octalText.getText().toString().isEmpty()
                && decimalText.getText().toString().isEmpty() && !hexadecimalText.getText().toString().isEmpty()) {
            hexadecimal = hexadecimalText.getText().toString();
            if (hexadecimal.matches("[0123456789abcdefABCDEF]*"))
                return 16;
        }
        return -1;
    }

    private void compute(int source) {

        switch (source) {
            case 2:

                decimal = Integer.valueOf(binary,2).toString();
                octal = Integer.toOctalString(Integer.parseInt(decimal));
                hexadecimal = Integer.toHexString(Integer.parseInt(decimal));
                break;
            case 8:

                decimal = Integer.valueOf(octal, 8).toString();
                binary = Integer.toBinaryString(Integer.parseInt(decimal));
                hexadecimal = Integer.toHexString(Integer.parseInt(decimal));
                break;
            case 10:

                binary = Integer.toBinaryString(Integer.parseInt(decimal));
                octal = Integer.toOctalString(Integer.parseInt(decimal));
                hexadecimal = Integer.toHexString(Integer.parseInt(decimal));
                break;
            case 16:

                decimal = Integer.valueOf(hexadecimal, 16).toString();
                binary = Integer.toBinaryString(Integer.parseInt(decimal));
                octal = Integer.toOctalString(Integer.parseInt(decimal));
                break;
        }
    }

    private void display() {
        binaryText.setText(binary);
        octalText.setText(octal);
        decimalText.setText(decimal);
        hexadecimalText.setText(hexadecimal);
    }

    private void init() {
        binary = "";
        octal = "";
        decimal = "";
        hexadecimal = "";
        binaryText.setText("");
        octalText.setText("");
        decimalText.setText("");
        hexadecimalText.setText("");
    }
}
