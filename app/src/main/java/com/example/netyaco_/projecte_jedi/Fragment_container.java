package com.example.netyaco_.projecte_jedi;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Fragment_container extends AppCompatActivity implements OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        //Creamos el primer fragment, y no le pasamos argumentos!
        setTitle("Fragment 1");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        //Reemplazamos el Frame Layout de la Activity por el nuevo fragment.
        //El Frame Layout es el contenedor
        //fragmentTransaction.replace(R.id.frameLayout, new Fragment_1());
        //fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(String text, Integer from) {

    }
}
