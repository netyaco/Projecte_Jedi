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
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_calc:
                intent = new Intent(getApplicationContext(), Calculadora.class);
                startActivity(intent);
                //finish();
                break;
            case R.id.bt_memory:
                intent = new Intent(getApplicationContext(), Memory.class);
                startActivity(intent);
                //finish();
                break;
            case R.id.bt_ranking:
                intent = new Intent(getApplicationContext(), Ranking.class);
                startActivity(intent);
                //finish();
                break;
            case R.id.bt_player:
                intent = new Intent(getApplicationContext(), Player.class);
                startActivity(intent);
                break;
            case R.id.bt_perfil:
                SharedPreferences pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
                String user_res = pref.getString("user", null);
                if (user_res == null) {
                    intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                } else {
                    DbHelper dbHelper = new DbHelper(this);
                    Cursor c = dbHelper.getUser(user_res);
                    c.moveToFirst();
                    Bundle bundle = new Bundle();
                    bundle.putString("user", user_res);
                    bundle.putInt("puntuacio", c.getInt(c.getColumnIndex(dbHelper.CN_POINTS)));
                    bundle.putInt("ranking", 0);

                    SharedPreferences.Editor editor = pref.edit();

                    editor.putString("user", user_res);
                    editor.commit();

                    intent = new Intent(getApplicationContext(), Perfil_usuari.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    //Intent intent = new Intent(getApplicationContext(), Perfil_usuari.class);
                    //startActivity(intent);
                    //finish();
                    //return;
                }


                break;
            case R.id.bt_proves:
                break;
            default:
                break;
        }
    }
}
