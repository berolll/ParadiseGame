package com.berol.ParadiseGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class ilkekran extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilkekran);
        Thread logoAnimation = new Thread() {
            public void run() {
                TextView logo=findViewById(R.id.txt_logo);
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.ilk_ekran);
                logo.startAnimation(animation);

            }
        };
        logoAnimation.start();
        Thread redirect=new Thread(){
            public void run(){
                try {
                    sleep(4500);
                    Intent i =new Intent(getApplicationContext(),giris.class);
                    startActivity(i);
                    finish();
                    super.run();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        redirect.start();

    }
}