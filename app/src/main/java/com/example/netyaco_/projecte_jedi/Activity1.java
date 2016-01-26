package com.example.netyaco_.projecte_jedi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        Intent intent = new Intent(getApplicationContext(), Activity2.class);
        startActivity(intent);
    }
}
