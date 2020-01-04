package com.example.shuvo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    public static String TAG = SplashScreen.class.getName();
    private static long SLEEP_TIME = 6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        IntentLauncher launcher = new IntentLauncher();
        launcher.start();

    }

    private class IntentLauncher extends Thread{

        @Override
        public void run(){
            try{
                Thread.sleep(SLEEP_TIME*1000);

            }catch (Exception e){
                Log.e(TAG, e.getMessage());
            }

            Intent intent = new Intent(SplashScreen.this,MainActivity.class);
            SplashScreen.this.startActivity(intent);
            SplashScreen.this.finish();
        }
    }
}
