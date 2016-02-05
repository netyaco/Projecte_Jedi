package com.example.netyaco_.projecte_jedi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Perfil_usuari extends AppCompatActivity implements View.OnClickListener {

    TextView tv_user, tv_punt, tv_direccio;
    Button bt_canvi, bt_logout;
    ImageView iv_foto;
    public SharedPreferences pref;

    List<Address> l;
    LocationManager lm;
    LocationListener lis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuari);

        tv_user = (TextView) findViewById(R.id.tv_user_perfil);
        tv_punt = (TextView) findViewById(R.id.tv_puntuacio_perfil);
        tv_direccio = (TextView) findViewById(R.id.tv_direccio_perfil);
        bt_canvi = (Button) findViewById(R.id.bt_canvi);
        bt_logout = (Button) findViewById(R.id.bt_logout);
        iv_foto = (ImageView) findViewById(R.id.iv_foto);

        bt_logout.setOnClickListener(this);
        bt_canvi.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();

        String user = bundle.getString("user");
        Integer punt = bundle.getInt("puntuacio");
        //ArrayList<Parcelable> uris = bundle.getParcelableArrayList("uri");
        //Uri uri = Uri.parse(bundle.getString("uri"));
        //String direccio = bundle.getString("address");


        tv_user.setText(user.toString());
        tv_punt.setText(punt.toString());
        /*try {
            iv_foto.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //tv_direccio.setText(direccio.toString());

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
                Toast.makeText(getApplicationContext(), "HOLAAAAAA", Toast.LENGTH_LONG).show();
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

    /*@Override
    protected void onPause() {
        // TODO Auto-generated method stub
        lm.removeGpsStatusListener((GpsStatus.Listener) lis);

        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }*/

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_canvi:
                //intent = new Intent(getApplicationContext(), Calculadora.class);
                //startActivity(intent);
                //finish();
                break;
            case R.id.bt_logout:
                //Toast.makeText(this, "Hola", Toast.LENGTH_LONG).show();
                SharedPreferences pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("user", null);
                editor.commit();
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }

    }
}
