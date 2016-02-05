package com.example.netyaco_.projecte_jedi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Memory3 extends AppCompatActivity {

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory3);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MemoryGameFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memory_menu, menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset_menu:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Segur que vols reiniciar el joc?");
                alertDialogBuilder.setPositiveButton("Dale", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new MemoryGameFragment())
                                .commit();
                    }
                });

                alertDialogBuilder.setNegativeButton("Ehm... Nop", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                //break;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        //return super.onOptionsItemSelected(item);
    }

    // Fragment del Memory
    public class MemoryGameFragment extends Fragment {

        //private Toolbar toolbar;



        private TextView intents;
        private static final int NUM_BUTTONS = 16;
        private boolean pause;
        private Handler handler = new Handler();
        private Button[] buttons = new Button[NUM_BUTTONS];
        private Integer punts;

        private int[] images = new int[]{
                R.drawable.card_c3po,
                R.drawable.card_chewie,
                R.drawable.card_han,
                R.drawable.card_r2d2,
                R.drawable.mamory1,
                R.drawable.memory2,
                R.drawable.memory3,
                R.drawable.memory4
        };

        private int[] assignarIndexImatges = new int[NUM_BUTTONS];

        private boolean[] imatgesRevelades = new boolean[NUM_BUTTONS];
        private int darrerIndexClicat;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.memory3_fragment, container, false);

            // Guardar les referencies als botons
            for(int i = 0; i < NUM_BUTTONS; i++){
                buttons[i] = (Button) rootView.findViewById(obtenirIdBoto("button" + i));
            }

            // Activem el click a cada botó
            for(int i = 0; i < buttons.length; i++){
                final int index = i;
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botoClicat(index, buttons[index]);
                    }
                });
            }

            intents = (TextView) rootView.findViewById(R.id.tv_intents);

            //toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
            //setSupportActionBar(toolbar);

            pause = false;
            darrerIndexClicat = -1;
            resetButtons();

            return rootView;
        }

        private int obtenirIdBoto(final String buttonIdName){
            final int buttonId = getResources().getIdentifier(buttonIdName,
                    "id", getActivity().getPackageName());
            return buttonId;
        }

        private void resetButtons(){
            for(int i = 0; i < imatgesRevelades.length; i++){
                imatgesRevelades[i] = false;
            }
            for(Button b : buttons){
                b.setBackgroundResource(R.drawable.card_back);
            }

            mesclarImatgesBotons();
        }

        private void mesclarImatgesBotons(){

            // Mesclar imatges
            Random random = new Random();
            int[] count = new int[images.length];
            for(int i = 0; i < buttons.length; i++){
                int possibleImatge = random.nextInt(images.length);
                while(count[possibleImatge] >= 2){
                    possibleImatge = random.nextInt(images.length);
                }
                count[possibleImatge]++;
                assignarIndexImatges[i] = possibleImatge;
            }
        }

        private void ocultarImatges(){
            for(int i = 0; i < buttons.length; i++){
                if(imatgesRevelades[i]){
                    buttons[i].setBackgroundResource(images[assignarIndexImatges[i]]);
                } else {
                    buttons[i].setBackgroundResource(R.drawable.card_back);
                }
            }
        }

        private void botoClicat(int indexClicked, Button b){
            if(pause) return;
            if(imatgesRevelades[indexClicked]) return;

            // Assignem la imatge al boto corresponent
            b.setBackgroundResource(images[assignarIndexImatges[indexClicked]]);

            if(darrerIndexClicat == -1){
                darrerIndexClicat = indexClicked; //Primera imatge clicada
            } else if(darrerIndexClicat != indexClicked){
                if(trobat(darrerIndexClicat, indexClicked)){
                    imatgesRevelades[darrerIndexClicat] = true;
                    imatgesRevelades[indexClicked] = true;
                    darrerIndexClicat = -1;

                    punts = Integer.valueOf((String)intents.getText());
                    punts++;
                    intents.setText(punts.toString());
                    comprovarFinal();
                } else {
                    darrerIndexClicat = -1;

                    punts = Integer.valueOf((String)intents.getText());
                    punts++;
                    intents.setText(punts.toString());

                    // Esperem... Hauria de ser amb Threads, però de moment no hi son
                    pause = true;

                    // Tornem a cridar a ocultarImatges
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ocultarImatges();
                            pause = false;
                        }
                    }, 2000);
                }
            }
        }
        
        private boolean trobat(int firstButtonIndex, int secondButtonIndex){
            return assignarIndexImatges[firstButtonIndex] == assignarIndexImatges[secondButtonIndex];
        }
        
        private void comprovarFinal(){
            // Si encara queda alguna imatges sense mostrar, es continua amb el joc
            for(boolean destapades : imatgesRevelades){
                if(!destapades) return;
            }
            
            // Totes les imatges revelades, guardem la puntuació del jugador
            SharedPreferences pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
            String user_res = pref.getString("user", null);
            DbHelper dbHelper = new DbHelper(getApplicationContext());
            if (user_res != null) {
                Cursor c = dbHelper.getUser(user_res);
                if (c.moveToFirst()) {
                    //punts = c.getInt(c.getColumnIndex(dbHelper.CN_POINTS));
                    punts = Integer.valueOf((String) intents.getText());
                    dbHelper.update_points(user_res, punts);
                }
            }

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setMessage("Has necessitat "+ punts + " intents");

            alertDialogBuilder.setPositiveButton("Tornar a jugar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    resetButtons();
                }
            });

            alertDialogBuilder.setNegativeButton("Rànking", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), Ranking.class);
                    startActivity(intent);
                    finish();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            intents.setText("0");

            // Fem reset al joc
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    resetButtons();
                }
            }, 1400);
        }
    }
}
