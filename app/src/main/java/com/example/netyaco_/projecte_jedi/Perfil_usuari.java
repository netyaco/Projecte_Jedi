package com.example.netyaco_.projecte_jedi;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Perfil_usuari extends AppCompatActivity implements View.OnClickListener {

    TextView tv_user, tv_punt, tv_pass, tv_pass_titol, tv_notify;
    Button bt_canvi, bt_logout;
    ImageView iv_foto;
    EditText et_pass;
    Boolean canvi = false;
    Bundle bundle;
    DbHelper dbHelper;
    String uri_string;

    List<Address> l;
    LocationManager lm;
    LocationListener lis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuari);

        dbHelper = new DbHelper(this);

        tv_user = (TextView) findViewById(R.id.tv_user_perfil);
        tv_punt = (TextView) findViewById(R.id.tv_puntuacio_perfil);
        tv_pass = (TextView) findViewById(R.id.tv_pass_perfil);
        bt_canvi = (Button) findViewById(R.id.bt_canvi);
        bt_logout = (Button) findViewById(R.id.bt_logout);
        iv_foto = (ImageView) findViewById(R.id.iv_perfil_foto);
        et_pass = (EditText) findViewById(R.id.et_pass_perfil);
        tv_pass_titol = (TextView) findViewById(R.id.tv_pass_titol);
        tv_notify = (TextView) findViewById(R.id.tv_notificacio_perfil);

        bt_logout.setOnClickListener(this);
        bt_canvi.setOnClickListener(this);
        iv_foto.setOnClickListener(this);

        String user = "";
        Integer punt = 0;
        Uri uri = null;
        String notify = "";
        bundle = getIntent().getExtras();
        if (bundle != null) {
            user = bundle.getString(dbHelper.CN_USER);
            punt = bundle.getInt(dbHelper.CN_POINTS);
            if (bundle.getString(dbHelper.CN_IMAGE)!= null)
                uri = Uri.parse(bundle.getString(dbHelper.CN_IMAGE));
            notify = bundle.getString(dbHelper.CN_NOTIFY);
        }


        tv_user.setText(user.toString());
        tv_punt.setText(punt.toString());
        if (notify != null) tv_notify.setText(notify.toString());
        Cursor c = dbHelper.getUser(user);
        if (c.moveToFirst()) {
            uri_string = c.getString(c.getColumnIndex(dbHelper.CN_IMAGE));
            if (uri_string != null) uri = Uri.parse(uri_string);
        }
        try {
            if (uri_string != null) iv_foto.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri));
        } catch (IOException e) {
            e.printStackTrace();
        }

        l = null;
        lm = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);
        lis = new LocationListener() {

            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }

            @Override
            public void onLocationChanged(Location location) {
                // TODO Auto-generated method stub
                Log.v("LOG","onlocationchanged");
                Geocoder gc = new Geocoder(getApplicationContext());
                try {
                    l = gc.getFromLocation(location.getLatitude(),
                            location.getLongitude(), 5);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < l.size(); ++i) {
                    Log.v("LOG", l.get(i).getAddressLine(0).toString());
                    TextView t = (TextView) findViewById(R.id.tv_direccio_perfil);
                    if (i == 0) t.setText("");
                    t.setText(t.getText() + "\n" + l.get(i).getAddressLine(0).toString());
                }
                Log.v("LOG", ((Double) location.getLatitude()).toString());
            }
        };

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, lis);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, lis);

    }

    @Override
    public void onClick(View v) {
        final Intent intent;
        switch (v.getId()) {
            case R.id.bt_canvi:
                if (!canvi) {
                    bt_canvi.setText("Guardar pass");
                    canvi = true;
                    et_pass.setVisibility(View.VISIBLE);
                    tv_pass.setVisibility(View.GONE);
                    tv_pass_titol.setVisibility(View.VISIBLE);
                }
                else {
                    if (et_pass.getText().toString().equals("")) {
                        Toast.makeText(this, "La contrassenya no pot ser buida",
                                Toast.LENGTH_SHORT).show();
                        break;
                    }
                    bt_canvi.setText("Modificar pass");
                    canvi = false;
                    DbHelper dbHelper = new DbHelper(this);
                    Cursor c = dbHelper.getUser(tv_user.getText().toString());
                    if (c.moveToFirst()) {
                        dbHelper.update_pass(tv_user.getText().toString(),
                                et_pass.getText().toString());
                    }
                    et_pass.setVisibility(View.GONE);
                    tv_pass_titol.setVisibility(View.GONE);
                }
                break;
            case R.id.bt_logout:
                SharedPreferences pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("user", null);
                editor.commit();
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_perfil_foto:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Vols modificar la imatge de perfil?");
                alertDialogBuilder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        bundle.putString("user",tv_user.toString());
                        Intent intent = new Intent(getApplicationContext(), Registre.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Ben pensat", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            default:
                break;
        }

    }
}
