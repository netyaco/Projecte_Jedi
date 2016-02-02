package com.example.netyaco_.projecte_jedi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Perfil_usuari extends AppCompatActivity {

    TextView tv_user, tv_punt, tv_rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuari);

        tv_user = (TextView) findViewById(R.id.tv_user_perfil);
        tv_punt = (TextView) findViewById(R.id.tv_puntuacio_perfil);
        tv_rank = (TextView) findViewById(R.id.tv_ranking_perfil);

        Bundle bundle = getIntent().getExtras();

        String user = bundle.getString("user");
        Integer punt = bundle.getInt("puntuacio");
        Integer rank = bundle.getInt("ranking");

        tv_user.setText(user.toString());
        tv_punt.setText(punt.toString());
        tv_rank.setText(rank.toString());

    }
}
