package com.berol.ParadiseGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class SplashScreenActivity extends AppCompatActivity {

    //Sorular İçin Listeler
    private String[] sorularList = {"Programlama dili ile yazılmış bir programı makine dili ile yazılmış amaç veya hedef programa çeviren yazılımlara ne ad verilir?", "Java dilinde değişkenleri sabit olarak tanımlamak için değişken tanımının önüne hangi deyim getirilir?",    "Aşağıdaki program parçasının ekran çıktısı ne olur?\n" +
            "float t, x;\n" +
            "t = 25 + 15;\n" +
            "x = t / 4;\n" +
            "System.out.println(x); \n","Bir alt sınıf içerisinden üst sınıfta tanımlı olan yapılandırıcı metodun çağrılması için hangi deyim kullanılır?","Javada kalıtım (miras alma) ilişkisini kurarken hangi deyim kullanılır?","Bir sınıftan new komutu ile bellekte bir kopya oluşturulduğunda bu kopyaya ne ad verilir?","Fonksiyon veya metot geriye bir değer döndürmeyecekse hangi tipte tanımlanmalıdır?"," Android uygulamalarinin paketlenmis haline ne denir?","Gelistirdigimiz Mobil Uygulamalari test ettiğimiz ortama ne ad verilir?","Birden fazla platformda(android,ios,windows) çalışacak uygulamaların ayni anda geliştirilmesine ne denir?","Mobil işletim sistemlerinden hangisine Java dili ile uygulama geliştirme yazılabilir?"};
    private String[] sorularKodList = {"1", "2","3","4","5","6","7","8","9","10","11"};

    //Kelimeler İçin Listeler
    private String[] kelimelerList = {"Derleyici","Final","10","Super","Extends","Nesne","Void","APK","Emulator","Cross","Android"};
    private String[] kelimelerKodList = {"1","2","3","4","5","6","7","8","9","10","11"};

    private ProgressBar mProgress;
    private TextView mTextView;
    private SQLiteDatabase database;
    private Cursor cursor;
    private float maksimumProgres = 100f, artacakProgress, progresMiktari = 0;
    static public HashMap<String, String> sorularHashmap;
    private String sqlSorgusu;
    private SQLiteStatement statement;
    private MediaPlayer gameTheme;

    private SharedPreferences preferences;
    private boolean muzikDurumu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mProgress = (ProgressBar)findViewById(R.id.splash_screen_activity_progressBar);
        mTextView = (TextView)findViewById(R.id.splash_screen_activity_textViewState);
        sorularHashmap = new HashMap<>();
        gameTheme = MediaPlayer.create(this, R.raw.gametheme);
        gameTheme.setLooping(true);

        preferences = this.getSharedPreferences("com.nexis.kelimebilmece", MODE_PRIVATE);
        muzikDurumu = preferences.getBoolean("muzikDurumu", true);

        try {
            database = this.openOrCreateDatabase("KelimeBilmece", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS Ayarlar (k_adi VARCHAR, k_heart VARCHAR, k_image BLOB)");
            cursor = database.rawQuery("SELECT * FROM Ayarlar", null);

            if (cursor.getCount() < 1)
                database.execSQL("INSERT INTO Ayarlar (k_adi, k_heart) VALUES ('Oyuncu', '0')");


            database.execSQL("CREATE TABLE IF NOT EXISTS Sorular (id INTEGER PRIMARY KEY, sKod VARCHAR UNIQUE, soru VARCHAR)");
            database.execSQL("DELETE FROM Sorular");
            sqlSorulariEkle();

            database.execSQL("CREATE TABLE IF NOT EXISTS Kelimeler (kKod VARCHAR, kelime VARCHAR, FOREIGN KEY (kKod) REFERENCES Sorular (sKod))");
            database.execSQL("DELETE FROM Kelimeler");
            sqlKelimeleriEkle();

            cursor = database.rawQuery("SELECT * FROM Sorular", null);
            artacakProgress = maksimumProgres /  cursor.getCount();

            int sKodIndex = cursor.getColumnIndex("sKod");
            int soruIndex = cursor.getColumnIndex("soru");

            mTextView.setText("Sorular Yükleniyor...");

            while (cursor.moveToNext()){
                sorularHashmap.put(cursor.getString(sKodIndex), cursor.getString(soruIndex));
                progresMiktari += artacakProgress;
                mProgress.setProgress((int)progresMiktari);
            }

            mTextView.setText("Sorular Alındı, Uygulama Başlatılıyor...");
            cursor.close();

            new CountDownTimer(1100, 1000){
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                }
            }.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (muzikDurumu)
            gameTheme.start();
    }

    private void sqlSorulariEkle(){
        try {
            for (int s = 0; s < sorularList.length; s++){
                sqlSorgusu = "INSERT INTO Sorular (sKod, soru) VALUES (?, ?)";
                statement = database.compileStatement(sqlSorgusu);
                statement.bindString(1, sorularKodList[s]);
                statement.bindString(2, sorularList[s]);;
                statement.execute();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sqlKelimeleriEkle(){
        try {
            for (int k = 0; k < kelimelerList.length; k++){
                sqlSorgusu = "INSERT INTO Kelimeler (kKod, kelime) VALUES (?, ?)";
                statement = database.compileStatement(sqlSorgusu);
                statement.bindString(1, kelimelerKodList[k]);
                statement.bindString(2, kelimelerList[k]);
                statement.execute();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}