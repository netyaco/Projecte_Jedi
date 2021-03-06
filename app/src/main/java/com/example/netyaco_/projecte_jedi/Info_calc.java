package com.example.netyaco_.projecte_jedi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Info_calc extends AppCompatActivity implements View.OnClickListener{

    Button bt;
    public SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_calc);

        pref = getSharedPreferences("pref", MODE_PRIVATE);

        bt = (Button) findViewById(R.id.bt_calc_info);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_calc_info:
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("calc", "nope");
                editor.commit();
                intent = new Intent(getApplicationContext(), Calculadora.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
