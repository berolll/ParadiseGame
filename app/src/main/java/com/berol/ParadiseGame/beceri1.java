package com.berol.ParadiseGame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class beceri1 extends AppCompatActivity {
    TextView txtTime;
    TextView txtScore;
    int score;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[] imageArray;
    Button btn_baslat;
    Handler handler;
    Runnable runnable;
    private Dialog settingssDialog;
  public Button button_carpiii;
    private WindowManager.LayoutParams params;

   private ImageButton imgbutton_ayar;
   private  Button btn_changeresim;
   ImageView btnCarpi;


    int izinverme=0;
    int izinverildi=1;
    private Intent resimDegistirIntent;
    private Uri resimUri;
    private Bitmap resimBitmap;
    private ImageDecoder.Source resimDosyasi;
    private CircleImageView userProfileImage;
    private android.app.AlertDialog.Builder alertBuilder;
    private byte[] resimByte;
    private int resimIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beceri1);
        txtScore=findViewById(R.id.txtScore);
        txtTime=findViewById(R.id.txtTime);
        imageView=findViewById(R.id.imageView);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        btn_baslat=findViewById(R.id.btn_baslat);




    }



    public void baslat(View V){

        btn_baslat.setVisibility(View.INVISIBLE);

        imageArray =new ImageView[] {imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};

        resimSaklama();

        score=0;

        new CountDownTimer(15000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                txtTime.setText("Time: "+millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                txtTime.setText("time off");
                handler.removeCallbacks(runnable);
                for(ImageView i : imageArray){
                    i.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert=new AlertDialog.Builder(beceri1.this);
                alert.setMessage("yeniden başlat?");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i=getIntent();
                        finish();
                        startActivity(i);
                    }
                });
                alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(beceri1.this,"GAME OVER!!!",Toast.LENGTH_LONG).show();
                        Intent i =new Intent(beceri1.this,becerioyunlari.class);
                        startActivity(i);
                        finish();

                    }
                });
                alert.show();
            }
        }.start();
    }
    public void arttir(View view){
        score++;
        txtScore.setText("Score: "+score);
    }
    public void resimSaklama(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView i : imageArray){
                    i.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i =random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);

            }
        };
        handler.post(runnable);

    }

}






