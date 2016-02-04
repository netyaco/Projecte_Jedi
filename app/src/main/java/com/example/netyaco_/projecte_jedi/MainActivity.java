package com.example.netyaco_.projecte_jedi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt1, bt2, bt3, bt4, bt5, bt6;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    SharedPreferences pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
    String user_res = pref.getString("user", null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();

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

    private void setUpViews() {
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navview);

        //Initializing DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout)
        ;
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    case R.id.profile:
                        Toast.makeText(getApplicationContext(), "Profile Selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.memory:
                        Toast.makeText(getApplicationContext(), "Memory Selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.ranking:
                        Toast.makeText(getApplicationContext(), "Ranking Selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.player:
                        Toast.makeText(getApplicationContext(), "Player Selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.calculator:
                        Toast.makeText(getApplicationContext(), "Calculator Selected", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainactivity_menu, menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        //return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String user_res = pref.getString("user", null);
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_calc:
                //intent = new Intent(getApplicationContext(), Calculadora.class);
                //startActivity(intent);
                break;
            case R.id.bt_memory:
                /*if (user_res == null) {
                    Toast.makeText(getApplicationContext(), "Cap usuari registrat",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    DbHelper dbHelper = new DbHelper(this);
                    Cursor c = dbHelper.getUser(user_res);
                    c.moveToFirst();
                    String aux = c.getString(c.getColumnIndex(dbHelper.CN_USER));
                    Toast.makeText(getApplicationContext(), "Sort, "+ aux,
                            Toast.LENGTH_LONG).show();
                }
                intent = new Intent(getApplicationContext(), Memory3.class);
                startActivity(intent);*/
                break;
            case R.id.bt_ranking:
                //intent = new Intent(getApplicationContext(), Ranking.class);
                //startActivity(intent);
                break;
            case R.id.bt_player:
                //intent = new Intent(getApplicationContext(), Player.class);
                //startActivity(intent);
                break;
            case R.id.bt_perfil:
                /*if (user_res == null) {
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
                    bundle.putString(dbHelper.CN_ADDRESS,
                            c.getString(c.getColumnIndex(dbHelper.CN_ADDRESS)));
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("user", user_res);
                    editor.commit();

                    intent = new Intent(getApplicationContext(), Perfil_usuari.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }*/
                break;
            case R.id.bt_proves:
                //SharedPreferences pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
                /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Segur que vols reiniciar totes les dades");
                alertDialogBuilder.setPositiveButton("¡¡¡Fuego!!!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        SharedPreferences pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
                        //String user_res = pref.getString("user", null);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("user", null);
                        editor.commit();
                        DbHelper dbHelper = new DbHelper(getApplicationContext());
                        dbHelper.resetAll();
                        Toast.makeText(getApplicationContext(),
                                "Reset all. No hi ha volta enrere", Toast.LENGTH_LONG).show();
                    }
                });

                alertDialogBuilder.setNegativeButton("M'ho he pensat millor", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Ben pensat", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();*/

                break;
            default:
                break;
        }
    }
}
