package com.tricky__tweaks.internshiptest;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class MusicService extends Service {

    MediaPlayer mp;
    Uri uri;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MUSIC SERVICE", intent.getExtras().get("MUSIC_URI")+"");

        Log.i("MUSIC SERVICE", "In onStartCommand");

        String sUri = intent.getStringExtra("MUSIC_URI");
        uri = Uri.parse(sUri);
        mp = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        mp.setLooping(true);
        mp.start();
        Log.e("MUSIC SERVICE", uri.getPath()+" before");

        Toast.makeText(this, "service started and playing Music", Toast.LENGTH_SHORT).show();
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service Successfully Created", Toast.LENGTH_SHORT).show();

    }

//    @Override
//    public void onStart(Intent intent, int startId) {
//        super.onStart(intent, startId);
//        Log.e("MUSIC SERVICE", intent.getExtras().get("MUSIC_URI")+"");
//        uri = Uri.parse((String) intent.getExtras().get("MUSIC_URI"));
//        Toast.makeText(this, "service started and playing Music", Toast.LENGTH_SHORT).show();
//        mp.start();
//    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.i("MUSIC_SERVICE", "In onTaskRemoved");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MUSIC _SERVICE", "In onDestroyed");
        //can be destroyed but want keep playing
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
