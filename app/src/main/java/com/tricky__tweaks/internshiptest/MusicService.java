package com.tricky__tweaks.internshiptest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MusicService extends Service {

    MediaPlayer mp;
    Uri uri;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MUSIC SERVICE", intent.getExtras().get("MUSIC_URI")+"");

        String sUri = intent.getStringExtra("MUSIC_URI");
        uri = Uri.parse(sUri);
        mp = MediaPlayer.create(this, uri);
        mp.start();
        Log.e("MUSIC SERVICE", uri.getPath()+" before");

        Toast.makeText(this, "service started and playing Music", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        //can be destroyed but want keep playing
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
