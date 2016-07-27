package com.example.gaih.day08_baidumusic;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Iservice iservice;
    private Myconn myconn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);

        myconn = new Myconn();
        bindService(intent,myconn,BIND_AUTO_CREATE);

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
