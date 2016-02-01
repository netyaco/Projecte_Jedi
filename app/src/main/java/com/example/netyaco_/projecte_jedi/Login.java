package com.example.netyaco_.projecte_jedi;

import android.content.ContentValues;
import android.content.Intent;
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
    private EditText etUser, etPass, etMail;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLog = (Button) findViewById(R.id.bt_login);
        btNew = (Button) findViewById(R.id.bt_new_user);
        etUser = (EditText) findViewById(R.id.et_user);
        etPass = (EditText) findViewById(R.id.et_pass);
        //etMail = (EditText) findViewById(R.id.et_email);
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        //inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);


        btNew.setOnClickListener(this);
        btLog.setOnClickListener(this);

        dbHelper = new DbHelper(this);
    }


    public void newUser (View v) {
        if (etUser.getText().toString().isEmpty()) {
            inputLayoutName.setErrorEnabled(true);
            inputLayoutName.setError("You need to enter a name");
        }
        else if (etPass.getText().toString().isEmpty()) {
            inputLayoutName.setErrorEnabled(false);
            inputLayoutPassword.setErrorEnabled(true);
            inputLayoutPassword.setError("You need to enter a pass");
        }
        else {
            ContentValues values = new ContentValues();
            values.put("user", String.valueOf(etUser.getText()));
            values.put("pass", String.valueOf(etPass.getText()));
            Cursor c = dbHelper.getUser(String.valueOf(etUser.getText()));
            if (c.moveToFirst()) {
                Toast.makeText(getApplicationContext(), "L'usuari ja exixteix",
                        Toast.LENGTH_LONG).show();
                return;
            }
            dbHelper.newUser(values, dbHelper.USER_TABLE);

            Toast.makeText(getApplicationContext(), "Usuari afegit correctament... o no",
                    Toast.LENGTH_LONG).show();
            etUser.setText("");
            etPass.setText("");
            Intent intent = new Intent(getApplicationContext(), Perfil_usuari.class);
            startActivity(intent);
            finish();
        }
    }

    public void login (View v) {
        if (etUser.getText().toString().isEmpty()) {
            inputLayoutName.setErrorEnabled(true);
            inputLayoutName.setError("You need to enter a name");
        }
        else if (etPass.getText().toString().isEmpty()) {
            inputLayoutName.setErrorEnabled(false);
            inputLayoutPassword.setErrorEnabled(true);
            inputLayoutPassword.setError("You need to enter a pass");
        }
        else {
            ContentValues values = new ContentValues();
            values.put("user", String.valueOf(etUser.getText()));
            values.put("pass", String.valueOf(etPass.getText()));
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
                    Intent intent = new Intent(getApplicationContext(), Perfil_usuari.class);
                    startActivity(intent);
                    finish();
                    //return;
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_new_user:
                newUser(v);
                //finish();
                break;
            case R.id.bt_login:
                login(v);
                break;
            default:
                break;
        }
    }


}
