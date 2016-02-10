package com.example.netyaco_.projecte_jedi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Intro extends AppCompatActivity implements View.OnClickListener{

    Button bt, bt2;
    public SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        pref = getSharedPreferences("pref", MODE_PRIVATE);

        bt = (Button) findViewById(R.id.bt_intro_continue);
        bt.setOnClickListener(this);
        bt2 = (Button) findViewById(R.id.bt_treume);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_intro_continue:
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("first", "nope");
                editor.commit();
                intent = new Intent(getApplicationContext(), Info_app.class);
                startActivity(intent);
                finish();
                break;
            case R.id.bt_treume:
                finish();
                break;
            default:
                break;
        }
    }
}
