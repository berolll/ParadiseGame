package com.berol.ParadiseGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class becerioyunlari extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_becerioyunlari);
    }
    public void yakala(View v){
        Intent i=new Intent(becerioyunlari.this,beceri1.class);
        startActivity(i);
    }
    public void eagle(View v){
        Intent i=new Intent(becerioyunlari.this,flappyEagle.class);
        startActivity(i);
    }
}