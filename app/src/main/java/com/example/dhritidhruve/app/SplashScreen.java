package com.example.dhritidhruve.app;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMEOUT = 1000;//timeout 2.5s

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("MyFiem");
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent registerClass = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(registerClass);
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}
