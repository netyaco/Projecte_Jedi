package com.example.netyaco_.projecte_jedi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Fragment_container extends AppCompatActivity implements OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
    }

    @Override
    public void onFragmentInteraction(String text, Integer from) {

    }
}
