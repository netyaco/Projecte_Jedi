package com.example.netyaco_.projecte_jedi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("log_tag", "Log_verbose");
        Log.d("log_tag", "Log_debug");
        Log.e("log_tag", "Log_error");
        Log.wtf("log_tag", "Log_wtf");
        Log.i("log_tag", "Log_information");
        Log.w("log_tag", "Log_warning");
    }
}
