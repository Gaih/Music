package com.example.gaih.day08_baidumusic;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by gaih on 2016/7/14.
 */

public class MusicService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    public void playMusic(){
        Log.d("music:","播放");
        //TODO 多媒体功能完善
    }
    public void pauseMusic(){
        Log.d("music:","暂停");
    }
    public void replayMusic(){
        Log.d("music:","继续播放");
    }

    private class MyBinder extends Binder implements Iservice{
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
    }
}
