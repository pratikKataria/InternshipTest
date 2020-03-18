package com.tricky__tweaks.internshiptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textViewFileName;
    private MaterialButton playBtn;
    private MaterialButton pauseBtn;

    private void initViews(){
        textViewFileName = findViewById(R.id.activity_main_tv_file_name);
        playBtn = findViewById(R.id.activity_main_mb_play);
        pauseBtn = findViewById(R.id.activity_main_mb_pause);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "playing..", Toast.LENGTH_LONG).show();
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "pause", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
