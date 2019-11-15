package com.yw.formatlogcat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //使用的时候
    private void userLogging() {
        RetrofitManager.getInstance().getRetrofit();
    }
}
