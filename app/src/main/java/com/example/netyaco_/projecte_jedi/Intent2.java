package com.example.netyaco_.projecte_jedi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Intent2 extends AppCompatActivity implements View.OnClickListener{

    TextView tv;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent2);

        tv = (TextView) findViewById(R.id.tv_text);
        bt = (Button) findViewById(R.id.bt_tornar);

        bt.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();

        String s = bundle.getString("text");

        tv.setText(s);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_tornar:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
