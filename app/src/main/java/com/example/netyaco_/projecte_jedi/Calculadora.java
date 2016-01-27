package com.example.netyaco_.projecte_jedi;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calculadora extends AppCompatActivity implements View.OnClickListener{

    TextView result;
    String s = new String();
    String operacio;
    Double val1, val2, total;
    Button bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7,bt8, bt9, bt10, bt11, bt12, bt13, bt14, bt15;
    Button bt16, bt17, bt18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

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

        val1 = val2 = total = 0.0;
    }

    @Override
    public void onClick(View v) {
        s = (String)result.getText();
        switch (v.getId()) {
            case R.id.bt_0:
                s+="0";
                result.setText(s);
                break;
            case R.id.bt_1:
                s+="1";
                result.setText(s);
                break;
            case R.id.bt_2:
                s+="2";
                result.setText(s);
                break;
            case R.id.bt_3:
                s+="3";
                result.setText(s);
                break;
            case R.id.bt_4:
                s+="4";
                result.setText(s);
                break;
            case R.id.bt_5:
                s+="5";
                result.setText(s);
                break;
            case R.id.bt_6:
                s+="6";
                result.setText(s);
                break;
            case R.id.bt_7:
                s+="7";
                result.setText(s);
                break;
            case R.id.bt_8:
                s+="8";
                result.setText(s);
                break;
            case R.id.bt_9:
                s+="9";
                result.setText(s);
                break;
            case R.id.bt_suma:
                operacio = "+";
                if (result.getText() != "") val1 = Double.parseDouble((String) result.getText());
                result.setText("");
                result.setHint(val1.toString());
                break;
            case R.id.bt_resta:
                operacio = "-";
                if (result.getText() != "") val1 = Double.parseDouble((String) result.getText());
                result.setText("");
                result.setHint(val1.toString());
                break;
            case R.id.bt_mult:
                operacio = "x";
                if (result.getText() != "") val1 = Double.parseDouble((String) result.getText());
                result.setText("");
                result.setHint(val1.toString());
                break;
            case R.id.bt_div:
                operacio = "/";
                if (result.getText() != "") val1 = Double.parseDouble((String) result.getText());
                result.setText("");
                result.setHint(val1.toString());
                break;
            case R.id.bt_punt:
                s+=".";
                result.setText(s);
                break;
            case R.id.bt_igual:
                if (result.getText() != "") {
                    val2 = Double.parseDouble((String) result.getText());
                    if (operacio.equals("+")) {
                        total = val1 + val2;
                    } else if (operacio.equals("-")) {
                        total = val1 - val2;
                    } else if (operacio.equals("x")) {
                        total = val1 * val2;
                    } else if (operacio.equals("/")) {
                        total = val1 / val2;
                    }
                    result.setText(total.toString());
                    break;
                }
            case R.id.bt_c:
                val2 = 0.0;
                result.setText("");
                result.setHint("0");
                break;
            case R.id.bt_del:
                if (s.length() > 0) {
                    s = s.substring(0,s.length()-1);
                    result.setText(s);
                }
                break;
            case R.id.bt_ac:
                val1 = val2 = total = 0.0;
                operacio = "";
                result.setText("");
                result.setHint("0");
                break;
            default:
                break;
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        s = (String)result.getText();
        outState.putString("result",s);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        result.setText(savedInstanceState.getString("result"));
    }
}
