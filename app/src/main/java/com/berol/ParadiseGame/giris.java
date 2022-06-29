package com.berol.ParadiseGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class giris extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
    }
    public  void zeka(View v){
        Intent i=new Intent(giris.this,SplashScreenActivity.class);
        startActivity(i);
    }
    public  void beceri(View v){
        Intent i=new Intent(giris.this,becerioyunlari.class);
        startActivity(i);
    }
}