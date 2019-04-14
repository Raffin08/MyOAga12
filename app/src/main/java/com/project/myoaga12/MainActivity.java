package com.project.myoaga12;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.parse.ParseInstallation;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("OAga");
        setContentView(R.layout.activity_main);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent=new Intent(MainActivity.this,SignupActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
