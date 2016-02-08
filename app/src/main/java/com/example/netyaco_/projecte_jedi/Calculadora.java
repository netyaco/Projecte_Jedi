package com.example.netyaco_.projecte_jedi;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

public class Calculadora extends AppCompatActivity implements View.OnClickListener {

    TextView result;
    String s = new String();
    String operacio;
    Double val1, val2, total, ans;
    Button bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10, bt11, bt12, bt13, bt14, bt15;
    Button bt16, bt17, bt18, bt19;
    boolean first = true;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        //setUpViews();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        result = (TextView) findViewById(R.id.tv_result);
        bt0 = (Button) findViewById(R.id.bt_0);
        bt1 = (Button) findViewById(R.id.bt_1);
        bt2 = (Button) findViewById(R.id.bt_2);
        bt3 = (Button) findViewById(R.id.bt_3);
        bt4 = (Button) findViewById(R.id.bt_4);
        bt5 = (Button) findViewById(R.id.bt_5);
        bt6 = (Button) findViewById(R.id.bt_6);
        bt7 = (Button) findViewById(R.id.bt_7);
        bt8 = (Button) findViewById(R.id.bt_8);
        bt9 = (Button) findViewById(R.id.bt_9);
        bt10 = (Button) findViewById(R.id.bt_punt);
        bt11 = (Button) findViewById(R.id.bt_suma);
        bt12 = (Button) findViewById(R.id.bt_resta);
        bt13 = (Button) findViewById(R.id.bt_mult);
        bt14 = (Button) findViewById(R.id.bt_div);
        bt15 = (Button) findViewById(R.id.bt_igual);
        bt16 = (Button) findViewById(R.id.bt_ac);
        bt17 = (Button) findViewById(R.id.bt_del);
        bt18 = (Button) findViewById(R.id.bt_c);
        bt19 = (Button) findViewById(R.id.bt_ans);

        bt0.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        bt10.setOnClickListener(this);
        bt11.setOnClickListener(this);
        bt12.setOnClickListener(this);
        bt13.setOnClickListener(this);
        bt14.setOnClickListener(this);
        bt15.setOnClickListener(this);
        bt16.setOnClickListener(this);
        bt17.setOnClickListener(this);
        bt18.setOnClickListener(this);
        bt19.setOnClickListener(this);

        //val1 = val2 = total = 0.0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calculadora_menu, menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_call:
                String call = "tel:" + result.getText().toString();
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse(call));
                startActivity(intent2);
                //finish();
                //break;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        //return super.onOptionsItemSelected(item);
    }


    private void igual() {
        //operacio = "";
        if (result.getText() != "") {
            if (val1 == null) {
                total = Double.parseDouble((String) result.getText());
                result.setText("");
                result.setHint(total.toString());
                val1 = total;
                ans = total;
            } else {
                val2 = Double.parseDouble((String) result.getText());
                if (operacio.equals("+")) {
                    total = val1 + val2;
                }
                else if (operacio.equals("-")) {
                    total = val1 - val2;
                }
                else if (operacio.equals("x")) {
                    total = val1 * val2;
                }
                else if (operacio.equals("/")) {
                    if (val2 == 0) {
                        Toast.makeText(getApplicationContext(), "Ni lo sueñes", Toast.LENGTH_LONG).show();
                        //reset();
                        return;
                    } else total = val1 / val2;
                }
                //result.setText(total.toString());
                result.setText("");
                result.setHint(total.toString());
                //igual = true;
                //if (!first) first = true;
                //else first = false;
                val1 = total;
                ans = total;
                operacio = "";
                //first = true;
            }
        }
    }

    private void reset() {
        val1 = val2 = total = ans = null;
        operacio = "";
        result.setText("");
        result.setHint("0");
        first = true;
    }

    @Override
    public void onClick(View v) {
        s = (String) result.getText();
        switch (v.getId()) {
            case R.id.bt_0:
                s += "0";
                result.setText(s);
                break;
            case R.id.bt_1:
                s += "1";
                result.setText(s);
                break;
            case R.id.bt_2:
                s += "2";
                result.setText(s);
                break;
            case R.id.bt_3:
                s += "3";
                result.setText(s);
                break;
            case R.id.bt_4:
                s += "4";
                result.setText(s);
                break;
            case R.id.bt_5:
                s += "5";
                result.setText(s);
                break;
            case R.id.bt_6:
                s += "6";
                result.setText(s);
                break;
            case R.id.bt_7:
                s += "7";
                result.setText(s);
                break;
            case R.id.bt_8:
                s += "8";
                result.setText(s);
                break;
            case R.id.bt_9:
                s += "9";
                result.setText(s);
                break;
            case R.id.bt_suma:
                //operacio = "+";
                if (first) {
                    operacio = "+";
                    if (result.getText() != "")
                        val1 = Double.parseDouble((String) result.getText());
                    result.setText("");
                    result.setHint(val1.toString());
                    first = false;
                } else {
                    igual();
                    operacio = "+";
                }
                break;
            case R.id.bt_resta:
                //operacio = "-";
                if (first) {
                    operacio = "-";
                    if (result.getText() != "")
                        val1 = Double.parseDouble((String) result.getText());
                    result.setText("");
                    result.setHint(val1.toString());
                    first = false;
                } else {
                    igual();
                    operacio = "-";
                }
                break;
            case R.id.bt_mult:
                //operacio = "x";
                if (first) {
                    operacio = "x";
                    if (result.getText() != "")
                        val1 = Double.parseDouble((String) result.getText());
                    result.setText("");
                    result.setHint(val1.toString());
                    first = false;
                } else {
                    igual();
                    operacio = "x";
                }
                break;
            case R.id.bt_div:
                //operacio = "/";
                if (first) {
                    operacio = "/";
                    if (result.getText() != "")
                        val1 = Double.parseDouble((String) result.getText());
                    result.setText("");
                    result.setHint(val1.toString());
                    first = false;
                } else {
                    igual();
                    operacio = "/";
                }
                break;
            case R.id.bt_punt:
                if (((String) result.getText()).isEmpty()) result.setText("0");
                s += ".";
                result.setText(s);
                break;
            case R.id.bt_igual:
                igual();
                break;
            case R.id.bt_c:
                val2 = 0.0;
                result.setText("");
                result.setHint("0");
                break;
            case R.id.bt_del:
                if (s.length() > 0) {
                    s = s.substring(0, s.length() - 1);
                    result.setText(s);
                }
                break;
            case R.id.bt_ac:
                reset();
                break;
            case R.id.bt_ans:
                if (first) {
                    val1 = ans;
                    result.setText("");
                    result.setHint(val1.toString());
                    first = false;
                } else {
                    result.setText(ans.toString());
                    igual();
                    //operacio = "-";
                }
                break;
            default:
                break;
        }
    }

    /* Codiiiiiiii
    *********************************************************************
     */
/*
    private void setUpViews() {
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navview);

        //Initializing DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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
    }*/

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        s = result.getText().toString();
        outState.putString("result", s);
        outState.putBoolean("first", first);
        if(val1 != null)
            outState.putDouble("val1", val1);
        if(val2 != null)
            outState.putDouble("val2", val2);
        if(total != null)
            outState.putDouble("total", total);
        if(ans != null)
            outState.putDouble("ans", ans);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            result.setText(savedInstanceState.getString("result"));
            first = savedInstanceState.getBoolean("first");
            val1 = savedInstanceState.getDouble("val1");
            val2 = savedInstanceState.getDouble("val2");
            total = savedInstanceState.getDouble("total");
            ans = savedInstanceState.getDouble("ans");
        }
    }
}
