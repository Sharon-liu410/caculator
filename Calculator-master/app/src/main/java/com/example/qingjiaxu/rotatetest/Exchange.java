package com.example.qingjiaxu.rotatetest;

import com.google.gson.annotations.SerializedName;

public class Exchange {
    //{"success":"1","result":{"status":"ALREADY","scur":"USD","tcur":"CNY","ratenm":"美元/人民币","rate":"6.895200","update":"2018-12-18 20:44:17"}}

    public String success;

    public Result result;

    public class Result{

        @SerializedName("scur")
        public String sourceCurrency;

        @SerializedName("tcur")
        public String destinationCurrency;

        @SerializedName("ratenm")
        public String type;

        public String rate;

        public String update;
    }
}