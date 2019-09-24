package com.example.androidui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

//This is first screen
public class SplashScreenActivity extends AppCompatActivity {
    private static int splashTimeOut=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent splashIntent=new Intent(SplashScreenActivity.this,LoginActivity.class);
                startActivity(splashIntent);
                finish();
            }
        },splashTimeOut);
    }
}
