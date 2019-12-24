package com.example.qingjiaxu.rotatetest;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CurrencyActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText sourceValueText;

    private EditText destinationValueText;

    private ImageButton copyBtn;

    private ClipboardManager myClipboard;

    private ClipData myClip;

    private TextView sourceCurrencyText;

    private TextView destinationCurrencyText;

    private ImageButton sourceBtn;

    private ImageButton destinationBtn;

    private Button clearBtn;

    private Button submitBtn;

    private static final String[] CURRENCY = {"CNY人民币", "EUR欧元", "GBP英镑", "HKD港币", "JPY日元", "KRW韩国元",
            "MOP澳门币", "RUB俄罗斯卢布", "TWD台币", "USD美元", "XAU黄金"};

    private ArrayAdapter<String> adapter;

    private static final String TAG = "Main9Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        findViews();

        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, CURRENCY);

        onClickListener();


    }

    private void findViews() {
        sourceValueText = findViewById(R.id.source_value);
        sourceCurrencyText = findViewById(R.id.source_currency);
        sourceBtn = findViewById(R.id.source_button);
        destinationValueText = findViewById(R.id.destination_value);
        copyBtn = findViewById(R.id.copy_button);
        destinationCurrencyText = findViewById(R.id.destination_currency);
        destinationBtn = findViewById(R.id.destination_button);
        clearBtn = findViewById(R.id.clear_button);
        submitBtn = findViewById(R.id.submit_button);
    }

    private void onClickListener() {
        sourceBtn.setOnClickListener(this);
        copyBtn.setOnClickListener(this);
        destinationBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.source_button:
                View sourceView = LayoutInflater.from(this).inflate(R.layout.list_layout, null);

                AlertDialog.Builder sourceBuilder = new AlertDialog.Builder(this);
                sourceBuilder.setTitle("请选择源币种");
                sourceBuilder.setView(sourceView);

                final AlertDialog sourceDialog = sourceBuilder.show();

                sourceDialog.getWindow().setLayout(800, 1000);
                ListView sourceListView = sourceView.findViewById(R.id.currency_list);
                sourceListView.setAdapter(adapter);
                sourceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(CurrencyActivity.this, CURRENCY[position], Toast.LENGTH_SHORT).show();
                        sourceCurrencyText.setText(CURRENCY[position]);
                        sourceDialog.dismiss();
                    }
                });

                break;

            case R.id.copy_button:
                if (destinationValueText.getText().toString().isEmpty())
                    Toast.makeText(this, "没有内容可以复制", Toast.LENGTH_SHORT).show();
                else {
                    String savedMessage = destinationValueText.getText().toString() + destinationCurrencyText.getText().toString()
                            + "(" + sourceValueText.getText().toString() + sourceCurrencyText.getText().toString() + ")";
                    myClip = ClipData.newPlainText("text", savedMessage);
                    myClipboard.setPrimaryClip(myClip);
                    Toast.makeText(getApplicationContext(), "复制数据成功",
                            Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.destination_button:
                View destinationView = LayoutInflater.from(this).inflate(R.layout.list_layout, null);

                AlertDialog.Builder destinationBuilder = new AlertDialog.Builder(this);
                destinationBuilder.setTitle("请选择目的币种");
                destinationBuilder.setView(destinationView);

                final AlertDialog destinationDialog = destinationBuilder.show();

                destinationDialog.getWindow().setLayout(800, 1000);
                ListView destinationListView = destinationView.findViewById(R.id.currency_list);
                destinationListView.setAdapter(adapter);
                destinationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(CurrencyActivity.this, CURRENCY[position], Toast.LENGTH_SHORT).show();
                        destinationCurrencyText.setText(CURRENCY[position]);
                        destinationDialog.dismiss();
                    }
                });
                break;


            case R.id.clear_button:
                sourceValueText.setText("");
                destinationValueText.setText("");
                sourceCurrencyText.setText("");
                destinationCurrencyText.setText("");
                break;

            case R.id.submit_button:
                if (valueValidateCheck())
                    if (currencyValidateCheck()) {
                        String source = sourceCurrencyText.getText().toString().substring(0, 3);
                        String destination = destinationCurrencyText.getText().toString().substring(0, 3);
                        requestExchange(source, destination);
                        //Toast.makeText(this, source + destination, Toast.LENGTH_SHORT).show();
                    }
                break;

        }
    }

    private boolean valueValidateCheck(){
        if (sourceValueText.getText().toString().isEmpty()) {
            Toast.makeText(this, "请输入兑换金额", Toast.LENGTH_SHORT).show();
            return false;
        }
//        boolean result1 = !sourceValueText.getText().toString().matches("[0123456789.]*");
//        else if (!sourceValueText.getText().toString().matches("[0123456789.]*")) {
//            Toast.makeText(this, "兑换金额中有非法字符", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }

    private boolean currencyValidateCheck() {
        if (sourceCurrencyText.getText().toString().isEmpty()) {
            Toast.makeText(this, "请选择源币种", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (destinationCurrencyText.getText().toString().isEmpty()) {
            Toast.makeText(this, "请选择目的币种", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }


    public static Exchange handleExchangeResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String exchangeContent = jsonObject.toString();
            return new Gson().fromJson(exchangeContent, Exchange.class);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    public void requestExchange(String source, String destination) {
        String exchangeUrl = "http://api.k780.com/?app=finance.rate&scur=" +
                source +
                "&tcur=" +
                destination +
                "&appkey=38905&sign=42fc5dc7b7f7a804a9fa29fd0454e38f&format=json";

        sendOkHttpRequest(exchangeUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.e(TAG, Log.getStackTraceString(e));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CurrencyActivity.this, "获取汇率信息失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Exchange exchange = handleExchangeResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (exchange != null && "1".equals(exchange.success)) {
                            String message = "汇率:（" + exchange.result.type + "）" + exchange.result.rate
                                    + "\n更新时间: " + exchange.result.update;
//                            Toast.makeText(Main9Activity.this, "获取汇率信息成功", Toast.LENGTH_SHORT).show();
//                            rate = Float.parseFloat(exchange.result.rate);
                            destinationValueText.setText("" + Float.parseFloat(exchange.result.rate) *
                                    Float.parseFloat(sourceValueText.getText().toString()));
                            Toast.makeText(CurrencyActivity.this, message, Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(CurrencyActivity.this, "获取汇率信息失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
