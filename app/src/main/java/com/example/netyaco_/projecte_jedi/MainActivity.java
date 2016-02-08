package com.example.netyaco_.projecte_jedi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt1, bt2, bt3, bt4, bt5, bt6;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    SharedPreferences pref;
    String user_res, first_init, calc_init;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_res = pref.getString("user", null);
        first_init = pref.getString("first", null);
        calc_init = pref.getString("calc", null);

        if (first_init == null) {
            Intent intent = new Intent(getApplicationContext(), Intro.class);
            startActivity(intent);
            finish();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bt1 = (Button) findViewById(R.id.bt_calc);
        bt2 = (Button) findViewById(R.id.bt_perfil);
        bt3 = (Button) findViewById(R.id.bt_memory);
        bt4 = (Button) findViewById(R.id.bt_ranking);
        bt5 = (Button) findViewById(R.id.bt_player);
        bt6 = (Button) findViewById(R.id.bt_proves);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainactivity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent intent = new Intent(getApplicationContext(), Intro.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        SharedPreferences pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String user_res = pref.getString("user", null);
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_calc:
                if (calc_init == null) {
                    intent = new Intent(getApplicationContext(), Info_calc.class);
                    startActivity(intent);
                }
                else {
                    intent = new Intent(getApplicationContext(), Calculadora.class);
                    startActivity(intent);
                }
                break;
            case R.id.bt_memory:
                if (user_res == null) {
                    Toast.makeText(getApplicationContext(), "Cap usuari registrat",
                            Toast.LENGTH_LONG).show();
                } else {
                    DbHelper dbHelper = new DbHelper(this);
                    Cursor c = dbHelper.getUser(user_res);
                    c.moveToFirst();
                    String aux = c.getString(c.getColumnIndex(dbHelper.CN_USER));
                    Toast.makeText(getApplicationContext(), "Sort, " + aux,
                            Toast.LENGTH_LONG).show();
                }
                intent = new Intent(getApplicationContext(), Memory3.class);
                startActivity(intent);
                break;
            case R.id.bt_ranking:
                intent = new Intent(getApplicationContext(), Ranking.class);
                startActivity(intent);
                break;
            case R.id.bt_player:
                intent = new Intent(getApplicationContext(), Media_player.class);
                startActivity(intent);
                break;
            case R.id.bt_perfil:
                if (user_res == null) {
                    intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                } else {
                    DbHelper dbHelper = new DbHelper(this);
                    Cursor c = dbHelper.getUser(user_res);
                    c.moveToFirst();
                    Bundle bundle = new Bundle();
                    bundle.putString(dbHelper.CN_USER, user_res);
                    bundle.putInt(dbHelper.CN_POINTS,
                            c.getInt(c.getColumnIndex(dbHelper.CN_POINTS)));
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("user", user_res);
                    editor.commit();

                    intent = new Intent(getApplicationContext(), Perfil_usuari.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.bt_proves:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("ATENCIÓ!!!!!! ");
                alertDialogBuilder.setMessage("Segur que vols reiniciar totes les dades???");
                alertDialogBuilder.setPositiveButton("¡¡¡Fuego!!!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        SharedPreferences pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("user", null);
                        editor.putString("first", null);
                        editor.putString("calc", null);
                        editor.commit();
                        DbHelper dbHelper = new DbHelper(getApplicationContext());
                        dbHelper.resetAll();
                        Toast.makeText(getApplicationContext(),
                                "Reset all. No hi ha volta enrere", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

                alertDialogBuilder.setNegativeButton("M'ho he pensat millor", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Ben pensat", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
            default:
                break;
        }
    }
}
