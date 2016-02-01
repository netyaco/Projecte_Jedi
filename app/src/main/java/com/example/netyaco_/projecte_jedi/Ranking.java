package com.example.netyaco_.projecte_jedi;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Ranking extends AppCompatActivity {

    DbHelper dbHelper = new DbHelper(getApplicationContext());
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayout;

    ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Cursor c = dbHelper.getAllUsers();
        if (c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex(dbHelper.CN_USER));
                String pass = c.getString(c.getColumnIndex(dbHelper.CN_PASS));
                User user = new User(name, pass, 0);
                users.add(user);
            } while (c.moveToNext());
        }

        //findViewById del layout activity_main
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        //LinearLayoutManager necesita el contexto de la Activity.
        //El LayoutManager se encarga de posicionar los items dentro del recyclerview
        //Y de definir la politica de reciclaje de los items no visibles.
        mLinearLayout = new LinearLayoutManager(this);

        //Asignamos el LinearLayoutManager al recycler:
        mRecyclerView.setLayoutManager(mLinearLayout);


        //El adapter se encarga de  adaptar un objeto definido en el c�digo a una vista en xml
        //seg�n la estructura definida.
        //Asignamos nuestro custom Adapter
        MyCustomAdapter adapter = new MyCustomAdapter();
        mRecyclerView.setAdapter(adapter);
        adapter.setDataSet(users);
    }
}
