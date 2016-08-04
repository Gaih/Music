package com.gaih.playmusic;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer player = new MediaPlayer();
        try {
            player.setDataSource("/mnt/sdcard/李志.mp3");
            player.prepare();
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
