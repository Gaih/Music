package com.example.gaih.day08_baidumusic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by gaih on 2016/7/14.
 */

public class MusicService extends Service {
    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        player = new MediaPlayer();

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public void seekToPosition(int position){
        player.seekTo(position);
    }


    public void playMusic() {
        try {
            player.reset();
            player.setDataSource("/mnt/sdcard/李志.mp3");
            player.prepare();
            player.start();
            updateSeekBar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateSeekBar() {
        final int duration = player.getDuration();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int currentPosition = player.getCurrentPosition();
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt("duration",duration);
                bundle.putInt("currentPosition",currentPosition);
                msg.setData(bundle);
                MainActivity.handler.sendMessage(msg);
            }
        };
        timer.schedule(task,1000,1000);

    }

    public void pauseMusic() {
        player.pause();
    }

    public void replayMusic() {
        player.start();
    }

    private class MyBinder extends Binder implements Iservice {
        @Override
        public void callpauseMusic() {
            pauseMusic();
        }

        @Override
        public void callplayMusic() {
            playMusic();
        }

        @Override
        public void callreplayMusic() {
            replayMusic();
        }

        @Override
        public void callSeekToPosition(int position) {
            seekToPosition(position);
        }
    }
}
