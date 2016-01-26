package com.example.netyaco_.projecte_jedi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        //Intent intent = new Intent(getApplicationContext(), Activity2.class);
        //startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("Estic en ", "estat stop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("Estic en ", "estat start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("Estic en ", "estat resume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("Estic en ", "estat destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("Estic en ", "estat pause");
    }
}
