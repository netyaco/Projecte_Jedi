package com.example.netyaco_.projecte_jedi;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class Registre extends AppCompatActivity implements View.OnClickListener{

    Button bt_foto, bt_registre;
    String user, image_string;
    ImageView iv_foto;
    Uri image;
    Bundle bundle;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        iv_foto = (ImageView) findViewById(R.id.iv_foto);
        bt_foto = (Button) findViewById(R.id.bt_select_foto);
        bt_registre = (Button) findViewById(R.id.bt_completar_registre);

        bt_foto.setOnClickListener(this);
        bt_registre.setOnClickListener(this);

        dbHelper = new DbHelper(this);
        bundle = getIntent().getExtras();

        user = bundle.getString("user");
        //Integer punt = bundle.getInt("puntuacio");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Perfil_usuari.class);
        switch (v.getId()) {
            case R.id.bt_select_foto:
                Intent pickAnImage = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickAnImage.setType("image/*");

                //image_string = image.toString();
                startActivityForResult(pickAnImage, 2);
                break;
            case R.id.bt_completar_registre:
                if (image_string == null) {
                    Toast.makeText(this, "Has de seleccionar una imatge", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbHelper.update_image(user, image.toString());
                //ArrayList<Uri> uri = new ArrayList<>();
                //uri.add(image);
                //bundle.putParcelableArrayList("uri", uri);
                bundle.putParcelable("uri", image);
                //bundle.putString("uri",Uri.image);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                //Toast.makeText(this, image.toString(), Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Como en este caso los 3 intents hacen lo mismo, si el estado es correcto recogemos el resultado
        //Aún así comprobamos los request code. Hay que tener total control de lo que hace nuestra app.
        if(resultCode == RESULT_OK){
            if(requestCode >= 1 && requestCode <= 3){
                data.getData();
                Uri selectedImage = data.getData();
                image = selectedImage;
                Log.v("PICK", "Selected image uri" + selectedImage);
                try {
                    iv_foto.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }else{
            Log.v("Result","Something happened");
        }
    }
}
