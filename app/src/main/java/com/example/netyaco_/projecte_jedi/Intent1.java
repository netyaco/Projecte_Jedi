package com.example.netyaco_.projecte_jedi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Intent1 extends AppCompatActivity implements View.OnClickListener{

    EditText et;
    Button bt;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent1);

        et = (EditText) findViewById(R.id.et_text);
        bt = (Button) findViewById(R.id.bt_next);

        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_next:
                bundle = new Bundle();
                bundle.putString("text",et.getText().toString());
                Intent intent = new Intent(getApplicationContext(), Intent2.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
