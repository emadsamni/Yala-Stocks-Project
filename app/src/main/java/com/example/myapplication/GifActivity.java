package com.example.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.droidsonroids.gif.GifImageView;


public class GifActivity extends AppCompatActivity {
    private static int TIME_OUT = 3300;
    GifImageView gifImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        gifImageView= (GifImageView)findViewById(R.id.gifImage);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(GifActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);



    }
}
