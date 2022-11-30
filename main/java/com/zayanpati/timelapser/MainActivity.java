package com.zayanpati.timelapser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView start, pause, reset;
    Chronometer chronometer;
    boolean running;
    long pauseOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        start = findViewById(R.id.start);
        pause = findViewById(R.id.pause);
        reset = findViewById(R.id.reset);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!running){
                    chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    chronometer.start();
                    running = true;
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(running){
                    chronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                    running = false;
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                chronometer.stop();
                running = false;
            }
        });

    }
}