package com.example.netyaco_.projecte_jedi;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private Button btLog, btNew;
    private EditText etUser, etPass;
    private TextInputLayout inputLayoutName, inputLayoutPassword;
    public SharedPreferences pref;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);

        btLog = (Button) findViewById(R.id.bt_login);
        btNew = (Button) findViewById(R.id.bt_new_user);
        etUser = (EditText) findViewById(R.id.et_user);
        etPass = (EditText) findViewById(R.id.et_pass);
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);


        btNew.setOnClickListener(this);
        btLog.setOnClickListener(this);

        dbHelper = new DbHelper(this);
    }


    public void newUser (View v) {
        if (etUser.getText().toString().isEmpty()) {
            inputLayoutName.setErrorEnabled(true);
            inputLayoutName.setError("Has d'introduïr un nom");
        }
        else if (etPass.getText().toString().isEmpty()) {
            inputLayoutName.setErrorEnabled(false);
            inputLayoutPassword.setErrorEnabled(true);
            inputLayoutPassword.setError("Has d'introduïr una contrassenya");
        }
        else {
            Cursor c = dbHelper.getUser(String.valueOf(etUser.getText()));
            if (c.moveToFirst()) {
                Toast.makeText(getApplicationContext(), "L'usuari ja exixteix",
                        Toast.LENGTH_LONG).show();
                return;
            }
            ContentValues values = new ContentValues();
            values.put("user", String.valueOf(etUser.getText()));
            values.put("pass", String.valueOf(etPass.getText()));
            values.put("points", 0);
            dbHelper.newUser(values, dbHelper.USER_TABLE);

            Toast.makeText(getApplicationContext(), "Usuari afegit correctament... o no",
                    Toast.LENGTH_LONG).show();
            final Bundle bundle = new Bundle();
            bundle.putString("user", etUser.getText().toString());
            bundle.putInt("puntuacio", 0);
            bundle.putString("pass", etPass.getText().toString());

            SharedPreferences.Editor editor = pref.edit();

            editor.putString("user", etUser.getText().toString());
            editor.commit();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Vols afegir una imatge al teu perfil?");
            alertDialogBuilder.setPositiveButton("Per què no?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    Intent intent = new Intent(getApplicationContext(), Registre.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            });

            alertDialogBuilder.setNegativeButton("Ho faré després", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), Perfil_usuari.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    public void login (View v) {
        if (etUser.getText().toString().isEmpty()) {
            inputLayoutName.setErrorEnabled(true);
            inputLayoutName.setError("Has d'introduïr un nom");
        }
        else if (etPass.getText().toString().isEmpty()) {
            inputLayoutName.setErrorEnabled(false);
            inputLayoutPassword.setErrorEnabled(true);
            inputLayoutPassword.setError("Has d'introduir una contrassenya");
        }
        else {
            Cursor c = dbHelper.getUser(String.valueOf(etUser.getText()));
            if (!c.moveToFirst()) {
                Toast.makeText(getApplicationContext(), "L'usuari no exixteix",
                        Toast.LENGTH_LONG).show();
                return;
            } else {
                if (!c.getString(c.getColumnIndex(dbHelper.CN_PASS)).equals(String.valueOf(etPass.getText()))) {
                    Toast.makeText(getApplicationContext(), "El pass no coincideix",
                            Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(getApplicationContext(), "Login realitzat correctament",
                            Toast.LENGTH_LONG).show();

                    Bundle bundle = new Bundle();
                    bundle.putString(dbHelper.CN_USER, etUser.getText().toString());
                    bundle.putInt(dbHelper.CN_POINTS,
                            c.getInt(c.getColumnIndex(dbHelper.CN_POINTS)));
                    bundle.putString("pass", c.getString(c.getColumnIndex(dbHelper.CN_PASS)));
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("user", etUser.getText().toString());
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), Perfil_usuari.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_new_user:
                newUser(v);
                break;
            case R.id.bt_login:
                login(v);
                break;
            default:
                break;
        }
    }


}
