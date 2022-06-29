package com.berol.ParadiseGame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class flappyEagle extends AppCompatActivity {
    public static TextView txt_score,txt_best_score,txt_score_over;
    public static Button btn_start;
    public static RelativeLayout rl_game_over;
    private GameView gv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dm=new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WİDTH=dm.widthPixels;
        Constants.SCREEN_HEİGHT=dm.heightPixels;

        setContentView(R.layout.activity_flappy_eagle);

        txt_score=findViewById(R.id.txt_score);
        txt_best_score=findViewById(R.id.txt_best_score);
        txt_score_over=findViewById(R.id.txt_score_over);
        btn_start=findViewById(R.id.btn_start);
        rl_game_over=findViewById(R.id.rl_game_over);
        gv=findViewById(R.id.gv);


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gv.setStart(true);
                txt_score.setVisibility(View.VISIBLE);
                btn_start.setVisibility(View.INVISIBLE);

            }
        });


        rl_game_over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_start.setVisibility(View.INVISIBLE);
                rl_game_over.setVisibility(View.INVISIBLE);
                gv.setStart(true);
                gv.reset();
                txt_score.setVisibility(View.VISIBLE);

            }
        });


    }


}