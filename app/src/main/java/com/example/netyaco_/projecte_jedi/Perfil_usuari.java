package com.example.netyaco_.projecte_jedi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Perfil_usuari extends AppCompatActivity implements View.OnClickListener{

    TextView tv_user, tv_punt, tv_direccio;
    Button bt_canvi, bt_logout;
    public SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuari);

        tv_user = (TextView) findViewById(R.id.tv_user_perfil);
        tv_punt = (TextView) findViewById(R.id.tv_puntuacio_perfil);
        tv_direccio = (TextView) findViewById(R.id.tv_direccio_perfil);
        bt_canvi = (Button) findViewById(R.id.bt_canvi);
        bt_logout = (Button) findViewById(R.id.bt_logout);

        bt_logout.setOnClickListener(this);
        bt_canvi.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();

        String user = bundle.getString("user");
        Integer punt = bundle.getInt("puntuacio");
        String direccio = bundle.getString("direccio");

        tv_user.setText(user.toString());
        tv_punt.setText(punt.toString());
        tv_direccio.setText(direccio.toString());

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_canvi:
                //intent = new Intent(getApplicationContext(), Calculadora.class);
                //startActivity(intent);
                //finish();
                break;
            case R.id.bt_logout:
                SharedPreferences pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("user", null);
                editor.commit();
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }

    }
}
