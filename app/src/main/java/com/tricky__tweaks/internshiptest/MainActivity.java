package com.tricky__tweaks.internshiptest;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private static final int PICK_MUSIC_FILE = 1003;
    private TextView textViewFileName;
    private MaterialButton playBtn;
    private MaterialButton pauseBtn;
    private Uri fileUri;

    private void initViews() {
        textViewFileName = findViewById(R.id.activity_main_tv_file_name);
        playBtn = findViewById(R.id.activity_main_mb_play);
        pauseBtn = findViewById(R.id.activity_main_mb_pause);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        playBtn.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "playing..", Toast.LENGTH_LONG).show();
            showFileChooser();
        });

        pauseBtn.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "pause", Toast.LENGTH_SHORT).show();
            stopService();
        });

    }

    public void startService() {
        Intent serviceIntent = new Intent(this, MusicService.class);
        serviceIntent.putExtra("FILE_URI", fileUri.toString());

        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService() {
        Intent serviceIntent = new Intent(this, MusicService.class);
        stopService(serviceIntent);
    }

    public void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent, "select a File to Upload"), PICK_MUSIC_FILE);
        } catch (android.content.ActivityNotFoundException xe) {
            Toast.makeText(this, "please install a file manager", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == PICK_MUSIC_FILE && resultCode == RESULT_OK && data != null) {
            fileUri = data.getData();
            if (fileUri != null) {

                startService();

                Log.e("$$$  MAIN_ACTIVITY $$$", fileUri.getPath());
                Log.e("$$$  MAIN_ACTIVITY $$$ -- file name", queryName(fileUri));

                textViewFileName.setText(queryName(fileUri));

                startService(new Intent(this, MusicService.class).putExtra("MUSIC_URI", fileUri.toString()));
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    //This is code i found out on stack over flow to get the name of the file form uri
    private String queryName(Uri uri) {
        Cursor returnCursor = getContentResolver().query(uri, null, null, null, null);
        assert returnCursor != null;
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String name = returnCursor.getString(nameIndex);
        returnCursor.close();
        return name;
    }
}
