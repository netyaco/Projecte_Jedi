package com.example.netyaco_.projecte_jedi;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registre extends AppCompatActivity implements View.OnClickListener{

    EditText et_direccio;
    Button bt_ok;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        et_direccio = (EditText) findViewById(R.id.et_direccio);
        bt_ok = (Button) findViewById(R.id.bt_registre_ok);

        bt_ok.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();

        user = bundle.getString("user");
        //Integer punt = bundle.getInt("puntuacio");
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_registre_ok:
                DbHelper dbHelper = new DbHelper(this);
                dbHelper.update_address(user, et_direccio.getText().toString());
                Cursor c = dbHelper.getUser(user);
                c.moveToFirst();
                Bundle bundle = new Bundle();
                bundle.putString("user", user);
                bundle.putInt("points",
                        c.getInt(c.getColumnIndex(dbHelper.CN_POINTS)));
                bundle.putString("direccio",
                        c.getString(c.getColumnIndex(dbHelper.CN_ADDRESS)));
                intent = new Intent(getApplicationContext(), Perfil_usuari.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
