package com.tricky__tweaks.internshiptest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MusicService extends Service {

    //final implemented only for local

    public static final String MUSIC_CHANNEL_ID = "foreground_music_channel";

    MediaPlayer mp;
    Uri uri;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String sUri = intent.getStringExtra("MUSIC_URI");

        if (sUri != null) {
            createNotificationChannel();

            Intent notificationIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

            Notification notification = new NotificationCompat.Builder(this, MUSIC_CHANNEL_ID)
                    .setContentTitle("Music Running in backgound")
                    .setContentText("file name")
                    .setContentIntent(pendingIntent).build();

            startForeground(1, notification);

            uri = Uri.parse(sUri);

            mp = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);

            mp.setLooping(true);

            mp.start();
        }


        Toast.makeText(this, "service started and playing Music", Toast.LENGTH_SHORT).show();
        return START_NOT_STICKY;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel serviceChannel = new NotificationChannel(
                    MUSIC_CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);

        }
    }


    @Override
    public void onCreate() {
        Toast.makeText(this, "Service Successfully Created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        if (mp !=null)
            mp.stop();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
