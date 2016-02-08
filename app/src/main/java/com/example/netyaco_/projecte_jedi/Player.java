package com.example.netyaco_.projecte_jedi;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class Player extends AppCompatActivity implements View.OnClickListener{

    Button bt_play, bt_pause, bt_stop;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        bt_play = (Button) findViewById(R.id.bt_play);
        bt_pause = (Button) findViewById(R.id.bt_pause);
        bt_stop = (Button) findViewById(R.id.bt_stop);

        bt_stop.setOnClickListener(this);
        bt_pause.setOnClickListener(this);
        bt_play.setOnClickListener(this);

        mediaPlayer = MediaPlayer.create(this, R.raw.song);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_play:
                if(mediaPlayer == null){
                    mediaPlayer = MediaPlayer.create(this, R.raw.song);
                }
                mediaPlayer.start();
                break;
            case R.id.bt_pause:
                if(mediaPlayer != null) mediaPlayer.pause();
                break;
            case R.id.bt_stop:
                if (mediaPlayer == null) break;
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                break;
            default:
                break;
        }
    }
}
