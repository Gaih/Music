package com.example.gaih.day08_baidumusic;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private Iservice iservice;
    private Myconn myconn;
    private static SeekBar sbar;

    public static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int duration = bundle.getInt("duration");
            int currentPosition = bundle.getInt("currentPosition");
            sbar.setMax(duration);
            sbar.setProgress(currentPosition);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbar = (SeekBar) findViewById(R.id.seekBar1);
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);

        myconn = new Myconn();
        bindService(intent,myconn,BIND_AUTO_CREATE);

        sbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                iservice.callSeekToPosition(seekBar.getProgress());
            }
        });

    }
    public void click1(View view){
        iservice.callplayMusic();
    }
    public void click2(View view){
        iservice.callpauseMusic();
    }
    public void click3(View view){
        iservice.callreplayMusic();
    }

    @Override
    protected void onDestroy() {
        unbindService(myconn);
        super.onDestroy();
    }

    private class Myconn implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iservice = (Iservice)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

}
