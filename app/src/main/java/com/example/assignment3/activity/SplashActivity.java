package com.example.assignment3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.assignment3.R;

public class SplashActivity extends AppCompatActivity {
    private static int splashTimeOut = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent splashIntent = new Intent(SplashActivity.this, StudentRecordActivity.class);
                startActivity(splashIntent);
                finish();
            }
        }, splashTimeOut);
    }
}
