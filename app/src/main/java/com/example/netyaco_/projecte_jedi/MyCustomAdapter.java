package com.example.netyaco_.projecte_jedi;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

/**
 * Created by netyaco_ on 01/02/2016.
 */
public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.AdapterViewHolder> {

    ArrayList<User> users;


    MyCustomAdapter() {
        users = new ArrayList<>();
    }

    @Override
    public MyCustomAdapter.AdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //Instancia un layout XML en la correspondiente vista.
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        //Inflamos en la vista el layout para cada elemento
        View view = inflater.inflate(R.layout.row_layout, viewGroup, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCustomAdapter.AdapterViewHolder adapterViewHolder, int position) {
        adapterViewHolder.user.setText(users.get(position).getName());
        adapterViewHolder.points.setText("Puntuació: "+users.get(position).getPoints().toString());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        /*
        *  Mantener una referencia a los elementos de nuestro ListView mientras el usuario realiza
        *  scrolling en nuestra aplicación. Así que cada vez que obtenemos la vista de un item,
        *  evitamos las frecuentes llamadas a findViewById, la cuál se realizaría únicamente la primera vez y el resto
        *  llamaríamos a la referencia en el ViewHolder, ahorrándonos procesamiento.
        */

        public TextView user;
        //public TextView pass;
        public TextView points;
        public View v;
        public AdapterViewHolder(View itemView) {
            super(itemView);
            this.v = itemView;
            this.user = (TextView) itemView.findViewById(R.id.tv_user_row);
            this.points = (TextView) itemView.findViewById(R.id.tv_points_row);
        }
    }

    public void setDataSet (ArrayList<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }
}
