package com.tricky__tweaks.internshiptest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

public class MusicService extends Service {

    MediaPlayer mp;
    Uri uri;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String sUri = intent.getStringExtra("MUSIC_URI");

        uri = Uri.parse(sUri);

        mp = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);

        mp.setLooping(true);

        mp.start();

        Toast.makeText(this, "service started and playing Music", Toast.LENGTH_SHORT).show();
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service Successfully Created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
